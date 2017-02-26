package local.koki.android.eventory.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by 浩生 on 2017/01/26.
 */
@RealmClass
open class PrefectureRealm: RealmObject(){
    var name:String=""
    var status:Boolean=false
}
