package com.autonavi.collect.resolve.newcollector;

import java.util.ArrayList;

public class Record {

	public int version;

	public int length;

	public String imei;

	public String imsi;

	public String mac;

	public int mcc;

	public int mnc;

	public int radioType;

	public String deviceName;

	public String sourceName ; 

	public int pointNum;

	public ArrayList<Point> listPoint = new ArrayList<Point>();

	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();

		result.append("version=" + version + "&length=" + length + "&imei="
				+ imei + "&imsi=" + imsi + "&mac=" + mac + "&mcc=" + mcc
				+ "&mnc=" + mnc + "&radioType=" + radioType + "&deviceName="
				+ deviceName + "&sourceName=" + sourceName + "&pointNum="
				+ pointNum + "&");

		for (int i = 0; i < listPoint.size(); i++) {

			if(i != (listPoint.size()-1)){
				 if(version == 4 ||version == 41) {
					 result.append(listPoint.get(i).toStringV4()+"**");
				 } else {
					 result.append(listPoint.get(i)+"**");
				 }
			 }else{
				 if(version == 4||version == 41) {
					 result.append(listPoint.get(i).toStringV4());
				 } else {
					 result.append(listPoint.get(i));
				 }
			 }
		}

		return result.toString();
	}

}
