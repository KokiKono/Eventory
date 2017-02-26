package local.koki.android.eventory.view.listener

import local.koki.android.eventory.model.EventRealm

/**
 * Created by 浩生 on 2017/01/31.
 */
interface EventActionListener {
    fun onActionKeep(realm: EventRealm)
    fun onActionNotKeep(realm: EventRealm)
}