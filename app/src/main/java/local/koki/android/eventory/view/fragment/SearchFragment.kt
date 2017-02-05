package local.koki.android.eventory.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import io.realm.RealmResults

import java.util.ArrayList

import local.koki.android.eventory.view.adapter.EventoryCardAdapter
import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.view.adapter.RealmEventCardAdapter
import local.koki.android.eventory.view.listener.EventActionListener

class SearchFragment : Fragment()
        ,RealmEventCardAdapter.ViewHolder.OnClickKeepListener
        ,RealmEventCardAdapter.ViewHolder.OnClickNoKeepListener{
    private var mSearchView: SearchView? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RealmEventCardAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mEventActionListener: EventActionListener?=null
    private var mData:RealmResults<EventRealm>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create optionMenu On ActionBar
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_search, container, false)
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
        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is EventActionListener){
            mEventActionListener=context
        }
    }

    override fun onResume() {
        super.onResume()
        mData= EventManager.searchEvent(context,"*")
        mAdapter!!.updateData(mData)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        //menu configuration
        inflater!!.inflate(R.menu.search_item_fragment_menu, menu)

        val menuItem = menu!!.findItem(R.id.menu_search)
        mSearchView = MenuItemCompat.getActionView(menuItem) as SearchView

        //whether display Magnifying Class Icon at first
        mSearchView!!.setIconifiedByDefault(true)

        //whether display Submit Button
        mSearchView!!.isSubmitButtonEnabled = true

        mSearchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                //onclick Submit Button
                mData= EventManager.searchEvent(context,"*"+query+"*")
                mAdapter!!.updateData(mData)
                mAdapter!!.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //change text
                /*mData= EventManager.searchEvent(context,"*"+newText+"*")
                mAdapter!!.updateData(mData)
                mAdapter!!.notifyDataSetChanged()*/
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClickKeep(eventRealm: EventRealm, position: Int) {
        if(mEventActionListener!=null)mEventActionListener!!.onActionKeep(eventRealm)
    }

    override fun onClickNotKeep(eventRealm: EventRealm, position: Int) {
        if(mEventActionListener!=null)mEventActionListener!!.onActionNotKeep(eventRealm)
    }
}
