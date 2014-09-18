package com.autonavi.newcollector.bean;

import java.util.ArrayList;

public class SensorData {

	public byte num;
	
	public ArrayList<SensorInfo> sensors = new ArrayList<SensorInfo>();
	
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append("sensorNum=" + num +";listSensor=");
		
		for(int i = 0; i < sensors.size(); i++) {
			if(i != (sensors.size() -1)) {
				result.append(sensors.get(i).toString()+"*");
			}else{				
				result.append(sensors.get(i).toString());
			}
		}
		
		return result.toString();
	}
}
