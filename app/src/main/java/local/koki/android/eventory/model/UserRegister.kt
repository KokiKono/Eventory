package local.koki.android.eventory.model

import android.content.Context
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 浩生 on 2017/01/28.
 */

class UserRegister{
    companion object{
        private fun updateUserEventInfoUpdateTime(realm:Realm){
            var nowDate:Date= Date()
            val simpleDateFormat=SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.JAPAN)
            realm.beginTransaction()
            var user=realm.where(UserRealm::class.java).findFirst()
            user.updateAt=simpleDateFormat.format(nowDate)
            realm.commitTransaction()
        }
        fun getUserEventInfoUpdateTime(realm: Realm):String{
            var query=realm.where(UserRealm::class.java).findAll()
            if(query.size==0){
                return ""
            }
            var user=realm.where(UserRealm::class.java).findFirst()
            return user.updateAt!!
        }
        fun getUserUpdateInfoUpdateTime(context: Context):String{
            Realm.init(context)
            return getUserEventInfoUpdateTime(Realm.getDefaultInstance())
        }
        fun createOrUpdateUserEventInfoUpdateTime(realm: Realm){
            var nowDate:Date= Date()
            val simpleDateFormat=SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.JAPAN)
            if(!getUserEventInfoUpdateTime(realm).isEmpty()){
                updateUserEventInfoUpdateTime(realm)
                return
            }
            realm.beginTransaction()
            var user=realm.createObject(UserRealm::class.java)
            user.updateAt=simpleDateFormat.format(nowDate)
            realm.commitTransaction()
        }
    }
}
