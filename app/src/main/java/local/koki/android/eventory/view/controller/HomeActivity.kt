package local.koki.android.eventory.view.controller

import android.content.SharedPreferences
import android.support.v4.app.FragmentTabHost
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import local.koki.android.eventory.R
import local.koki.android.eventory.view.fragment.*

class HomeActivity : AppCompatActivity()
{
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


        //各タブの初期設定
        //「探す」
        searchTab = tabHost.newTabSpec("search")
        searchTab.setIndicator(getString(R.string.search), ResourcesCompat.getDrawable(resources, android.R.drawable.ic_search_category_default, null))
        tabHost.addTab(searchTab, SearchItemFragment::class.java, null)
        //「興味なし」
        notInterestedTab = tabHost.newTabSpec("not_interested")
        notInterestedTab.setIndicator(getString(R.string.not_interested))
        tabHost.addTab(notInterestedTab, NotKeepItemFragment::class.java, null)
        //「新着情報」
        whatNewTab = tabHost.newTabSpec("what_new")
        whatNewTab.setIndicator(getString(R.string.what_new))
        tabHost.addTab(whatNewTab, WhatNewItemFragment::class.java, null)
        //「興味あり」
        interestedTab = tabHost.newTabSpec("interested")
        interestedTab.setIndicator(getString(R.string.interested))
        tabHost.addTab(interestedTab, KeepItemFragment::class.java, null)
        //「設定」
        configurationTab = tabHost.newTabSpec("configuration")
        configurationTab.setIndicator(getString(R.string.configuration))
        tabHost.addTab(configurationTab, ConfigurationItemFragment::class.java, null)

        tabHost.setOnTabChangedListener{
            v ->
            val fragment=supportFragmentManager.findFragmentByTag("what_new")
            if(fragment!=null && fragment is WhatNewItemFragment){
            }
            Log.e("HOME Activity","タブが切り変えられた。")}
    }

}
