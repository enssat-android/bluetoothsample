package com.example.bluetoothsample

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

data class Shortcut( val shortcutKey: Int,
                     val modifiers: List<KeyModifier> = emptyList(),
                     val releaseModifiers: Boolean = true,) {

    companion object {
        const val LEFT_CONTROL: KeyModifier = 0b1
        const val LEFT_ALT: KeyModifier = 0b100
        const val LEFT_GUI: KeyModifier = 0b1000
        const val RIGHT_ALT: KeyModifier = 0b100_0000
        const val RIGHT_GUI: KeyModifier = 0b1000_0000
    }
}


@Composable
fun BluetoothUiConnection(bluetoothController: BluetoothController) {
    val context = LocalContext.current
    var isButtonInitVisible by remember { mutableStateOf(true) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    }
        if (isButtonInitVisible) {
            Button(
                onClick = { bluetoothController.init(context.applicationContext)
                            isButtonInitVisible = false }
            ) {
                Text(text = "Initialize Bluetooth device with HID profile")
            }
        }
        else {

            val btOn = bluetoothController.status is BluetoothController.Status.Connected
            if(!btOn) {
                Button(
                    onClick = { context.startActivity(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)) }
                ) {
                    Text(text = "discover and Pair new devices")
                }
            }
            val waiting = bluetoothController.status is BluetoothController.Status.Waiting
            val disconnected = bluetoothController.status is BluetoothController.Status.Disconnected
            if (waiting or disconnected) {
                Button(
                    onClick = { bluetoothController.connectHost() }
                ) {
                    Text(text = "Bluetooth connect to host")
                }
            }
            Text(
                //modifier=Modifier.align(Alignment.CenterHorizontally),
                text = bluetoothController.status.display,

            )

            Icon(
                if (btOn) Icons.Default.Bluetooth else Icons.Default.BluetoothDisabled,
                "bluetooth",
                modifier = Modifier.size(100.dp),
                tint = if (btOn) Color.Blue else Color.Black,
            )
            if (btOn) {
                Button(
                    onClick = { bluetoothController.release()}
                ) {
                    Text(text = "Bluetooth disconnect from host")
                }
            }


        }
}


@Composable
fun BluetoothDesk(bluetoothController: BluetoothController) {

        val connected = bluetoothController.status as? BluetoothController.Status.Connected ?: return

        val context = LocalContext.current
        val keyboardSender = KeyboardSender(connected.btHidDevice, connected.hostDevice)


        fun press(shortcut: Shortcut, releaseModifiers: Boolean = true) {
            @SuppressLint("MissingPermission")
            val result = keyboardSender.sendKeyboard(shortcut.shortcutKey, shortcut.modifiers, releaseModifiers)
            if (!result) Toast.makeText(context,"can't find keymap for $shortcut",Toast.LENGTH_LONG).show()
        }

        Column( modifier = Modifier.fillMaxWidth().padding(20.dp)) {

            Spacer(modifier = Modifier.size(20.dp))
            Text("Slide Desk")
            Spacer(modifier = Modifier.size(10.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(onClick = { press(Shortcut(KeyEvent.KEYCODE_DPAD_LEFT)) }) {
                    Text("<-")
                }
                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = { press(Shortcut(KeyEvent.KEYCODE_DPAD_RIGHT)) }) {
                    Text("->")
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(onClick = {press(Shortcut(KeyEvent.KEYCODE_DPAD_LEFT,listOf(Shortcut.LEFT_ALT, Shortcut.LEFT_GUI)) )}) {
                    Text("<- tab")
                }
                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {press(Shortcut( KeyEvent.KEYCODE_DPAD_RIGHT,listOf(Shortcut.RIGHT_ALT, Shortcut.RIGHT_GUI)))}) {
                    Text("tab ->")
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(onClick = {press(Shortcut(KeyEvent.KEYCODE_F,listOf(Shortcut.LEFT_CONTROL, Shortcut.LEFT_GUI)))}) {
                    Text("full screen")
                }
            }
        }
}
