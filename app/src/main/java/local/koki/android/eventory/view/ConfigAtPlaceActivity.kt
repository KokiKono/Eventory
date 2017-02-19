package local.koki.android.eventory.view

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*

import local.koki.android.eventory.R
import local.koki.android.eventory.view.receycler.DividerItemDecoration
import android.widget.*
import io.realm.Realm
import local.koki.android.eventory.model.PrefectureRealm
import local.koki.android.eventory.view.adapter.RealmConfigAtPlaceAdapter
import local.koki.android.eventory.view.receycler.ScrollBaseFABBehavior

class ConfigAtPlaceActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mFloatingActionButton: FloatingActionButton? = null
    private var mRealm: Realm? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_at_place)
        var toolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        var actionBar=supportActionBar as ActionBar
        actionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
        Realm.init(applicationContext)
        mRealm = Realm.getDefaultInstance()
        mRecyclerView = findViewById(R.id.list) as RecyclerView
        mFloatingActionButton = findViewById(R.id.floatingActionButton) as FloatingActionButton

        //リストに下線を付ける
        mRecyclerView!!.addItemDecoration(DividerItemDecoration(applicationContext))
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mFloatingActionButton!!.setOnClickListener { v ->
            var edit: EditText = EditText(v.context)
            AlertDialog.Builder(v.context)
                    .setTitle("開催地追加")
                    .setView(edit)
                    .setPositiveButton("OK",
                            {
                                dialogInterface, i ->
                                addItem(edit.text.toString(),true)
                            }).show()
        }
        //スクロールした時にFloatingActionButtonを隠す。
        mRecyclerView!!.addOnScrollListener(ScrollBaseFABBehavior(mFloatingActionButton!!))
    }


    override fun onResume() {
        super.onResume()
        var realmQuery = mRealm!!.where(PrefectureRealm::class.java).findAll()
        if (realmQuery.size == 0) {
            //初期データ
            mRealm!!.beginTransaction()
            mRealm!!.createAllFromJson(PrefectureRealm::class.java, resources.assets.open("Prefecture0.json"))
            mRealm!!.commitTransaction()
            realmQuery = mRealm!!.where(PrefectureRealm::class.java).findAll()
        }
        val adapter: RealmConfigAtPlaceAdapter = RealmConfigAtPlaceAdapter(this, realmQuery)
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

    fun changeItem(item: PrefectureRealm) {
        var check = item.status
        mRealm!!.executeTransaction { realm ->
            var pref = mRealm!!.where(PrefectureRealm::class.java)
                    .equalTo("name", item.name).findFirst()
            pref.status = !check
        }
    }
    fun addItem(name:String,status:Boolean){
        mRealm!!.beginTransaction()
        var newPref: PrefectureRealm =mRealm!!.createObject(PrefectureRealm::class.java)
        newPref.name=name
        newPref.status=status
        mRealm!!.commitTransaction()
    }

}
