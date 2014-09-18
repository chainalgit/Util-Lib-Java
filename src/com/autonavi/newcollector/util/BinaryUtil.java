package com.autonavi.newcollector.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.autonavi.newcollector.bean.Ap;
import com.autonavi.newcollector.bean.Cdma;
import com.autonavi.newcollector.bean.Const;
import com.autonavi.newcollector.bean.Ea;
import com.autonavi.newcollector.bean.Gtw;
import com.autonavi.newcollector.bean.Neigh;
import com.autonavi.newcollector.bean.Point;
import com.autonavi.newcollector.bean.Position;
import com.autonavi.newcollector.bean.Record;
import com.autonavi.newcollector.bean.SensorData;
import com.autonavi.newcollector.bean.SensorInfo;
import com.autonavi.newcollector.bean.Wifi;

public class BinaryUtil {


	public  static byte[] parseStrToByteArray(String src) {

		char[] chrs = src.toCharArray();
		int len = src.length();
		if (len % 2 != 0) {
			System.out.println("len="+len);
			return null;
		}
		byte[] res = new byte[len / 2];
		for (int i = 0; i < len / 2; i++) {
			res[i] = (byte) (parseCharToHex(chrs[2 * i]) * 16 + parseCharToHex(chrs[2 * i + 1]));
		}		
		
		return res;
	}

	private static byte parseCharToHex(char chr) {
		switch (chr) {
		case '0':
			return 0x0;
		case '1':
			return 0x1;
		case '2':
			return 0x2;
		case '3':
			return 0x3;
		case '4':
			return 0x4;
		case '5':
			return 0x5;
		case '6':
			return 0x6;
		case '7':
			return 0x7;
		case '8':
			return 0x8;
		case '9':
			return 0x9;
		case 'a':
			return 0xa;
		case 'b':
			return 0xb;
		case 'c':
			return 0xc;
		case 'd':
			return 0xd;
		case 'e':
			return 0xe;
		case 'f':
			return 0xf;
		}

		return -1;
	}
	
	public static String parseBytesToHexStr(byte[] bytes) {
		
		StringBuilder sb = new StringBuilder();
		String tmp = null;
		
		if(bytes == null || bytes.length <= 0) {
			return null;
		}
		
		for(int i = 0; i < bytes.length; i++) {
			tmp = Integer.toHexString(0xff & bytes[i]);
			if(tmp.length() < 2) {
				sb.append("0");
			}
			sb.append(tmp);
		}
		return sb.toString();
	}

	public static List<byte[]> generateContents(byte[] srcBytes) {

		List<byte[]> listContents = new ArrayList<byte[]>();
		int index = 0;
		int length = 0;
		int maxLen = 0;

		if(srcBytes == null) {
			return listContents;
		}
		maxLen = srcBytes.length;
		
		while (index < maxLen) {
			// 获取length+content列表里的length
			length = bytesToInt(srcBytes, index);
			
			System.out.println("length:content,length="+length);

			index = index + 4;
			
			if(length > maxLen - index) { // 获取的content长度超过余下的所有字节长度
				System.out.println("line error.");
				break;
			}

			// 获取length+content列表里的content
			byte[] content_bytes = new byte[length];
			System.arraycopy(srcBytes, index, content_bytes, 0, length);
			listContents.add(content_bytes);

			System.out.println("content="+printBytes(content_bytes));
			
			index = index + length;
		}

		return listContents;
	}
	
	/***
	 * 压缩GZip
	 * 
	 * @param data
	 * @return
	 */
	protected static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***
	 * 解压GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	private static byte bytesToByte(byte[] bytes, int start) {
		return bytes[start];
	}

	private static short bytesToShort(byte[] bytes, int start) {
		int[] ints = new int[2];
		for (int i = 0; i < 2; i++) {
			ints[i] = bytes[i + start] >= 0 ? bytes[i + start] : (256 + bytes[i
					+ start]);
		}
		return (short) (ints[0] * 256 + ints[1]);
	}

	public static int bytesToInt(byte[] bytes, int start) {
		int[] ints = new int[4];
		for (int i = 0; i < 4; i++) {
			ints[i] = bytes[i + start] >= 0 ? bytes[i + start] : (256 + bytes[i
					+ start]);
		}
		return ints[0] * 256 * 256 * 256 + ints[1] * 256 * 256 + ints[2] * 256
				+ ints[3];
	}
	
	public static long bytesToLong(byte[] bytes, int start) {
		long[] longs = new long[8];
		for (int i = 0; i < 8; i++) {
			longs[i] = bytes[i + start] >= 0 ? bytes[i + start] : (256 + bytes[i
					+ start]);
		}
		return longs[0] * 256 * 256 * 256 * 256 * 256 * 256 * 256 
				+ longs[1] * 256 * 256 * 256 * 256 * 256 * 256
				+ longs[2] * 256 * 256 * 256 * 256 * 256
				+ longs[3] * 256 * 256 * 256 * 256
				+ longs[4] * 256 * 256 * 256
				+ longs[5] * 256 * 256 
				+ longs[6] * 256
				+ longs[7];
	}
	
	public static double bytesToDouble(byte[] b, int start) {
		long l;

	    l=b[7+start]; 
	    l&=0xff; 
	    l|=((long)b[6+start]<<8); 
	    l&=0xffff; 
	    l|=((long)b[5+start]<<16); 
	    l&=0xffffff; 
	    l|=((long)b[4+start]<<24); 
	    l&=0xffffffffl; 
	    l|=((long)b[3+start]<<32);
	    l&=0xffffffffffl;
	    l|=((long)b[2+start]<<40); 
	    l&=0xffffffffffffl; 
	    l|=((long)b[1+start]<<48); 
	    l&=0xffffffffffffffl; 
	    l|=((long)b[0+start]<<56); 
	    return Double.longBitsToDouble(l); 
	}

	private static String bytesToString(byte[] bytes, int start, int len) {
		return new String(bytes, start, len);
	}

	private static int bytesFindZero(byte[] bytes, int start, int max) {
		int i = 1;
		while (bytes[start + i - 1] != '\0' && i <= max) {
			i++;
		}
		return i;
	}

	public static Record parseContent(byte[] bytes_content) {

		boolean flagV4 = false;
		int lenToZero;
		int pointIndex = 0;
		int eaIndex = 0;
		int neighIndex = 0;
		int apIndex = 0;
		int sensorIndex = 0;
		int index = 0;
		int tmp_len = 0;
		int sensor_type = 0;
		Record rec = new Record();
		byte[] unzipContent = unGZip(bytes_content);
		
		rec.version = bytesToByte(unzipContent, index);
		index = index + 1;
		
		if(rec.version == Const.V4 || rec.version == Const.V41) {
			flagV4 = true;
		}

		rec.length = bytesToShort(unzipContent, index);
		index = index + 2;

		lenToZero = bytesFindZero(unzipContent, index, 16);
		rec.imei = bytesToString(unzipContent, index, lenToZero - 1);
		index = index + 16;

		lenToZero = bytesFindZero(unzipContent, index, 16);
		rec.imsi = bytesToString(unzipContent, index, lenToZero - 1);
		index = index + 16;

		lenToZero = bytesFindZero(unzipContent, index, 16);
		rec.mac = bytesToString(unzipContent, index, lenToZero - 1);
		index = index + 16;

		rec.mcc = bytesToShort(unzipContent, index);
		index = index + 2;

		rec.mnc = bytesToShort(unzipContent, index);
		index = index + 2;

		rec.radioType = bytesToByte(unzipContent, index);
		index = index + 1;

		lenToZero = bytesFindZero(unzipContent, index, 16);
		rec.deviceName = bytesToString(unzipContent, index, lenToZero - 1);
		index = index + lenToZero;

		lenToZero = bytesFindZero(unzipContent, index, 32);
		rec.sourceName = bytesToString(unzipContent, index, lenToZero - 1);
		index = index + lenToZero;

		rec.pointNum = bytesToShort(unzipContent, index);
		index = index + 2;

		pointIndex = 0;
		rec.listPoint.clear();
		while (pointIndex < rec.pointNum) {

			Point pt = new Point();

			pt.length = bytesToShort(unzipContent, index);
			index = index + 2;

			pt.time = bytesToInt(unzipContent, index);
			index = index + 4;

			pt.type = bytesToByte(unzipContent, index);
			index = index + 1;

			pt.pos = null;
			pt.gtw = null;
			pt.cdma = null;
			pt.wifi = null;
			if (pt.type == 1) { // point-position

				Position pos = new Position();

				pos.lon = bytesToInt(unzipContent, index);
				index = index + 4;

				pos.lat = bytesToInt(unzipContent, index);
				index = index + 4;

				pos.alt = bytesToInt(unzipContent, index);
				index = index + 4;

				pos.radius = bytesToInt(unzipContent, index);
				index = index + 4;

				pos.speed = bytesToInt(unzipContent, index);
				index = index + 4;

				pos.direction = bytesToShort(unzipContent, index);
				index = index + 2;
				
				if(flagV4 == true) {
					pos.isMock = bytesToByte(unzipContent, index);
					index = index + 1;
					
					pos.isDrift = bytesToByte(unzipContent, index);
					index = index + 1;
					
					pos.timeSys = bytesToLong(unzipContent, index);
					index = index + 8;
					
					pos.timeGps = bytesToLong(unzipContent, index);
					index = index + 8;
				} else {
					pos.satellitesNum = bytesToByte(unzipContent, index);
					index = index + 1;

					pos.signals = bytesToInt(unzipContent, index);
					index = index + 4;

					pos.hdop = bytesToShort(unzipContent, index);
					index = index + 2;

					pos.eaNum = bytesToByte(unzipContent, index);
					index = index + 1;

					eaIndex = 0;
					pos.listEa.clear();
					while (eaIndex < pos.eaNum) {

						Ea ea = new Ea();

						ea.elevation = bytesToByte(unzipContent, index);
						index = index + 1;

						ea.azimuth = bytesToShort(unzipContent, index);
						index = index + 2;

						pos.listEa.add(ea);
						eaIndex++;
					}
				}			

				pt.pos = pos;
			} else if (pt.type == 2) { // point-gtw

				Gtw gtw = new Gtw();

				gtw.lac = bytesToShort(unzipContent, index);
				if (gtw.lac < 0) {
					gtw.lac += 65536;
				}
				index = index + 2;

				gtw.cid = bytesToInt(unzipContent, index);
				index = index + 4;

				gtw.rssi = bytesToByte(unzipContent, index);
				index = index + 1;

				if(flagV4 == true) {
//					gtw.criArea = bytesToByte(unzipContent, index);
//					index = index + 1;
				}
				
				gtw.neighNum = bytesToByte(unzipContent, index);
				index = index + 1;

				neighIndex = 0;
				gtw.listNeigh.clear();
				while (neighIndex < gtw.neighNum) {

					Neigh neigh = new Neigh();

					neigh.lac = bytesToShort(unzipContent, index);
					if (neigh.lac < 0) {
						neigh.lac += 65536;
					}
					index = index + 2;

					neigh.cid = bytesToInt(unzipContent, index);
					index = index + 4;

					neigh.rssi = bytesToByte(unzipContent, index);
					index = index + 1;

					gtw.listNeigh.add(neigh);
					neighIndex++;
				}

				pt.gtw = gtw;
			} else if (pt.type == 4) { // point-cdma

				Cdma cdma = new Cdma();

				cdma.lon = bytesToInt(unzipContent, index);
				index = index + 4;

				cdma.lat = bytesToInt(unzipContent, index);
				index = index + 4;

				cdma.sid = bytesToShort(unzipContent, index);
				index = index + 2;

				cdma.nid = bytesToShort(unzipContent, index);
				index = index + 2;

				cdma.bsid = bytesToInt(unzipContent, index);
				index = index + 4;

				if(flagV4 == true) {
//					cdma.criArea = bytesToByte(unzipContent, index);
//					index = index + 1;
				}
				
				cdma.rssi = bytesToByte(unzipContent, index);
				index = index + 1;

				pt.cdma = cdma;
			} else if (pt.type == 8) { // point-wifi

				Wifi wifi = new Wifi();

				wifi.wifiNum = bytesToByte(unzipContent, index);
				index = index + 1;

				apIndex = 0;
				wifi.listAp.clear();
				while (apIndex < wifi.wifiNum) {

					Ap ap = new Ap();

					lenToZero = bytesFindZero(unzipContent, index, 16);
					ap.mac = bytesToString(unzipContent, index, lenToZero - 1);
					index = index + 16;

					ap.rssi = bytesToShort(unzipContent, index);
					index = index + 2;

					lenToZero = bytesFindZero(unzipContent, index, 32);
					ap.ssid = bytesToString(unzipContent, index, lenToZero - 1);
					index = index + lenToZero;

					wifi.listAp.add(ap);
					apIndex++;
				}

				pt.wifi = wifi;
			} else if (pt.type == 3) { // point-sensor
				
				SensorData sensorData = new SensorData();
				
				sensorData.num = bytesToByte(unzipContent, index);
				index = index + 1;
				
				sensorIndex = 0;
				sensorData.sensors.clear();
				while(sensorIndex < sensorData.num) {
					
					SensorInfo sensorInfo = new SensorInfo();
					
					tmp_len = bytesToByte(unzipContent, index);
					index = index + 1;					
					sensorInfo.name = bytesToString(unzipContent, index, tmp_len);
					index = index + tmp_len;
					
					sensorInfo.max_range = bytesToDouble(unzipContent, index);
					index += 8;
					
					sensorInfo.min_delay = bytesToInt(unzipContent, index);
					index += 4;
					
					sensorInfo.power = bytesToInt(unzipContent, index)/1.e2;
					index += 4;
					
					sensorInfo.resolution = bytesToDouble(unzipContent, index);
					index += 8;
					
					sensor_type = bytesToByte(unzipContent, index);
					sensorInfo.type = (sensor_type > SensorInfo.SENSOR_NAMES.length ? ""+sensor_type : SensorInfo.SENSOR_NAMES[sensor_type-1]);
					index += 1;
					
					tmp_len = bytesToByte(unzipContent, index);
					index = index + 1;					
					sensorInfo.vendor = bytesToString(unzipContent, index, tmp_len);
					index = index + tmp_len;
					
					sensorInfo.version = bytesToByte(unzipContent, index);
					index = index + 1;
					
					sensorData.sensors.add(sensorInfo);
					sensorIndex++;
				}
				
				pt.sensor = sensorData;
			}

			rec.listPoint.add(pt);
			pointIndex++;
		}

		return rec;
	}
	
	public static String printBytes(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		
		if(bytes.length >= 1024)
			return "TOO LARGE BYTES";
		
		for(int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]);
			sb.append(",");
		}
		return sb.toString();
	}
}
