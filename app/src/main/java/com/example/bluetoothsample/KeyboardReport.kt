package com.example.bluetoothsample

import android.view.KeyEvent

@JvmInline
value class KeyboardReport(
    val bytes: ByteArray = ByteArray(3) { 0 }
) {

    fun setModifiers(list: List<Int>) {
        val value = list.fold(0) { i,j -> i or j } and 0b1111_1111
        bytes[0] = value.toByte()
    }

    var key1: Byte
        get() = bytes[2]
        set(value) { bytes[2] = value }

    fun reset() = bytes.fill(0)

    companion object {
        const val ID = 8

        // see https://developer.apple.com/library/archive/technotes/tn2450/_index.html
        // see also https://gist.github.com/MightyPork/6da26e382a7ad91b5496ee55fdc73db2
        // see also https://learn.microsoft.com/fr-fr/windows-hardware/drivers/hid/

        //qwerty
        /*val KeyEventMap = mapOf(
            KeyEvent.KEYCODE_A to 4,
            KeyEvent.KEYCODE_B to 5,
            KeyEvent.KEYCODE_C to 6,
            KeyEvent.KEYCODE_D to 7,
            KeyEvent.KEYCODE_E to 8,
            KeyEvent.KEYCODE_F to 9,
            KeyEvent.KEYCODE_G to 10,
            KeyEvent.KEYCODE_H to 11,
            KeyEvent.KEYCODE_I to 12,
            KeyEvent.KEYCODE_J to 13,
            KeyEvent.KEYCODE_K to 14,
            KeyEvent.KEYCODE_L to 15,
            KeyEvent.KEYCODE_M to 16,
            KeyEvent.KEYCODE_N to 17,
            KeyEvent.KEYCODE_O to 18,
            KeyEvent.KEYCODE_P to 19,
            KeyEvent.KEYCODE_Q to 20,
            KeyEvent.KEYCODE_R to 21,
            KeyEvent.KEYCODE_S to 22,
            KeyEvent.KEYCODE_T to 23,
            KeyEvent.KEYCODE_U to 24,
            KeyEvent.KEYCODE_V to 25,
            KeyEvent.KEYCODE_W to 26,
            KeyEvent.KEYCODE_X to 27,
            KeyEvent.KEYCODE_Y to 28,
            KeyEvent.KEYCODE_Z to 29,


            KeyEvent.KEYCODE_1 to 30,
            KeyEvent.KEYCODE_2 to 31,
            KeyEvent.KEYCODE_3 to 32,
            KeyEvent.KEYCODE_4 to 33,
            KeyEvent.KEYCODE_5 to 34,
            KeyEvent.KEYCODE_6 to 35,
            KeyEvent.KEYCODE_7 to 36,
            KeyEvent.KEYCODE_8 to 37,
            KeyEvent.KEYCODE_9 to 38,
            KeyEvent.KEYCODE_0 to 39,

            KeyEvent.KEYCODE_F1 to 58,
            KeyEvent.KEYCODE_F2 to 59,
            KeyEvent.KEYCODE_F3 to 60,
            KeyEvent.KEYCODE_F4 to 61,
            KeyEvent.KEYCODE_F5 to 62,
            KeyEvent.KEYCODE_F6 to 63,
            KeyEvent.KEYCODE_F7 to 64,
            KeyEvent.KEYCODE_F8 to 65,
            KeyEvent.KEYCODE_F9 to 66,
            KeyEvent.KEYCODE_F10 to 67,
            KeyEvent.KEYCODE_F11 to 68,
            KeyEvent.KEYCODE_F12 to 69,

            KeyEvent.KEYCODE_ENTER to 40,
            KeyEvent.KEYCODE_ESCAPE to 41,
            KeyEvent.KEYCODE_DEL to 42,
            KeyEvent.KEYCODE_TAB to 43,
            KeyEvent.KEYCODE_SPACE to 44,
            KeyEvent.KEYCODE_MINUS to 45,
            KeyEvent.KEYCODE_EQUALS to 46,
            KeyEvent.KEYCODE_LEFT_BRACKET to 47,
            KeyEvent.KEYCODE_RIGHT_BRACKET to 48,
            KeyEvent.KEYCODE_BACKSLASH to 49,
            KeyEvent.KEYCODE_POUND to 50,
            KeyEvent.KEYCODE_SEMICOLON to 51,
            KeyEvent.KEYCODE_APOSTROPHE to 52,
            KeyEvent.KEYCODE_GRAVE to 53,
            KeyEvent.KEYCODE_COMMA to 54,
            KeyEvent.KEYCODE_PERIOD to 55,
            KeyEvent.KEYCODE_SLASH to 56,

            KeyEvent.KEYCODE_SCROLL_LOCK to 71,
            KeyEvent.KEYCODE_INSERT to 73,
            KeyEvent.KEYCODE_HOME to 74,
            KeyEvent.KEYCODE_PAGE_UP to 75,
            KeyEvent.KEYCODE_FORWARD_DEL to 76,
            KeyEvent.KEYCODE_MOVE_END to 77,
            KeyEvent.KEYCODE_PAGE_DOWN to 78,
            KeyEvent.KEYCODE_NUM_LOCK to 83,

            KeyEvent.KEYCODE_DPAD_RIGHT to 79,
            KeyEvent.KEYCODE_DPAD_LEFT to 80,
            KeyEvent.KEYCODE_DPAD_DOWN to 81,
            KeyEvent.KEYCODE_DPAD_UP to 82,

            // special key for FR MAC keyboard '@', spend a while to figure it out
            KeyEvent.KEYCODE_AT to 100
        )
*/

//  Alt Gr azerty                   €                                                                    ~  #  {  [  |  `  \  ^  @    ' '  ]  }  ¤
//   Shift azerty       Q  B  C  D  E  F  G  H  I  J  K  L  ?  N  O  P  A  R  S  T  U  V  Z  X  Y  W  1  2  3  4  5  6  7  8  9  0    ' '  °  +  ¨  £  µ  No fr  M  %  NONE  .  /  §    >
//         azerty       q  b  c  d  e  f  g  h  i  j  k  l  ,  n  o  p  a  r  s  t  u  v  z  x  y  w  &  é  "  '  (  -  è  _  ç  à    ' '  )  =  ^  $  *  No fr  m  ù   ²    ;  :  !    <
//         qwerty       a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z  1  2  3  4  5  6  7  8  9  0    ' '  -  =  [  ]  \  No US  ;  '   `    ,  .  /   No US
//       scancode       4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,  44, 45,46,47,48,49,  50,  51,52, 53,  54,55,56,  100};


        //azerty
        val KeyEventMap = mapOf(
                KeyEvent.KEYCODE_Q to 4,
            KeyEvent.KEYCODE_B to 5,
            KeyEvent.KEYCODE_C to 6,
            KeyEvent.KEYCODE_D to 7,
            KeyEvent.KEYCODE_E to 8,
            KeyEvent.KEYCODE_F to 9,
            KeyEvent.KEYCODE_G to 10,
            KeyEvent.KEYCODE_H to 11,
            KeyEvent.KEYCODE_I to 12,
            KeyEvent.KEYCODE_J to 13,
            KeyEvent.KEYCODE_K to 14,
            KeyEvent.KEYCODE_L to 15,
                KeyEvent.KEYCODE_COMMA to 16,
            KeyEvent.KEYCODE_N to 17,
            KeyEvent.KEYCODE_O to 18,
            KeyEvent.KEYCODE_P to 19,
                KeyEvent.KEYCODE_A to 20,
            KeyEvent.KEYCODE_R to 21,
            KeyEvent.KEYCODE_S to 22,
            KeyEvent.KEYCODE_T to 23,
            KeyEvent.KEYCODE_U to 24,
            KeyEvent.KEYCODE_V to 25,
                KeyEvent.KEYCODE_Z to 26,
            KeyEvent.KEYCODE_X to 27,
            KeyEvent.KEYCODE_Y to 28,
                KeyEvent.KEYCODE_W to 29,


            KeyEvent.KEYCODE_1 to 30,
            KeyEvent.KEYCODE_2 to 31,
            KeyEvent.KEYCODE_3 to 32,
            KeyEvent.KEYCODE_4 to 33,
            KeyEvent.KEYCODE_5 to 34,
            KeyEvent.KEYCODE_6 to 35,
            KeyEvent.KEYCODE_7 to 36,
            KeyEvent.KEYCODE_8 to 37,
            KeyEvent.KEYCODE_9 to 38,
            KeyEvent.KEYCODE_0 to 39,

            KeyEvent.KEYCODE_SPACE to 44,

            KeyEvent.KEYCODE_F1 to 58,
            KeyEvent.KEYCODE_F2 to 59,
            KeyEvent.KEYCODE_F3 to 60,
            KeyEvent.KEYCODE_F4 to 61,
            KeyEvent.KEYCODE_F5 to 62,
            KeyEvent.KEYCODE_F6 to 63,
            KeyEvent.KEYCODE_F7 to 64,
            KeyEvent.KEYCODE_F8 to 65,
            KeyEvent.KEYCODE_F9 to 66,
            KeyEvent.KEYCODE_F10 to 67,
            KeyEvent.KEYCODE_F11 to 68,
            KeyEvent.KEYCODE_F12 to 69,

            //TBC
            //KeyEvent.KEYCODE_ENTER to 40,
            //KeyEvent.KEYCODE_ESCAPE to 41,
            //KeyEvent.KEYCODE_DEL to 42,
            //KeyEvent.KEYCODE_TAB to 43,
            //KeyEvent.KEYCODE_SPACE to 44,
                KeyEvent.KEYCODE_MINUS to 35,
            //KeyEvent.KEYCODE_EQUALS to 46,
                KeyEvent.KEYCODE_LEFT_BRACKET to 34,
                KeyEvent.KEYCODE_RIGHT_BRACKET to 45,
            //KeyEvent.KEYCODE_BACKSLASH to 49,
            //KeyEvent.KEYCODE_POUND to 50,
                KeyEvent.KEYCODE_M to 51,
            //KeyEvent.KEYCODE_APOSTROPHE to 52,
            //KeyEvent.KEYCODE_GRAVE to 53,
                KeyEvent.KEYCODE_SEMICOLON to 54,
            //KeyEvent.KEYCODE_PERIOD to 55,
            //KeyEvent.KEYCODE_SLASH to 56,

            /*KeyEvent.KEYCODE_SCROLL_LOCK to 71,
            KeyEvent.KEYCODE_INSERT to 73,
            KeyEvent.KEYCODE_HOME to 74,
            KeyEvent.KEYCODE_PAGE_UP to 75,
            KeyEvent.KEYCODE_FORWARD_DEL to 76,
            KeyEvent.KEYCODE_MOVE_END to 77,
            KeyEvent.KEYCODE_PAGE_DOWN to 78,
            KeyEvent.KEYCODE_NUM_LOCK to 83,

            KeyEvent.KEYCODE_DPAD_RIGHT to 79,
            KeyEvent.KEYCODE_DPAD_LEFT to 80,
            KeyEvent.KEYCODE_DPAD_DOWN to 81,
            KeyEvent.KEYCODE_DPAD_UP to 82,

            // special key for FR MAC keyboard '@', spend a while to figure it out
            KeyEvent.KEYCODE_AT to 100,*/
        )

    }
}