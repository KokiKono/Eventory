package local.koki.android.eventory.view.controller

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.hardware.input.InputManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*

import local.koki.android.eventory.R
import local.koki.android.eventory.data.storage.orm.PrefectureDao
import local.koki.android.eventory.data.util.PrefectureInterface
import local.koki.android.eventory.view.adapter.ConfigAtPlaceAdapter
import local.koki.android.eventory.view.receycler.DividerItemDecoration
import java.util.*
import android.support.v7.widget.SearchView
import android.view.inputmethod.InputMethodManager
import android.widget.*
import local.koki.android.eventory.view.actionbar.CustomEditBar
import local.koki.android.eventory.view.receycler.ScrollBaseFABBehavior

class ConfigAtPlaceActivity : AppCompatActivity() {

    private var mRecyclerView:RecyclerView?=null
    private var mPrefecutreOnListener : OnPrefectureItemClickListener?=null
    private var mFloatingActionButton:FloatingActionButton ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_at_place)

        mRecyclerView=findViewById(R.id.list) as RecyclerView

        //リストに下線を付ける
        mRecyclerView!!.addItemDecoration(DividerItemDecoration(applicationContext))
        mPrefecutreOnListener= OnPrefectureItemClickListener()
        mRecyclerView!!.layoutManager=LinearLayoutManager(this)

        //←の付与
        val actionBar:ActionBar =supportActionBar as ActionBar
        actionBar.setDisplayHomeAsUpEnabled(true)

        mFloatingActionButton=findViewById(R.id.floatingActionButton) as FloatingActionButton
        mFloatingActionButton!!.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(v!!.context,"追加処理！",Toast.LENGTH_SHORT).show()
            }
        })
        //スクロールした時にFloatingActionButtonを隠す。
        mRecyclerView!!.addOnScrollListener(ScrollBaseFABBehavior(mFloatingActionButton!!))

    }

    override fun onResume() {
        super.onResume()
        val list:List<PrefectureInterface> =PrefectureDao.findAll(applicationContext)
        val adapter:ConfigAtPlaceAdapter= ConfigAtPlaceAdapter(list,OnPrefectureItemClickListener())
        mRecyclerView!!.adapter=adapter
    }
    private class OnPrefectureItemClickListener : ConfigAtPlaceAdapter.OnItemClickListener{
        override fun onItemClickListener(holder: ConfigAtPlaceAdapter.ViewHolder) {
            holder.reversalCheck()
            holder.mPrefecture!!.setFlg(holder.mCheckBox!!.isChecked)
        }
    }
    private fun onSave(){
        val adapter:ConfigAtPlaceAdapter = mRecyclerView!!.adapter as ConfigAtPlaceAdapter
        val list:ArrayList<PrefectureInterface> = adapter.getItems() as ArrayList<PrefectureInterface>
        for(prefecture : PrefectureInterface in list){
            PrefectureDao.updateAt(applicationContext,prefecture)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId){
            android.R.id.home -> {
                onSave()
                finish()
            }
            else ->{
                Log.e("ConfigAtPlaceActivity","not supported Option Item Selected")
            }
        }
        return true
    }



}
