package com.autonavi.newcollector.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.autonavi.newcollector.bean.Ap;
import com.autonavi.newcollector.bean.Cdma;
import com.autonavi.newcollector.bean.Const;
import com.autonavi.newcollector.bean.Ea;
import com.autonavi.newcollector.bean.Gtw;
import com.autonavi.newcollector.bean.Neigh;
import com.autonavi.newcollector.bean.Point;
import com.autonavi.newcollector.bean.Position;
import com.autonavi.newcollector.bean.Record;
import com.autonavi.newcollector.bean.Wifi;
import com.autonavi.newcollector.util.BinaryUtil;

public class MainProgram {

	private static final String[] PATH = 
		{ 
		"E:\\c\\workspace_1115\\OfflineCollectDataParser\\in\\1.txt"
		};

	private static ArrayList<Record> listRec = new ArrayList<Record>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int fileIndex = 0; fileIndex < PATH.length; fileIndex++) {

			File file = new File(PATH[fileIndex]);
			if (!file.exists() || file.isDirectory()) {
				return;
			}

			String temp = null;
			MainProgram mp = new MainProgram();

			listRec.clear();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				temp = br.readLine();
				while (temp != null && temp.length() > 0) {
					List<byte[]> listContent = BinaryUtil.generateContents(BinaryUtil
							.parseStrToByteArray(temp));

					for (int i = 0; i < listContent.size(); i++) {
						listRec.add(BinaryUtil.parseContent(listContent.get(i)));
					}

					temp = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

			FileWriter fw = null;
			try {
				File tmpFile = new File("out_" + PATH[fileIndex]);
				if(tmpFile.exists()) {
					tmpFile.delete();
				}
				
				fw = new FileWriter(PATH[fileIndex]+".out");

				for (int i = 0; i < listRec.size(); i++) {
					if (listRec.get(i).version != Const.V3 && listRec.get(i).version != Const.V4
							&& listRec.get(i).version != Const.V41) {
						continue;
					}

					fw.write(listRec.get(i).toString()+"\n");
					
//					fw.write("------------------\r\n");
//					fw.write(">>>>>>Record<<<<<<\r\n");
//					fw.write("------------------\r\n");
//					writeByteToFile(fw, "version", listRec.get(i).version);
//					writeShortToFile(fw, "length", listRec.get(i).length);
//					writeStrToFile(fw, "imei", listRec.get(i).imei);
//					writeStrToFile(fw, "imsi", listRec.get(i).imsi);
//					writeStrToFile(fw, "mac", listRec.get(i).mac);
//					writeShortToFile(fw, "mcc", listRec.get(i).mcc);
//					writeShortToFile(fw, "mnc", listRec.get(i).mnc);
//					writeByteToFile(fw, "radioType", listRec.get(i).radioType);
//					writeStrToFile(fw, "deviceName", listRec.get(i).deviceName);
//					writeStrToFile(fw, "sourceName", listRec.get(i).sourceName);
//					writeShortToFile(fw, "pointNum", listRec.get(i).pointNum);
//					for (int j = 0; j < listRec.get(i).listPoint.size(); j++) {
//						writeShortToFile(fw, "point-length",
//								listRec.get(i).listPoint.get(j).length);
//						writeIntToFile(fw, "point-time",
//								listRec.get(i).listPoint.get(j).time);
//						writeByteToFile(fw, "point-type",
//								listRec.get(i).listPoint.get(j).type);
//						if (listRec.get(i).listPoint.get(j).type == 1) { // position
//							writeIntToFile(fw, "point-pos-lon",
//									listRec.get(i).listPoint.get(j).pos.lon);
//							writeIntToFile(fw, "point-pos-lat",
//									listRec.get(i).listPoint.get(j).pos.lat);
//							writeIntToFile(fw, "point-pos-alt",
//									listRec.get(i).listPoint.get(j).pos.alt);
//							writeIntToFile(fw, "point-pos-radius",
//									listRec.get(i).listPoint.get(j).pos.radius);
//							writeIntToFile(fw, "point-pos-speed",
//									listRec.get(i).listPoint.get(j).pos.speed);
//							writeShortToFile(
//									fw,
//									"point-pos-direct",
//									listRec.get(i).listPoint.get(j).pos.direction);
//							writeByteToFile(
//									fw,
//									"point-pos-sateNum",
//									listRec.get(i).listPoint.get(j).pos.satellitesNum);
//							writeIntToFile(fw, "point-pos-signal",
//									listRec.get(i).listPoint.get(j).pos.signals);
//							writeShortToFile(fw, "point-pos-hdop",
//									listRec.get(i).listPoint.get(j).pos.hdop);
//							writeByteToFile(fw, "point-pos-eaNum",
//									listRec.get(i).listPoint.get(j).pos.eaNum);
//							temp = "";
//							for (int k = 0; k < listRec.get(i).listPoint.get(j).pos.listEa
//									.size(); k++) {
//								temp += "["
//										+ Byte.toString(listRec.get(i).listPoint
//												.get(j).pos.listEa.get(k).elevation)
//										+ ","
//										+ Short.toString(listRec.get(i).listPoint
//												.get(j).pos.listEa.get(k).azimuth)
//										+ "]";
//							}
//							writeStrToFile(fw, "point-pos-ea", temp);
//						} else if (listRec.get(i).listPoint.get(j).type == 2) { // gtw
//							writeIntToFile(fw, "point-gtw-lac",
//									listRec.get(i).listPoint.get(j).gtw.lac);
//							writeIntToFile(fw, "point-gtw-cid",
//									listRec.get(i).listPoint.get(j).gtw.cid);
//							writeByteToFile(fw, "point-gtw-rssi",
//									listRec.get(i).listPoint.get(j).gtw.rssi);
//							writeByteToFile(
//									fw,
//									"point-gtw-neighNum",
//									listRec.get(i).listPoint.get(j).gtw.neighNum);
//							temp = "";
//							for (int k = 0; k < listRec.get(i).listPoint.get(j).gtw.listNeigh
//									.size(); k++) {
//								temp += "["
//										+ Integer.toString(listRec.get(i).listPoint
//												.get(j).gtw.listNeigh.get(k).lac)
//										+ ","
//										+ Integer
//												.toString(listRec.get(i).listPoint
//														.get(j).gtw.listNeigh
//														.get(k).cid)
//										+ ","
//										+ Byte.toString(listRec.get(i).listPoint
//												.get(j).gtw.listNeigh.get(k).rssi)
//										+ "]";
//							}
//							writeStrToFile(fw, "point-gtw-neigh", temp);
//						} else if (listRec.get(i).listPoint.get(j).type == 4) { // cdma
//							writeIntToFile(fw, "point-cdma-lon",
//									listRec.get(i).listPoint.get(j).cdma.lon);
//							writeIntToFile(fw, "point-cdma-lat",
//									listRec.get(i).listPoint.get(j).cdma.lat);
//							writeShortToFile(fw, "point-cdma-sid",
//									listRec.get(i).listPoint.get(j).cdma.sid);
//							writeShortToFile(fw, "point-cdma-nid",
//									listRec.get(i).listPoint.get(j).cdma.nid);
//							writeIntToFile(fw, "point-cdma-bsid",
//									listRec.get(i).listPoint.get(j).cdma.bsid);
//							writeByteToFile(fw, "point-cdma-rssi",
//									listRec.get(i).listPoint.get(j).cdma.rssi);
//						} else if (listRec.get(i).listPoint.get(j).type == 8) { // wifi
//							writeByteToFile(
//									fw,
//									"point-wifi-wifiNum",
//									listRec.get(i).listPoint.get(j).wifi.wifiNum);
//							temp = "";
//							for (int k = 0; k < listRec.get(i).listPoint.get(j).wifi.listAp
//									.size(); k++) {
//								temp += "["
//										+ listRec.get(i).listPoint.get(j).wifi.listAp
//												.get(k).mac
//										+ ","
//										+ Short.toString(listRec.get(i).listPoint
//												.get(j).wifi.listAp.get(k).rssi)
//										+ ","
//										+ listRec.get(i).listPoint.get(j).wifi.listAp
//												.get(k).ssid + "]";
//							}
//							writeStrToFile(fw, "point-wifi-ap", temp);
//						}
//					}
				}

				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@SuppressWarnings("unused")
	private static void writeByteToFile(FileWriter fw, String tag, byte value)
			throws Exception {
		fw.write("[");
		fw.write(tag);
		fw.write("] ");
		fw.write(Byte.toString(value));
		fw.write("\r\n");
	}

	@SuppressWarnings("unused")
	private static void writeShortToFile(FileWriter fw, String tag, short value)
			throws Exception {
		fw.write("[");
		fw.write(tag);
		fw.write("] ");
		fw.write(Short.toString(value));
		fw.write("\r\n");
	}

	@SuppressWarnings("unused")
	private static void writeIntToFile(FileWriter fw, String tag, int value)
			throws Exception {
		fw.write("[");
		fw.write(tag);
		fw.write("] ");
		fw.write(Integer.toString(value));
		fw.write("\r\n");
	}

	@SuppressWarnings("unused")
	private static void writeStrToFile(FileWriter fw, String tag, String value)
			throws Exception {
		fw.write("[");
		fw.write(tag);
		fw.write("] ");
		fw.write(value);
		fw.write("\r\n");
	}
}
