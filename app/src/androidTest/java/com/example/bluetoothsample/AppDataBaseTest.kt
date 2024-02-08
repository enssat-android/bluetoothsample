package com.example.bluetoothsample
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppDataBaseTest {

    private lateinit var db: AppDataBase
    @Before
     fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun createDeck() {
        val sc1 = db.shortcutDao().insert(Shortcut(KeyEvent.KEYCODE_A))
        val sc2 = db.shortcutDao().insert(Shortcut(KeyEvent.KEYCODE_B))
        val sc3 = db.shortcutDao().insert(Shortcut(KeyEvent.KEYCODE_C))

        Log.d("DEBUG", "shortcuts: $sc1, $sc2, $sc3")

        val deck = Deck("Testing deck")
        var d1 = db.deckDao().insert(deck)
        Log.d("DEBUG", "deck: $d1")

        val sd1 = db.shortcutbydeckDao().insert(Shortcutbydeck(deck.deckId, sc1))
        val sd2 = db.shortcutbydeckDao().insert(Shortcutbydeck(deck.deckId, sc2))
        val sd3 = db.shortcutbydeckDao().insert(Shortcutbydeck(deck.deckId, sc3))
        Log.d("DEBUG", "shorcuts by deck: $sd1, $sd2, $sd3")

        val shortcuts = db.shortcutbydeckDao().getAllShortcutsByDeckId(deck.deckId)
        val txt= shortcuts.joinToString(", ")
        Log.d("DEBUG", "shorcuts found: $txt")
    }
}