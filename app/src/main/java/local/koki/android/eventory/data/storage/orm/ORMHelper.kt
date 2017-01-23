package local.koki.android.eventory.data.storage.orm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

/**
 * Created by 浩生 on 2017/01/22.
 */

class ORMHelper(context: Context) : OrmLiteSqliteOpenHelper(context, ORMHelper.DATABASE_NAME, null, ORMHelper.DATA_BASE_VERSION) {

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            //create table
            TableUtils.createTable(connectionSource, PrefectureORM::class.java)
            TableUtils.createTable(connectionSource, JenreORM::class.java)
            //デフォルトデータの設定
            for (value: String in ORMHelper.DEFAULT_DATA_PRE) {
                insertPre(value)
            }
            for(value:String in ORMHelper.DEFAULT_DATA_JENRE){
                insertJenre(value)
            }
        } catch(e: SQLiteException) {
            Log.e(ORMHelper.DEBUG_TAG, ORMHelper.javaClass.name + "を作成失敗しました。")
        }

    }

    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {

    }

    private fun insertPre(name: String) {
        val sql = "INSERT INTO prefecture(name) VALUES('" + name + "');"
        var dao: Dao<PrefectureORM, Int>? = getDao(PrefectureORM::class.java)
        dao!!.updateRaw(sql)
    }
    private fun insertJenre(name:String){
        val sql = "INSERT INTO jenre(name) VALUES('" + name + "');"
        var dao: Dao<PrefectureORM, Int>? = getDao(PrefectureORM::class.java)
        dao!!.updateRaw(sql)
    }

    companion object {
        private val DATABASE_NAME = "ormlite.eventory"
        private val DATA_BASE_VERSION = 1
        private val DEBUG_TAG = "PrefectureORMHelper"
        private val DEFAULT_DATA_PRE = arrayListOf(
                "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県",
                "福島県", "茨城県", "栃木県", "群馬県", "埼玉県",
                "千葉県", "東京都", "神奈川県", "新潟県", "富山県",
                "石川県", "福井県", "山梨県", "岐阜県", "静岡県", "愛知県",
                "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
                "奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県",
                "山口県", "徳島県", "香川県", "愛媛県", "高知県", "福岡県",
                "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
        )
        private val DEFAULT_DATA_JENRE = arrayListOf(
                "Javascript", "PHP", "Java", "Swift", "Ruby", "Python", "Perl"
                , "Scala", "Haskell", "Go", "LT", "HTML", "CSS", "JQuery")

    }
}
