package com.autonavi.collect.resolve.newcollector;

public class Neigh {

	public int lac = 0;

	public int cid = 0;

	public int rssi = 0;

	@Override
	public String toString() {
		return "Neigh=lac=" + lac + "$cid=" + cid + "$rssi=" + rssi;
	}

	public String getNeigh() {
		return lac + ";" + cid + ";" + rssi;
	}
}
