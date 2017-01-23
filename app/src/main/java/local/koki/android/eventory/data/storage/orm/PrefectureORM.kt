package local.koki.android.eventory.data.storage.orm

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import local.koki.android.eventory.data.util.PrefectureInterface

/**
 * Created by 浩生 on 2017/01/22.
 */
@DatabaseTable( tableName = "prefecture")
class PrefectureORM:PrefectureInterface{
    @DatabaseField(id = true)
    private var id:Int?=0
    @DatabaseField
    private var name:String?=""
    @DatabaseField(defaultValue = "false")
    private var flg:Boolean?=false
    @SuppressWarnings("unused")
    private constructor()
    public constructor(id:Int,name:String,flg:Boolean){
        this.id=id
        this.name=name
        this.flg=flg
    }

    override fun toString(): String {
        return "Prefecture[id="+id+",name="+name+"flg="+flg+"]"
    }

    override fun getId(): Int? {
        return this.id
    }
    override fun getFlg(): Boolean? {
        return this.flg
    }

    override fun getName(): String? {
        return  this.name
    }

    override fun setId(id: Int?) {
        this.id=id
    }

    override fun setName(name: String?) {
        this.name=name
    }

    override fun setFlg(flg: Boolean?) {
        this.flg=flg
    }



}
