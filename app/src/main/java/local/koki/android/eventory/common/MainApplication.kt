package local.koki.android.eventory.common

import android.app.Application
import android.app.FragmentManager
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.viewController.ConfigurationFragment
import local.koki.android.eventory.viewController.EventFragment
import local.koki.android.eventory.viewController.SearchFragment

/**
 * Created by 浩生 on 2017/02/10.
 */
//ToDo:MVCモデルに移行に伴うフラグメント登録アプリケーション
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        //FragmentManagerに各Fragmentのクラス名を登録
        //ここではインスタンス化してない!!
        //検索は使用されていないため使わない。
        //FragmentRouter.register(FragmentRouter.Tag.Search,SearchFragment::class.java)
        FragmentRouter.register(FragmentRouter.Tag.NoKeep,EventFragment::class.java)
        FragmentRouter.register(FragmentRouter.Tag.New,EventFragment::class.java)
        FragmentRouter.register(FragmentRouter.Tag.Keep,EventFragment::class.java)
        FragmentRouter.register(FragmentRouter.Tag.Configuration, ConfigurationFragment::class.java)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}
