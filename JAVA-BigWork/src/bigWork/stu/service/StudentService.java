package bigWork.stu.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bigWork.stu.entity.Student;
import bigWork.stu.repository.StudentRepository;

@Service
public class StudentService {//service�㴦��ҵ������߼�
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAll(){//�õ�����
		return studentRepository.getAll();
	}
	public Student getStudentByStuId(String string){//ͨ��ѧ�Ų�
		return studentRepository.getByStuId(string);
	}
	public void saveAndFlush(Student stu){//�������
		studentRepository.saveAndFlush(stu);
	}
	public void delete(Integer id){//ɾ����ӦID
		studentRepository.delete(id);
	}
	public Student getById(Integer id){//�õ�ͨ��ID
		return studentRepository.getById(id);
	}
}
