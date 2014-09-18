package com.autonavi.newcollector.bean;
import java.util.ArrayList;


public class Gtw {
	
	public int lac;
	
	public int cid;
	
	public byte rssi;
	
	//public byte criArea;
	
	public byte neighNum;
	
	public ArrayList<Neigh> listNeigh = new ArrayList<Neigh>();

	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append( "gsm=lac=" + lac + ";cid=" + cid + ";rssi=" + rssi
				+ ";neighNum=" + neighNum + ";listNeigh=") ;
		
		for(int i=0;i<listNeigh.size();i++){
			
			if(i != (listNeigh.size()-1)){
				
				result.append(listNeigh.get(i).toString()+"*");

			}else{
				
				result.append(listNeigh.get(i).toString());

			}
		}
		
		
		
		return result.toString();
	}


	
	
	public String toStringV4() {
		
		StringBuffer result = new StringBuffer();
		
		result.append( "gsm=lac=" + lac + ";cid=" + cid + ";rssi=" + rssi
				//+";criArea=" + criArea
				+ ";neighNum=" + neighNum + ";listNeigh=") ;
		
		for(int i=0;i<listNeigh.size();i++){
			
			if(i != (listNeigh.size()-1)){
				
				result.append(listNeigh.get(i).toString()+"*");

			}else{
				
				result.append(listNeigh.get(i).toString());

			}
		}
		
		
		
		return result.toString();
	}
	
}
