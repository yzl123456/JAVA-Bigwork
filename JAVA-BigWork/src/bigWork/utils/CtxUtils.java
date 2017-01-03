package bigWork.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bigWork.stu.service.StudentService;
import bigWork.user.service.UserService;
//得到spring IOC容器的工具类
public class CtxUtils {
	private static ApplicationContext ctx = null;
	public static ApplicationContext getContext(){
		if(ctx==null)
			ctx = new FileSystemXmlApplicationContext("/src/applicationContext.xml");
		return ctx;
	}
	public static StudentService getStudentService(){
		return getContext().getBean(StudentService.class);
	}
	public static UserService getUserService(){
		return getContext().getBean(UserService.class);
	}
}
