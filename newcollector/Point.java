package com.autonavi.collect.resolve.newcollector;


public class Point {

	public int length;

	public int time = 0;

	public int type;

	public Position pos = null;

	public Gtw gtw = null;

	public Cdma cdma = null;

	public Wifi wifi = null;

	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();

		result.append("length=" + length + ",time=" + time + ",type=" + type
				+ ",");

		switch (type) {

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

}
