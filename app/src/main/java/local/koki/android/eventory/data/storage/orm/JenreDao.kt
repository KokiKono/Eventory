package local.koki.android.eventory.data.storage.orm

import android.content.Context
import com.j256.ormlite.dao.Dao
import local.koki.android.eventory.data.util.JenreInterface
import android.util.Log
import com.j256.ormlite.stmt.query.In

/**
 * Created by 浩生 on 2017/01/22.
 */
class JenreDao {
    companion object{
        private val DEBUG_TAG:String="JenreDao"
        public fun findAll(context: Context):List<JenreInterface>{
            val helper:ORMHelper= ORMHelper(context)
            try{
                val dao:Dao<JenreORM,Int>?=helper.getDao(JenreORM::class.java)
                return dao!!.queryForAll()
            }catch (e:Exception){
                Log.e(DEBUG_TAG,"全件取得失敗",e)
            }
            return emptyList()
        }
        public fun updateAt(context: Context,jenre: JenreInterface){
            val helper:ORMHelper = ORMHelper(context)
            val util:JenreORM = jenre as JenreORM
            try{
                val dao:Dao<JenreORM,Int> =helper.getDao(JenreORM::class.java)
                dao.createOrUpdate(util)
            }catch (e:Exception){
                Log.e(DEBUG_TAG,"更新失敗",e)
            }
        }
    }
}