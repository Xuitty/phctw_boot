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
@RequestMapping
public class TestController {
	Gson gson = new Gson();

	@Autowired
	StudentService studentService;

	@PostMapping("test")
	public Student test(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		if (studentService.loginStudent(student.getSno(), student.getSpwd())) {
			student = studentService.queryStudent(student.getSno());
			student.setCookie(studentService.addCookie(student.getSno()));
			return student;
		}
		return student;
	}
	
	@PostMapping("main_ajax")
	public Student action(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		System.out.println("in: " + student.toString());
		student=studentService.queryStudent(studentService.queryCookie(student.getCookie()));
		System.out.println("out: "+student.toString());
		return student;
	}
}
