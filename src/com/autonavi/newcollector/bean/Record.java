package com.autonavi.newcollector.bean;
import java.util.ArrayList;


public class Record {

	public byte version;

	public short length;
	
	public String imei;
	
	public String imsi;
	
	public String mac;
	
	public short mcc;
	
	public short mnc;
	
	public byte radioType;
	
	public String deviceName;
	
	public String sourceName;
	
	public short pointNum;
	
	public ArrayList<Point> listPoint = new ArrayList<Point>();

	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		 result.append("version=" + version + "&length=" + length + "&imei="
				+ imei + "&imsi=" + imsi + "&mac=" + mac + "&mcc=" + mcc
				+ "&mnc=" + mnc + "&radioType=" + radioType + "&deviceName="
				+ deviceName + "&sourceName=" + sourceName + "&pointNum="
				+ pointNum + "&");
		 
		 
		 for(int i=0 ;i<listPoint.size();i++){
			 
			 if(i != (listPoint.size()-1)){
				 if(version == Const.V4 || version == Const.V41) {
					 result.append(listPoint.get(i).toStringV4()+"**");
				 } else {
					 result.append(listPoint.get(i)+"**");
				 }
			 }else{
				 if(version == Const.V4 || version == Const.V41) {
					 result.append(listPoint.get(i).toStringV4());
				 } else {
					 result.append(listPoint.get(i));
				 }
			 }
		 }
		 
		 return result.toString();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
