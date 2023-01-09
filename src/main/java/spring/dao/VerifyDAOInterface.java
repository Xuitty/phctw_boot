package spring.dao;

import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import spring.bean.Student;
import spring.bean.Verify;
//@Component
public interface VerifyDAOInterface extends JpaRepository<Verify, String> {
	Verify findBySno(String sno); // queryVerify
	
}
