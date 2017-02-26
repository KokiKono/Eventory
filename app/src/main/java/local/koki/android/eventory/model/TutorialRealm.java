package local.koki.android.eventory.model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by kokikono on 2017/02/21.
 */
@RealmClass
public class TutorialRealm extends RealmObject{
    public String key;
    public boolean isTutorial=false;
}
