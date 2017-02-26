package local.koki.android.eventory.common

import android.graphics.Color

import local.koki.android.eventory.view.adapter.RealmConfigAtPlaceAdapter

/**
 * Created by 浩生 on 2017/01/28.
 */

enum class Colors constructor(val color: Int, val state: Boolean) {
    STATE_ON(Color.parseColor("#E67E22"), true), STATE_OFF(Color.parseColor("#C0BfBF"), false);
    companion object {
        fun isColor(state: Boolean): Int {
            for (color in values()) {
                if (color.state == state) {
                    return color.color
                }
            }
            return STATE_OFF.color
        }
        public val KeepOn:Int=Color.rgb(230,126,34)
    }
}
