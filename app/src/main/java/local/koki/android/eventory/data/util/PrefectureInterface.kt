package local.koki.android.eventory.data.util

/**
 * Created by 浩生 on 2017/01/22.
 */

interface PrefectureInterface{
    fun getId():Int?
    fun getName():String?
    fun getFlg():Boolean?
    fun setId(id:Int?)
    fun setName(name:String?)
    fun setFlg(flg:Boolean?)
}
