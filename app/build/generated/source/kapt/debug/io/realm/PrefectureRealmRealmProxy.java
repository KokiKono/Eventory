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

public class PrefectureRealmRealmProxy extends local.koki.android.eventory.model.PrefectureRealm
    implements RealmObjectProxy, PrefectureRealmRealmProxyInterface {

    static final class PrefectureRealmColumnInfo extends ColumnInfo
        implements Cloneable {

        public long nameIndex;
        public long statusIndex;

        PrefectureRealmColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.nameIndex = getValidColumnIndex(path, table, "PrefectureRealm", "name");
            indicesMap.put("name", this.nameIndex);
            this.statusIndex = getValidColumnIndex(path, table, "PrefectureRealm", "status");
            indicesMap.put("status", this.statusIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final PrefectureRealmColumnInfo otherInfo = (PrefectureRealmColumnInfo) other;
            this.nameIndex = otherInfo.nameIndex;
            this.statusIndex = otherInfo.statusIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final PrefectureRealmColumnInfo clone() {
            return (PrefectureRealmColumnInfo) super.clone();
        }

    }
    private PrefectureRealmColumnInfo columnInfo;
    private ProxyState<local.koki.android.eventory.model.PrefectureRealm> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("name");
        fieldNames.add("status");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    PrefectureRealmRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PrefectureRealmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<local.koki.android.eventory.model.PrefectureRealm>(local.koki.android.eventory.model.PrefectureRealm.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
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
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$status() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.statusIndex);
    }

    public void realmSet$status(boolean value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.statusIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("PrefectureRealm")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("PrefectureRealm");
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("status", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("PrefectureRealm");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_PrefectureRealm")) {
            Table table = sharedRealm.getTable("class_PrefectureRealm");
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "status", Table.NOT_NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_PrefectureRealm");
    }

    public static PrefectureRealmColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_PrefectureRealm")) {
            Table table = sharedRealm.getTable("class_PrefectureRealm");
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

            final PrefectureRealmColumnInfo columnInfo = new PrefectureRealmColumnInfo(sharedRealm.getPath(), table);

            if (table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
            }

            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("status")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'status' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("status") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'status' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.statusIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'status' does support null values in the existing Realm file. Use corresponding boxed type for field 'status' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'PrefectureRealm' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_PrefectureRealm";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static local.koki.android.eventory.model.PrefectureRealm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        local.koki.android.eventory.model.PrefectureRealm obj = realm.createObjectInternal(local.koki.android.eventory.model.PrefectureRealm.class, true, excludeFields);
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((PrefectureRealmRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((PrefectureRealmRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                ((PrefectureRealmRealmProxyInterface) obj).realmSet$status((boolean) json.getBoolean("status"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static local.koki.android.eventory.model.PrefectureRealm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        local.koki.android.eventory.model.PrefectureRealm obj = new local.koki.android.eventory.model.PrefectureRealm();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PrefectureRealmRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((PrefectureRealmRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("status")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                } else {
                    ((PrefectureRealmRealmProxyInterface) obj).realmSet$status((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static local.koki.android.eventory.model.PrefectureRealm copyOrUpdate(Realm realm, local.koki.android.eventory.model.PrefectureRealm object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.PrefectureRealm) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static local.koki.android.eventory.model.PrefectureRealm copy(Realm realm, local.koki.android.eventory.model.PrefectureRealm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.PrefectureRealm) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            local.koki.android.eventory.model.PrefectureRealm realmObject = realm.createObjectInternal(local.koki.android.eventory.model.PrefectureRealm.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((PrefectureRealmRealmProxyInterface) realmObject).realmSet$name(((PrefectureRealmRealmProxyInterface) newObject).realmGet$name());
            ((PrefectureRealmRealmProxyInterface) realmObject).realmSet$status(((PrefectureRealmRealmProxyInterface) newObject).realmGet$status());
            return realmObject;
        }
    }

    public static long insert(Realm realm, local.koki.android.eventory.model.PrefectureRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.PrefectureRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        PrefectureRealmColumnInfo columnInfo = (PrefectureRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.PrefectureRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$name = ((PrefectureRealmRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.statusIndex, rowIndex, ((PrefectureRealmRealmProxyInterface)object).realmGet$status(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.PrefectureRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        PrefectureRealmColumnInfo columnInfo = (PrefectureRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.PrefectureRealm.class);
        local.koki.android.eventory.model.PrefectureRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.PrefectureRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$name = ((PrefectureRealmRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.statusIndex, rowIndex, ((PrefectureRealmRealmProxyInterface)object).realmGet$status(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, local.koki.android.eventory.model.PrefectureRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.PrefectureRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        PrefectureRealmColumnInfo columnInfo = (PrefectureRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.PrefectureRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$name = ((PrefectureRealmRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.statusIndex, rowIndex, ((PrefectureRealmRealmProxyInterface)object).realmGet$status(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.PrefectureRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        PrefectureRealmColumnInfo columnInfo = (PrefectureRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.PrefectureRealm.class);
        local.koki.android.eventory.model.PrefectureRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.PrefectureRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$name = ((PrefectureRealmRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.statusIndex, rowIndex, ((PrefectureRealmRealmProxyInterface)object).realmGet$status(), false);
            }
        }
    }

    public static local.koki.android.eventory.model.PrefectureRealm createDetachedCopy(local.koki.android.eventory.model.PrefectureRealm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        local.koki.android.eventory.model.PrefectureRealm unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (local.koki.android.eventory.model.PrefectureRealm)cachedObject.object;
            } else {
                unmanagedObject = (local.koki.android.eventory.model.PrefectureRealm)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new local.koki.android.eventory.model.PrefectureRealm();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((PrefectureRealmRealmProxyInterface) unmanagedObject).realmSet$name(((PrefectureRealmRealmProxyInterface) realmObject).realmGet$name());
        ((PrefectureRealmRealmProxyInterface) unmanagedObject).realmSet$status(((PrefectureRealmRealmProxyInterface) realmObject).realmGet$status());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("PrefectureRealm = [");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status());
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
        PrefectureRealmRealmProxy aPrefectureRealm = (PrefectureRealmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPrefectureRealm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPrefectureRealm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPrefectureRealm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
