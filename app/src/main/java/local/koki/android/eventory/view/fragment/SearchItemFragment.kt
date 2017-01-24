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

import java.util.ArrayList

import local.koki.android.eventory.view.adapter.EventoryCardAdapter
import local.koki.android.eventory.R
import local.koki.android.eventory.data.util.EventoryUtil

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class SearchItemFragment : Fragment() {
    private var mSearchView: SearchView? = null

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var list: List<EventoryUtil>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create optionMenu On ActionBar
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_search, container, false)
        val eventoryUtils = ArrayList<EventoryUtil>()
        if(view is RecyclerView){
            //find parent view
            mRecyclerView = view.findViewById(R.id.list) as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)

            mLayoutManager = LinearLayoutManager(context)
            mRecyclerView!!.layoutManager = mLayoutManager

            //:TODO データ取得をする。

            //adapter set
            mAdapter = EventoryCardAdapter(context, eventoryUtils)
            mRecyclerView!!.adapter = mAdapter
        }
        return view
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
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //change text
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
