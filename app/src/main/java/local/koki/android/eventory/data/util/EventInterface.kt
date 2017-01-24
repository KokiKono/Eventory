package local.koki.android.eventory.data.util

/**
 * Created by 浩生 on 2017/01/24.
 */

open interface EventInterface{
    fun setEventId(eventId:String?)
    fun getEventId():String?
    fun setApiId(apiId:Int?)
    fun getApiId():Int?
    fun setTitle(title:String?)
    fun getTitle():String?
    fun setUrl(url:String?)
    fun getUrl():String?
    fun setLimit(limit:Int?)
    fun getLimit():Int?
    fun setAccepted(accepted:Int?)
    fun getAccepted():Int?
    fun setAddress(address:String?)
    fun getAddress():String?
    fun setPlace(place:String?)
    fun getPlace():String?
    fun setStartAt(startAt:String?)
    fun getStartAt():String?
    fun setEndAt(endAt:String?)
    fun getEndAt():String?
    fun setId(id:Int?)
    fun getId():Int?
}
