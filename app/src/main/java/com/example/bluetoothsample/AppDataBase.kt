package com.example.bluetoothsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface ShortcutDao {
    @Query("SELECT * FROM shortcut WHERE shortcutId IS (:id)") fun getById(id: Long): LiveData<Shortcut>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(shortcut: Shortcut): Long
    @Update fun update(shortcut: Shortcut)
    @Delete fun delete(shortcut: Shortcut)
    @Query("DELETE FROM shortcut WHERE shortcutId IS (:id)") fun deleteById(id: Long)
}

@Dao
interface DeckDao {
    @Query("SELECT * FROM deck WHERE deckId IS (:id)") fun getById(id: Long): LiveData<Deck>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(deck: Deck): Long
    @Update fun update(deck: Deck)
    @Delete fun delete(deck: Deck)
    @Query("DELETE FROM deck WHERE deckId IS (:id)") fun deleteById(id: Long)
}

@Dao
interface ShortcutbydeckDao {
    @Query("SELECT shortcudId FROM shortcutbydeck WHERE deckId IS (:deckId)") fun getAllShortcutsByDeckId(deckId: Long): List<Long>
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(deck_shortcuts: Shortcutbydeck): Long
    @Update fun update(deck_shortcuts: Shortcutbydeck)
    @Delete fun delete(deck_shortcuts: Shortcutbydeck)
    @Query("DELETE FROM shortcutbydeck WHERE deckId IS (:id)") fun deleteById(id: Long)
}

@Database(entities = [Shortcut::class, Deck::class, Shortcutbydeck::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun shortcutDao(): ShortcutDao
    abstract fun deckDao(): DeckDao
    abstract fun shortcutbydeckDao(): ShortcutbydeckDao
}

