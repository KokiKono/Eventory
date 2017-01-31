package local.koki.android.eventory.view.fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm

import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.view.adapter.RealmEventCardAdapter
import java.util.*
import android.util.Log
import android.view.animation.OvershootInterpolator
import io.realm.OrderedRealmCollection
import io.realm.RealmList
import io.realm.RealmResults
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import local.koki.android.eventory.model.UserRealm
import local.koki.android.eventory.model.UserRegister

class WhatNewItemFragment :
        Fragment(),
        Runnable,
        EventManager.LoadEventInterface,
        RealmEventCardAdapter.ViewHolder.OnClickKeepListener,
        RealmEventCardAdapter.ViewHolder.OnClickNoKeepListener {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RealmEventCardAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mRealm: Realm? = null

    private var changeItem=HashMap<EventRealm,Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(context)
        mRealm = Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_what_new, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            //find parent view
            mRecyclerView = view.findViewById(R.id.list) as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)
            mLayoutManager = LinearLayoutManager(context)
            mRecyclerView!!.layoutManager = mLayoutManager
            mAdapter = RealmEventCardAdapter(context,null)
            mAdapter!!.onClickKeep=this
            mAdapter!!.onClickNotKeep=this
            mRecyclerView!!.adapter = mAdapter
            mRecyclerView!!.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        loadEvent()
    }


    private var mProgressDialog: ProgressDialog? = null
    private var mThread: Thread? = null
    private var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            mProgressDialog!!.dismiss()
        }
    }

    private fun loadEvent() {
        mProgressDialog = ProgressDialog(context)
        mProgressDialog!!.setTitle("イベント取得中")
        mProgressDialog!!.setMessage("イベント取得を準備しています。")
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.show()
        var eventManager: EventManager = EventManager()
        eventManager.loadEventInterface = this
        var updateAt = UserRegister.getUserEventInfoUpdateTime(mRealm as Realm)
        eventManager.eventConnection(updateAt)
    }

    override fun startLoad() {
        mProgressDialog!!.setMessage("取得開始")
    }

    override fun startConnection() {
        mProgressDialog!!.setMessage("取得中...")
    }

    override fun endConnection(data: ArrayList<EventRealm>) {
        mProgressDialog!!.setMessage("更新中...")
        mRealm!!.beginTransaction()
        for (event in data) {
            mRealm!!.copyToRealm(event)
        }
        mRealm!!.commitTransaction()
    }

    override fun endLoad() {
        val data:RealmResults<EventRealm> = mRealm!!.
                where(EventRealm::class.java).
                equalTo("status",EventManager.CheckStatus.NoCheck.code).findAll()
        //リフレッシュ
        /*mRealm!!.beginTransaction()
        for(event in data){
            event.status=EventManager.CheckStatus.NoCheck.code
        }
        mRealm!!.commitTransaction()*/
        mAdapter!!.updateData(data)
        mProgressDialog!!.setMessage("お待たせいたしました。")
        UserRegister.createOrUpdateUserEventInfoUpdateTime(mRealm as Realm)
        mThread = Thread(this@WhatNewItemFragment)
        mThread!!.start()
    }

    override fun run() {
        try {
            Thread.sleep(750)
        } catch (e: InterruptedException) {

        }
        var message = Message()
        message.arg1 = 1
        mHandler.sendMessage(message)
    }

    override fun onClickKeep(eventRealm: EventRealm,position:Int) {
        changeItem.put(eventRealm,EventManager.CheckStatus.Keep.code)
    }
    override fun onClickNotKeep(eventRealm: EventRealm,position: Int) {
        changeItem.put(eventRealm,EventManager.CheckStatus.NotKeep.code)
    }
    fun onUpdate(){
        mRealm!!.beginTransaction()
        changeItem.forEach { eventRealm, i ->
            eventRealm.status=i
        }
        mRealm!!.commitTransaction()
    }

}
