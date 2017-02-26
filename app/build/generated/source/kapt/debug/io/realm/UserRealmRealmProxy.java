package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRealmRealmProxy extends local.koki.android.eventory.model.UserRealm
    implements RealmObjectProxy, UserRealmRealmProxyInterface {

    static final class UserRealmColumnInfo extends ColumnInfo
        implements Cloneable {

        public long updateAtIndex;

        UserRealmColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(1);
            this.updateAtIndex = getValidColumnIndex(path, table, "UserRealm", "updateAt");
            indicesMap.put("updateAt", this.updateAtIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final UserRealmColumnInfo otherInfo = (UserRealmColumnInfo) other;
            this.updateAtIndex = otherInfo.updateAtIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final UserRealmColumnInfo clone() {
            return (UserRealmColumnInfo) super.clone();
        }

    }
    private UserRealmColumnInfo columnInfo;
    private ProxyState<local.koki.android.eventory.model.UserRealm> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("updateAt");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserRealmRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserRealmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<local.koki.android.eventory.model.UserRealm>(local.koki.android.eventory.model.UserRealm.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$updateAt() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.updateAtIndex);
    }

    public void realmSet$updateAt(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.updateAtIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.updateAtIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.updateAtIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.updateAtIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("UserRealm")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("UserRealm");
            realmObjectSchema.add(new Property("updateAt", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("UserRealm");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_UserRealm")) {
            Table table = sharedRealm.getTable("class_UserRealm");
            table.addColumn(RealmFieldType.STRING, "updateAt", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_UserRealm");
    }

    public static UserRealmColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_UserRealm")) {
            Table table = sharedRealm.getTable("class_UserRealm");
            final long columnCount = table.getColumnCount();
            if (columnCount != 1) {
                if (columnCount < 1) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 1 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 1 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 1 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final UserRealmColumnInfo columnInfo = new UserRealmColumnInfo(sharedRealm.getPath(), table);

            if (table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
            }

            if (!columnTypes.containsKey("updateAt")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'updateAt' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("updateAt") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'updateAt' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.updateAtIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'updateAt' is required. Either set @Required to field 'updateAt' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'UserRealm' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_UserRealm";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static local.koki.android.eventory.model.UserRealm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        local.koki.android.eventory.model.UserRealm obj = realm.createObjectInternal(local.koki.android.eventory.model.UserRealm.class, true, excludeFields);
        if (json.has("updateAt")) {
            if (json.isNull("updateAt")) {
                ((UserRealmRealmProxyInterface) obj).realmSet$updateAt(null);
            } else {
                ((UserRealmRealmProxyInterface) obj).realmSet$updateAt((String) json.getString("updateAt"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static local.koki.android.eventory.model.UserRealm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        local.koki.android.eventory.model.UserRealm obj = new local.koki.android.eventory.model.UserRealm();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("updateAt")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmRealmProxyInterface) obj).realmSet$updateAt(null);
                } else {
                    ((UserRealmRealmProxyInterface) obj).realmSet$updateAt((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static local.koki.android.eventory.model.UserRealm copyOrUpdate(Realm realm, local.koki.android.eventory.model.UserRealm object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.UserRealm) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static local.koki.android.eventory.model.UserRealm copy(Realm realm, local.koki.android.eventory.model.UserRealm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.UserRealm) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            local.koki.android.eventory.model.UserRealm realmObject = realm.createObjectInternal(local.koki.android.eventory.model.UserRealm.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((UserRealmRealmProxyInterface) realmObject).realmSet$updateAt(((UserRealmRealmProxyInterface) newObject).realmGet$updateAt());
            return realmObject;
        }
    }

    public static long insert(Realm realm, local.koki.android.eventory.model.UserRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.UserRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserRealmColumnInfo columnInfo = (UserRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.UserRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$updateAt = ((UserRealmRealmProxyInterface)object).realmGet$updateAt();
        if (realmGet$updateAt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updateAtIndex, rowIndex, realmGet$updateAt, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.UserRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserRealmColumnInfo columnInfo = (UserRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.UserRealm.class);
        local.koki.android.eventory.model.UserRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.UserRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$updateAt = ((UserRealmRealmProxyInterface)object).realmGet$updateAt();
                if (realmGet$updateAt != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.updateAtIndex, rowIndex, realmGet$updateAt, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, local.koki.android.eventory.model.UserRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.UserRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserRealmColumnInfo columnInfo = (UserRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.UserRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$updateAt = ((UserRealmRealmProxyInterface)object).realmGet$updateAt();
        if (realmGet$updateAt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updateAtIndex, rowIndex, realmGet$updateAt, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.updateAtIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.UserRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserRealmColumnInfo columnInfo = (UserRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.UserRealm.class);
        local.koki.android.eventory.model.UserRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.UserRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$updateAt = ((UserRealmRealmProxyInterface)object).realmGet$updateAt();
                if (realmGet$updateAt != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.updateAtIndex, rowIndex, realmGet$updateAt, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.updateAtIndex, rowIndex, false);
                }
            }
        }
    }

    public static local.koki.android.eventory.model.UserRealm createDetachedCopy(local.koki.android.eventory.model.UserRealm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        local.koki.android.eventory.model.UserRealm unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (local.koki.android.eventory.model.UserRealm)cachedObject.object;
            } else {
                unmanagedObject = (local.koki.android.eventory.model.UserRealm)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new local.koki.android.eventory.model.UserRealm();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((UserRealmRealmProxyInterface) unmanagedObject).realmSet$updateAt(((UserRealmRealmProxyInterface) realmObject).realmGet$updateAt());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UserRealm = [");
        stringBuilder.append("{updateAt:");
        stringBuilder.append(realmGet$updateAt() != null ? realmGet$updateAt() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRealmRealmProxy aUserRealm = (UserRealmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUserRealm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserRealm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUserRealm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
