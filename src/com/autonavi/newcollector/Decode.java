package com.autonavi.newcollector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.RandomAccess;

import com.autonavi.newcollector.bean.Const;
import com.autonavi.newcollector.bean.Record;
import com.autonavi.newcollector.util.BinaryUtil;

public class Decode {

	/**
	 * 将按记录换行的文本二进制文件解析成文本记录
	 * @param fileName
	 * @return
	 */
	public static ArrayList<String> decodeFile(String fileName) {

		ArrayList<String> result = new ArrayList<String>();

		File file = new File(fileName);
		if (!file.exists() || file.isDirectory()) {

			return result;
		}

		String temp = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			temp = br.readLine();
			while (temp != null) {
				List<byte[]> listContent = BinaryUtil
						.generateContents(BinaryUtil.parseStrToByteArray(temp));

				for (int i = 0; i < listContent.size(); i++) {
					Record rec = BinaryUtil.parseContent(listContent.get(i));
					if (rec.version == 3 || rec.version == 4) {

						result.add(rec.toString());
					}

				}

				temp = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static ArrayList<String> decodeBytesList(ArrayList<byte[]> bytes_list) {

		ArrayList<String> result = new ArrayList<String>();

		// File file = new File(fileName);
		// if (!file.exists() || file.isDirectory()) {

		// return result;
		// }
		if (bytes_list == null) {
			return result;
		}

		String temp = null;

		try {
			// BufferedReader br = new BufferedReader(new FileReader(file));
			// temp = br.readLine();
			// while (temp != null) {
			for (int list_index = 0; list_index < bytes_list.size(); list_index++) {
				List<byte[]> listContent = BinaryUtil
						.generateContents(bytes_list.get(list_index));

				for (int i = 0; i < listContent.size(); i++) {
					Record rec = BinaryUtil.parseContent(listContent.get(i));
					if (rec.version == 3 || rec.version == 4) {

						result.add(rec.toString());
					}

				}

				// temp = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String decodeBytes(byte[] bytes) {

		String result = "";

		if (bytes == null) {
			return result;
		}

		String temp = null;

		try {
			List<byte[]> listContent = BinaryUtil.generateContents(bytes);

			for (int i = 0; i < listContent.size(); i++) {
				Record rec = BinaryUtil.parseContent(listContent.get(i));
				if (rec.version == Const.V3 || rec.version == Const.V4 || rec.version == Const.V41) {

					result = rec.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 解析手机端采集文件（纯二进制）
	 * 
	 * @param context
	 *            应用程序的context
	 * @param flag
	 *            0为小文件 1为大文件 2为普通文件
	 */
	public static void parseOfflineCollection(File in, File out, int flag) {

		// 获取文件
		// String packageName = context.getPackageName();
		String path = "";
		File file = null;
		String[] strFiles = null;
		String fileName = "";
		String logInfo = "";
		int len = 0;
		int size = 0;
		int index = 0;
		int ignoreSize = 0;
		int usefulSize = 0;
		byte[] tmpBytes = null;
		byte[] srcBytes = null;
		byte[] byteSnap = null;
		BitSet bitSet = null;
		List<Long> usefulStarts = null;

		// if(packageName == null || packageName.length() <= 0) {
		// return ;
		// }

		// path += Environment.getExternalStorageDirectory().getPath();
		// path += "/";
		// path += "/Android/data/";
		// path += packageName;
		// path += "/files/carrierdata/";

		// file = new File(path);
		// strFiles = file.list();
		//
		// if(strFiles == null || strFiles.length <= 0) {
		// return ;
		// }

		// Log.i(packageName, "strFiles.length="+strFiles.length);

		// fileName = path + strFiles[0];

		fileName = in.getPath();

		// 读取二进制记录依次解析

		try {
			FileInputStream stream = new FileInputStream(fileName);
			long readPointer = 0;
			BufferedWriter bw = new BufferedWriter(new FileWriter(out));

			// 跳过文件头
			if (flag == 0) {
				byteSnap = readBytes(stream, 256);
				bitSet = byteArray2BitSet(byteSnap);
				usefulStarts = getStartPointer(bitSet, 256);
				
				readBytes(stream, 4);
				
				readPointer += 256+4;
			} else if (flag == 1) {
				byteSnap = readBytes(stream, 8736);
				bitSet = byteArray2BitSet(byteSnap);
				usefulStarts = getStartPointer(bitSet, 8736);
				
				readBytes(stream, 4);
				
				readPointer += 8736+4;
			} else if(flag == 2) {
				byteSnap = readBytes(stream, 768);
				bitSet = byteArray2BitSet(byteSnap);
				usefulStarts = getStartPointer(bitSet, 768);
				
				readBytes(stream, 4);
				
				readPointer += 768+4;
			} else {
				bw.close();
				stream.close();
				return;
			}

			// 按照每块1500字节的速度读取
			while ((tmpBytes = readBytes(stream, 1500)) != null) {

				Thread.sleep(10);				

				// length为0时，不进行解析
				if ((len = BinaryUtil.bytesToInt(tmpBytes, 0)) <= 0) {
					break;
				}				

				// Log.i(packageName, "length=" + len);
				
				// 当前指针在位表中为无效时，不进行解析
				for(index = 0; index < usefulStarts.size(); index++) {
					if(readPointer == (long)usefulStarts.get(index)) {
						break;
					}
				}
				if(index == usefulStarts.size()) {
					ignoreSize++;
					//continue ;
				} else {
				
				usefulSize++;
				}
				
				// 剔除了length+time+content中的time字段
				srcBytes = new byte[len + 4];
				System.arraycopy(tmpBytes, 0, srcBytes, 0, 4);
				System.arraycopy(tmpBytes, 12, srcBytes, 4, len);

				//
				//System.out.println(Decode.printBytes(srcBytes));
				
				logInfo = decodeBytes(srcBytes);
				bw.write(logInfo + "\n");
				bw.flush();
				//System.out.println(logInfo);
				//Log.i(packageName, logInfo);
				size++;
				
				readPointer += 1500;
			}

			System.out.println("ignored/useful " + ignoreSize + "/" + usefulSize);
			
			// rFile_out.close();
			bw.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// Log.i(packageName, "logCollection finished.Total:" + size);
		return;
	}

	public static byte[] readBytes(FileInputStream fis, int len)
			throws Exception {

		byte[] result = null;
		int i = 0;
		int n = 0;

		if (fis == null) {
			return null;
		}

		result = new byte[len];

		for (int j = 0; j < len; j++) {

			n = fis.read();
			if (n == -1) {
				break;
			}
			// 将有效数据存储到数组中
			result[i] = (byte) n;
			// 下标增加
			i++;
		}

		return result;
	}

	// 测试

	// public static void main(String[] args) {
	//
	//
	// final String[] PATH =
	// {
	// "d:/logs/v1.0_ali_352746051667295_460018211604449.dat",
	// "d:/logs/v1.0_ali_352746050090903_460018211604440.dat",
	// "d:/logs/v1.0_ali_354692042554077_460011301650272.dat"
	// };
	//
	//
	// for (int fileIndex = 0; fileIndex < PATH.length; fileIndex++) {
	//
	// System.out.println("filename:"+PATH[fileIndex]);
	//
	// ArrayList<String> ddd = decodeFile(PATH[fileIndex]);
	//
	// if(ddd.size() > 0){
	// System.out.println(ddd.size());
	//
	// for(int i=0 ;i< ddd.size();i++){
	// System.out.println(ddd.get(i));
	// }
	//
	// }
	//
	// }
	//
	// }

	protected static BitSet byteArray2BitSet(byte[] bytes) {
		BitSet bitSet = new BitSet(bytes.length * 8);
		int index = 0;
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 7; j >= 0; j--) {
				bitSet.set(index++, (bytes[i] & (1 << j)) >> j == 1 ? true
						: false);
			}
		}
		return bitSet;
	}
	
	protected static String printBytes(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		String str = null;
		
		for(int i = 0; i < bytes.length; i++) {
			str = Integer.toHexString(bytes[i] & 0xFF).toUpperCase();
			sb.append(str.length() == 1 ? "0"+str : str);
			//sb.append(",");
		}
		return sb.toString();
	}

	private static List<Long> getStartPointer(BitSet currentBitSet, int snapshot) {
		List<Long> start = new ArrayList<Long>();
		for (int i = 0; i < currentBitSet.length(); i++) {
			if (currentBitSet.get(i)) {
				long start_i = i * 1500L + 4L + snapshot;
				start.add(start_i);
			}
		}
		return start;
	}
}
