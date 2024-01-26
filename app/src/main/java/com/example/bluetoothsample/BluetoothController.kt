package com.example.bluetoothsample

import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@SuppressLint("MissingPermission")
class BluetoothController(val autoPairFlag: Boolean = false) {
    companion object {
        const val TAG = "BluetoothController"

        private val sdpRecord by lazy { BluetoothHidDeviceAppSdpSettings(
                                        "Pixel HID1",
                                    "Mobile BController",
                                      "ENSSAT",
                                        BluetoothHidDevice.SUBCLASS1_COMBO,
                                        DescriptorCollection.MOUSE_KEYBOARD_COMBO
                                        )
        }

        private fun Int.toState(): String = when(this) {
            BluetoothProfile.STATE_CONNECTING -> "CONNECTING"
            BluetoothProfile.STATE_CONNECTED -> "CONNECTED"
            BluetoothProfile.STATE_DISCONNECTING -> "DISCONNECTING"
            BluetoothProfile.STATE_DISCONNECTED -> "DISCONNECTED"
            else -> this.toString()
        }
    }

    sealed class Status(val display: String) {
        class Disconnected(var btHidDevice: BluetoothHidDevice?):Status("Disconnected")
        class Initialized(val btHidDevice: BluetoothHidDevice): Status("Initialized")
        class Waiting(val btHidDevice: BluetoothHidDevice, val pluggedDevice: BluetoothDevice): Status("Waiting for ${pluggedDevice.name}")
        class Connected(val btHidDevice: BluetoothHidDevice, val hostDevice: BluetoothDevice): Status("Connected to ${hostDevice.name}")
    }

    var status: Status by mutableStateOf(Status.Disconnected(null))

    private lateinit var bluetoothManager: BluetoothManager
    private val executor: Executor = Executors.newSingleThreadExecutor()
    private var btHidDevice: BluetoothHidDevice? = null
    private var mpluggedDevice: BluetoothDevice? = null
    private var hostDevice: BluetoothDevice? = null

    val btAppCallback = object: BluetoothHidDevice.Callback() {

        override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
            Log.d(TAG, "onAppStatusChanged $pluggedDevice, $registered")
            super.onAppStatusChanged(pluggedDevice, registered)
            if (registered) {
                val pairedDevices = btHidDevice?.getDevicesMatchingConnectionStates(
                    intArrayOf(BluetoothProfile.STATE_CONNECTING, BluetoothProfile.STATE_CONNECTED, BluetoothProfile.STATE_DISCONNECTED, BluetoothProfile.STATE_DISCONNECTING)
                )
                pairedDevices?.forEach {
                    Log.d(TAG, "Paired devices : $it: ${it.name}")
                }
                mpluggedDevice = pluggedDevice
                if (pluggedDevice != null) status = Status.Waiting(btHidDevice!!, pluggedDevice)

                if (autoPairFlag) {
                    if (pluggedDevice != null && btHidDevice?.getConnectionState(pluggedDevice) == BluetoothProfile.STATE_DISCONNECTED) {
                        btHidDevice?.connect(pluggedDevice)
                    } else {
                        pairedDevices?.firstOrNull()?.let {
                            val pairedDState = btHidDevice?.getConnectionState(it)
                            Log.d("paired d", pairedDState.toString())
                            if (pairedDState == BluetoothProfile.STATE_DISCONNECTED) {
                                btHidDevice?.connect(it)
                            }
                        }
                    }
                }
            }
        }

        override fun onConnectionStateChanged(device: BluetoothDevice, state: Int) {
            super.onConnectionStateChanged(device, state)
            Log.i(TAG, "Connection state ${state.toState()}")
            if (state == BluetoothProfile.STATE_CONNECTED) {
                hostDevice = device
                btHidDevice?.connect(device)
                status = Status.Connected(btHidDevice!!, device)
            } else {
                hostDevice = null
                if (state == BluetoothProfile.STATE_DISCONNECTED) {
                    btHidDevice?.also { status = Status.Waiting(it, device) }
                }
            }
        }

        override fun onSetReport(device: BluetoothDevice?, type: Byte, id: Byte, data: ByteArray?) {
            super.onSetReport(device, type, id, data)
            Log.i("setreport","$device / $type / $id / $data")
        }

        override fun onGetReport(device: BluetoothDevice?, type: Byte, id: Byte, bufferSize: Int) {
            super.onGetReport(device, type, id, bufferSize)
            if (type == BluetoothHidDevice.REPORT_TYPE_FEATURE) {
                val report: ByteArray = ByteArray(1) { 0 }
                val reportID = 6.toByte()
                val report_success = btHidDevice?.replyReport(device, type, reportID, report)
                Log.i("report success flag:",report_success.toString())
            }
        }
    }

    private val bluetoothProfileServiceListener = object: BluetoothProfile.ServiceListener {
        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
            Log.i(TAG, "Connected to service")
            if (profile != BluetoothProfile.HID_DEVICE) {
                Log.e(TAG, "oups! wrong profile $profile")}
            else {
                val btHid = proxy as? BluetoothHidDevice
                if (btHid == null) {
                    Log.e(TAG, "Oups! Proxy received but it's not BluetoothHidDevice")
                } else {
                    btHidDevice = btHid
                    status = Status.Initialized(btHid)
                    btHid.registerApp(sdpRecord, null, null, executor, btAppCallback)

                }
            }
        }

        override fun onServiceDisconnected(profile: Int) {
            Log.i(TAG, "Service disconnected!")
            if (profile == BluetoothProfile.HID_DEVICE) {
                btHidDevice = null
                status = Status.Disconnected(null)
            }
        }
    }

    fun init(context: Context) {
        bluetoothManager = context.getSystemService(BluetoothManager::class.java)
        if (btHidDevice != null) { Log.d(TAG, "already initialised"); return }
        val btAdapter = bluetoothManager.adapter
        btAdapter.getProfileProxy(context, bluetoothProfileServiceListener, BluetoothProfile.HID_DEVICE)
    }

    fun connectHost() {
        val waiting = status as? Status.Waiting ?: return
        btHidDevice?.connect(waiting.pluggedDevice)
    }

    fun release() {
        btHidDevice?.disconnect(hostDevice)
        status = Status.Disconnected(btHidDevice)
    }
}