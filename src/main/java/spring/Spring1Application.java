package spring;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.persistence.EntityManagerFactory;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"spring.bean","spring.controller","spring.dao","spring.service","spring.tools"})
//@EntityScan("spring.bean")
@EnableAutoConfiguration
//@Controller
//@EnableTransactionManagement
public class Spring1Application {
	public final static String KEY="cufgraxhdiqkcpyg";
	public static void main(String[] args) {
		SpringApplication.run(Spring1Application.class, args);
	}
//	@GetMapping("/")
//	public ModelAndView test() {
//		ModelAndView mv = new ModelAndView("index");
//		System.out.println("main");
//		return mv;
//	}

//    @Bean
//    LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(data);
//    }

}
