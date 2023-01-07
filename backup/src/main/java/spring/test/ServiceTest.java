package spring.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.dao.StudentDAO;
import spring.service.StudentService;
import spring.service.StudentServiceImpl;
public class ServiceTest {
	@Autowired
	StudentService studentService;
	
	@Test
	public void test() {
		studentService.queryStudent("ABCD");
		System.out.println();
	}
}
