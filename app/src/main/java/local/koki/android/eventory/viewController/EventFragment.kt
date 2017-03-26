package local.koki.android.eventory.viewController

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.gordonwong.materialsheetfab.MaterialSheetFab
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener
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
import local.koki.android.eventory.view.parts.MaterialFab
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
    protected var mEventAction: EventActionListener? = null
    private var mMaterialSheetFab:MaterialSheetFab<MaterialFab>?=null
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

        //create recycler view
        mRecyclerView = view.findViewById(R.id.list) as RecyclerView
        mRecyclerView?.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(context)
        }
        //create adapter
        var adapter = RealmEventCardAdapter(context, null)
        adapter.onClickKeep = this
        adapter.onClickNotKeep = this
        adapter.onClickTitle = this
        mRecyclerView?.let { it.adapter = adapter }

        //create materialSheetFab
        setupFab(view)
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
            var updateAt = UserRegister.getUserUpdateInfoUpdateTime()
            eventManager.eventConnection(updateAt)
            //取得失敗時にも更新されるためタイミングを変更:endLoadで行う。
            //UserRegister.createOrUpdateUserEventInfoUpdateTime()
        }
        //}
        var data = EventManager.fetchEvent(mEventStatus)
        if (data.size == 0) {
            val adapter = NoDataAdapter(context)
            mRecyclerView?.let {
                it.adapter = adapter
                return
            }
        }
        var adapter: RealmEventCardAdapter? = mRecyclerView?.let { it.adapter as RealmEventCardAdapter }
        adapter?.let {
            it.updateData(data)
            it.notifyDataSetChanged()
        }
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
            val now = realm.where(EventRealm::class.java).equalTo("id", event.id).findAll()
            if (now.isNotEmpty()) break
            realm.copyToRealm(event)
        }
        realm.commitTransaction()
        realm.close()
    }

    override fun endLoad(success: Boolean) {
        if (success) {
            UserRegister.createOrUpdateUserEventInfoUpdateTime()
        }
    }

    override fun onClickTitle(eventRealm: EventRealm) {
        val uri = Uri.parse(eventRealm.url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun setupFab(view:View){
        //create materialSheetFab
        val fab:MaterialFab=view.findViewById(R.id.materialFab) as MaterialFab
        var fabSheet=view.findViewById(R.id.sheet_fab)

        //add sheet items
        /*val items=FragmentRouter.Tag.values()
        var sheetItemLayout:LinearLayout=fabSheet.findViewById(R.id.sheet_items) as LinearLayout
        for(item in items){
            val textview=TextView(context)
            textview.text=item.tabTitle.toString()
            sheetItemLayout.addView(textview)
        }*/

        val overLay:View=view.findViewById(R.id.overlay)

        val sheetColor=resources.getColor(android.R.color.white)
        val fabColor=resources.getColor(R.color.keep)

        mMaterialSheetFab=MaterialSheetFab(fab,fabSheet,overLay,sheetColor,fabColor)

        /*mMaterialSheetFab?.let {
            it.setEventListener(object :MaterialSheetFabEventListener(){
                override fun onSheetShown() {
                    Toast.makeText(context,"sheet show !",Toast.LENGTH_SHORT).show()
                    super.onSheetShown()
                }

                override fun onShowSheet() {
                    Toast.makeText(context,"sheet show !",Toast.LENGTH_SHORT).show()
                    super.onShowSheet()
                }
                override fun onHideSheet() {
                    Toast.makeText(context,"sheet hide !",Toast.LENGTH_SHORT).show()
                    super.onHideSheet()
                }
            })
        }*/
    }

}
