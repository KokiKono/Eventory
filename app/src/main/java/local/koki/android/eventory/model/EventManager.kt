package local.koki.android.eventory.model

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import io.realm.Realm
import local.koki.android.eventory.view.util.Json
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * Created by 浩生 on 2017/01/29.
 */

class EventManager {
    constructor()

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
            //:TODO　開発用に一時的にapiを使わない
            /*try {
                var url = URL(url)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 2000
                connection.connect()
                statusCode = connection.responseCode
                inputStream = connection!!.inputStream
                resultStr = Json.is2String(inputStream)
            } catch (e: MalformedURLException) {
                Log.e(DEBUG_TAG, "URL変換失敗", e)
            } catch (e: IOException) {
                Log.e(DEBUG_TAG, "通信失敗", e)
            } finally {
                connection!!.disconnect()
                try {
                    inputStream!!.close()
                } catch (e: IOException) {
                    Log.e(DEBUG_TAG, "InputStream解放失敗", e)
                }
            }*/
            return resultStr
        }

        override fun onPostExecute(result: String?) {
            eventRealms = ArrayList()

            try {
                val array = JSONArray(result)
                var count: Int = array.length()
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
                    //:TODO strat_atになってるよ！
                    //event.startAt = row.getString("start_at")
                    event.startAt = row.getString("strat_at")
                    event.endAt = row.getString("end_at")
                    event.id = row.getInt("id")
                    eventRealms!!.add(event)
                    count--
                }
            } catch (e: JSONException) {
                Log.e(DEBUG_TAG, "JSON変換失敗", e)
                success = false
            }
            onPostExecuteCustomize()
        }

        abstract fun onPostExecuteCustomize()
        fun getData(): ArrayList<EventRealm> {
            return eventRealms!!
        }

        fun getStatusCode(): Int {
            return statusCode
        }
    }

    interface LoadEventInterface {
        fun startLoad()
        fun startConnection()
        fun endConnection(data: ArrayList<EventRealm>)
        fun endLoad()
    }

    var loadEventInterface: LoadEventInterface? = null

    fun eventConnection(updateAt: String?) {
        if (loadEventInterface != null) loadEventInterface!!.startLoad()
        var connection = object : EventConnection() {
            override fun onPostExecuteCustomize() {
                if (loadEventInterface != null) loadEventInterface!!.endConnection(getData())
                if (loadEventInterface != null) loadEventInterface!!.endLoad()
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
    enum class CheckStatus(val code: Int){
        NoCheck(0),
        Keep(1),
        NotKeep(2),
        Search(3),
        None(5)
    }


}
