package local.koki.android.eventory.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import io.realm.Realm
import local.koki.android.eventory.R
import local.koki.android.eventory.common.FragmentRouter
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.view.listener.EventActionListener
import java.util.*
class HomeActivity : AppCompatActivity()
, EventActionListener
,ViewPager.OnPageChangeListener{
    private var mStockChangedEvent=HashMap<EventRealm,EventManager.CheckStatus>()
    private var mViewPager:ViewPager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mvc)
        val toolBar=findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(toolBar)
        //ActionBarとTabの影をなくす。
        supportActionBar!!.elevation = 0f
        val titleStr=getString(R.string.app_name)
        val searchStr=getString(R.string.search)
        val noKeepStr=getString(R.string.not_interested)
        val newEventStr=getString(R.string.what_new)
        val keepStr=getString(R.string.interested)
        val configStr=getString(R.string.configuration)
        title = titleStr

        mViewPager=findViewById(R.id.pager) as ViewPager
        val tabLayout=findViewById(android.R.id.tabs) as TabLayout
        val pageTitles= arrayListOf(searchStr,noKeepStr,newEventStr,keepStr,configStr)
        val tags=FragmentRouter.Tag.values();
        var fragmentAdapter=object :FragmentPagerAdapter(supportFragmentManager){
            override fun getCount(): Int {
                return tags.size
            }

            override fun getItem(position: Int): Fragment {
                //tagsの配列位置と連動させる。
                val tag=tags.get(position)
                val args=FragmentRouter.newFragmentArgs(tag)
                return FragmentRouter.newInstance(tag,args)
            }

            override fun getPageTitle(position: Int): CharSequence {
                return tags.get(position).tabTitle
            }
        }
        mViewPager!!.adapter=fragmentAdapter
        mViewPager!!.addOnPageChangeListener(this)
        tabLayout.setupWithViewPager(mViewPager)
    }

    override fun onActionKeep(realm: EventRealm) {
        mStockChangedEvent.put(realm,EventManager.CheckStatus.Keep)
    }

    override fun onActionNotKeep(realm: EventRealm) {
        mStockChangedEvent.put(realm,EventManager.CheckStatus.NoKeep)
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

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        onUpdate()
    }

}
