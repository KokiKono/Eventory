package local.koki.android.eventory.view.controller

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.EditText
import android.widget.Toast

import local.koki.android.eventory.data.storage.orm.PrefectureDao
import local.koki.android.eventory.data.storage.orm.PrefectureORM

/**
 * Created by 浩生 on 2017/01/24.
 */

class Test {
    private fun main() {
        val editText = EditText(null)
        AlertDialog.Builder(null)
                .setPositiveButton("OK") { dialog, which -> Toast.makeText(null, null, Toast.LENGTH_SHORT).show() }
                .show()
    }
}
