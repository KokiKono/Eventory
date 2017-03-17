package local.koki.android.eventory.viewController

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import local.koki.android.eventory.R
import local.koki.android.eventory.common.Colors
import local.koki.android.eventory.common.FragmentRouter
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.model.TutorialRealm
import local.koki.android.eventory.common.TutorialRegister
import local.koki.android.eventory.view.listener.EventActionListener
import uk.co.deanwild.materialshowcaseview.IShowcaseListener
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import java.util.*

class HomeActivity : AppCompatActivity()
        , EventActionListener
        , ViewPager.OnPageChangeListener {
    private var mStockChangedEvent = HashMap<EventRealm, EventManager.CheckStatus>()
    //private var mViewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(applicationContext)
        setContentView(R.layout.activity_home)

        val toolBar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(toolBar)
        val titleStr = getString(R.string.app_name)
        title = titleStr

        var viewPager = findViewById(R.id.pager) as ViewPager
        val tabLayout = findViewById(android.R.id.tabs) as TabLayout
        val tags = FragmentRouter.Tag.values();
        var fragmentAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return tags.size
            }

            override fun getItem(position: Int): Fragment {
                //tagsの配列位置と連動させる。
                val tag = tags.get(position)
                val args = FragmentRouter.newFragmentArgs(tag)
                return FragmentRouter.newFragmentInstance(tag, args)
            }

            override fun getPageTitle(position: Int): CharSequence {
                return tags.get(position).tabTitle
            }
        }
        /*mViewPager!!.adapter = fragmentAdapter
        mViewPager!!.addOnPageChangeListener(this)*/
        viewPager?.let {
            it.adapter=fragmentAdapter
            it.addOnPageChangeListener(this)
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onActionKeep(realm: EventRealm) {
        mStockChangedEvent.put(realm, EventManager.CheckStatus.Keep)
    }

    override fun onActionNotKeep(realm: EventRealm) {
        mStockChangedEvent.put(realm, EventManager.CheckStatus.NoKeep)
    }

    private fun onUpdate() {
        Realm.init(applicationContext)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val keys = mStockChangedEvent.keys
        for (key in keys) {
            key.status = mStockChangedEvent.get(key)!!.code
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //初回チュートリアルを実装する。
        Handler().post {
            val tabLayout = findViewById(android.R.id.tabs) as TabLayout
            var sequence = MaterialShowcaseSequence(this,"version1")
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(tabLayout)
                    .setContentText("インストールありがとうございます。\nEventoryはあなたが行きたいと思えるイベントを教えてくれるアプリです。")
                    .setDismissText("次へ")
                    .setDismissTextColor(Colors.KeepOn)
                    .setListener(object :IShowcaseListener{
                        override fun onShowcaseDismissed(p0: MaterialShowcaseView?) {
                        }
                        override fun onShowcaseDisplayed(p0: MaterialShowcaseView?) {
                            TutorialRegister.start(TutorialRegister.Keys.Version1)
                        }
                    })
                    .build())
            val tabGroup = tabLayout.getChildAt(0) as ViewGroup
            val tabView = tabGroup.getChildAt(FragmentRouter.Tag.indexOf(FragmentRouter.Tag.Configuration))
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(tabLayout)
                    .setContentText("イベントは、日々開催されております。そのほとんどはあなたには興味がないかもしれません。\nまた、それらの通知は必要ですか？")
                    .setDismissText("次へ")
                    .setDismissTextColor(Colors.KeepOn)
                    .build())
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(tabView)
                    .setContentText("我々はそれを必要とは感じていません。なので、フィルタリングを行います。\nでは、早速、ジャンルの設定をしてみましょう。")
                    .setDismissText("次へ")
                    .setDismissTextColor(Colors.KeepOn)
                    .setListener(object : IShowcaseListener {
                        override fun onShowcaseDismissed(p0: MaterialShowcaseView?) {
                            startActivity(Intent(applicationContext, ConfigAtJenreAcitivty::class.java))
                        }

                        override fun onShowcaseDisplayed(p0: MaterialShowcaseView?) {
                            //設定画面を開く
                            tabView.performClick()
                            //Tutorial.isTutorial=false
                        }
                    })
                    .build())
            sequence.start()
        }
        return super.onCreateOptionsMenu(menu)
    }
}
