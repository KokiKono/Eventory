package local.koki.android.eventory.viewController

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pnikosis.materialishprogress.ProgressWheel
import io.realm.Realm
import io.realm.RealmResults
import local.koki.android.eventory.R
import local.koki.android.eventory.common.Colors
import local.koki.android.eventory.common.FragmentRouter
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.common.TutorialRegister
import local.koki.android.eventory.model.UserRegister
import local.koki.android.eventory.view.adapter.NoDataAdapter
import local.koki.android.eventory.view.adapter.RealmEventCardAdapter
import local.koki.android.eventory.view.listener.EventActionListener
import java.util.*

/**
 * Created by 浩生 on 2017/01/31.
 */
open class EventFragment : Fragment()
        , RealmEventCardAdapter.ViewHolder.OnClickKeepListener
        , RealmEventCardAdapter.ViewHolder.OnClickNoKeepListener
        , RealmEventCardAdapter.ViewHolder.OnClickTitleListener
        , EventManager.LoadEventInterface {
    protected var mEventStatus: EventManager.CheckStatus = EventManager.CheckStatus.None
    protected var mRecyclerView: RecyclerView? = null
    //protected var mLayoutManager: RecyclerView.LayoutManager? = null
    //protected var mAdapter: RealmEventCardAdapter? = null
    //protected var mData: RealmResults<EventRealm>? = null
    protected var mEventAction: EventActionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            mEventStatus = EventManager.CheckStatus.None
            return
        }
        mEventStatus = EventManager.CheckStatus.indexOf(arguments.getInt(FragmentRouter.ARGS_KEY))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_event_list, container, false)

        if (view is RecyclerView) {
            //create recycler view
            mRecyclerView = view.findViewById(R.id.list) as RecyclerView
            mRecyclerView?.let {
                it.setHasFixedSize(true)
                it.layoutManager=LinearLayoutManager(context)
            }
            var adapter = RealmEventCardAdapter(context, null)
            adapter.onClickKeep=this
            adapter.onClickNotKeep=this
            adapter.onClickTitle=this
            mRecyclerView?.let { it.adapter=adapter }
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is EventActionListener) {
            mEventAction = context
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        //if(!TutorialRegister.isTutorial(TutorialRegister.Keys.Version1.name)) {
            //チュートリアルの最中でなければ
            if (mEventStatus == EventManager.CheckStatus.NoCheck) {
                //サーバーからデータを取得する。
                var eventManager = EventManager()
                eventManager.loadEventInterface = this
                var updateAt=UserRegister.getUserUpdateInfoUpdateTime()
                eventManager.eventConnection(updateAt)
                //取得失敗時にも更新されるためタイミングを変更:endLoadで行う。
                //UserRegister.createOrUpdateUserEventInfoUpdateTime()
            }
        //}
        var data = EventManager.fetchEvent(mEventStatus)
        if (data.size==0){
            val adapter=NoDataAdapter(context)
            mRecyclerView?.let {
                it.adapter=adapter
                return
            }
        }
        var adapter:RealmEventCardAdapter=mRecyclerView?.let { it.adapter as RealmEventCardAdapter }!!
        adapter.updateData(data)
        adapter.notifyDataSetChanged()
    }

    override fun onClickKeep(eventRealm: EventRealm, position: Int) {
        mEventAction?.let { it.onActionKeep(eventRealm) }
    }

    override fun onClickNotKeep(eventRealm: EventRealm, position: Int) {
        mEventAction?.let { it.onActionNotKeep(eventRealm) }
    }

    override fun startLoad() {
    }

    override fun startConnection() {
    }

    override fun endConnection(data: ArrayList<EventRealm>) {
        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        for (event in data) {
            val now=realm.where(EventRealm::class.java).equalTo("id",event.id).findAll()
            if(now.isNotEmpty())break
            realm.copyToRealm(event)
        }
        realm.commitTransaction()
        realm.close()
    }

    override fun endLoad(success: Boolean) {
        if(success){
            UserRegister.createOrUpdateUserEventInfoUpdateTime()
        }
    }

    override fun onClickTitle(eventRealm: EventRealm) {
        val uri = Uri.parse(eventRealm.url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }


}
