package com.autonavi.collect.resolve.newcollector;

public class GsmInfo implements Cloneable {
	public int mcc = 0;
	public int mnc = 0;
	public int lac = 0;
	public int cid = 0;
	public int rssi = 0;

	public boolean isValid() {
		if (mcc <= 0)
			return false;
		if (lac <= 0 || lac >= 65535)
			return false;
		if (cid <= 0 || cid == 65535 || cid >= 268435455)
			return false;
		if (mnc < 0 || mnc >= 100)
			return false;

		return true;
	}
	
	public GsmInfo clone() {
		GsmInfo clone = null;
		try {
			clone = (GsmInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
