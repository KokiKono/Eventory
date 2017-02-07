package local.koki.android.eventory.view.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.view.adapter.RealmEventCardAdapter
import local.koki.android.eventory.view.receycler.ScrollBaseFABBehavior
import java.util.regex.Pattern

class SearchFragment : EventFragment() {
    private var mSearchView: SearchView? = null
    private var mFloatingActionButton: FloatingActionButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create optionMenu On ActionBar
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_search, container, false)

        //if (view is RecyclerView) {
        //find parent view
        mRecyclerView = view.findViewById(R.id.list) as RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView!!.layoutManager = mLayoutManager

        mAdapter = RealmEventCardAdapter(context, null)
        mAdapter!!.onClickKeep = this
        mAdapter!!.onClickNotKeep = this
        mAdapter!!.onClickTitle = this
        mRecyclerView!!.adapter = mAdapter
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton) as FloatingActionButton
        mFloatingActionButton!!.setOnClickListener { v ->
            mSearchView!!.isIconified = false
            mFloatingActionButton!!.hide()
        }
        mRecyclerView!!.addOnScrollListener(ScrollBaseFABBehavior(mFloatingActionButton!!))
        //}
        return view
    }

    override fun onResume() {
        super.onResume()
        mData = EventManager.fetchEvent(context, EventManager.CheckStatus.Search)
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
        mSearchView!!.setOnCloseListener(object :SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                mFloatingActionButton!!.show()
                return  false
            }
        })
        mSearchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                //onclick Submit Button
                val patt = Pattern.compile("[,\\s]+")
                var list = patt.split(query).asList()
                mData = EventManager.searchEvent(context, list)
                mAdapter!!.updateData(mData)
                mAdapter!!.notifyDataSetChanged()
                mFloatingActionButton!!.show()
                mSearchView!!.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

}
