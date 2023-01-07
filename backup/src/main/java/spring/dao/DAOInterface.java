package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bean.Student;

public interface DAOInterface extends JpaRepository<Student, String> {
	Student findBySnoAndSpwdAndActive(String sno, String spwd, int active); // loginStudent

	Student findBySno(String sno); // queryStudent

	Student findByCookie(String cookie); // queryCookie

	<S extends Student> S save(Student s); // insertStudent updateStudent activeAccount addCookie

}
