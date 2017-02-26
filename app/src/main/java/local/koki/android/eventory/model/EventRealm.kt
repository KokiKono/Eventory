package local.koki.android.eventory.model

import io.realm.RealmObject
import java.util.*

/**
 * Created by 浩生 on 2017/01/17.
 */

open class EventRealm : RealmObject(){
    var eventId: String? = null
    var apiId: Int = 0
    var title: String? = null
    var url: String? = null
    var limit: Int = 0
    var accepted: Int = 0
    var address: String? = null
    var place: String? = null
    var startAt: Date? = null
    var endAt: Date? = null
    var id: Int = 0
    var status:Int=EventManager.CheckStatus.NoCheck.code
}
