package bigWork.stu.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bigWork.stu.entity.Student;

//�ֿ�㣬��װ�������ݿ��һЩ����
public interface StudentRepository extends JpaRepository<Student, Integer>{
	@Query("FROM Student")
	List<Student> getAll();
	Student getByStuId(String StuId);
	Student getById(Integer id);
}
