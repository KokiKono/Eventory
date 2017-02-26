package local.koki.android.eventory.common

import android.app.Activity
import android.content.Context
import android.support.annotation.Nullable

import io.realm.Realm
import io.realm.RealmQuery
import local.koki.android.eventory.model.TutorialRealm
import java.util.ArrayList

/**
 * Created by kokikono on 2017/02/21.
 */

class TutorialRegister() {

    companion object {
        private fun add(tutorialRealm: TutorialRealm) {
            var realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            var tutorial = realm.createObject(TutorialRealm::class.java)
            tutorial.key=tutorialRealm.key
            tutorial.isTutorial=tutorialRealm.isTutorial
            realm.commitTransaction()
        }

        private fun update(tutorialRealm: TutorialRealm) {
            var realm = Realm.getDefaultInstance()
            var tutorial = get(tutorialRealm.key)
            if (tutorial == null) {
                return
            }
            realm.beginTransaction()
            tutorial.key=tutorialRealm.key
            tutorial.isTutorial=tutorialRealm.isTutorial
            realm.commitTransaction()
        }

        fun addOrUpdate(tutorialRealm: TutorialRealm) {
            var realm = Realm.getDefaultInstance()
            if (get(tutorialRealm.key) != null) {
                update(tutorialRealm)
                return
            }
            add(tutorialRealm)
        }

        fun addOrUpdate(key: String) {
            var tutorial = TutorialRealm()
            tutorial.key = key
            addOrUpdate(tutorial)
        }

        fun addOrUpdate(key: Keys){
            addOrUpdate(key.toString())
        }

        @Nullable
        fun get(key: String): TutorialRealm? {
            var realm = Realm.getDefaultInstance()
            var tutorial = realm.where(TutorialRealm::class.java).equalTo("key", key).findAll()
            if (tutorial.count() == 0) {
                return null
            }
            return tutorial.get(0)
        }
        @Nullable
        fun get(key: Keys): TutorialRealm?{
            return get(key.toString())
        }

        private fun action(key: String, isTutorial: Boolean) {
            var tutoral = TutorialRealm()
            tutoral.key = key
            tutoral.isTutorial = isTutorial
            addOrUpdate(tutoral)
        }

        fun start(key: String) {
            action(key, true)
        }

        fun start(key: Keys){
            start(key.toString())
        }

        fun end(key: String) {
            action(key, false)
        }

        fun end(key: Keys){
            end(key.toString())
        }

        fun isTutorial(key: String):Boolean {
            val tutorial = get(key)
            if (tutorial == null) {
                return false
            }
            return tutorial.isTutorial
        }
        fun isTutorial(key: Keys):Boolean{
            return isTutorial(key.toString())
        }
        fun init(){
            for( key in mShowcase){
                addOrUpdate(key)
            }
        }

        private var mShowcase= ArrayList<String>()

        private fun registerChowcase(showcase:String){
            mShowcase.add(showcase)
        }
    }

    public enum class Keys{
        Version1;
        init{
            registerChowcase(this.name)
        }
    }

}
