package com.autonavi.collect.resolve.newcollector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autonavi.collect.etl.CollectUniformMapReduce;


public class Decode {

	/**
	 * 
	 * @description 解析新版的采集文件
	 * 
	 * @author decun.meng
	 * 
	 * @param fileName
	 *            待解析的一条记录
	 * @return 明文的采集数据集合
	 */

	public static ArrayList<Record> decode(String value) {
	    Log log = LogFactory.getLog(Decode.class);
		ArrayList<Record> result = new ArrayList<Record>();
		if (null == value)
			return null;
		String [] reValues = value.split("#");
		String reValue = reValues[reValues.length-1];
		List<byte[]> listContent = BinaryUtil.generateContents(BinaryUtil
				.parseStrToByteArray(reValue));
		for (int i = 0; i < listContent.size(); i++) {
			Record rec = BinaryUtil.parseContent(listContent.get(i));
			if (rec != null){
			  if((rec.version == 3 || rec.version == 4 || rec.version == 41)) {
			    result.add(rec);
			  }
			}
			else{
			  log.info("rec is null -===");
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Record> data = Decode
				.decode("");
		for (int i = 0; i < data.size(); i++)
			System.out.println(data.get(i));
	}
}
