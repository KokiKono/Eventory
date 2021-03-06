package local.koki.android.eventory.model

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import local.koki.android.eventory.common.Json
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by 浩生 on 2017/01/29.
 */

class EventManager() {
    companion object{
        fun fetchEvent(status:CheckStatus):RealmResults<EventRealm>{
            var realm:Realm= Realm.getDefaultInstance()
            if(status==CheckStatus.Search){
                val data=realm.where(EventRealm::class.java).findAll().sort("startAt")
                return data
            }
            var data=realm.where(EventRealm::class.java).equalTo("status",status.code).beginGroup()
            //ToDo:絞り込をする。
            val places=realm.where(PrefectureRealm::class.java).equalTo("status",true).findAll()
            var count=1
            for(place in places){
                data.like("address","*"+place.name+"*")
                if(count++<places.size){
                    data.or()
                }
            }
            data.endGroup()
            data.beginGroup()
            var jenres=realm.where(JenreRealm::class.java).equalTo("status",true).findAll()
            count=1
            for(jenre in jenres){
                data.like("title","*"+jenre.name+"*")
                if(count++<jenres.size){
                    data.or()
                }
            }
            data.endGroup()
            return data.findAll().sort("startAt")
        }
        fun searchEvent(args:List<String>):RealmResults<EventRealm>{
            var realm=Realm.getDefaultInstance()
            var data=realm.where(EventRealm::class.java).beginGroup()
            for(arg in args){
                data.like("title","*"+arg+"*")
            }
            data.endGroup()
            return data.findAll().sort("startAt")
        }

    }
    abstract class EventConnection : AsyncTask<String, Void, String>() {
        companion object {
            val EVENT_CONNECTION_URL = "https://eventory-155000.appspot.com/api/smt/events"
        }

        private val DEBUG_TAG = "EventConnection"
        private var statusCode: Int = 0
        private var success = false
        private var eventRealms: ArrayList<EventRealm>? = null
        override fun doInBackground(vararg params: String?): String {
            var url = params[0]
            if (params.size > 1) {
                if (!params[1].isNullOrBlank()) {
                    var updateAt = params[1]!!.replaceFirst(" ", "%20")
                    url += "?updated_at=" + updateAt
                }
            }
            var connection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            var resultStr = ""
            try {
                var url = URL(url)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 2000
                connection.connect()
                statusCode = connection.responseCode
                inputStream = connection?.let { it.inputStream }
                resultStr = Json.is2String(inputStream)
            } catch (e: MalformedURLException) {
                Log.e(DEBUG_TAG, "URL変換失敗", e)
            } catch (e: IOException) {
                Log.e(DEBUG_TAG, "通信失敗", e)
            } finally {
                connection?.let { it.disconnect() }
                try {
                    inputStream?.let { it.close() }
                } catch (e: IOException) {
                    Log.e(DEBUG_TAG, "InputStream解放失敗", e)
                }
            }
            return resultStr
        }

        override fun onPostExecute(result: String?) {
            eventRealms = ArrayList()

            try {
                val array = JSONArray(result)
                var count: Int = array.length()
                val simpleFormat=SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                while (count > 0) {
                    val row = array.getJSONObject(count - 1)
                    var event = EventRealm()
                    event.eventId = row.getString("event_id")
                    event.apiId = row.getInt("api_id")
                    event.title = row.getString("title")
                    event.url = row.getString("url")
                    event.limit = row.getInt("limit")
                    event.accepted = row.getInt("accepted")
                    event.address = row.getString("address")
                    event.place = row.getString("place")
                    event.startAt = simpleFormat.parse(row.getString("start_at"))
                    event.endAt = simpleFormat.parse(row.getString("end_at"))
                    event.id = row.getInt("id")
                    eventRealms!!.add(event)
                    count--
                }
                success=true
            } catch (e: JSONException) {
                Log.e(DEBUG_TAG, "JSON変換失敗", e)
                success = false
            }
            onPostExecuteCustomize()
        }

        abstract fun onPostExecuteCustomize()
        fun getData(): ArrayList<EventRealm> {
            eventRealms?.let { return it }
            return ArrayList()
        }
        fun getSuccess():Boolean {
            return success
        }

        fun getStatusCode(): Int {
            return statusCode
        }
    }

    interface LoadEventInterface {
        fun startLoad()
        fun startConnection()
        fun endConnection(data: ArrayList<EventRealm>)
        fun endLoad(success:Boolean)
    }

    var loadEventInterface: LoadEventInterface? = null

    fun eventConnection(updateAt: String?) {
        if (loadEventInterface != null) loadEventInterface!!.startLoad()
        var connection = object : EventConnection() {
            override fun onPostExecuteCustomize() {
                if (loadEventInterface != null) loadEventInterface!!.endConnection(getData())
                if (loadEventInterface != null) loadEventInterface!!.endLoad(getSuccess())
            }
        }
        if (loadEventInterface != null) loadEventInterface!!.startConnection()
        if (updateAt.isNullOrBlank()) {
            connection!!.execute(EventConnection.EVENT_CONNECTION_URL)
        } else {
            connection!!.execute(EventConnection.EVENT_CONNECTION_URL, updateAt)
        }
    }

    enum class Api(val code: Int, val out: String) {
        Atdn(0, "ATDN"),
        Connpass(1, "Connpass"),
        Doorleeper(2, "Doorkeeper"),
        None(5, "");

        companion object {
            fun indexOf(code: Int): Api {
                for (api in Api.values()) {
                    if (api.code == code) {
                        return api
                    }
                }
                return None
            }
        }
    }
    enum class CheckStatus(val code: Int,val value:String){
        NoCheck(0,"新着情報"),
        Keep(1,"キープ"),
        NoKeep(2,"興味なし"),
        Search(3,"検索"),
        None(5,"");
        companion object {
            fun indexOf(status:Int):CheckStatus{
                for(code in CheckStatus.values()){
                    if(status==code.code){
                        return code
                    }
                }
                return None
            }
        }

        override fun toString(): String {
            return this.name
        }
    }
}
