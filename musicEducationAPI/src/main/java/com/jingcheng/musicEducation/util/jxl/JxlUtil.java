package com.jingcheng.musicEducation.util.jxl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.jingcheng.musicEducation.order.entity.CourseOrder;
import com.jingcheng.musicEducation.user.entity.User;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 使用jxl工具操作Excel表
 * @author cx
 *
 */
public class JxlUtil {
	
	public static WritableSheet sheetMould(String[] titles,String sheetName,WritableSheet sheet){

		//设置标题：格式，位置，内容，合并单元格
		//设置标题格式：字体，大小，粗体，非斜体，无下划线，黑色
		WritableFont header=new WritableFont(WritableFont.createFont("微软雅黑"), 20, WritableFont.BOLD, false, 
				                             UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		
		WritableCellFormat headerFormat=new WritableCellFormat(header);
		//水平居中
		try {
			headerFormat.setAlignment(Alignment.CENTRE);
			  //垂直居中
			headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			//设置标题行宽度
			sheet.setRowView(0, 1000);
			sheet.addCell(new Label(0, 0, sheetName,headerFormat ));
			//表示从第一列第一行单元格开始到titles.length列第一行
			sheet.mergeCells(0, 0, titles.length-1, 0);
			//编写表头
			for(int i=0;i<titles.length;i++){
				Label label=new Label(i, 1, titles[i]);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
      
		return sheet;
	}
	
	
	
	public static void userExplorExcel(List<Object> objData,String fileName ) throws Exception {
		    WritableWorkbook wwb=null;
		    OutputStream os=new FileOutputStream(new File(fileName));
			wwb=Workbook.createWorkbook(os);
			WritableSheet sheet=wwb.createSheet("用户注册统计表", 0);
			String[] titles={"用户名","密码","微信id","性别","昵称","生日","电话号码","余额","最近登录时间","状态","注册时间"};
			
			//设置标题：格式，位置，内容，合并单元格
			//设置标题格式：字体，大小，粗体，非斜体，无下划线，黑色
			WritableFont header=new WritableFont(WritableFont.createFont("微软雅黑"), 20, WritableFont.BOLD, false, 
					                             UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			
			WritableCellFormat headerFormat=new WritableCellFormat(header);
			//水平居中
			headerFormat.setAlignment(Alignment.CENTRE);
            //垂直居中
			headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			//设置标题行宽度
			sheet.setRowView(0, 1000);
			sheet.addCell(new Label(0, 0, "用户注册统计",headerFormat ));
			//表示从第一列第一行单元格开始到titles.length列第一行
			sheet.mergeCells(0, 0, titles.length-1, 0);
			//编写表头
			for(int i=0;i<titles.length;i++){
				Label label=new Label(i, 1, titles[i]);
				sheet.addCell(label);
			}

			//遍历数据，按列将数据输入到对应列中
			Label label=null;
			for(int i=0;i<objData.size();i++){
				User u=(User)objData.get(i);
				label=new Label(0, i+2, u.getName());
				sheet.addCell(label);
				label=new Label(1,i+2,u.getPassword());
				sheet.addCell(label);
				label=new Label(2,i+2,u.getOpenId());
				sheet.addCell(label);
				label=new Label(3,i+2,u.getGender().toString());
				sheet.addCell(label);
				label=new Label(4,i+2,u.getNickName());
				sheet.addCell(label);
				label=new Label(5,i+2,u.getBirthday());
				sheet.addCell(label);
				label=new Label(6,i+2,u.getTelephone());
				sheet.addCell(label);
				label=new Label(7,i+2,u.getPurseBalance().toString());
				sheet.addCell(label);
				label=new Label(8,i+2,u.getLastLoginTime());
				sheet.addCell(label);
				label=new Label(9,i+2,u.getStatus().toString());
				sheet.addCell(label);
				label=new Label(10, i+2, u.getCreatedAt());
				sheet.addCell(label);			
			}
	      wwb.write();    
	      wwb.close();
	      os.close();
	}
	
	public static void orderExplorExcel(List<Object> objDate,String fileName,String type) {
		WritableWorkbook wwb=null;
		OutputStream os=null;
		final String[] titles={"订单号","状态","用户ID","支付编号","支付回调","支付金额","支付时间","下单时间"};
		final String sheetName="订单统计表";
		try {
			os=new FileOutputStream(new File(fileName));
			wwb=Workbook.createWorkbook(os);
			WritableSheet sheet=wwb.createSheet(sheetName, 0);
			sheet=JxlUtil.sheetMould(titles, sheetName, sheet);
			Label label=null;
			if(type=="course"){
				for(int i=0;i<objDate.size();i++){
					CourseOrder co=(CourseOrder)objDate.get(i);
					label=new Label(0, i+2, co.getCourseOrderSn());
					sheet.addCell(label);
					label=new Label(1, 1+2, co.getStatus());
					sheet.addCell(label);
					label=new Label(2, i+2, co.getUserId().toString());
					sheet.addCell(label);
					label=new Label(3, i+2, co.getTranscationId());
					sheet.addCell(label);
					label=new Label(4, i+2, co.getPayCallBack());
					sheet.addCell(label);
					label=new Label(5, i+2, co.getPayAmount().toString());
					sheet.addCell(label);
					label=new Label(6, i+2, co.getPayTime());
					sheet.addCell(label);
					label=new Label(7, i+2, co.getCreatedAt());
				}	
			}
			if(type=="product"){
				//此处写商品订单的业务
			}
			wwb.write();
			wwb.close();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
