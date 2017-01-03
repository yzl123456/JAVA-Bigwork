package bigWork.utils;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
//得到entity manager的工具类
public class EntityManagerUtil {
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static EntityTransaction transaction;
	public static EntityManager getEntityManager(){
		//1. 创建 EntitymanagerFactory
		String persistenceUnitName = "jpa-1";
		
		Map<String, Object> properites = new HashMap<String, Object>();
		properites.put("hibernate.show_sql",true);
		
		entityManagerFactory = 
				//Persistence.createEntityManagerFactory(persistenceUnitName);
				Persistence.createEntityManagerFactory(persistenceUnitName, properites);
				
		//2. 创建 EntityManager. 类似于 Hibernate 的 SessionFactory
		entityManager = entityManagerFactory.createEntityManager();
		
		//3. 开启事务
		transaction = entityManager.getTransaction();
		transaction.begin();
		return entityManager;
	}
	public static void closeEntityManager(){
		//5. 提交事务
		transaction.commit();
		
		//6. 关闭 EntityManager
		entityManager.close();
		
		//7. 关闭 EntityManagerFactory
		entityManagerFactory.close();
	}

}
