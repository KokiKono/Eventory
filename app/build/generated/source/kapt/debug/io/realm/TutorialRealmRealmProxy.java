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

public class TutorialRealmRealmProxy extends local.koki.android.eventory.model.TutorialRealm
    implements RealmObjectProxy, TutorialRealmRealmProxyInterface {

    static final class TutorialRealmColumnInfo extends ColumnInfo
        implements Cloneable {

        public long keyIndex;
        public long isTutorialIndex;

        TutorialRealmColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.keyIndex = getValidColumnIndex(path, table, "TutorialRealm", "key");
            indicesMap.put("key", this.keyIndex);
            this.isTutorialIndex = getValidColumnIndex(path, table, "TutorialRealm", "isTutorial");
            indicesMap.put("isTutorial", this.isTutorialIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final TutorialRealmColumnInfo otherInfo = (TutorialRealmColumnInfo) other;
            this.keyIndex = otherInfo.keyIndex;
            this.isTutorialIndex = otherInfo.isTutorialIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final TutorialRealmColumnInfo clone() {
            return (TutorialRealmColumnInfo) super.clone();
        }

    }
    private TutorialRealmColumnInfo columnInfo;
    private ProxyState<local.koki.android.eventory.model.TutorialRealm> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("key");
        fieldNames.add("isTutorial");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TutorialRealmRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TutorialRealmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<local.koki.android.eventory.model.TutorialRealm>(local.koki.android.eventory.model.TutorialRealm.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$key() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.keyIndex);
    }

    public void realmSet$key(String value) {
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
                row.getTable().setNull(columnInfo.keyIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.keyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.keyIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.keyIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isTutorial() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isTutorialIndex);
    }

    public void realmSet$isTutorial(boolean value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isTutorialIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isTutorialIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("TutorialRealm")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("TutorialRealm");
            realmObjectSchema.add(new Property("key", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isTutorial", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("TutorialRealm");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_TutorialRealm")) {
            Table table = sharedRealm.getTable("class_TutorialRealm");
            table.addColumn(RealmFieldType.STRING, "key", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isTutorial", Table.NOT_NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_TutorialRealm");
    }

    public static TutorialRealmColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_TutorialRealm")) {
            Table table = sharedRealm.getTable("class_TutorialRealm");
            final long columnCount = table.getColumnCount();
            if (columnCount != 2) {
                if (columnCount < 2) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TutorialRealmColumnInfo columnInfo = new TutorialRealmColumnInfo(sharedRealm.getPath(), table);

            if (table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
            }

            if (!columnTypes.containsKey("key")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'key' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("key") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'key' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.keyIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'key' is required. Either set @Required to field 'key' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isTutorial")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isTutorial' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isTutorial") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isTutorial' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isTutorialIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isTutorial' does support null values in the existing Realm file. Use corresponding boxed type for field 'isTutorial' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'TutorialRealm' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TutorialRealm";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static local.koki.android.eventory.model.TutorialRealm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        local.koki.android.eventory.model.TutorialRealm obj = realm.createObjectInternal(local.koki.android.eventory.model.TutorialRealm.class, true, excludeFields);
        if (json.has("key")) {
            if (json.isNull("key")) {
                ((TutorialRealmRealmProxyInterface) obj).realmSet$key(null);
            } else {
                ((TutorialRealmRealmProxyInterface) obj).realmSet$key((String) json.getString("key"));
            }
        }
        if (json.has("isTutorial")) {
            if (json.isNull("isTutorial")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isTutorial' to null.");
            } else {
                ((TutorialRealmRealmProxyInterface) obj).realmSet$isTutorial((boolean) json.getBoolean("isTutorial"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static local.koki.android.eventory.model.TutorialRealm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        local.koki.android.eventory.model.TutorialRealm obj = new local.koki.android.eventory.model.TutorialRealm();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("key")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TutorialRealmRealmProxyInterface) obj).realmSet$key(null);
                } else {
                    ((TutorialRealmRealmProxyInterface) obj).realmSet$key((String) reader.nextString());
                }
            } else if (name.equals("isTutorial")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isTutorial' to null.");
                } else {
                    ((TutorialRealmRealmProxyInterface) obj).realmSet$isTutorial((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static local.koki.android.eventory.model.TutorialRealm copyOrUpdate(Realm realm, local.koki.android.eventory.model.TutorialRealm object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.TutorialRealm) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static local.koki.android.eventory.model.TutorialRealm copy(Realm realm, local.koki.android.eventory.model.TutorialRealm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.TutorialRealm) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            local.koki.android.eventory.model.TutorialRealm realmObject = realm.createObjectInternal(local.koki.android.eventory.model.TutorialRealm.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((TutorialRealmRealmProxyInterface) realmObject).realmSet$key(((TutorialRealmRealmProxyInterface) newObject).realmGet$key());
            ((TutorialRealmRealmProxyInterface) realmObject).realmSet$isTutorial(((TutorialRealmRealmProxyInterface) newObject).realmGet$isTutorial());
            return realmObject;
        }
    }

    public static long insert(Realm realm, local.koki.android.eventory.model.TutorialRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.TutorialRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        TutorialRealmColumnInfo columnInfo = (TutorialRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.TutorialRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$key = ((TutorialRealmRealmProxyInterface)object).realmGet$key();
        if (realmGet$key != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.keyIndex, rowIndex, realmGet$key, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isTutorialIndex, rowIndex, ((TutorialRealmRealmProxyInterface)object).realmGet$isTutorial(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.TutorialRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        TutorialRealmColumnInfo columnInfo = (TutorialRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.TutorialRealm.class);
        local.koki.android.eventory.model.TutorialRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.TutorialRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$key = ((TutorialRealmRealmProxyInterface)object).realmGet$key();
                if (realmGet$key != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.keyIndex, rowIndex, realmGet$key, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isTutorialIndex, rowIndex, ((TutorialRealmRealmProxyInterface)object).realmGet$isTutorial(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, local.koki.android.eventory.model.TutorialRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.TutorialRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        TutorialRealmColumnInfo columnInfo = (TutorialRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.TutorialRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$key = ((TutorialRealmRealmProxyInterface)object).realmGet$key();
        if (realmGet$key != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.keyIndex, rowIndex, realmGet$key, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.keyIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isTutorialIndex, rowIndex, ((TutorialRealmRealmProxyInterface)object).realmGet$isTutorial(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.TutorialRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        TutorialRealmColumnInfo columnInfo = (TutorialRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.TutorialRealm.class);
        local.koki.android.eventory.model.TutorialRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.TutorialRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$key = ((TutorialRealmRealmProxyInterface)object).realmGet$key();
                if (realmGet$key != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.keyIndex, rowIndex, realmGet$key, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.keyIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isTutorialIndex, rowIndex, ((TutorialRealmRealmProxyInterface)object).realmGet$isTutorial(), false);
            }
        }
    }

    public static local.koki.android.eventory.model.TutorialRealm createDetachedCopy(local.koki.android.eventory.model.TutorialRealm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        local.koki.android.eventory.model.TutorialRealm unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (local.koki.android.eventory.model.TutorialRealm)cachedObject.object;
            } else {
                unmanagedObject = (local.koki.android.eventory.model.TutorialRealm)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new local.koki.android.eventory.model.TutorialRealm();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((TutorialRealmRealmProxyInterface) unmanagedObject).realmSet$key(((TutorialRealmRealmProxyInterface) realmObject).realmGet$key());
        ((TutorialRealmRealmProxyInterface) unmanagedObject).realmSet$isTutorial(((TutorialRealmRealmProxyInterface) realmObject).realmGet$isTutorial());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TutorialRealm = [");
        stringBuilder.append("{key:");
        stringBuilder.append(realmGet$key() != null ? realmGet$key() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isTutorial:");
        stringBuilder.append(realmGet$isTutorial());
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
        TutorialRealmRealmProxy aTutorialRealm = (TutorialRealmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTutorialRealm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTutorialRealm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTutorialRealm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
