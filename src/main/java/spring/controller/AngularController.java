package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import spring.bean.Student;
import spring.bean.Verify;
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
//		System.out.println("in: " + student.toString());
		student = studentService.queryStudent(studentService.queryCookie(student.getCookie()));
//		System.out.println("out: " + student.toString());
		return student;
	}

	@PostMapping("registerCheck")
	public String registerCheck(@RequestBody String s) {
//		System.out.println(s);
		Student student = gson.fromJson(s, Student.class);
//		System.out.println("in: " + student.toString());
		String result = null;
		if (student.getSid() == null || student.getSno() == null || student.getSid().equals("")
				|| student.getSno().equals("")) {
			result = "somethingEmpty";
			return result;
		}
		if (studentService.queryStudent(student.getSno()) != null) {
			result = "duplicatedSno";
			return result;
		}
		if (studentService.queryStudentBySid(student.getSid()) != null) {
			result = "duplicatedSid";
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

	@PostMapping("register_ajax")
	public String registerAjax(@RequestBody String s) {
//		System.out.println(s);
		Student student = gson.fromJson(s, Student.class);
		student.setActive(0);
		Boolean r1 = studentService.insertStudent(student);
		Boolean r2 = studentService.writeVerify(student);
		if (r1 && r2) {
			return "true";
		}
		return "false";
	}

	@PostMapping("verify_ajax")
	public String verifyAjax(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		Verify verify = new Verify(student.getSno(), student.getSid());
		if(studentService.queryStudent(verify.getSno()).getActive()==1) {
			return "alredyActive";
		}
		if (verify.getVerify().equals(studentService.queryVerify(verify.getSno()))) {
			studentService.activeAccount(verify.getSno());
			studentService.addCookie(verify.getSno());
			studentService.deleteVerify(verify.getSno());
			return studentService.queryStudent(verify.getSno()).getCookie();
		}
		return "false";
	}
	
	@PostMapping("resend_ajax")
	public String resendAjax(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		if(studentService.queryStudent(student.getSno())==null) {
			return "snoNotExist";
		}
		if(!(studentService.queryStudent(student.getSno()).getSmail().equals((student.getSmail())))) {
			return "wrongSmail";
		}
		if(studentService.queryStudent(student.getSno()).getActive()==1) {
			return "alredyActive";
		}
		studentService.writeVerify(studentService.queryStudent(student.getSno()));
		return "true";
	}
	
	@PostMapping("forgetPassword_ajax")
	public String forgetPasswordAjax(@RequestBody String s) {
		Student student = gson.fromJson(s, Student.class);
		System.out.println(student);
		if(studentService.queryStudent(student.getSno())==null) {
			return "snoNotExist";
		}
		if(!(studentService.queryStudent(student.getSno()).getSmail().equals(student.getSmail()))) {
			return "wrongSmail";
		}
		studentService.forgetPassword(student.getSno(), student.getSmail());
		return "true";
	}

}
