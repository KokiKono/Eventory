package local.koki.android.eventory.view.actionbar

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import local.koki.android.eventory.R

/**
 * ActionBarにEditTextを表示している状態でそのイベントを簡略したものです。
 * 各イベントを拡張する場合はリスナーを拡張して
 * その中でイベント処理を行ってください。
 * Close処理、Done処理のどちらでもEditTextのtextをクリアにする処理は
 * 必ず行われます。
 * @see CustomEditBar.OnEditorActionListener リスナー
 * Created by 浩生 on 2017/01/22.
 */

class CustomEditBar(private val activity: Activity, private val actionView: View) {
    private var mListener: OnEditorActionListener?=null
    private var mInputManager: InputMethodManager?=null
    private var mView: View?=(activity.findViewById(android.R.id.content) as ViewGroup)!!.getChildAt(0)
    private var mCloseView: View?=null
    private var mEdit: EditText?=null
    init {
        if(actionView==null){
            throw RuntimeException("actionView is Null !!: Please check that the attribute of actionLayout is http://schemas.android.com/apk/res-auto of xmlns.")
        }
        mEdit=actionView.findViewById(R.id.edit)as EditText
        if(mEdit==null){
            throw RuntimeException("Not fund Edit !!:The ID of EditView in actionLayout must be 'edit'.")
        }
        mCloseView=actionView.findViewById(R.id.close)
        if(mCloseView==null){
            throw RuntimeException("Not fund CloseView !! :The ID of CloseView in actionLayout must be 'close'.")
        }
        mEdit!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                onEditorAction()
                return true
            }
        })
        mCloseView!!.setOnClickListener(View.OnClickListener {  onClickClose() })

    }
    public fun setListener(listener: OnEditorActionListener?){
        mListener=listener
    }
    public interface OnEditorActionListener{
        fun onEditorAction(edit: EditText?)
        fun onEditorClose(edit: EditText?)
    }
    private fun onClickClose(){
        if(mListener!=null){
            mListener!!.onEditorClose(mEdit)
        }
        close()
    }
    private fun onEditorAction(){
        if(mListener!=null){
            mListener!!.onEditorAction(mEdit)
        }
        close()
    }

    private fun close(){
        mInputManager=activity.getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
        mInputManager!!.hideSoftInputFromWindow(mView!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        mView!!.requestFocus()
        mEdit!!.text.clear()
    }

}
