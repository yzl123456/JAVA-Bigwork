package bigWork;

import bigWork.utils.CtxUtils;

public class Main {
	public static void main(String[] args) {
		CtxUtils.getContext();//��ʼ��spring ��IOC����
		new LoginWindow();
	}
}
