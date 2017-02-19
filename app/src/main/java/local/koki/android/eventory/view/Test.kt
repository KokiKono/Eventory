package local.koki.android.eventory.view


import android.app.Activity
import android.os.Handler

import uk.co.deanwild.materialshowcaseview.IShowcaseListener
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

/**
 * Created by 浩生 on 2017/02/14.
 */

class Test {
    private fun main() {
        MaterialShowcaseView.Builder(null)
                .setListener(object : IShowcaseListener {
                    override fun onShowcaseDisplayed(materialShowcaseView: MaterialShowcaseView) {

                    }

                    override fun onShowcaseDismissed(materialShowcaseView: MaterialShowcaseView) {

                    }
                })
    }
}
