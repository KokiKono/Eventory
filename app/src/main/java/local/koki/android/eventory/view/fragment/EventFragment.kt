package local.koki.android.eventory.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmResults
import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.model.UserRegister
import local.koki.android.eventory.view.adapter.RealmEventCardAdapter
import local.koki.android.eventory.view.adapter.RealmEventCardAdapter.ViewHolder
import local.koki.android.eventory.view.listener.EventActionListener
import java.util.*

/**
 * Created by 浩生 on 2017/01/31.
 */

class EventFragment : Fragment()
        , RealmEventCardAdapter.ViewHolder.OnClickKeepListener
        , RealmEventCardAdapter.ViewHolder.OnClickNoKeepListener
        ,EventManager.LoadEventInterface{
    private var mEventStatus: EventManager.CheckStatus = EventManager.CheckStatus.None
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: RealmEventCardAdapter? = null
    private var mData: RealmResults<EventRealm>? = null
    private var mEventAction: EventActionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEventStatus = EventManager.CheckStatus.indexOf(arguments.getInt("event_status"))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_event_list, container, false)

        if (view is RecyclerView) {
            //create recycler view
            mRecyclerView = view.findViewById(R.id.list) as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)
            mLayoutManager = LinearLayoutManager(context)
            mRecyclerView!!.layoutManager = mLayoutManager
            mAdapter = RealmEventCardAdapter(context, null)
            mAdapter!!.onClickKeep = this
            mAdapter!!.onClickNotKeep = this
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
        if(mEventStatus==EventManager.CheckStatus.NoCheck) {
            //サーバーからデータを首都する。
           /* var eventManager=EventManager()
            eventManager.loadEventInterface=this
            eventManager.eventConnection(null)*/
        }
        mData=EventManager.fetchEvent(context,mEventStatus)
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
        Realm.init(context)
        var realm=Realm.getDefaultInstance()
        realm.beginTransaction()
        for(event in data){
            realm.copyToRealm(event)
        }
        realm.commitTransaction()
        realm.close()
    }

    override fun endLoad() {
    }
}
