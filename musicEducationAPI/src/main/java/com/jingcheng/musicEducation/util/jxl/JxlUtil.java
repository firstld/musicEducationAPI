package com.jingcheng.musicEducation.util.jxl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jingcheng.musicEducation.util.MyUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 使用jxl工具操作Excel表
 * @author cx
 *
 */
public class JxlUtil {
	@SuppressWarnings("rawtypes")
	public static void explorExcel(List<Object> objData,String fileName ) throws Exception {
		    WritableWorkbook wwb=null;
		    OutputStream os=new FileOutputStream(new File(fileName));
			wwb=Workbook.createWorkbook(os);
			WritableSheet sheet=wwb.createSheet("sheet1", 0);
			//处理表头
			List<String> tableHeader = new ArrayList<String>();
	            if (objData.size() >= 1) {
	                //Map map = (Map) objData.get(0);
	                Object entity=objData.get(0);
	                Map map=MyUtil.beanToMap(entity);
	            	Set keySet = map.keySet();
	                for (Object keyName : keySet) {
	                    tableHeader.add(keyName.toString());
	                    //System.out.println(keyName);
	                }
	            }
	      //将列名写入表头
	      for(int i=0;i<tableHeader.size();i++){
	    	  Label label=new Label(i, 0, tableHeader.get(i));
	    	  sheet.addCell(label);
	      }
	      //写入数据(逻辑为每一行数据的每一列一一对应写入到label中)
	      for(int i=0;i<objData.size();i++){
	    	Object entity=objData.get(i);
	    	Map map=MyUtil.beanToMap(entity);
			//Map map=(Map) objData.get(i);
	    	  for(int j=0;j<tableHeader.size();j++){
	    		  Label label=new Label(j, i+1, map.get(tableHeader.get(j)).toString());
	    		  sheet.addCell(label);
	    	  } 
	      }
	      wwb.write();    
	      wwb.close();
	      os.close();
	}
	
}
