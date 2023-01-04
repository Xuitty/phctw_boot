package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bean.Student;
import spring.bean.Verify;

public interface VerifyDAOInterface extends JpaRepository<Verify, String> {

	Verify findBySno(String sno); // queryVerify
	
}
