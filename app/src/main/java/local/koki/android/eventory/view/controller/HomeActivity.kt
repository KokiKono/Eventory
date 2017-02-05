package local.koki.android.eventory.view.controller

import android.content.SharedPreferences
import android.support.v4.app.FragmentTabHost
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import io.realm.Realm
import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.view.fragment.*
import local.koki.android.eventory.view.listener.EventActionListener
import java.util.*

class HomeActivity : AppCompatActivity()
, EventActionListener {
    private var mStockChangedEvent=HashMap<EventRealm,EventManager.CheckStatus>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        title = "eventory"
        //TabWidgetの線を消す
        val tabWidget = findViewById(android.R.id.tabs) as TabWidget
        tabWidget.isStripEnabled = false
        tabWidget.showDividers = LinearLayout.SHOW_DIVIDER_NONE
        //ActionBarとTabの影をなくす。
        supportActionBar!!.elevation = 0f


        //FragmentTabHostを取得する。
        val tabHost = findViewById(android.R.id.tabhost) as FragmentTabHost
        tabHost.setup(applicationContext, supportFragmentManager, R.id.container)
        //各タブ、「探す」、「興味なし」、「新着情報」、「興味あり」、「設定」
        val searchTab: TabHost.TabSpec
        val notInterestedTab: TabHost.TabSpec
        val whatNewTab: TabHost.TabSpec
        val interestedTab: TabHost.TabSpec
        val configurationTab: TabHost.TabSpec

        var args:Bundle
        //各タブの初期設定
        //「探す」
        args= Bundle()
        args.putInt("event_status",EventManager.CheckStatus.Search.code)
        searchTab = tabHost.newTabSpec("search")
        searchTab.setIndicator(getString(R.string.search), ResourcesCompat.getDrawable(resources, android.R.drawable.ic_search_category_default, null))
        tabHost.addTab(searchTab, SearchFragment::class.java, args)
        //「興味なし」
        args= Bundle()
        args.putInt("event_status",EventManager.CheckStatus.NotKeep.code)
        notInterestedTab = tabHost.newTabSpec("not_interested")
        notInterestedTab.setIndicator(getString(R.string.not_interested))
        tabHost.addTab(notInterestedTab, EventFragment::class.java, args)
        //「新着情報」
        args= Bundle()
        args.putInt("event_status",EventManager.CheckStatus.NoCheck.code)
        whatNewTab = tabHost.newTabSpec("what_new")
        whatNewTab.setIndicator(getString(R.string.what_new))
        tabHost.addTab(whatNewTab, EventFragment::class.java, args)
        //「興味あり」
        args= Bundle()
        args.putInt("event_status",EventManager.CheckStatus.Keep.code)
        interestedTab = tabHost.newTabSpec("interested")
        interestedTab.setIndicator(getString(R.string.interested))
        tabHost.addTab(interestedTab, EventFragment::class.java, args)
        //「設定」
        configurationTab = tabHost.newTabSpec("configuration")
        configurationTab.setIndicator(getString(R.string.configuration))
        tabHost.addTab(configurationTab, ConfigurationItemFragment::class.java, null)

        tabHost.setOnTabChangedListener{
            v ->
            onUpdate()
            Log.e("HOME Activity","タブが切り変えられた。")}
    }

    override fun onActionKeep(realm: EventRealm) {
        mStockChangedEvent.put(realm,EventManager.CheckStatus.Keep)
    }

    override fun onActionNotKeep(realm: EventRealm) {
        mStockChangedEvent.put(realm,EventManager.CheckStatus.NotKeep)
    }
    private fun onUpdate(){
        Realm.init(applicationContext)
        val realm=Realm.getDefaultInstance()
        realm.beginTransaction()
        val keys=mStockChangedEvent.keys
        for(key in keys){
            key.status=mStockChangedEvent.get(key)!!.code
        }
        realm.commitTransaction()
    }

}
