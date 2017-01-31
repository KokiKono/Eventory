package local.koki.android.eventory.view.controller

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import io.realm.Realm
import local.koki.android.eventory.R
import local.koki.android.eventory.model.JenreRealm
import local.koki.android.eventory.view.adapter.RealmConfigAtJenreAdapter
import local.koki.android.eventory.view.receycler.DividerItemDecoration
import local.koki.android.eventory.view.receycler.ScrollBaseFABBehavior

/**
 * Created by 浩生 on 2017/01/28.
 */

class ConfigAtJenreAcitivty:AppCompatActivity(){
    private var mRecyclerView:RecyclerView?=null
    private var mFloatingActionButton:FloatingActionButton?=null
    private var mRealm:Realm?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_at_jenre)

        Realm.init(applicationContext)
        mRealm= Realm.getDefaultInstance()
        mRecyclerView=findViewById(R.id.list)as RecyclerView
        mFloatingActionButton=findViewById(R.id.floatingActionButton)as FloatingActionButton

        //リストに下線を付ける
        mRecyclerView!!.addItemDecoration(DividerItemDecoration(applicationContext))
        mRecyclerView!!.layoutManager=LinearLayoutManager(this)
        //戻るボタン追加
        var actionBar:ActionBar=supportActionBar as ActionBar
        actionBar.setDisplayHomeAsUpEnabled(true)
        mFloatingActionButton!!.setOnClickListener{v->
            var edit=EditText(v.context)
            AlertDialog.Builder(v.context)
                    .setTitle("ジャンル追加")
                    .setView(edit)
                    .setPositiveButton("OK",{
                        dialogInterface,i -> addItem(edit.text.toString(),true)
                    }).show()
        }
        //スクロール時にFloatingActionButtonを隠す
        mRecyclerView!!.addOnScrollListener(ScrollBaseFABBehavior(mFloatingActionButton!!))
    }

    override fun onResume() {
        super.onResume()
        var realmQuery=mRealm!!.where(JenreRealm::class.java).findAll()
        if(realmQuery.size==0){
            //初期データ
            mRealm!!.beginTransaction()
            mRealm!!.createAllFromJson(JenreRealm::class.java,resources.assets.open("Jenre0.json"))
            mRealm!!.commitTransaction()
            realmQuery=mRealm!!.where(JenreRealm::class.java).findAll()
        }
        val adapter=RealmConfigAtJenreAdapter(this,realmQuery)
        mRecyclerView!!.adapter=adapter
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
    fun addItem(name:String,status:Boolean){
        mRealm!!.beginTransaction()
        var newPref: JenreRealm =mRealm!!.createObject(JenreRealm::class.java)
        newPref.name=name
        newPref.status=status
        mRealm!!.commitTransaction()
    }
}
