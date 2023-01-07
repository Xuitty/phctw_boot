package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import spring.bean.Student;
import spring.service.StudentService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("")
public class TestController {
	Gson g = new Gson();
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("test")
	public Student test(@RequestBody String s) {
		Student student=g.fromJson(s, Student.class);
		System.out.println("in: "+student.toString());
		if(studentService.loginStudent(student.getSno(), student.getSpwd())) {
			student = studentService.queryStudent(student.getSno());
			System.out.println("out: "+student.toString());
			return student;
		}
		return student;
	}
}
