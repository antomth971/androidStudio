package com.farouk.beewireader.reader;

public class SensorData {
    private double temperature;
    private int humidity;
    private int battery;
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getBattery() {
        return battery;
    }
    public void setBattery(int battery) {
        this.battery = battery;
    }

    public static SensorData parseSensorData(String[] bytes) {

        double temperature = Integer.valueOf(bytes[2]+bytes[1],16).shortValue() / 10.0;
        int humidity = Integer.parseInt(bytes[4], 16 );
        int battery = Integer.parseInt(bytes[9], 16 );

        SensorData sensorData = new SensorData();
        sensorData.setTemperature(temperature);
        sensorData.setHumidity(humidity);
        sensorData.setBattery(battery);
        return sensorData;
    }

}
