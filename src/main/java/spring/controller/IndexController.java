package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import spring.bean.Student;
import spring.service.StudentService;
import spring.service.StudentServiceImpl;

@Controller
public class IndexController {

	String message = "";

	@Autowired
	StudentService studentservice;

	@GetMapping("/redirect")
	public ModelAndView goLogin(@CookieValue(value = "username",required = false) String username) {
		username = studentservice.queryCookie(username);
		if (username != null) {
			Student data = studentservice.queryStudent(username);
			ModelAndView mv = new ModelAndView("main/main");
			message = "歡迎登入";
			mv.addObject("message", message);
			mv.addObject("sno", data.getSno());
			mv.addObject("sname", data.getSname());
			mv.addObject("sbday", data.getSbday());
			mv.addObject("ssex", data.getSsex() == 0 ? "女" : "男");
			mv.addObject("smail", data.getSmail());
			mv.addObject("sid", data.getSid());
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("login/login2");
			return mv;
		}

	}

}