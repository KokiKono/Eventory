package local.koki.android.eventory.view.receycler

import android.content.Context
import android.graphics.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.EditText
import io.realm.Realm
import io.realm.RealmResults
import local.koki.android.eventory.model.JenreRealm
import local.koki.android.eventory.view.adapter.RealmConfigAtJenreAdapter
import me.drakeet.materialdialog.MaterialDialog

/**
 * Created by kokikono on 2017/02/26.
 */
class JenreCardSwipe(var context: Context,var recyclerView: RecyclerView):ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
    private var editDialog=MaterialDialog(context)
    init{
        var itemTouchHelper=ItemTouchHelper(this@JenreCardSwipe)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        val fromPos = viewHolder?.let { it.adapterPosition }
        val toPos = target?.let { it.adapterPosition }
        recyclerView?.let { it.adapter.notifyItemMoved(fromPos!!, toPos!!) }
        return false;
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        val realm=Realm.getDefaultInstance()
        val fromPos = viewHolder?.let { it.adapterPosition }
        val adapter: RealmConfigAtJenreAdapter=recyclerView.adapter as RealmConfigAtJenreAdapter
        val itemName = adapter.data?.let { it.get(fromPos!!).name }
        if (direction == ItemTouchHelper.LEFT) {
            //delete
            realm.beginTransaction()
            val item: RealmResults<JenreRealm>? = realm.where(JenreRealm::class.java).equalTo("name", itemName).findAll()
            item?.let { it.deleteAllFromRealm() }
            realm.commitTransaction()
        }
        if (direction == ItemTouchHelper.RIGHT) {
            //edit
            var edit = EditText(context)
            edit.setText(itemName)
            editDialog?.let {
                it.setTitle("編集")
                        .setContentView(edit)
                        .setPositiveButton("登録", object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                realm.beginTransaction()
                                var item = realm.where(JenreRealm::class.java).equalTo("name", itemName).findFirst()
                                item?.let { it.name = edit.text.toString() }
                                realm.commitTransaction()
                                editDialog.dismiss()
                            }
                        })
                        .setNegativeButton("キャンセル", object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                editDialog.dismiss()
                            }
                        })
                        .show()
            }
            adapter.notifyDataSetChanged()
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
}