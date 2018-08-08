package musicEducationAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jingcheng.musicEducation.category.entity.Category;
import com.jingcheng.musicEducation.category.resource.CategoryResource;
import com.jingcheng.musicEducation.comment.dao.CourseReplyCommentDao;
import com.jingcheng.musicEducation.comment.service.CommentBackgroundService;
import com.jingcheng.musicEducation.course.dao.CourseDao;
import com.jingcheng.musicEducation.course.entity.Course;
import com.jingcheng.musicEducation.order.dao.CourseOrderDao;
import com.jingcheng.musicEducation.system.dao.BroadcastImageDao;
import com.jingcheng.musicEducation.system.entity.BroadcastImage;
import com.jingcheng.musicEducation.system.service.BroadcastImageBackgroundService;
import com.jingcheng.musicEducation.teacher.dao.TeacherDao;
import com.jingcheng.musicEducation.teacher.entity.Teacher;
import com.jingcheng.musicEducation.teacher.resource.TeacherResource;
import com.jingcheng.musicEducation.teacher.service.TeacherBackgroundService;
import com.jingcheng.musicEducation.user.dao.UserDao;
import com.jingcheng.musicEducation.user.entity.User;
import com.jingcheng.musicEducation.user.service.UserBackgroundService;
import com.jingcheng.musicEducation.util.MyUtil;
import com.jingcheng.musicEducation.util.jxl.ExplorExcelResource;
import com.jingcheng.musicEducation.util.page.PagePojo;

public class TestCaes {
    private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init(){
	  	ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void test1(){
		CourseDao dao=ctx.getBean("courseDao",CourseDao.class);
/*		Course course = new Course();
		course.setCategorySn("C1000");
		course.setTeacherId(1);
		course.setCoverImage("https://menublob.blob.core.chinacloudapi.cn:443/biai/goods/ea506b468589cc07a4b37b518e0c9975.jpg");
		course.setDetails("https://menublob.blob.core.chinacloudapi.cn:443/biai/goods/f2967a8bcba4a3f819dbfa2b615f629c.jpg");
		//1:无经验 2:1-3年经验 3：3年以上
		course.setFitUser(1);
		//0:不分销 1：分销
		course.setIsDistribution(0);
		//0:初级 1:中级2：高级；3：大师级
		course.setLevel(0);
		course.setOriginPrice(306.15);
		course.setSalePrice(206.75);
		//0:在架 1：已下架
		course.setStatus(0);
		course.setTitle("java基础");
        dao.insertEntity(course);*/
/*		Course course=(Course)dao.findObjById(1);
		String courseSn="C10"+String.valueOf((int)(Math.random()*100000));
		course.setCourseSn(courseSn);
		dao.updateEntity(course);*/
		Course course=(Course)dao.findObjById(1);
		System.out.println(course);
		
	}
	
	@Test
	public void test2(){
		TeacherDao dao=ctx.getBean("teacherDao",TeacherDao.class);
		Teacher t= new Teacher();
		t.setTeacherSn("T0000001");
		t.setName("陈雄");
		t.setAvatar("https://menublob.blob.core.chinacloudapi.cn:443/biai/goods/87529f4522c83a35a991cdbdfc81cdb5.jpg");
		t.setDescription("此处省略一万字。。。。。。。因为无话可说！！！");
		t.setLevel("超级讲师");
		t.setStatus(1);
		dao.insertEntity(t);
		System.out.println("save ok");
	}
	@Test
	public void test3(){
		TeacherBackgroundService service=ctx.getBean("teacherBackgroundServiceImpl",TeacherBackgroundService.class);
		Teacher t=service.getTeacherById(3);
		System.out.println(t);
	}
	@Test
	public void jerseyTest(){
		Client c=ClientBuilder.newClient(new ClientConfig().register(TeacherResource.class));
	    WebTarget wt=c.target("http://localhost:8080/musicEducationAPI/rest").path("/teacher/3");
	    javax.ws.rs.client.Invocation.Builder b=wt.request(MediaType.APPLICATION_JSON);
	    String result=b.get(String.class);
	    System.out.println(result);
	    
	}
	@Test
	public void test4(){
		double i=15/2^2;
		System.out.println(i);
        int a=1;
        int b=2;
        if(a==b){
        	System.out.println(0);
        }else{
        	System.out.println(1);
        }
	}
	@Test
	public void testExplor(){
		Client c=ClientBuilder.newClient(new ClientConfig().register(CategoryResource.class));
		WebTarget wt=c.target("http://localhost:8080/musicEducationAPI/rest")
				      .path("category").queryParam("pageCurrent", 1)
				                       .queryParam("pageSize", 10)
				                       .queryParam("orderBy", "createdAt")
				                       .queryParam("sort", "desc")
				                       .queryParam("parentSn", "C1000");
		javax.ws.rs.client.Invocation.Builder b=wt.request(MediaType.APPLICATION_JSON);
		String result=b.get(String.class);
        JsonObject obj=(JsonObject)new JsonParser().parse(result);
        JsonArray jsonArray=obj.get("data").getAsJsonObject().get("list").getAsJsonArray();
        List<Object> list=new ArrayList<>();
        for(JsonElement category:jsonArray){
        	Category categoryBean=new Gson().fromJson(category, Category.class);
        	list.add(categoryBean);
        }
        String listJson=new Gson().toJson(list);
        System.out.println(listJson);
      
		Client t=ClientBuilder.newClient(new ClientConfig().register(ExplorExcelResource.class));
	    WebTarget wt1=t.target("http://localhost:8080/musicEducationAPI/rest")
	    		       .path("excel").path("explor");
	    Builder b1=wt1.request(MediaType.APPLICATION_JSON);
		//Response res=b1.post(Entity.entity(list, MediaType.APPLICATION_JSON));
	    Response res=b1.post(Entity.entity(listJson, MediaType.APPLICATION_JSON));
	    System.out.println(res);
	}
	@Test
	public void test5(){
		CommentBackgroundService cs=ctx.getBean("commentBackgroundServiceImpl",CommentBackgroundService.class);
		
	/*	Object obj=cs.getCommentById(1, "course");
		System.out.println(obj);*/
		String  pageCurrent="1";
		String  pageSize="10";
		String  orderBy="createdAt";
		String  sort="desc";
		String  type="course";
		Map<String,Object> params=new HashMap<>();
		params.put("pageCurrent", pageCurrent);
		params.put("pageSize", pageSize);
		params.put("orderBy", orderBy);
		params.put("sort", sort);
		params.put("type", type);
		PagePojo pojo=cs.getCommentList(params);
		System.out.println(pojo);
	}
	@Test
	public void test6(){
		CourseReplyCommentDao dao=ctx.getBean("courseReplyCommentDao",CourseReplyCommentDao.class);
		Integer[] ids={1,2};
		int rows=dao.deleteEntities(ids);
		System.out.println(rows);
	}
	
	@Test
	public void test7(){
		UserDao dao=ctx.getBean("userDao",UserDao.class);
/*		Map<String, Object> params=new HashMap<>();
		params.put("biginTime", "2018-07-04 11:16:50");
		params.put("endTime", "2018-07-04 11:23:13");
		List<Object> list=dao.findObjList(params);
		for(Object o:list){
			System.out.println(o);
		}*/
		/*User u=(User)dao.findObjById(1);
		System.out.println(u);*/
		List<Map<String,Object>> list=dao.countDayRegisterUser("2018-07-04", "2018-07-14");
		System.out.println(list);
		for(Map<String,Object> map:list){
			System.out.println(map);
		}
		List<Map<String,Object>> list1=dao.countMonthRegisterUser("2018-07-04", "2018-08-14");
		System.out.println(list1);
		for(Map<String,Object> map:list1){
			System.out.println(map);
		}
	}
	@Test
	public void beanToMap() throws Exception{
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User u=(User)dao.findObjById(1);
		System.out.println("实体类："+u);
		//Map map=MyUtil.beanToMap(u);
		Map<String,Object> map=MyUtil.entityToMap(u);
		System.out.println("map:"+map);
	}
	@Test
	public void test8() {
		UserBackgroundService us=ctx.getBean("userBackgroundServiceImpl",UserBackgroundService.class);
		Map<String,Long> map=us.groupCountRegister("2018-07-04", "2018-07-14", "month");
		System.out.println(map);

	}
	@Test
	public void order(){
		CourseOrderDao dao=ctx.getBean("courseOrderDao",CourseOrderDao.class);
		Map<String,Object> params=null;
		List<Object> list=dao.findObjList(params);
		for(Object o:list){
			System.out.println(o);
		}
	}
	@Test
	public void broadcastImage(){
		BroadcastImageBackgroundService service=ctx.getBean("broadcastImageBackgroundServiceImpl",BroadcastImageBackgroundService.class);
		//BroadcastImage image=new BroadcastImage();
/*		image.setImageUrl("https://menublob.blob.core.chinacloudapi.cn/biai/courseSection/c509c387be9675a2a0faebb938e31923.jpg");
		image.setPosition(1);
		image.setDescription("轮播图第二张图");
		service.addBroadcastImage(image);*/
		/*image=service.findImageById(17);
		System.out.println(image);
		image.setPosition(2);
		service.updateBroadcastImage(image);
		System.out.println(image);*/
		Map<String,Object> params=new HashMap<>();
		params.put("beginTime","2018-08-03");
		params.put("endTime", "2018-08-03");
		//params.put("position", 2);
		params.put("pageCurrent", 1);
		params.put("pageSize", 10);
		params.put("orderBy", "createdAt");
		params.put("sort", " ");
		PagePojo pojo=service.findBroadcastImageList(params);
		for(int i=0;i<pojo.getList().size();i++){
			Object o=pojo.getList().get(i);
			System.out.println(o);
		}
		
	}
	
	@Test
	public void broadcastDao(){
		BroadcastImageDao dao=ctx.getBean("broadcastImageDao",BroadcastImageDao.class);
		Map<String,Object> params=new HashMap<>();
		params.put("status", 1);
		List<Object> list=dao.findObjList(params);
		for(Object o:list){
			System.out.println(o);
		}
	}
	
	
	
	@After
	public void destory(){
		ctx.close();
	}
	
}
