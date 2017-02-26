package io.realm;


import android.util.JsonReader;
import io.realm.RealmObjectSchema;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(local.koki.android.eventory.model.EventRealm.class);
        modelClasses.add(local.koki.android.eventory.model.TutorialRealm.class);
        modelClasses.add(local.koki.android.eventory.model.UserRealm.class);
        modelClasses.add(local.koki.android.eventory.model.PrefectureRealm.class);
        modelClasses.add(local.koki.android.eventory.model.JenreRealm.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm) {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return io.realm.EventRealmRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return io.realm.TutorialRealmRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return io.realm.UserRealmRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return io.realm.PrefectureRealmRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return io.realm.JenreRealmRealmProxy.initTable(sharedRealm);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public RealmObjectSchema createRealmObjectSchema(Class<? extends RealmModel> clazz, RealmSchema realmSchema) {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return io.realm.EventRealmRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return io.realm.TutorialRealmRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return io.realm.UserRealmRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return io.realm.PrefectureRealmRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return io.realm.JenreRealmRealmProxy.createRealmObjectSchema(realmSchema);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return io.realm.EventRealmRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return io.realm.TutorialRealmRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return io.realm.UserRealmRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return io.realm.PrefectureRealmRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return io.realm.JenreRealmRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return io.realm.EventRealmRealmProxy.getFieldNames();
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return io.realm.TutorialRealmRealmProxy.getFieldNames();
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return io.realm.UserRealmRealmProxy.getFieldNames();
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return io.realm.PrefectureRealmRealmProxy.getFieldNames();
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return io.realm.JenreRealmRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return io.realm.EventRealmRealmProxy.getTableName();
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return io.realm.TutorialRealmRealmProxy.getTableName();
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return io.realm.UserRealmRealmProxy.getTableName();
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return io.realm.PrefectureRealmRealmProxy.getTableName();
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return io.realm.JenreRealmRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
                return clazz.cast(new io.realm.EventRealmRealmProxy());
            } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
                return clazz.cast(new io.realm.TutorialRealmRealmProxy());
            } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
                return clazz.cast(new io.realm.UserRealmRealmProxy());
            } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
                return clazz.cast(new io.realm.PrefectureRealmRealmProxy());
            } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
                return clazz.cast(new io.realm.JenreRealmRealmProxy());
            } else {
                throw getMissingProxyClassException(clazz);
            }
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return clazz.cast(io.realm.EventRealmRealmProxy.copyOrUpdate(realm, (local.koki.android.eventory.model.EventRealm) obj, update, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return clazz.cast(io.realm.TutorialRealmRealmProxy.copyOrUpdate(realm, (local.koki.android.eventory.model.TutorialRealm) obj, update, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return clazz.cast(io.realm.UserRealmRealmProxy.copyOrUpdate(realm, (local.koki.android.eventory.model.UserRealm) obj, update, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return clazz.cast(io.realm.PrefectureRealmRealmProxy.copyOrUpdate(realm, (local.koki.android.eventory.model.PrefectureRealm) obj, update, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return clazz.cast(io.realm.JenreRealmRealmProxy.copyOrUpdate(realm, (local.koki.android.eventory.model.JenreRealm) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            io.realm.EventRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.EventRealm) object, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            io.realm.TutorialRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.TutorialRealm) object, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            io.realm.UserRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.UserRealm) object, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            io.realm.PrefectureRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.PrefectureRealm) object, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            io.realm.JenreRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.JenreRealm) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
                io.realm.EventRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.EventRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
                io.realm.TutorialRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.TutorialRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
                io.realm.UserRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.UserRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
                io.realm.PrefectureRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.PrefectureRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
                io.realm.JenreRealmRealmProxy.insert(realm, (local.koki.android.eventory.model.JenreRealm) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
                    io.realm.EventRealmRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
                    io.realm.TutorialRealmRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
                    io.realm.UserRealmRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
                    io.realm.PrefectureRealmRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
                    io.realm.JenreRealmRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            io.realm.EventRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.EventRealm) obj, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            io.realm.TutorialRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.TutorialRealm) obj, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            io.realm.UserRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.UserRealm) obj, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            io.realm.PrefectureRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.PrefectureRealm) obj, cache);
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            io.realm.JenreRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.JenreRealm) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
                io.realm.EventRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.EventRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
                io.realm.TutorialRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.TutorialRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
                io.realm.UserRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.UserRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
                io.realm.PrefectureRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.PrefectureRealm) object, cache);
            } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
                io.realm.JenreRealmRealmProxy.insertOrUpdate(realm, (local.koki.android.eventory.model.JenreRealm) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
                    io.realm.EventRealmRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
                    io.realm.TutorialRealmRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
                    io.realm.UserRealmRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
                    io.realm.PrefectureRealmRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
                    io.realm.JenreRealmRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return clazz.cast(io.realm.EventRealmRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return clazz.cast(io.realm.TutorialRealmRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return clazz.cast(io.realm.UserRealmRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return clazz.cast(io.realm.PrefectureRealmRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return clazz.cast(io.realm.JenreRealmRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return clazz.cast(io.realm.EventRealmRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return clazz.cast(io.realm.TutorialRealmRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return clazz.cast(io.realm.UserRealmRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return clazz.cast(io.realm.PrefectureRealmRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return clazz.cast(io.realm.JenreRealmRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(local.koki.android.eventory.model.EventRealm.class)) {
            return clazz.cast(io.realm.EventRealmRealmProxy.createDetachedCopy((local.koki.android.eventory.model.EventRealm) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.TutorialRealm.class)) {
            return clazz.cast(io.realm.TutorialRealmRealmProxy.createDetachedCopy((local.koki.android.eventory.model.TutorialRealm) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.UserRealm.class)) {
            return clazz.cast(io.realm.UserRealmRealmProxy.createDetachedCopy((local.koki.android.eventory.model.UserRealm) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.PrefectureRealm.class)) {
            return clazz.cast(io.realm.PrefectureRealmRealmProxy.createDetachedCopy((local.koki.android.eventory.model.PrefectureRealm) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(local.koki.android.eventory.model.JenreRealm.class)) {
            return clazz.cast(io.realm.JenreRealmRealmProxy.createDetachedCopy((local.koki.android.eventory.model.JenreRealm) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
