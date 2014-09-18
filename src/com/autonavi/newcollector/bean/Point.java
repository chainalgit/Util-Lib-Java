package com.autonavi.newcollector.bean;

public class Point {
	
	public short length;
	
	public int time;
	
	public byte type;
	
	public Position pos = null;
	
	public Gtw gtw = null;
	
	public Cdma cdma = null;
	
	public Wifi wifi = null;

	public SensorData sensor = null;
	
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append("length=" + length + ",time=" + time + ",type=" + type+",");
		
		
		switch (type){
		
		case 1:
			result.append(pos.toString());
			break;
		case 2:
			result.append(gtw.toString());
			break;
		case 4:
			result.append(cdma.toString());
			break;
		case 8:
			result.append(wifi.toString());
			break;
		
		}
		return result.toString();	
	
	}
	
	public String toStringV4() {
		StringBuffer result = new StringBuffer();
		
		result.append("length=" + length + ",time=" + time + ",type=" + type+",");
		
		
		switch (type){
		
		case 1:
			result.append(pos.toStringV4());
			break;
		case 2:
			result.append(gtw.toStringV4());
			break;
		case 4:
			result.append(cdma.toStringV4());
			break;
		case 8:
			result.append(wifi.toString());
			break;
		case 3:
			result.append(sensor.toString());
			break;
		
		}
		return result.toString();	
	
	}	
}
