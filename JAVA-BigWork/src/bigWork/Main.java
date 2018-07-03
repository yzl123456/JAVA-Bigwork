package bigWork;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import bigWork.utils.CtxUtils;
import bigWork.utils.EntityManagerUtil;

public class Main {
	public static void main(String[] args) {
		CtxUtils.getContext();//³õÊ¼»¯spring µÄIOCÈÝÆ÷
		new LoginWindow();
	}
}
