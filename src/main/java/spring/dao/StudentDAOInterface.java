package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bean.Student;

public interface StudentDAOInterface extends JpaRepository<Student, String> {
	Student findBySnoAndSpwdAndActive(String sno, String spwd, int active); // loginStudent

	Student findBySno(String sno); // queryStudent

	Student findBySid(String sid); // queryStudent
	
	Student findByCookie(String cookie); // queryCookie

}
