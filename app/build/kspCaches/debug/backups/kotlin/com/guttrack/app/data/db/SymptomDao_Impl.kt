package com.guttrack.app.`data`.db

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.guttrack.app.`data`.model.SymptomEntry
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
public class SymptomDao_Impl(
  __db: RoomDatabase,
) : SymptomDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSymptomEntry: EntityInsertAdapter<SymptomEntry>

  private val __deleteAdapterOfSymptomEntry: EntityDeleteOrUpdateAdapter<SymptomEntry>

  private val __updateAdapterOfSymptomEntry: EntityDeleteOrUpdateAdapter<SymptomEntry>
  init {
    this.__db = __db
    this.__insertAdapterOfSymptomEntry = object : EntityInsertAdapter<SymptomEntry>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `symptom_entries` (`id`,`dateEpoch`,`time`,`severity`,`note`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SymptomEntry) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.dateEpoch)
        statement.bindText(3, entity.time)
        statement.bindLong(4, entity.severity.toLong())
        statement.bindText(5, entity.note)
      }
    }
    this.__deleteAdapterOfSymptomEntry = object : EntityDeleteOrUpdateAdapter<SymptomEntry>() {
      protected override fun createQuery(): String = "DELETE FROM `symptom_entries` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SymptomEntry) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfSymptomEntry = object : EntityDeleteOrUpdateAdapter<SymptomEntry>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `symptom_entries` SET `id` = ?,`dateEpoch` = ?,`time` = ?,`severity` = ?,`note` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SymptomEntry) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.dateEpoch)
        statement.bindText(3, entity.time)
        statement.bindLong(4, entity.severity.toLong())
        statement.bindText(5, entity.note)
        statement.bindLong(6, entity.id)
      }
    }
  }

  public override suspend fun insert(entry: SymptomEntry): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSymptomEntry.insertAndReturnId(_connection, entry)
    _result
  }

  public override suspend fun delete(entry: SymptomEntry): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfSymptomEntry.handle(_connection, entry)
    _result
  }

  public override suspend fun update(entry: SymptomEntry): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfSymptomEntry.handle(_connection, entry)
    _result
  }

  public override fun observeForDate(dateEpoch: Long): Flow<List<SymptomEntry>> {
    val _sql: String = "SELECT * FROM symptom_entries WHERE dateEpoch = ? ORDER BY id"
    return createFlow(__db, false, arrayOf("symptom_entries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, dateEpoch)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDateEpoch: Int = getColumnIndexOrThrow(_stmt, "dateEpoch")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _result: MutableList<SymptomEntry> = mutableListOf()
        while (_stmt.step()) {
          val _item: SymptomEntry
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDateEpoch: Long
          _tmpDateEpoch = _stmt.getLong(_columnIndexOfDateEpoch)
          val _tmpTime: String
          _tmpTime = _stmt.getText(_columnIndexOfTime)
          val _tmpSeverity: Int
          _tmpSeverity = _stmt.getLong(_columnIndexOfSeverity).toInt()
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          _item = SymptomEntry(_tmpId,_tmpDateEpoch,_tmpTime,_tmpSeverity,_tmpNote)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeSince(fromEpoch: Long): Flow<List<SymptomEntry>> {
    val _sql: String = "SELECT * FROM symptom_entries WHERE dateEpoch >= ? ORDER BY dateEpoch, id"
    return createFlow(__db, false, arrayOf("symptom_entries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fromEpoch)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDateEpoch: Int = getColumnIndexOrThrow(_stmt, "dateEpoch")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _result: MutableList<SymptomEntry> = mutableListOf()
        while (_stmt.step()) {
          val _item: SymptomEntry
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDateEpoch: Long
          _tmpDateEpoch = _stmt.getLong(_columnIndexOfDateEpoch)
          val _tmpTime: String
          _tmpTime = _stmt.getText(_columnIndexOfTime)
          val _tmpSeverity: Int
          _tmpSeverity = _stmt.getLong(_columnIndexOfSeverity).toInt()
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          _item = SymptomEntry(_tmpId,_tmpDateEpoch,_tmpTime,_tmpSeverity,_tmpNote)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long): Int {
    val _sql: String = "DELETE FROM symptom_entries WHERE id = ?"
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
