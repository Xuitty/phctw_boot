package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.Student;

public interface StudentService {
	public List<Student> selectStudent(); // 列出所有學生資料

	public Boolean insertStudent(Student student); // 新增學生資料

	public Boolean updateStudent(Student student); // 修改學生資料

	public Boolean deleteStudent(String sno); // 刪除學生資料

	public Student queryStudent(String sno); // 查詢學生資料

	public Boolean loginStudent(String acc, String pass); // 學生登入檢查

	public Boolean writeVerify(Student student); // 驗證碼寫入

	public Boolean deleteVerify(String sno); // 驗證碼清除

	public String queryVerify(String sno); // 驗證碼查詢

	public Boolean activeAccount(String sno); // 啟用帳號

	public Boolean forgetPassword(String sno, String smail);

	public Boolean resetPassword(String sno, String oldpassword, String newpassword);

	public String addCookie(String sno);

	public String queryCookie(String cookie);

}
