package local.koki.android.eventory.data.storage.realm

import io.realm.RealmObject
import local.koki.android.eventory.data.util.JenreInterface

/**
 * Created by 浩生 on 2017/01/24.
 */
class JenreRealm : RealmObject,JenreInterface{
    private var id:Int?=null
    private var name:String?=null
    private var flg:Boolean?=false
    public constructor()

    override fun setId(id: Int?) {
        this.id=id
    }

    override fun getId(): Int? {
        return this.id
    }

    override fun setName(name: String?) {
        this.name=name
    }

    override fun getName(): String? {
        return this.name
    }

    override fun setFlg(flg: Boolean?) {
        this.flg=flg
    }

    override fun getFlg(): Boolean? {
        return this.flg
    }
}