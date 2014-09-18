package com.autonavi.newcollector.bean;

public class Cdma {
	
	public int lon;
	
	public int lat;
	
	public short sid;
	
	public short nid;
	
	public int bsid;
	
	//public byte criArea;
	
	public byte rssi;

	@Override
	public String toString() {
		return "Cdma=lon=" + lon + ";lat=" + lat + ";sid=" + sid + ";nid="
				+ nid + ";bsid=" + bsid + ";rssi=" + rssi ;
	}

	public String toStringV4() {
		return "Cdma=lon=" + lon + ";lat=" + lat + ";sid=" + sid + ";nid="
				+ nid + ";bsid=" + bsid 
				//+ ";criArea=" + criArea
				+ ";rssi=" + rssi ;
	}
	
}
