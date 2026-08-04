package com.guttrack.app.`data`.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GutTrackDatabase_Impl : GutTrackDatabase() {
  private val _mealDao: Lazy<MealDao> = lazy {
    MealDao_Impl(this)
  }

  private val _symptomDao: Lazy<SymptomDao> = lazy {
    SymptomDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "64b0ade1215c60097395b1da6233eca5", "406ade9108e3ae1309b504c549a004c1") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `meal_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateEpoch` INTEGER NOT NULL, `type` TEXT NOT NULL, `time` TEXT NOT NULL, `note` TEXT NOT NULL, `photoUris` TEXT NOT NULL, `intoleranceTags` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `symptom_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateEpoch` INTEGER NOT NULL, `time` TEXT NOT NULL, `severity` INTEGER NOT NULL, `note` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '64b0ade1215c60097395b1da6233eca5')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `meal_entries`")
        connection.execSQL("DROP TABLE IF EXISTS `symptom_entries`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsMealEntries: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMealEntries.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMealEntries.put("dateEpoch", TableInfo.Column("dateEpoch", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMealEntries.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMealEntries.put("time", TableInfo.Column("time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMealEntries.put("note", TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMealEntries.put("photoUris", TableInfo.Column("photoUris", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMealEntries.put("intoleranceTags", TableInfo.Column("intoleranceTags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMealEntries: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesMealEntries: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoMealEntries: TableInfo = TableInfo("meal_entries", _columnsMealEntries, _foreignKeysMealEntries, _indicesMealEntries)
        val _existingMealEntries: TableInfo = read(connection, "meal_entries")
        if (!_infoMealEntries.equals(_existingMealEntries)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |meal_entries(com.guttrack.app.data.model.MealEntry).
              | Expected:
              |""".trimMargin() + _infoMealEntries + """
              |
              | Found:
              |""".trimMargin() + _existingMealEntries)
        }
        val _columnsSymptomEntries: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSymptomEntries.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSymptomEntries.put("dateEpoch", TableInfo.Column("dateEpoch", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSymptomEntries.put("time", TableInfo.Column("time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSymptomEntries.put("severity", TableInfo.Column("severity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSymptomEntries.put("note", TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSymptomEntries: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSymptomEntries: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSymptomEntries: TableInfo = TableInfo("symptom_entries", _columnsSymptomEntries, _foreignKeysSymptomEntries, _indicesSymptomEntries)
        val _existingSymptomEntries: TableInfo = read(connection, "symptom_entries")
        if (!_infoSymptomEntries.equals(_existingSymptomEntries)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |symptom_entries(com.guttrack.app.data.model.SymptomEntry).
              | Expected:
              |""".trimMargin() + _infoSymptomEntries + """
              |
              | Found:
              |""".trimMargin() + _existingSymptomEntries)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "meal_entries", "symptom_entries")
  }

  public override fun clearAllTables() {
    super.performClear(false, "meal_entries", "symptom_entries")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(MealDao::class, MealDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SymptomDao::class, SymptomDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun mealDao(): MealDao = _mealDao.value

  public override fun symptomDao(): SymptomDao = _symptomDao.value
}
