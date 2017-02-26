package local.koki.android.eventory.view.receycler

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView

/**
 * Created by 浩生 on 2017/01/23.
 */

class ScrollBaseFABBehavior(private val floatingActionButton: FloatingActionButton) : RecyclerView.OnScrollListener(){
    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        if(newState==RecyclerView.SCROLL_STATE_IDLE){
            floatingActionButton!!.show()
        }
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        if(dy>0 || dy<0 && floatingActionButton!!.isShown){
            floatingActionButton!!.hide()
        }
    }
}
