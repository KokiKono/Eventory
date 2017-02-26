package local.koki.android.eventory.viewController

import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import io.realm.Realm
import io.realm.RealmResults
import local.koki.android.eventory.R
import local.koki.android.eventory.common.Colors
import local.koki.android.eventory.model.JenreRealm
import local.koki.android.eventory.common.TutorialRegister
import local.koki.android.eventory.view.adapter.RealmConfigAtJenreAdapter
import local.koki.android.eventory.view.receycler.DividerItemDecoration
import local.koki.android.eventory.view.receycler.JenreCardSwipe
import local.koki.android.eventory.view.receycler.ScrollBaseFABBehavior
import me.drakeet.materialdialog.MaterialDialog
import uk.co.deanwild.materialshowcaseview.IShowcaseListener
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

/**
 * Created by 浩生 on 2017/01/28.
 */

class ConfigAtJenreAcitivty : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mFloatingActionButton: FloatingActionButton? = null
    private var mRealm: Realm? = null
    private var addItemDialog:MaterialDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_at_jenre)
        var toolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mRealm = Realm.getDefaultInstance()
        mRecyclerView = findViewById(R.id.list) as RecyclerView
        mFloatingActionButton = findViewById(R.id.floatingActionButton) as FloatingActionButton

        //リストに下線を付ける
        mRecyclerView!!.addItemDecoration(DividerItemDecoration(applicationContext))
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mFloatingActionButton!!.setOnClickListener { v ->
            showAddItemDialog()
        }
        //スクロール時にFloatingActionButtonを隠す
        mRecyclerView!!.addOnScrollListener(ScrollBaseFABBehavior(mFloatingActionButton!!))
        mRecyclerView?.let { JenreCardSwipe(this@ConfigAtJenreAcitivty,it) }
    }

    override fun onResume() {
        super.onResume()
        var realmQuery = mRealm!!.where(JenreRealm::class.java).findAll()
        if (realmQuery.size == 0) {
            //初期データ
            mRealm!!.beginTransaction()
            mRealm!!.createAllFromJson(JenreRealm::class.java, resources.assets.open("Jenre0.json"))
            mRealm!!.commitTransaction()
            realmQuery = mRealm!!.where(JenreRealm::class.java).findAll()
        }
        val adapter = RealmConfigAtJenreAdapter(this, realmQuery)
        mRecyclerView!!.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> {
                Log.e("ConfigAtPlaceActivity", "not supported Option Item Selected")
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm!!.close()
    }

    fun changeItem(item: JenreRealm) {
        var check = item.status
        mRealm!!.executeTransaction { realm ->
            var pref = mRealm!!.where(JenreRealm::class.java)
                    .equalTo("name", item.name).findFirst()
            pref.status = !check
        }
    }

    fun addItem(name: String, status: Boolean) {
        mRealm!!.beginTransaction()
        var newPref: JenreRealm = mRealm!!.createObject(JenreRealm::class.java)
        newPref.name = name
        newPref.status = status
        mRealm!!.commitTransaction()
    }
    fun showAddItemDialog(){
        var edit=EditText(this@ConfigAtJenreAcitivty)
        addItemDialog= MaterialDialog(this@ConfigAtJenreAcitivty)
        addItemDialog?.let {
            it.setTitle("ジャンル追加")
                    .setContentView(edit)
                    .setPositiveButton("登録",object : View.OnClickListener{
                        override fun onClick(v: View?) {
                            addItem(edit.text.toString(),true)
                            addItemDialog?.let { it.dismiss() }
                        }
                    })
                    .setNegativeButton("キャンセル",object : View.OnClickListener{
                        override fun onClick(v: View?) {
                            addItemDialog?.let { it.dismiss() }
                        }
                    })
                    .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Handler().post {
            var sequence = MaterialShowcaseSequence(this, "version1_2")
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(mRecyclerView!!.getChildAt(0))
                    .setContentText("ここでは、ジャンル（分野）ごとに、\nフィルタリングします。")
                    .setDismissText("次へ")
                    .setDismissTextColor(Colors.KeepOn)
                    .setListener(object : IShowcaseListener {
                        override fun onShowcaseDismissed(p0: MaterialShowcaseView?) {
                        }

                        override fun onShowcaseDisplayed(p0: MaterialShowcaseView?) {
                        }
                    })
                    .build())
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(mRecyclerView!!.getChildAt(0))
                    .setContentText("リストをタップする事でフィルタリング状態になります。")
                    .setDismissText("次へ")
                    .setDismissTextColor(Colors.KeepOn)
                    .setListener(object : IShowcaseListener {
                        override fun onShowcaseDismissed(p0: MaterialShowcaseView?) {
                        }

                        override fun onShowcaseDisplayed(p0: MaterialShowcaseView?) {
                            mRecyclerView!!.getChildAt(0).callOnClick()
                        }
                    })
                    .build())
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(mRecyclerView!!.getChildAt(0))
                    .setContentText("もう一度タップでフィルタリングを解除します。")
                    .setDismissText("次へ")
                    .setDismissTextColor(Colors.KeepOn)
                    .setListener(object : IShowcaseListener {
                        override fun onShowcaseDismissed(p0: MaterialShowcaseView?) {
                        }

                        override fun onShowcaseDisplayed(p0: MaterialShowcaseView?) {
                            mRecyclerView!!.getChildAt(0).callOnClick()
                        }
                    })
                    .build())
            sequence.addSequenceItem(MaterialShowcaseView.Builder(this)
                    .setTarget(mRecyclerView!!.getChildAt(0))
                    .setContentText("はじめに適当なジャンルを選択しておきました。")
                    .setDismissText("それでは、Eventoryでもっと成長しましょう！")
                    .setDismissTextColor(Colors.KeepOn)
                    .setListener(object : IShowcaseListener {
                        override fun onShowcaseDismissed(p0: MaterialShowcaseView?) {
                            TutorialRegister.end(TutorialRegister.Keys.Version1)
                            finish()
                        }

                        override fun onShowcaseDisplayed(p0: MaterialShowcaseView?) {
                            mRecyclerView!!.getChildAt(0).callOnClick()
                        }
                    })
                    .build())
            sequence.start()
        }
        return super.onCreateOptionsMenu(menu)
    }

    /*private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            val fromPos = viewHolder?.let { it.adapterPosition }
            val toPos = target?.let { it.adapterPosition }
            mRecyclerView?.let { it.adapter.notifyItemMoved(fromPos!!, toPos!!) }
            return false;
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            val fromPos = viewHolder?.let { it.adapterPosition }
            val adapter: RealmConfigAtJenreAdapter? = mRecyclerView?.let { it.adapter as RealmConfigAtJenreAdapter }
            val itemName = adapter?.let { it.data?.let { it.get(fromPos!!).name  } }
            if (direction == ItemTouchHelper.LEFT) {
                //delete
                mRealm?.let { it.beginTransaction() }
                val item:RealmResults<JenreRealm>? = mRealm?.let { it.where(JenreRealm::class.java).equalTo("name", itemName).findAll() }
                item?.let { it.deleteAllFromRealm() }
                adapter?.let { it.notifyItemRemoved(fromPos!!) }
                mRealm?.let { it.commitTransaction() }
                return
            }
            if (direction == ItemTouchHelper.RIGHT) {
                //edit
                var edit = EditText(this@ConfigAtJenreAcitivty)
                edit.setText(itemName)
                mEditDialog = MaterialDialog(this@ConfigAtJenreAcitivty)
                mEditDialog?.let {
                    it.setTitle("編集")
                            .setContentView(edit)
                            .setPositiveButton("登録", object : View.OnClickListener {
                                override fun onClick(v: View?) {
                                    mRealm?.let { it.beginTransaction() }
                                    var item = mRealm?.let { it.where(JenreRealm::class.java).equalTo("name", itemName).findFirst() }
                                    item?.let { it.name = edit.text.toString() }
                                    mRealm?.let { it.commitTransaction() }
                                    mEditDialog?.let { it.dismiss() }
                                }
                            })
                            .setNegativeButton("キャンセル", object : View.OnClickListener {
                                override fun onClick(v: View?) {
                                    mEditDialog?.let { it.dismiss() }
                                }
                            })
                            .show()
                }
                adapter?.let { it.notifyDataSetChanged() }
                return
            }
        }


        override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                val itemView = viewHolder?.let { it.itemView }
                val bottom = itemView?.let { it.bottom.toFloat() }
                val right = itemView?.let { it.right.toFloat() }
                val left = itemView?.let { it.left.toFloat() }
                val top = itemView?.let { it.top.toFloat() }

                val height = bottom!! - top!!
                val width = height!! / 3
                var p = Paint()
                if (dX > 0) {
                    val background = RectF(left!!, top!!, dX, bottom!!)
                    p.color = Color.parseColor("#3F51B5")
                    c?.let { it.drawRect(background, p) }
                    p.color = Color.WHITE
                    p.textSize = 50f
                    p.typeface = Typeface.MONOSPACE
                    c?.let { it.drawText("編集", left!! + width, bottom!! - width, p) }
                } else {
                    val background = RectF(right!! + dX, top!!, right!!, bottom!!)
                    p.color = Color.RED
                    c?.let { it.drawRect(background, p) }
                    p.color = Color.WHITE
                    p.textSize = 50f
                    p.typeface = Typeface.MONOSPACE
                    c?.let { it.drawText("削除", right!! - 2 * width, bottom!! - width, p) }
                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    })*/
}
