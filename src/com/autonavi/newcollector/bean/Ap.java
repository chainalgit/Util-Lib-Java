package com.autonavi.newcollector.bean;

public class Ap {
	
	public String mac;
	
	public short rssi;
	
	public String ssid;

	@Override
	public String toString() {
		return "mac=" + mac + "$rssi=" + rssi + "$ssid=" + ssid;
	}
	
	
	
	
}
