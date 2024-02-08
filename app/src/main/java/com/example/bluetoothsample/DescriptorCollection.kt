package com.example.bluetoothsample

object DescriptorCollection {

    //https://www.usb.org/sites/default/files/hid1_11.pdf

    val MOUSE_KEYBOARD_COMBO = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)

        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x02.toByte(),                         //       COLLECTION (Logical)

        0x85.toByte(), 0x04.toByte(),                         //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x16.toByte(), 0x01.toByte(), 0xf8.toByte(),           //     LOGICAL_MINIMUM (-2047)
        0x26.toByte(), 0xff.toByte(), 0x07.toByte(),           //     LOGICAL_MAXIMUM (2047)
        0x75.toByte(), 0x10.toByte(),                         //     REPORT_SIZE (16)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)

        0xa1.toByte(), 0x02.toByte(),        //         COLLECTION (Logical)
        0x85.toByte(), 0x06.toByte(),        //         REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)


        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        //0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x38.toByte(),        //         USAGE (Wheel)

        0x15.toByte(), 0x81.toByte(),        //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),        //         LOGICAL_MAXIMUM (127)
        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x08.toByte(),        //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0xc0.toByte(),              //       END_COLLECTION

        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)
        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)

        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x04.toByte(),        //         REPORT_SIZE (4)
        0xb1.toByte(), 0x03.toByte(),        //         FEATURE (Cnst,Var,Abs)


        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x05.toByte(), 0x0c.toByte(),        //         USAGE_PAGE (Consumer Devices)
        0x0a.toByte(), 0x38.toByte(), 0x02.toByte(),  //         USAGE (AC Pan)

        0x15.toByte(), 0x81.toByte(),        //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),        //         LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),        //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),        //         INPUT (Data,Var,Rel)
        0xc0.toByte(),              //       END_COLLECTION
        0xc0.toByte(),              //       END_COLLECTION

        0xc0.toByte(),                               //   END_COLLECTION
        0xc0.toByte(),                                //END_COLLECTION

        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)

        0x09.toByte(), 0x06.toByte(),                         // Usage (Keyboard)
        0xA1.toByte(), 0x01.toByte(),                         // Collection (Application)
        0x85.toByte(), 0x08.toByte(),                           //   REPORT_ID (Keyboard)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key Codes)
        0x19.toByte(), 0xe0.toByte(),                         //     Usage Minimum (224)
        0x29.toByte(), 0xe7.toByte(),                         //     Usage Maximum (231)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x01.toByte(),                         //     Logical Maximum (1)
        0x75.toByte(), 0x01.toByte(),                         //     Report Size (1)
        0x95.toByte(), 0x08.toByte(),                         //     Report Count (8)
        0x81.toByte(), 0x02.toByte(),                         //     Input (Data, Variable, Absolute)

        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x81.toByte(), 0x01.toByte(),                         //     Input (Constant) reserved byte(1)


        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x65.toByte(),                         //     Logical Maximum (101)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key codes)
        0x19.toByte(), 0x00.toByte(),                         //     Usage Minimum (0)
        0x29.toByte(), 0x65.toByte(),                         //     Usage Maximum (101)
        0x81.toByte(), 0x00.toByte(),                         //     Input (Data, Array) Key array(6 bytes)
        0xc0.toByte()                               // End Collection (Application)
    )
}