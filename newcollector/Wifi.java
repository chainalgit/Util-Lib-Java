package com.autonavi.collect.resolve.newcollector;

import java.util.ArrayList;

public class Wifi {

	public int wifiNum = 0;

	public ArrayList<Ap> listAp = new ArrayList<Ap>();

	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();

		result.append("wifiNum=" + wifiNum + ";listAP=");

		for (int i = 0; i < listAp.size(); i++) {
			if (i != (listAp.size() - 1)) {

				result.append(listAp.get(i).toString() + "*");

			} else {

				result.append(listAp.get(i).toString());
			}
		}

		return result.toString();
	}

	public String getListAp() {
		if (listAp.size() == 0)
			return "";

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < listAp.size(); i++) {
			sb.append(listAp.get(i).getAp() + "*");
		}

		return sb.toString().substring(0, sb.length() - 1);
	}
}
