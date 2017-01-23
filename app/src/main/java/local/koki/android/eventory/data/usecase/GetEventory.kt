package local.koki.android.eventory.data.usecase

import local.koki.android.eventory.data.cache.EventoryCache
import local.koki.android.eventory.data.util.EventoryUtil

/**
 * Created by 浩生 on 2017/01/22.
 */

class GetEventory : EventoryCache{
    override fun cache(): List<EventoryUtil> {
        //:TODO イベント情報を取得してくる。
        return emptyList()
    }
}
