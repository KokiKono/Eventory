package local.koki.android.eventory.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class UserRealm :RealmObject(){
    var updateAt:String?=null
}