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
import spring.tools.IdVerify;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping
public class AngularController {
	Gson gson = new Gson();
	IdVerify idVerify = new IdVerify();
	@Autowired
	StudentService studentService;

	@PostMapping("login_ajax")
	public Student loginAjax(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		if (studentService.loginStudent(student.getSno(), student.getSpwd())) {
			student = studentService.queryStudent(student.getSno());
			student.setCookie(studentService.addCookie(student.getSno()));
			return student;
		}
		return student;
	}

	@PostMapping("main_ajax")
	public Student mainAjax(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		System.out.println("in: " + student.toString());
		student = studentService.queryStudent(studentService.queryCookie(student.getCookie()));
		System.out.println("out: " + student.toString());
		return student;
	}

	@PostMapping("registerCheck")
	public String registerCheck(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		System.out.println("in: " + student.toString());
		String result = null;
		if(student.getSid()==null || student.getSno()==null) {
			result="somethingEmpty";
			return result;
		}
		if (studentService.queryStudent(student.getSno()) != null) {
			result = "duplicatedSno";
			return result;
		}
		if (idVerify.idCardVerification(student.getSid()) == false) {
			result = "idError";
			return result;
		}
		if ((student.getSsex() == 1 && student.getSid().substring(1, 2).equals("2"))
				|| (student.getSsex() == 0 && student.getSid().substring(1, 2).equals("1"))) {
			result = "idSexError";
			return result;
		}
		result = "pass";
		return result;
	}
}
