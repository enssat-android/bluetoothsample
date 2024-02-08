package com.example.bluetoothsample

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

class ListModifiersConverter {
    @TypeConverter
    public fun fromInt(value: Int): List<KeyModifier> {
        return Shortcut.allModifiers.filter { it and value != 0 }
    }

    @TypeConverter
    public fun listToInt(l: List<KeyModifier>): Int {
        return l.fold(0) { acc, i -> acc or i }
    }
}

@Entity
@TypeConverters(ListModifiersConverter::class)
data class Shortcut(
    val shortcutKey: Int,
    val modifiers: List<KeyModifier> = emptyList(),
    val releaseModifiers: Boolean = true,
    @PrimaryKey(autoGenerate = true) val shortcutId: Long = 0,
) {
    companion object {
        const val LEFT_CONTROL: KeyModifier = 0b1
        const val LEFT_SHIFT: KeyModifier = 0b10
        const val LEFT_ALT: KeyModifier = 0b100
        const val LEFT_GUI: KeyModifier = 0b1000
        const val RIGHT_CONTROL: KeyModifier = 0b1_0000
        const val RIGHT_SHIFT: KeyModifier = 0b10_0000
        const val RIGHT_ALT: KeyModifier = 0b100_0000
        const val RIGHT_GUI: KeyModifier = 0b1000_0000

        val allModifiers = listOf(
            LEFT_CONTROL, LEFT_SHIFT, LEFT_ALT, LEFT_GUI,
            RIGHT_CONTROL, RIGHT_SHIFT, RIGHT_ALT, RIGHT_GUI
        )
    }
}

@Entity
data class Deck(
    val label: String,
    @PrimaryKey(autoGenerate = true) val deckId: Long = 0
)

@Entity(primaryKeys = ["deckId","shortcudId"])
data class Shortcutbydeck(
    val deckId: Long,
    val shortcudId:Long,
)