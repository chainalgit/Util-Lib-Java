package com.autonavi.collect.resolve.newcollector;

import java.util.ArrayList;

public class Gtw {

	public int lac = 0;

	public int cid = 0;

	public int rssi = 0;

	public int neighNum = 0;

	public ArrayList<Neigh> listNeigh = new ArrayList<Neigh>();

	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();

		result.append("gsm=lac=" + lac + ";cid=" + cid + ";rssi=" + rssi
				+ ";neighNum=" + neighNum + ";listNeigh=");

		for (int i = 0; i < listNeigh.size(); i++) {

			if (i != (listNeigh.size() - 1)) {

				result.append(listNeigh.get(i).toString() + "*");

			} else {

				result.append(listNeigh.get(i).toString());

			}
		}

		return result.toString();
	}

	public String getNeigh() {
		if(listNeigh.size() == 0)
			return "";
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < listNeigh.size(); i++) {
			sb.append(listNeigh.get(i).getNeigh() + "*");
		}

		return sb.toString().substring(0, sb.length() - 1);
	}
}
