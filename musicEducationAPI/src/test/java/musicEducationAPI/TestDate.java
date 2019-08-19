package musicEducationAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestDate {
	
	public static void main(String[] args) throws Exception {
	    List<String> list=new ArrayList<>();
	    list.add("2018-01");
	    list.add("2018-02");
	    list.add("2018-04");
	    list.add("2018-07");
	    list.add("2018-10");
	   /* for(int i=0;i<list.size();i++){
	    	String month=list.get(i);
	    	System.out.println(month);
	
	    }*/
	    List<String> list1=new ArrayList<>();
    	//开始时间
	    Date star=new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-01");
	    //结束时间
	    Date end =new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-25");
	    Calendar dd=Calendar.getInstance();
	    //设置起始时间
	    dd.setTime(star);
	    //保证时间在结束时间之前
	    while(dd.getTime().before(end)){
	    	String d=new SimpleDateFormat("yyyy-MM-dd").format(dd.getTime());
	    	//System.out.println(d);
	    	dd.add(Calendar.DAY_OF_YEAR, 1);
	    	//System.out.println("--****--:"+dd);
	    	list1.add(d);	
	    }
	    list1.add(new SimpleDateFormat("yyyy-MM-dd").format(end));
	    for(String str:list1){
	    	System.out.println(str);
	    }
	    
	    
	    /*for(int j=0;j<list1.size();j++){
	    	 System.out.println(list1.get(j));
	    } */
	    //List<String> diff=new ArrayList<>();
	   /* list1.removeAll(list);
	    for(String str : list1){
	    	System.out.println(str);
	    }*/
	    
	    int i=(int)Math.random()*100000;
	    System.out.println(i);
	    
	    }
	    
	}

