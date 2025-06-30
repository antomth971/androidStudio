package com.farouk.beewireader.reader;

import android.Manifest;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.UUID;

public class BeeWiSmartBtCallback extends BluetoothGattCallback {
    private static final String SERVICE_UUID = "a8b3fa04-4834-4051-89d0-3de95cddd318";
    private static final String CHARACTERISTIC_UUID = "a8b3fb43-4834-4051-89d0-3de95cddd318";
    private String[] data;
    private final Object syncObj;
    private Context context;
    public BeeWiSmartBtCallback(Context context, String[] data, Object syncObj) {
        this.data = data;
        this.syncObj = syncObj;
        this.context = context;
    }

    @Override
    public void onConnectionStateChange(final BluetoothGatt gatt, final int status, final int newState) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            gatt.discoverServices();
        }
    }

    @Override
    public void onServicesDiscovered(final BluetoothGatt gatt, final int status) {
        BluetoothGattService service = gatt.getService(UUID.fromString(SERVICE_UUID));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(CHARACTERISTIC_UUID));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            gatt.readCharacteristic(characteristic);
        }
    }

    @Override
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        byte[] bytes = characteristic.getValue();
        for (int i = 0; i < bytes.length; i++) {
            data[i] = String.format("%02X", bytes[i]);
        }
        System.out.println();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            gatt.disconnect();
            synchronized (syncObj) {
                syncObj.notify();
            }
        }
    }
}
