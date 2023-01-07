package spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

//	@Resource
//	StudentServiceImpl ssi;
	@RequestMapping("/logout")

	public ModelAndView deleteCookie(HttpServletResponse response) {
		Cookie c = new Cookie("username", "empty");
		c.setMaxAge(0);
		response.addCookie(c);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

}