package spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.bean.Student;
import spring.service.StudentService;
import spring.service.StudentServiceImpl;

@Controller
public class LoginController {

	@Autowired
	StudentService studentservice;

	@RequestMapping("/login")
	public ModelAndView action(
			@RequestParam(value = "buttonAction", required = false, defaultValue = "register") String action,
			@RequestParam(value = "acc", required = false, defaultValue = "") String acc,
			@RequestParam(value = "pass", required = false, defaultValue = "") String pass,
			@RequestParam(value = "sno", required = false, defaultValue = "") String sno,
			@RequestParam(value = "smail", required = false, defaultValue = "") String smail,
			@RequestParam(value = "oldPassword", required = false, defaultValue = "") String oldpassword,
			@RequestParam(value = "newPassword", required = false, defaultValue = "") String newpassword,
			@RequestParam(value = "confirmNewPassword", required = false, defaultValue = "") String confirmnewpassword,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in LoginController");

		if (action.equals("登入")) {
			Boolean r = studentservice.loginStudent(acc, pass);
			if (r) {

				Student data = studentservice.queryStudent(acc);
				ModelAndView mv = new ModelAndView("main/main");
				String username_encrypted=studentservice.addCookie(acc);
				Cookie c = new Cookie("username", username_encrypted);
				c.setMaxAge(3600 * 24 * 7);
				response.addCookie(c);
				String message = "歡迎登入";
				mv.addObject("message", message);
				mv.addObject("sno", data.getSno());
				mv.addObject("sname", data.getSname());
				mv.addObject("sbday", data.getSbday());
				mv.addObject("ssex", data.getSsex() == 0 ? "女" : "男");
				mv.addObject("smail", data.getSmail());
				mv.addObject("sid", data.getSid());
				return mv;
			} else {
				String message = "";
				ModelAndView mv = new ModelAndView("failed");
				message += "登入失敗";
				mv.addObject("message", message);
				return mv;
			}
//			Student s = new Student();
//			s.setSno("B000001");
//			s.setSname("Spring測試");
//			s.setSbday("1900-01-01");
//			s.setSsex(1);
//			s.setSmail("SpringTest@test.org");
//			s.setSpwd("Password");
//			s.setSid("Z123456789");
//			dao.insertStudent(s);
		}

		else if (action.equals("註冊")) {

			String message = "";
			ModelAndView mv = new ModelAndView("register/register");

			return mv;
		} else if (action.equals("forgetPasswordLogin")) {

			String message = "";
			ModelAndView mv = new ModelAndView("login/forgetPassword");

			return mv;
		} else if (action.equals("forgetPassword")) {
			Boolean r = studentservice.forgetPassword(sno, smail);
			if (r == true) {
				System.out.println("成功");
				ModelAndView mv = new ModelAndView("login/login2");
				mv.addObject("message", "請至信箱收信使用新密碼登入");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("login/forgetPassword");
				mv.addObject("message", "無此帳號或信箱");
				return mv;
			}
		} else if (action.equals("mainResetPassword")) {
			String message = "請輸入舊密碼以及欲設定的新密碼";
			ModelAndView mv = new ModelAndView("main/resetPassword");
			mv.addObject("sno", sno);
			mv.addObject("message", message);
			return mv;
		} else if (action.equals("resetPassword")) {
			if (!(newpassword.equals(confirmnewpassword))) {
				ModelAndView mv = new ModelAndView("main/resetPassword");
				mv.addObject("message", "新密碼不一致");
				mv.addObject("sno",sno);
				return mv;
			}
			boolean r = studentservice.resetPassword(sno, oldpassword, newpassword);
			if (r) {
				ModelAndView mv = new ModelAndView("login/login2");
				mv.addObject("message", "修改成功，請使用新密碼登入");
				Cookie c = new Cookie("username", "empty");
				c.setMaxAge(0);
				response.addCookie(c);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("main/resetPassword");
				mv.addObject("message", "舊密碼驗證錯誤");
				mv.addObject("sno",sno);
				return mv;
			}
		}

		else {
			String message = "";
			ModelAndView mv = new ModelAndView("failed");
			message += "登入/註冊失敗";
			mv.addObject("message", message);
			return mv;
		}
	}

}