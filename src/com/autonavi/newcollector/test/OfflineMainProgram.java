package com.autonavi.newcollector.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.autonavi.newcollector.Decode;

public class OfflineMainProgram {

	public static String in_path = "E:\\c\\workspace_1115\\OfflineCollectDataParser\\in";
	public static String out_path = "E:\\c\\workspace_1115\\OfflineCollectDataParser\\out";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File file = new File(in_path);
		String[] str_files = null;
		String in_str = null;
		String out_str = null;
		File in_file = null;
		File out_file = null;

		if (!(file != null && file.exists() && file.isDirectory())) {
			return;
		}

		str_files = file.list();

		for (int i = 0; i < str_files.length; i++) {

			in_str = in_path + "\\" + str_files[i];
			in_file = new File(in_str);

			out_str = out_path + "\\" + str_files[i];			
			out_file = new File(out_str);
			
			if (out_file.exists() && out_file.isFile()) {
				out_file.delete();
			}
			try {
				out_file.createNewFile();
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.toString());
				continue;
			}

			Decode.parseOfflineCollection(in_file, out_file, 2);
		}

//		File f = new File(in_path);
//		File out = new File("out.txt");
//		try {
//		out.createNewFile();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Decode.parseOfflineCollection(f, out, 0);
//		 List<String> strs = Decode.decodeFile(in_path);
//		
//		 for (int i = 0; i < strs.size(); i++) {
//			 System.out.println(strs.get(i));
//		 }
//
		//System.out.println(mills2timeString(1387870070000L));
		// System.out.println(mills2timeString(1385112962000L));
		// System.out.println(mills2timeString(1385112972000L));
		// System.out.println(mills2timeString(1385105480000L));

		return;
	}

	public static String mills2timeString(long mills) {

		Date date = new Date(mills);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return sdf.format(date);
	}

}
