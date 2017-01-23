package local.koki.android.eventory.data.storage.orm

import android.content.Context
import android.util.Log
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.stmt.query.In
import local.koki.android.eventory.data.util.PrefectureInterface

/**
 * Created by 浩生 on 2017/01/22.
 */

class PrefectureDao{

    companion object{
        private val DEBUG_TAG:String="PrefectureDao"
        public fun findAll(context: Context):List<PrefectureORM>{
            var helper:ORMHelper= ORMHelper(context)
            try{
                var dao:Dao<PrefectureORM,Int>?=helper.getDao(PrefectureORM::class.java)
                return dao!!.queryForAll()
            }catch (e:Exception){
                Log.e(PrefectureDao.DEBUG_TAG,"全件取得失敗",e)
            }
            return emptyList()
        }
        public fun updateAt(context: Context,prefecture : PrefectureInterface){
            var helper:ORMHelper= ORMHelper(context)
            val uril:PrefectureORM = prefecture as PrefectureORM
            try{
                val dao:Dao<PrefectureORM,Int> =helper.getDao(PrefectureORM::class.java)
                dao.createOrUpdate(uril)
            }catch (e:Exception){
                Log.e(PrefectureDao.DEBUG_TAG,"更新失敗",e)
            }
        }
        public fun insertAt(context: Context,name:String){
            val sql = "INSERT INTO prefecture(name) VALUES('" + name + "');"
            var helper:ORMHelper= ORMHelper(context)
            var dao: Dao<PrefectureORM, Int>? = helper.getDao(PrefectureORM::class.java)
            dao!!.updateRaw(sql)
        }
    }
    private constructor()


}
