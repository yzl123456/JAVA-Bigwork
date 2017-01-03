package bigWork.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bigWork.user.entity.User;
import bigWork.user.repository.UserRepository;
@Service
public class UserService {//User��ҵ���
	@Autowired
	UserRepository userRepository;

	public void saveAndFlush(User user){//����
		userRepository.saveAndFlush(user);
	}
	
	public User getUserByName(String name){//ͨ������
		return userRepository.findByUsername(name);
	}
	
}
