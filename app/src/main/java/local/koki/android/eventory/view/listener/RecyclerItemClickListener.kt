package local.koki.android.eventory.view.listener

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView

/**
 * Created by 浩生 on 2017/01/21.
 */

class RecyclerItemClickListener : RecyclerView.OnItemTouchListener {
    var mGestureDetector : GestureDetector?= null
    private var mItemClickListener: OnItemClickListener?= null
    constructor(context : Context, listener: OnItemClickListener?){
        mItemClickListener=listener
        mGestureDetector = GestureDetector(context,MySimpleOnGestureListener())

    }
    private class MySimpleOnGestureListener : GestureDetector.SimpleOnGestureListener {
        constructor()
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true;
        }
    }

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        //タッチした個所のViewを取得
        var childView : View = rv!!.findChildViewUnder(e!!.getX(),e!!.getY())
        if(childView != null && mItemClickListener != null && mGestureDetector!!.onTouchEvent(e)){
            //OnItemerceptTouchEventのタイミングだとアイテムのtouch feedbackがつく前にonItemClickが呼ばれる。
            childView.isPressed=true
            mItemClickListener!!.onItemClick(childView,rv!!.getChildAdapterPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        //何もしない
    }
    public interface OnItemClickListener{
        public fun onItemClick(view : View, position : Int)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}
