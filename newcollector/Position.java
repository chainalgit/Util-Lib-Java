package com.autonavi.collect.resolve.newcollector;

import java.util.ArrayList;


public class Position {
	
	public int lon;
	
	public int lat;
	
	public int alt;
	
	public int radius;
	
	public int speed;
	
	public short direction;
	
	public byte satellitesNum;
	
	public int signals;
	
	public short hdop;
	
	public byte eaNum;
	
	public ArrayList<Ea> listEa = new ArrayList<Ea>();
	
	public byte isMock;
	
	public byte isDrift;
	
	public long timeSys;
	
	public long timeGps;

	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append("Position=lon=" + lon + ";lat=" + lat + ";alt=" + alt
				+ ";radius=" + radius + ";speed=" + speed + ";direction="
				+ direction + ";satellitesNum=" + satellitesNum + ";signals="
				+ signals + ";hdop=" + hdop + ";eaNum=" + eaNum + ";listEa=");
		
		for(int i=0;i<listEa.size();i++){
			
			if(i != (listEa.size() -1)){
				
				result.append(listEa.get(i).toString()+"*");
			}else{
				
				result.append(listEa.get(i).toString());
			}
		}		
		
		return result.toString();	
		
	}

	public String toStringV4() {
		StringBuffer result = new StringBuffer();
		
		result.append("Position=lon=" + lon + ";lat=" + lat + ";alt=" + alt
				+ ";radius=" + radius + ";speed=" + speed + ";direction="
				+ direction + ";isMock=" + isMock + ";isDrift="
				+ isDrift + ";SysTime=" + timeSys + ";GpsTime=" + timeGps);
		System.out.println("========="+result.toString());
		return result.toString();	
	}	
}
