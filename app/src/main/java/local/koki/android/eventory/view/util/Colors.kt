package local.koki.android.eventory.view.util

import android.graphics.Color

import local.koki.android.eventory.view.adapter.RealmConfigAtPlaceAdapter

/**
 * Created by 浩生 on 2017/01/28.
 */

enum class Colors constructor(val color: Int, val state: Boolean) {
    STATE_ON(Color.parseColor("#ffff8800"), true), STATE_OFF(Color.parseColor("#ff000000"), false);
    companion object {
        fun isColor(state: Boolean): Int {
            for (color in Colors.values()) {
                if (color.state == state) {
                    return color.color
                }
            }
            return Colors.STATE_OFF.color
        }
    }
}
