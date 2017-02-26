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

public class EventRealmRealmProxy extends local.koki.android.eventory.model.EventRealm
    implements RealmObjectProxy, EventRealmRealmProxyInterface {

    static final class EventRealmColumnInfo extends ColumnInfo
        implements Cloneable {

        public long eventIdIndex;
        public long apiIdIndex;
        public long titleIndex;
        public long urlIndex;
        public long limitIndex;
        public long acceptedIndex;
        public long addressIndex;
        public long placeIndex;
        public long startAtIndex;
        public long endAtIndex;
        public long idIndex;
        public long statusIndex;

        EventRealmColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(12);
            this.eventIdIndex = getValidColumnIndex(path, table, "EventRealm", "eventId");
            indicesMap.put("eventId", this.eventIdIndex);
            this.apiIdIndex = getValidColumnIndex(path, table, "EventRealm", "apiId");
            indicesMap.put("apiId", this.apiIdIndex);
            this.titleIndex = getValidColumnIndex(path, table, "EventRealm", "title");
            indicesMap.put("title", this.titleIndex);
            this.urlIndex = getValidColumnIndex(path, table, "EventRealm", "url");
            indicesMap.put("url", this.urlIndex);
            this.limitIndex = getValidColumnIndex(path, table, "EventRealm", "limit");
            indicesMap.put("limit", this.limitIndex);
            this.acceptedIndex = getValidColumnIndex(path, table, "EventRealm", "accepted");
            indicesMap.put("accepted", this.acceptedIndex);
            this.addressIndex = getValidColumnIndex(path, table, "EventRealm", "address");
            indicesMap.put("address", this.addressIndex);
            this.placeIndex = getValidColumnIndex(path, table, "EventRealm", "place");
            indicesMap.put("place", this.placeIndex);
            this.startAtIndex = getValidColumnIndex(path, table, "EventRealm", "startAt");
            indicesMap.put("startAt", this.startAtIndex);
            this.endAtIndex = getValidColumnIndex(path, table, "EventRealm", "endAt");
            indicesMap.put("endAt", this.endAtIndex);
            this.idIndex = getValidColumnIndex(path, table, "EventRealm", "id");
            indicesMap.put("id", this.idIndex);
            this.statusIndex = getValidColumnIndex(path, table, "EventRealm", "status");
            indicesMap.put("status", this.statusIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final EventRealmColumnInfo otherInfo = (EventRealmColumnInfo) other;
            this.eventIdIndex = otherInfo.eventIdIndex;
            this.apiIdIndex = otherInfo.apiIdIndex;
            this.titleIndex = otherInfo.titleIndex;
            this.urlIndex = otherInfo.urlIndex;
            this.limitIndex = otherInfo.limitIndex;
            this.acceptedIndex = otherInfo.acceptedIndex;
            this.addressIndex = otherInfo.addressIndex;
            this.placeIndex = otherInfo.placeIndex;
            this.startAtIndex = otherInfo.startAtIndex;
            this.endAtIndex = otherInfo.endAtIndex;
            this.idIndex = otherInfo.idIndex;
            this.statusIndex = otherInfo.statusIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final EventRealmColumnInfo clone() {
            return (EventRealmColumnInfo) super.clone();
        }

    }
    private EventRealmColumnInfo columnInfo;
    private ProxyState<local.koki.android.eventory.model.EventRealm> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("eventId");
        fieldNames.add("apiId");
        fieldNames.add("title");
        fieldNames.add("url");
        fieldNames.add("limit");
        fieldNames.add("accepted");
        fieldNames.add("address");
        fieldNames.add("place");
        fieldNames.add("startAt");
        fieldNames.add("endAt");
        fieldNames.add("id");
        fieldNames.add("status");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    EventRealmRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (EventRealmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<local.koki.android.eventory.model.EventRealm>(local.koki.android.eventory.model.EventRealm.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$eventId() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.eventIdIndex);
    }

    public void realmSet$eventId(String value) {
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
                row.getTable().setNull(columnInfo.eventIdIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.eventIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.eventIdIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.eventIdIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$apiId() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.apiIdIndex);
    }

    public void realmSet$apiId(int value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.apiIdIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.apiIdIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$title() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleIndex);
    }

    public void realmSet$title(String value) {
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
                row.getTable().setNull(columnInfo.titleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$url() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.urlIndex);
    }

    public void realmSet$url(String value) {
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
                row.getTable().setNull(columnInfo.urlIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.urlIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.urlIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.urlIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$limit() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.limitIndex);
    }

    public void realmSet$limit(int value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.limitIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.limitIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$accepted() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.acceptedIndex);
    }

    public void realmSet$accepted(int value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.acceptedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.acceptedIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$address() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.addressIndex);
    }

    public void realmSet$address(String value) {
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
                row.getTable().setNull(columnInfo.addressIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.addressIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.addressIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.addressIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$place() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.placeIndex);
    }

    public void realmSet$place(String value) {
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
                row.getTable().setNull(columnInfo.placeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.placeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.placeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.placeIndex, value);
    }

    @SuppressWarnings("cast")
    public Date realmGet$startAt() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.startAtIndex)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.startAtIndex);
    }

    public void realmSet$startAt(Date value) {
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
                row.getTable().setNull(columnInfo.startAtIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDate(columnInfo.startAtIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.startAtIndex);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.startAtIndex, value);
    }

    @SuppressWarnings("cast")
    public Date realmGet$endAt() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.endAtIndex)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.endAtIndex);
    }

    public void realmSet$endAt(Date value) {
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
                row.getTable().setNull(columnInfo.endAtIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDate(columnInfo.endAtIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.endAtIndex);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.endAtIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$id() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    public void realmSet$id(int value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$status() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.statusIndex);
    }

    public void realmSet$status(int value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.statusIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("EventRealm")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("EventRealm");
            realmObjectSchema.add(new Property("eventId", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("apiId", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("url", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("limit", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("accepted", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("address", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("place", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("startAt", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("endAt", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("EventRealm");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_EventRealm")) {
            Table table = sharedRealm.getTable("class_EventRealm");
            table.addColumn(RealmFieldType.STRING, "eventId", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "apiId", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "url", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "limit", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "accepted", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "address", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "place", Table.NULLABLE);
            table.addColumn(RealmFieldType.DATE, "startAt", Table.NULLABLE);
            table.addColumn(RealmFieldType.DATE, "endAt", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "status", Table.NOT_NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_EventRealm");
    }

    public static EventRealmColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_EventRealm")) {
            Table table = sharedRealm.getTable("class_EventRealm");
            final long columnCount = table.getColumnCount();
            if (columnCount != 12) {
                if (columnCount < 12) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 12 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 12 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 12 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final EventRealmColumnInfo columnInfo = new EventRealmColumnInfo(sharedRealm.getPath(), table);

            if (table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
            }

            if (!columnTypes.containsKey("eventId")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'eventId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("eventId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'eventId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.eventIdIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'eventId' is required. Either set @Required to field 'eventId' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("apiId")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'apiId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("apiId") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'apiId' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.apiIdIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'apiId' does support null values in the existing Realm file. Use corresponding boxed type for field 'apiId' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("title") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.titleIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'title' is required. Either set @Required to field 'title' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("url")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'url' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("url") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'url' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.urlIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'url' is required. Either set @Required to field 'url' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("limit")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'limit' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("limit") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'limit' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.limitIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'limit' does support null values in the existing Realm file. Use corresponding boxed type for field 'limit' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("accepted")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'accepted' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("accepted") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'accepted' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.acceptedIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'accepted' does support null values in the existing Realm file. Use corresponding boxed type for field 'accepted' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("address")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'address' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("address") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'address' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.addressIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'address' is required. Either set @Required to field 'address' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("place")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'place' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("place") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'place' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.placeIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'place' is required. Either set @Required to field 'place' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("startAt")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'startAt' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("startAt") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'startAt' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.startAtIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'startAt' is required. Either set @Required to field 'startAt' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("endAt")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'endAt' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("endAt") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'endAt' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.endAtIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'endAt' is required. Either set @Required to field 'endAt' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("status")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'status' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("status") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'status' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.statusIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'status' does support null values in the existing Realm file. Use corresponding boxed type for field 'status' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'EventRealm' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_EventRealm";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static local.koki.android.eventory.model.EventRealm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        local.koki.android.eventory.model.EventRealm obj = realm.createObjectInternal(local.koki.android.eventory.model.EventRealm.class, true, excludeFields);
        if (json.has("eventId")) {
            if (json.isNull("eventId")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$eventId(null);
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$eventId((String) json.getString("eventId"));
            }
        }
        if (json.has("apiId")) {
            if (json.isNull("apiId")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'apiId' to null.");
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$apiId((int) json.getInt("apiId"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$title(null);
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("url")) {
            if (json.isNull("url")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$url(null);
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$url((String) json.getString("url"));
            }
        }
        if (json.has("limit")) {
            if (json.isNull("limit")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'limit' to null.");
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$limit((int) json.getInt("limit"));
            }
        }
        if (json.has("accepted")) {
            if (json.isNull("accepted")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'accepted' to null.");
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$accepted((int) json.getInt("accepted"));
            }
        }
        if (json.has("address")) {
            if (json.isNull("address")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$address(null);
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$address((String) json.getString("address"));
            }
        }
        if (json.has("place")) {
            if (json.isNull("place")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$place(null);
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$place((String) json.getString("place"));
            }
        }
        if (json.has("startAt")) {
            if (json.isNull("startAt")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$startAt(null);
            } else {
                Object timestamp = json.get("startAt");
                if (timestamp instanceof String) {
                    ((EventRealmRealmProxyInterface) obj).realmSet$startAt(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$startAt(new Date(json.getLong("startAt")));
                }
            }
        }
        if (json.has("endAt")) {
            if (json.isNull("endAt")) {
                ((EventRealmRealmProxyInterface) obj).realmSet$endAt(null);
            } else {
                Object timestamp = json.get("endAt");
                if (timestamp instanceof String) {
                    ((EventRealmRealmProxyInterface) obj).realmSet$endAt(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$endAt(new Date(json.getLong("endAt")));
                }
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                ((EventRealmRealmProxyInterface) obj).realmSet$status((int) json.getInt("status"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static local.koki.android.eventory.model.EventRealm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        local.koki.android.eventory.model.EventRealm obj = new local.koki.android.eventory.model.EventRealm();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("eventId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$eventId(null);
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$eventId((String) reader.nextString());
                }
            } else if (name.equals("apiId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'apiId' to null.");
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$apiId((int) reader.nextInt());
                }
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$title(null);
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$title((String) reader.nextString());
                }
            } else if (name.equals("url")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$url(null);
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$url((String) reader.nextString());
                }
            } else if (name.equals("limit")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'limit' to null.");
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$limit((int) reader.nextInt());
                }
            } else if (name.equals("accepted")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'accepted' to null.");
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$accepted((int) reader.nextInt());
                }
            } else if (name.equals("address")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$address(null);
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$address((String) reader.nextString());
                }
            } else if (name.equals("place")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$place(null);
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$place((String) reader.nextString());
                }
            } else if (name.equals("startAt")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$startAt(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((EventRealmRealmProxyInterface) obj).realmSet$startAt(new Date(timestamp));
                    }
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$startAt(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("endAt")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((EventRealmRealmProxyInterface) obj).realmSet$endAt(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((EventRealmRealmProxyInterface) obj).realmSet$endAt(new Date(timestamp));
                    }
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$endAt(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
            } else if (name.equals("status")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                } else {
                    ((EventRealmRealmProxyInterface) obj).realmSet$status((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static local.koki.android.eventory.model.EventRealm copyOrUpdate(Realm realm, local.koki.android.eventory.model.EventRealm object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.EventRealm) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static local.koki.android.eventory.model.EventRealm copy(Realm realm, local.koki.android.eventory.model.EventRealm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (local.koki.android.eventory.model.EventRealm) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            local.koki.android.eventory.model.EventRealm realmObject = realm.createObjectInternal(local.koki.android.eventory.model.EventRealm.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((EventRealmRealmProxyInterface) realmObject).realmSet$eventId(((EventRealmRealmProxyInterface) newObject).realmGet$eventId());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$apiId(((EventRealmRealmProxyInterface) newObject).realmGet$apiId());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$title(((EventRealmRealmProxyInterface) newObject).realmGet$title());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$url(((EventRealmRealmProxyInterface) newObject).realmGet$url());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$limit(((EventRealmRealmProxyInterface) newObject).realmGet$limit());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$accepted(((EventRealmRealmProxyInterface) newObject).realmGet$accepted());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$address(((EventRealmRealmProxyInterface) newObject).realmGet$address());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$place(((EventRealmRealmProxyInterface) newObject).realmGet$place());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$startAt(((EventRealmRealmProxyInterface) newObject).realmGet$startAt());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$endAt(((EventRealmRealmProxyInterface) newObject).realmGet$endAt());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$id(((EventRealmRealmProxyInterface) newObject).realmGet$id());
            ((EventRealmRealmProxyInterface) realmObject).realmSet$status(((EventRealmRealmProxyInterface) newObject).realmGet$status());
            return realmObject;
        }
    }

    public static long insert(Realm realm, local.koki.android.eventory.model.EventRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.EventRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        EventRealmColumnInfo columnInfo = (EventRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.EventRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$eventId = ((EventRealmRealmProxyInterface)object).realmGet$eventId();
        if (realmGet$eventId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.eventIdIndex, rowIndex, realmGet$eventId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.apiIdIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$apiId(), false);
        String realmGet$title = ((EventRealmRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        String realmGet$url = ((EventRealmRealmProxyInterface)object).realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.limitIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$limit(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.acceptedIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$accepted(), false);
        String realmGet$address = ((EventRealmRealmProxyInterface)object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
        }
        String realmGet$place = ((EventRealmRealmProxyInterface)object).realmGet$place();
        if (realmGet$place != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.placeIndex, rowIndex, realmGet$place, false);
        }
        java.util.Date realmGet$startAt = ((EventRealmRealmProxyInterface)object).realmGet$startAt();
        if (realmGet$startAt != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.startAtIndex, rowIndex, realmGet$startAt.getTime(), false);
        }
        java.util.Date realmGet$endAt = ((EventRealmRealmProxyInterface)object).realmGet$endAt();
        if (realmGet$endAt != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.endAtIndex, rowIndex, realmGet$endAt.getTime(), false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$status(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.EventRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        EventRealmColumnInfo columnInfo = (EventRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.EventRealm.class);
        local.koki.android.eventory.model.EventRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.EventRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$eventId = ((EventRealmRealmProxyInterface)object).realmGet$eventId();
                if (realmGet$eventId != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.eventIdIndex, rowIndex, realmGet$eventId, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.apiIdIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$apiId(), false);
                String realmGet$title = ((EventRealmRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                }
                String realmGet$url = ((EventRealmRealmProxyInterface)object).realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.limitIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$limit(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.acceptedIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$accepted(), false);
                String realmGet$address = ((EventRealmRealmProxyInterface)object).realmGet$address();
                if (realmGet$address != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
                }
                String realmGet$place = ((EventRealmRealmProxyInterface)object).realmGet$place();
                if (realmGet$place != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.placeIndex, rowIndex, realmGet$place, false);
                }
                java.util.Date realmGet$startAt = ((EventRealmRealmProxyInterface)object).realmGet$startAt();
                if (realmGet$startAt != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.startAtIndex, rowIndex, realmGet$startAt.getTime(), false);
                }
                java.util.Date realmGet$endAt = ((EventRealmRealmProxyInterface)object).realmGet$endAt();
                if (realmGet$endAt != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.endAtIndex, rowIndex, realmGet$endAt.getTime(), false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$id(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$status(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, local.koki.android.eventory.model.EventRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(local.koki.android.eventory.model.EventRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        EventRealmColumnInfo columnInfo = (EventRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.EventRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$eventId = ((EventRealmRealmProxyInterface)object).realmGet$eventId();
        if (realmGet$eventId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.eventIdIndex, rowIndex, realmGet$eventId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.eventIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.apiIdIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$apiId(), false);
        String realmGet$title = ((EventRealmRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        String realmGet$url = ((EventRealmRealmProxyInterface)object).realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.limitIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$limit(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.acceptedIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$accepted(), false);
        String realmGet$address = ((EventRealmRealmProxyInterface)object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.addressIndex, rowIndex, false);
        }
        String realmGet$place = ((EventRealmRealmProxyInterface)object).realmGet$place();
        if (realmGet$place != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.placeIndex, rowIndex, realmGet$place, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.placeIndex, rowIndex, false);
        }
        java.util.Date realmGet$startAt = ((EventRealmRealmProxyInterface)object).realmGet$startAt();
        if (realmGet$startAt != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.startAtIndex, rowIndex, realmGet$startAt.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.startAtIndex, rowIndex, false);
        }
        java.util.Date realmGet$endAt = ((EventRealmRealmProxyInterface)object).realmGet$endAt();
        if (realmGet$endAt != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.endAtIndex, rowIndex, realmGet$endAt.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.endAtIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$status(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(local.koki.android.eventory.model.EventRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        EventRealmColumnInfo columnInfo = (EventRealmColumnInfo) realm.schema.getColumnInfo(local.koki.android.eventory.model.EventRealm.class);
        local.koki.android.eventory.model.EventRealm object = null;
        while (objects.hasNext()) {
            object = (local.koki.android.eventory.model.EventRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$eventId = ((EventRealmRealmProxyInterface)object).realmGet$eventId();
                if (realmGet$eventId != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.eventIdIndex, rowIndex, realmGet$eventId, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.eventIdIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.apiIdIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$apiId(), false);
                String realmGet$title = ((EventRealmRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                }
                String realmGet$url = ((EventRealmRealmProxyInterface)object).realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.limitIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$limit(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.acceptedIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$accepted(), false);
                String realmGet$address = ((EventRealmRealmProxyInterface)object).realmGet$address();
                if (realmGet$address != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.addressIndex, rowIndex, false);
                }
                String realmGet$place = ((EventRealmRealmProxyInterface)object).realmGet$place();
                if (realmGet$place != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.placeIndex, rowIndex, realmGet$place, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.placeIndex, rowIndex, false);
                }
                java.util.Date realmGet$startAt = ((EventRealmRealmProxyInterface)object).realmGet$startAt();
                if (realmGet$startAt != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.startAtIndex, rowIndex, realmGet$startAt.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.startAtIndex, rowIndex, false);
                }
                java.util.Date realmGet$endAt = ((EventRealmRealmProxyInterface)object).realmGet$endAt();
                if (realmGet$endAt != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.endAtIndex, rowIndex, realmGet$endAt.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.endAtIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$id(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((EventRealmRealmProxyInterface)object).realmGet$status(), false);
            }
        }
    }

    public static local.koki.android.eventory.model.EventRealm createDetachedCopy(local.koki.android.eventory.model.EventRealm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        local.koki.android.eventory.model.EventRealm unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (local.koki.android.eventory.model.EventRealm)cachedObject.object;
            } else {
                unmanagedObject = (local.koki.android.eventory.model.EventRealm)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new local.koki.android.eventory.model.EventRealm();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$eventId(((EventRealmRealmProxyInterface) realmObject).realmGet$eventId());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$apiId(((EventRealmRealmProxyInterface) realmObject).realmGet$apiId());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$title(((EventRealmRealmProxyInterface) realmObject).realmGet$title());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$url(((EventRealmRealmProxyInterface) realmObject).realmGet$url());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$limit(((EventRealmRealmProxyInterface) realmObject).realmGet$limit());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$accepted(((EventRealmRealmProxyInterface) realmObject).realmGet$accepted());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$address(((EventRealmRealmProxyInterface) realmObject).realmGet$address());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$place(((EventRealmRealmProxyInterface) realmObject).realmGet$place());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$startAt(((EventRealmRealmProxyInterface) realmObject).realmGet$startAt());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$endAt(((EventRealmRealmProxyInterface) realmObject).realmGet$endAt());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$id(((EventRealmRealmProxyInterface) realmObject).realmGet$id());
        ((EventRealmRealmProxyInterface) unmanagedObject).realmSet$status(((EventRealmRealmProxyInterface) realmObject).realmGet$status());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("EventRealm = [");
        stringBuilder.append("{eventId:");
        stringBuilder.append(realmGet$eventId() != null ? realmGet$eventId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{apiId:");
        stringBuilder.append(realmGet$apiId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{url:");
        stringBuilder.append(realmGet$url() != null ? realmGet$url() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{limit:");
        stringBuilder.append(realmGet$limit());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{accepted:");
        stringBuilder.append(realmGet$accepted());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{address:");
        stringBuilder.append(realmGet$address() != null ? realmGet$address() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{place:");
        stringBuilder.append(realmGet$place() != null ? realmGet$place() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{startAt:");
        stringBuilder.append(realmGet$startAt() != null ? realmGet$startAt() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{endAt:");
        stringBuilder.append(realmGet$endAt() != null ? realmGet$endAt() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
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
        EventRealmRealmProxy aEventRealm = (EventRealmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aEventRealm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aEventRealm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aEventRealm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
