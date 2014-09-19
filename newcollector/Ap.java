package com.autonavi.collect.resolve.newcollector;

public class Ap {

	public String mac = "";

	public int rssi = 0;

	public String ssid = "";

	@Override
	public String toString() {
		return "mac=" + mac + "$rssi=" + rssi + "$ssid=" + ssid;
	}

	public String getAp() {
		return mac + ";" + ssid + ";" + rssi;
	}
}
