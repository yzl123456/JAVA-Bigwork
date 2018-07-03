package bigWork.utils;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
//�õ�entity manager�Ĺ�����
public class EntityManagerUtil {
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static EntityTransaction transaction;
	public static EntityManager getEntityManager(){
		//1. ���� EntitymanagerFactory
		String persistenceUnitName = "jpa-1";
		
		Map<String, Object> properites = new HashMap<String, Object>();
		properites.put("hibernate.show_sql",true);
	
		entityManagerFactory = 
				Persistence.createEntityManagerFactory(persistenceUnitName);
//				Persistence.createEntityManagerFactory(persistenceUnitName, properites);
				
		//2. ���� EntityManager. ������ Hibernate �� SessionFactory
		entityManager = entityManagerFactory.createEntityManager();
		
		//3. ��������
		transaction = entityManager.getTransaction();
		transaction.begin();
		return entityManager;
	}
	public static void closeEntityManager(){
		//5. �ύ����
		transaction.commit();
		
		//6. �ر� EntityManager
		entityManager.close();
		
		//7. �ر� EntityManagerFactory
		entityManagerFactory.close();
	}

}
