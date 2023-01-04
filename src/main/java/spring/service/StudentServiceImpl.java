package spring.service;

import java.net.URLEncoder;
import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import spring.bean.Student;
import spring.bean.Verify;
import spring.dao.StudentDAOInterface;
import spring.dao.VerifyDAOInterface;
import spring.dao.StudentDAO;
import spring.tools.MD5Tools;

@EnableTransactionManagement
@Service
public class StudentServiceImpl implements StudentService {
	MD5Tools md5 = new MD5Tools();
	@Autowired
	VerifyDAOInterface verifyDAOInterface;
	@Autowired
	StudentDAOInterface studentDAOInterface;

	@Override
	public List<Student> selectStudent() {
		return null;
	}

	@Transactional
	@Override
	public Boolean insertStudent(Student student) {
//		StudentDAO s = new StudentDAO();
		String salt = saltGen();
		student.setSpwd(md5.string2MD5(student.getSpwd() + salt));
		student.setSalt_pass(salt);
		boolean r = studentDAOInterface.save(student)==null?false:true;
		return r;
	}

	@Transactional
	@Override
	public Boolean updateStudent(Student student) {
//		StudentDAO s = new StudentDAO();
		Boolean r = studentDAOInterface.save(student)==null?false:true;
		return r;
	}

	@Override
	public Boolean deleteStudent(String sno) {
		return null;
	}

	@Transactional
	@Override
	public Student queryStudent(String sno) {
//		StudentDAO s = new StudentDAO();
		Student r = studentDAOInterface.findBySno(sno);
		return r;
	}

	@Transactional
	@Override
	public Boolean loginStudent(String acc, String pass) {
		// TODO Auto-generated method stub
		String salt = queryStudent(acc).getSalt_pass();
		pass = md5.string2MD5(pass + salt);
		Boolean r = studentDAOInterface.findBySnoAndSpwdAndActive(acc, pass, 1)==null?false:true;
		return r;
	}

	@Transactional
	@Override
	public Boolean writeVerify(Student student) {
		String verify = verifyGen();
		sendVerify(student.getSname(), student.getSmail(), verify);
		Verify v= new Verify(student.getSno(),verify);
		Boolean r = verifyDAOInterface.save(v)==null?false:true;
		return r;
	}

	@Transactional
	@Override
	public Boolean deleteVerify(String sno) {
		verifyDAOInterface.deleteById(sno);
		return true;

	}

	@Transactional
	@Override
	public String queryVerify(String sno) {
		String r = verifyDAOInterface.findBySno(sno).getVerify();
		return r;

	}

	@Transactional
	@Override
	public Boolean activeAccount(String sno) {
		Student s = studentDAOInterface.findBySno(sno);
		s.setActive(1);
		Boolean r = studentDAOInterface.save(s)==null?false:true;
		return r;
	}

	@Transactional
	@Override
	public Boolean forgetPassword(String sno, String smail) {
		Student s = studentDAOInterface.findBySno(sno);
		if (s.getSmail().equals(smail)) {
			String newPassword = newPasswordGen();
			String salt = saltGen();
			s.setSpwd(new MD5Tools().string2MD5(newPassword + salt));
			s.setSalt_pass(salt);
			Boolean r = studentDAOInterface.save(s)==null?false:true;
			System.out.println(r);
			sendNewPassword(sno, studentDAOInterface.findBySno(sno).getSmail(), newPassword);
			return r == true ? true : false;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean resetPassword(String sno, String oldpassword, String newpassword) {
		if (queryStudent(sno).getSpwd()
				.equals(new MD5Tools().string2MD5(oldpassword + (queryStudent(sno).getSalt_pass())))) {
			Student s = queryStudent(sno);
			String salt = saltGen();
			s.setSpwd(new MD5Tools().string2MD5(newpassword + salt));
			s.setSalt_pass(salt);
			updateStudent(s);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public String addCookie(String sno) {
		String salt = saltGen();
		String encrypted_sno = (new MD5Tools().string2MD5(sno + salt));
		Student s = studentDAOInterface.findBySno(sno);
		s.setCookie(encrypted_sno);
		s.setSalt(salt);
		String r = studentDAOInterface.save(s).getCookie();
		return r;
	}

	@Override
	@Transactional
	public String queryCookie(String cookie) {
		if(cookie==null) {
			return null;
		}
		System.out.println(cookie);
		String r = (studentDAOInterface.findByCookie(cookie)).getSno();
		return r;
	}

	public int[] add(int amount) {
//		StudentServiceImpl ssi = new StudentServiceImpl();
		int err = 0;
		int[] r = new int[2];
		ArrayList<Student> curList = (ArrayList<Student>) selectStudent();
		int lastNo = Integer.parseInt(curList.get(curList.size() - 1).getSno().substring(1,
				curList.get(curList.size() - 1).getSno().length()));
		for (int i = 0; i < amount; i++) {
			try {
				int cal = lastNo + i + 1;
				String cal_str = null;
				Student s1 = new Student();
				cal_str = String.format("%06d", cal);
				s1.setSno("A" + cal_str);
				s1.setSbday(bdayGen());
				s1.setSsex((int) Math.round(Math.random()));
				s1.setSname(nameGen(s1.getSsex()));
				s1.setSid(idCardProduce(s1.getSsex()));
				s1.setSmail("PhctwStudent" + cal_str + "@gmail.com");
				s1.setSpwd("Student" + cal_str);
				boolean rr = insertStudent(s1);
				if (!rr) {
					err++;
				}

			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			r[0] = amount - err;
			r[1] = err;
		}
		return r;
	}

	public List<Student> query() {
		ArrayList<Student> r = (ArrayList<Student>) selectStudent();
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		for (int i = 0; i < r.size(); i++) {
			Student a = r.get(i);
			c.set(Integer.parseInt(a.getSbday().substring(0, 4)), Integer.parseInt(a.getSbday().substring(5, 7)) - 1,
					Integer.parseInt(a.getSbday().substring(8, 10)));
			c1.setTime(c1.getTime());
			int age = c1.get(Calendar.YEAR) - c.get(Calendar.YEAR);
			a.setSbday(String.valueOf(age));
		}

		return r;

	}

	private String bdayGen() {
		int year = (int) (Math.random() * 60) + 1950;
		int month = (int) Math.round(Math.random() * 11 + 1);
		int day = (int) Math.round(Math.random() * 30 + 1);
		if (year % 4 == 0) {

			if (month == 4 || month == 6 || month == 9 || month == 11) {
				while (day > 30) {
					day = (int) Math.round(Math.random() * 30 + 1);
				}
			} else if (month == 2) {
				if (day > 29) {
					day = (int) Math.round(Math.random() * 28 + 1);
				}
			}

		} else {
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				while (day > 30) {
					day = (int) Math.round(Math.random() * 30 + 1);
				}
			} else if (month == 2) {
				if (day > 28) {
					day = (int) Math.round(Math.random() * 27 + 1);
				}
			}
		}
		String result = year + "/" + month + "/" + day;
//		System.out.println(result);
		return result;
	}

	private String idCardProduce(int sex) {
		if (sex == 0 || sex == 1) {
			Character[] first_check = new Character[26];
			int[] city = { 10, 11, 12, 13, 14, 15, 16, 17, 34, 18, 19, 20, 21, 22, 35, 23, 24, 25, 26, 27, 28, 29, 32,
					30, 31, 33 };
			int check_num = 0, generated_digit = 0;
			String result = "";
			Random r = new Random();
			for (int i = 0; i < 26; i++) {
				first_check[i] = (char) (65 + i);
			}

			result += (char) (r.nextInt(90 - 65 + 1) + 65);
			String city_code = String.valueOf(city[Arrays.binarySearch(first_check, result.charAt(0))]);
			check_num += Integer.parseInt(String.valueOf(city_code.charAt(0))) * 1
					+ Integer.parseInt(String.valueOf(city_code.charAt(1))) * 9;
			if (sex == 1) {
				generated_digit = sex;
			} else {
				generated_digit = 2;
			}
			result += generated_digit;
			check_num += generated_digit * 8;

			for (int i = 2; i < 9; i++) {
				generated_digit = r.nextInt(10);
				result += generated_digit;
				check_num += generated_digit * (9 - i);
			}
			if (check_num % 10 == 0) {
				result += 0;
			} else {
				int last_digit = 0;
				last_digit = 10 - check_num % 10;
				result += last_digit;
			}

			return result;
		} else {
			return "";
		}
	}

	private String nameGen(int sex) {
		String result = "", a, b, c;
		int buffer = 0;
		// 百家姓
		String surname = "趙錢孫李周吳鄭王馮陳褚衛蔣沈韓楊朱秦尤許何呂施張孔曹嚴華金魏陶姜戚謝鄒喻柏水竇章雲蘇潘葛奚範彭郎魯韋昌馬苗鳳花方俞任袁柳酆鮑史唐費廉岑薛雷賀倪湯滕殷羅畢郝鄔安常樂於時傅皮卞齊康伍餘元卜顧孟平黃和穆蕭尹姚邵湛汪祁毛禹狄米貝明臧計伏成戴談宋茅龐熊紀舒屈項祝董樑杜阮藍閔席季麻強賈路婁危江童顏郭梅盛林刁鍾徐邱駱高夏蔡田樊胡凌霍虞萬支柯咎管盧莫經房裘繆幹解應宗宣丁賁鄧鬱單杭洪包諸左石崔吉鈕龔程嵇邢滑裴陸榮翁荀羊於惠甄魏加封芮羿儲靳汲邴糜鬆井段富巫烏焦巴弓牧隗山谷車侯宓蓬全郗班仰秋仲伊宮寧仇欒暴甘鈄厲戎祖武符劉姜詹束龍葉幸司韶郜黎薊薄印宿白懷蒲臺從鄂索鹹籍賴卓藺屠蒙池喬陰鬱胥能蒼雙聞莘黨翟譚貢勞逄姬申扶堵冉宰酈雍卻璩桑桂濮牛壽通邊扈燕冀郟浦尚農溫別莊晏柴瞿閻充慕連茹習宦艾魚容向古易慎戈廖庚終暨居衡步都耿滿弘匡國文寇廣祿闕東毆殳沃利蔚越夔隆師鞏厙聶晁勾敖融冷訾辛闞那簡饒空曾毋沙乜養鞠須豐巢關蒯相查後江紅遊竺權逯蓋益桓公万俟司馬上官歐陽夏侯諸葛聞人東方赫連皇甫尉遲公羊澹臺公冶宗政濮陽淳于仲孫太叔申屠公孫樂正軒轅令狐鍾離閭丘長孫慕容鮮于宇文司徒司空亓官司寇仉督子車顓孫端木巫馬公西漆雕樂正壤駟公良拓拔夾谷宰父谷粱晉楚閻法汝鄢塗欽段幹百里東郭南門呼延歸海羊舌微生嶽帥緱亢況後有琴樑丘左丘東門西門商牟佘佴伯賞南宮墨哈譙笪年愛陽佟";

		// 女生名
		String girlName = "秀娟英華慧巧美娜靜淑惠珠翠雅芝玉萍紅娥玲芬芳燕彩春菊蘭鳳潔梅琳素雲蓮真環雪榮愛妹霞香月鶯媛豔瑞凡佳嘉瓊勤珍貞莉桂娣葉璧璐婭琦晶妍茜秋珊莎錦黛青倩婷姣婉嫺瑾穎露瑤怡嬋雁蓓紈儀荷丹蓉眉君琴蕊薇菁夢嵐苑婕馨瑗琰韻融園藝詠卿聰瀾純毓悅昭冰爽琬茗羽希寧欣飄育瀅馥筠柔竹靄凝曉歡霄楓芸菲寒伊亞宜可姬舒影荔枝思麗";

		// 男生名
		String boyName = "偉剛勇毅俊峯強軍平保東文輝力明永健世廣志義興良海山仁波寧貴福生龍元全國勝學祥才發武新利清飛彬富順信子傑濤昌成康星光天達安巖中茂進林有堅和彪博誠先敬震振壯會思羣豪心邦承樂紹功鬆善厚慶磊民友裕河哲江超浩亮政謙亨奇固之輪翰朗伯宏言若鳴朋斌樑棟維啓克倫翔旭鵬澤晨辰士以建家致樹炎德行時泰盛雄琛鈞冠策騰楠榕風航弘";
		if (sex == 1) {
			buffer = (int) Math.round(Math.random() * 559);
			a = surname.substring(buffer, buffer + 1);
			buffer = (int) Math.round(Math.random() * 148);
			b = boyName.substring(buffer, buffer + 1);
			buffer = (int) Math.round(Math.random() * 148);
			c = boyName.substring(buffer, buffer + 1);
			result = a + b + c;
			return result;
		} else if (sex == 0) {
			buffer = (int) Math.round(Math.random() * 559);
			a = surname.substring(buffer, buffer + 1);
			buffer = (int) Math.round(Math.random() * 146);
			b = girlName.substring(buffer, buffer + 1);
			buffer = (int) Math.round(Math.random() * 146);
			c = girlName.substring(buffer, buffer + 1);
			result = a + b + c;
			return result;
		} else {
			return result;
		}
	}

	private String verifyGen() {
		String r = "";
		for (int i = 0; i < 6; i++) {
			r += (int) Math.floor(Math.random() * 10);
		}
		return r;
	}

	public String saltGen() {

		String r = "";
		Character[] upper = new Character[26];
		for (int i = 0; i < 26; i++) {
			upper[i] = (char) (65 + i);
		}
		Character[] lower = new Character[26];
		for (int i = 0; i < 26; i++) {
			lower[i] = (char) (97 + i);
		}
		Character[] number = new Character[10];
		for (int i = 0; i < 10; i++) {
			number[i] = (char) (48 + i);
		}
		ArrayList<Character> all = new ArrayList<>();
		all.addAll(Arrays.asList(upper));
		all.addAll(Arrays.asList(lower));
		all.addAll(Arrays.asList(number));

		for (int i = 0; i < 32; i++) {
			r += all.get((int) (Math.floor(Math.random() * 62)));
		}
		return r;
	}

	private String newPasswordGen() {
		Character[] upper = new Character[26];
		for (int i = 0; i < 26; i++) {
			upper[i] = (char) (65 + i);
		}
		Character[] lower = new Character[26];
		for (int i = 0; i < 26; i++) {
			lower[i] = (char) (97 + i);
		}
		Character[] number = new Character[10];
		for (int i = 0; i < 10; i++) {
			number[i] = (char) (48 + i);
		}
		ArrayList<Character> all = new ArrayList<>();
		all.addAll(Arrays.asList(upper));
		all.addAll(Arrays.asList(lower));
		all.addAll(Arrays.asList(number));
		String result = "";
		for (int i = 0; i < 12; i++) {
			result += all.get((int) (Math.floor(Math.random() * 62)));
		}

		return result;
	}

	private void sendVerify(String sname, String smail, String verify) {

		String to = smail;
		String subject = "學生管理系統註冊驗證碼";
		String msg = "親愛的 " + sname + "，您的驗證碼為: " + verify;
		final String from = "sopdf2@gmail.com";
		final String password = "gtclesnkitaxafed";

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// session.setDebug(true);
		Transport transport = null;
		try {
			transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			MimeMessage message = new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			message.setContent(msg, "text/plain; charset=UTF-8");
//			message.setContent(message, "text/plain; charset=UTF-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendNewPassword(String sname, String smail, String newPassword) {

		String to = smail;
		String subject = "學生管理系統補發密碼";
		String msg = "親愛的 " + sname + "，您的新密碼為: " + newPassword;
		final String from = "sopdf2@gmail.com";
		final String password = "gtclesnkitaxafed";

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// session.setDebug(true);
		Transport transport = null;
		try {
			transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			MimeMessage message = new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			message.setContent(msg, "text/plain; charset=UTF-8");
//			message.setContent(message, "text/plain; charset=UTF-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
