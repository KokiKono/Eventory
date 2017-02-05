package local.koki.android.eventory.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import local.koki.android.eventory.model.EventManager

/**
 * Created by 浩生 on 2017/01/31.
 */

class EventFragment : Fragment(){
    private var mEventStatus:Int=EventManager.CheckStatus.None.code
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEventStatus=arguments.getInt("event_status")
    }
    
}
