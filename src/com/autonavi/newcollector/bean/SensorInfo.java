package com.autonavi.newcollector.bean;

public class SensorInfo {

	public String name;
	
	public double max_range;
	
	public int min_delay;
	
	public double power;
	
	public double resolution;
	
	public String type;
	
	public String vendor;
	
	public int version;
	
	public String toString() {
		return "Name=" + name
				+ "$MaxRange=" + String.format("%.6f", max_range)
				+ "$MinDelay=" + min_delay
				+ "$Power=" + String.format("%.2f", power)
				+ "$Resolution=" + String.format("%.10f", resolution)
				+ "$Type=" + type
				+ "$Vendor=" + vendor
				+ "$Version=" + version;
	}
	
	public static String[] SENSOR_NAMES = {
		"TYPE_ACCELEROMETER",
		"TYPE_MAGNETIC_FIELD",
		"TYPE_ORIENTATION",
		"TYPE_GYROSCOPE",
		"TYPE_LIGHT",
		"TYPE_PRESSURE",
		"TYPE_TEMPERATURE",
		"TYPE_PROXIMITY",
		"TYPE_GRAVITY",
		"TYPE_LINEAR_ACCELERATION",
		"TYPE_ROTATION_VECTOR",
		"TYPE_RELATIVE_HUMIDITY",
		"TYPE_AMBIENT_TEMPERATURE",
		"TYPE_MAGNETIC_FIELD_UNCALIBRATED",
		"TYPE_GAME_ROTATION_VECTOR",
		"TYPE_GYROSCOPE_UNCALIBRATED",
		"TYPE_SIGNIFICANT_MOTION",
		"TYPE_STEP_DETECTOR",
		"TYPE_STEP_COUNTER",
		"TYPE_GEOMAGNETIC_ROTATION_VECTOR",
		"TYPE_HEART_RATE"
	};
}
