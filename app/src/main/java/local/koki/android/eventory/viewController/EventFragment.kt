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
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected var mAdapter: RealmEventCardAdapter? = null
    protected var mData: RealmResults<EventRealm>? = null
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
            //TODO:Nullableな処理はこれに変える！！！
            //mRecyclerView?.let { it.setHasFixedSize(true) }
            mRecyclerView!!.setHasFixedSize(true)
            mLayoutManager = LinearLayoutManager(context)
            mRecyclerView!!.layoutManager = mLayoutManager
            mAdapter = RealmEventCardAdapter(context, null)
            mAdapter!!.onClickKeep = this
            mAdapter!!.onClickNotKeep = this
            mAdapter!!.onClickTitle = this
            mRecyclerView!!.adapter = mAdapter
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
        Log.e("TutorialTAG", TutorialRegister.isTutorial(TutorialRegister.Keys.Version1.toString()).toString())
        if(!TutorialRegister.isTutorial(TutorialRegister.Keys.Version1.toString())) {
            //チュートリアルの最中でなければ
            if (mEventStatus == EventManager.CheckStatus.NoCheck) {
                //サーバーからデータを取得する。
                var eventManager = EventManager()
                eventManager.loadEventInterface = this
                eventManager.eventConnection(null)
            }
        }
        mData = EventManager.fetchEvent(mEventStatus)
        mAdapter!!.updateData(mData)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun onClickKeep(eventRealm: EventRealm, position: Int) {
        if (mEventAction != null) mEventAction!!.onActionKeep(eventRealm)
    }

    override fun onClickNotKeep(eventRealm: EventRealm, position: Int) {
        if (mEventAction != null) mEventAction!!.onActionNotKeep(eventRealm)
    }

    override fun startLoad() {
    }

    override fun startConnection() {
    }

    override fun endConnection(data: ArrayList<EventRealm>) {
        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        for (event in data) {
            realm.copyToRealm(event)
        }
        realm.commitTransaction()
        realm.close()
    }

    override fun endLoad() {
    }

    override fun onClickTitle(eventRealm: EventRealm) {
        val uri = Uri.parse(eventRealm.url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }


}
