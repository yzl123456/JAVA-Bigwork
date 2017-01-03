package bigWork.stu.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bigWork.stu.entity.Student;
import bigWork.stu.repository.StudentRepository;

@Service
public class StudentService {//service层处理业务相关逻辑
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAll(){//得到所有
		return studentRepository.getAll();
	}
	public Student getStudentByStuId(String string){//通过学号查
		return studentRepository.getByStuId(string);
	}
	public void saveAndFlush(Student stu){//保存更新
		studentRepository.saveAndFlush(stu);
	}
	public void delete(Integer id){//删除对应ID
		studentRepository.delete(id);
	}
	public Student getById(Integer id){//得到通过ID
		return studentRepository.getById(id);
	}
}
