package com.autonavi.collect.resolve.newcollector;

public class Cdma {

	public int lon = 0;

	public int lat = 0;

	public int sid = 0;

	public int nid = 0;

	public int bsid = 0;

	public int rssi = 0;

	@Override
	public String toString() {
		return "Cdma=lon=" + lon + ";lat=" + lat + ";sid=" + sid + ";nid="
				+ nid + ";bsid=" + bsid + ";rssi=" + rssi;
	}

}
