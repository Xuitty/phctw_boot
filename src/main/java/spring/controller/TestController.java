package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.bean.Student;
import spring.dao.DAOInterface;

@RestController
public class TestController {
	@Autowired
	DAOInterface daoInterface;
	
	@GetMapping("/test")
	public String test() {
		Student s = new Student();
		s.setSno("ABC1234");
		String test = 
//				daoInterface.findBySno("ABCD").toString();
//				daoInterface.queryByActive(1).toString();
//				daoInterface.save(s).toString();
				daoInterface.findBySnoAndSpwdAndActive("ABCD", "84eeb59c76d634ae461fb87a55010fca", 1).toString();
		return test;
	}
}
