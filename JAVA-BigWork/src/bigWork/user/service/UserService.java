package bigWork.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bigWork.user.entity.User;
import bigWork.user.repository.UserRepository;
@Service
public class UserService {//User的业务层
	@Autowired
	UserRepository userRepository;

	public void saveAndFlush(User user){//报错
		userRepository.saveAndFlush(user);
	}
	
	public User getUserByName(String name){//通过名字
		return userRepository.findByUsername(name);
	}
	
}
