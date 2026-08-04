package com.guttrack.app.`data`.db

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.guttrack.app.`data`.model.MealEntry
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class MealDao_Impl(
  __db: RoomDatabase,
) : MealDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfMealEntry: EntityInsertAdapter<MealEntry>

  private val __deleteAdapterOfMealEntry: EntityDeleteOrUpdateAdapter<MealEntry>

  private val __updateAdapterOfMealEntry: EntityDeleteOrUpdateAdapter<MealEntry>
  init {
    this.__db = __db
    this.__insertAdapterOfMealEntry = object : EntityInsertAdapter<MealEntry>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `meal_entries` (`id`,`dateEpoch`,`type`,`time`,`note`,`photoUris`,`intoleranceTags`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MealEntry) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.dateEpoch)
        statement.bindText(3, entity.type)
        statement.bindText(4, entity.time)
        statement.bindText(5, entity.note)
        statement.bindText(6, entity.photoUris)
        statement.bindText(7, entity.intoleranceTags)
      }
    }
    this.__deleteAdapterOfMealEntry = object : EntityDeleteOrUpdateAdapter<MealEntry>() {
      protected override fun createQuery(): String = "DELETE FROM `meal_entries` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: MealEntry) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfMealEntry = object : EntityDeleteOrUpdateAdapter<MealEntry>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `meal_entries` SET `id` = ?,`dateEpoch` = ?,`type` = ?,`time` = ?,`note` = ?,`photoUris` = ?,`intoleranceTags` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: MealEntry) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.dateEpoch)
        statement.bindText(3, entity.type)
        statement.bindText(4, entity.time)
        statement.bindText(5, entity.note)
        statement.bindText(6, entity.photoUris)
        statement.bindText(7, entity.intoleranceTags)
        statement.bindLong(8, entity.id)
      }
    }
  }

  public override suspend fun insert(entry: MealEntry): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfMealEntry.insertAndReturnId(_connection, entry)
    _result
  }

  public override suspend fun delete(entry: MealEntry): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfMealEntry.handle(_connection, entry)
    _result
  }

  public override suspend fun update(entry: MealEntry): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfMealEntry.handle(_connection, entry)
    _result
  }

  public override fun observeForDate(dateEpoch: Long): Flow<List<MealEntry>> {
    val _sql: String = "SELECT * FROM meal_entries WHERE dateEpoch = ? ORDER BY id"
    return createFlow(__db, false, arrayOf("meal_entries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, dateEpoch)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDateEpoch: Int = getColumnIndexOrThrow(_stmt, "dateEpoch")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfPhotoUris: Int = getColumnIndexOrThrow(_stmt, "photoUris")
        val _columnIndexOfIntoleranceTags: Int = getColumnIndexOrThrow(_stmt, "intoleranceTags")
        val _result: MutableList<MealEntry> = mutableListOf()
        while (_stmt.step()) {
          val _item: MealEntry
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDateEpoch: Long
          _tmpDateEpoch = _stmt.getLong(_columnIndexOfDateEpoch)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpTime: String
          _tmpTime = _stmt.getText(_columnIndexOfTime)
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpPhotoUris: String
          _tmpPhotoUris = _stmt.getText(_columnIndexOfPhotoUris)
          val _tmpIntoleranceTags: String
          _tmpIntoleranceTags = _stmt.getText(_columnIndexOfIntoleranceTags)
          _item = MealEntry(_tmpId,_tmpDateEpoch,_tmpType,_tmpTime,_tmpNote,_tmpPhotoUris,_tmpIntoleranceTags)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeSince(fromEpoch: Long): Flow<List<MealEntry>> {
    val _sql: String = "SELECT * FROM meal_entries WHERE dateEpoch >= ? ORDER BY dateEpoch, id"
    return createFlow(__db, false, arrayOf("meal_entries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fromEpoch)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDateEpoch: Int = getColumnIndexOrThrow(_stmt, "dateEpoch")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfPhotoUris: Int = getColumnIndexOrThrow(_stmt, "photoUris")
        val _columnIndexOfIntoleranceTags: Int = getColumnIndexOrThrow(_stmt, "intoleranceTags")
        val _result: MutableList<MealEntry> = mutableListOf()
        while (_stmt.step()) {
          val _item: MealEntry
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDateEpoch: Long
          _tmpDateEpoch = _stmt.getLong(_columnIndexOfDateEpoch)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpTime: String
          _tmpTime = _stmt.getText(_columnIndexOfTime)
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpPhotoUris: String
          _tmpPhotoUris = _stmt.getText(_columnIndexOfPhotoUris)
          val _tmpIntoleranceTags: String
          _tmpIntoleranceTags = _stmt.getText(_columnIndexOfIntoleranceTags)
          _item = MealEntry(_tmpId,_tmpDateEpoch,_tmpType,_tmpTime,_tmpNote,_tmpPhotoUris,_tmpIntoleranceTags)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findByDateAndType(dateEpoch: Long, type: String): MealEntry? {
    val _sql: String = "SELECT * FROM meal_entries WHERE dateEpoch = ? AND type = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, dateEpoch)
        _argIndex = 2
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDateEpoch: Int = getColumnIndexOrThrow(_stmt, "dateEpoch")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfPhotoUris: Int = getColumnIndexOrThrow(_stmt, "photoUris")
        val _columnIndexOfIntoleranceTags: Int = getColumnIndexOrThrow(_stmt, "intoleranceTags")
        val _result: MealEntry?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDateEpoch: Long
          _tmpDateEpoch = _stmt.getLong(_columnIndexOfDateEpoch)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpTime: String
          _tmpTime = _stmt.getText(_columnIndexOfTime)
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpPhotoUris: String
          _tmpPhotoUris = _stmt.getText(_columnIndexOfPhotoUris)
          val _tmpIntoleranceTags: String
          _tmpIntoleranceTags = _stmt.getText(_columnIndexOfIntoleranceTags)
          _result = MealEntry(_tmpId,_tmpDateEpoch,_tmpType,_tmpTime,_tmpNote,_tmpPhotoUris,_tmpIntoleranceTags)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long): Int {
    val _sql: String = "DELETE FROM meal_entries WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
