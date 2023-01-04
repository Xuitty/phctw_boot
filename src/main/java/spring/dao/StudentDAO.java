package spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Query;
import spring.bean.Student;
import spring.bean.Verify;
import spring.tools.MD5Tools;

@Repository
public class StudentDAO {

	@Autowired
	SessionFactory sessionFactory;
//	Session session;
	Transaction tr;

	public Boolean insertStudent(Student s) {

		Session session = sessionFactory.openSession();
		tr = session.beginTransaction();
		try {
			session.persist(s);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public List<Student> selectStudent() {
		return null;

	}

	public Boolean updateStudent(Student student) {
		Session session = sessionFactory.openSession();
		tr = session.beginTransaction();
		try {
			session.merge(student);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public Boolean deleteStudent(String sno) {
		return null;
	}

	public Student queryStudent(String sno) {
		Session session = sessionFactory.openSession();
		tr = session.beginTransaction();
		Student s = session.get(Student.class, sno);
		tr.commit();
		session.close();
		return s;
	}

	public Boolean loginStudent(String acc, String pass) {
		String hql = "from Student where sno like :sno and spwd like :spwd and active = 1";
		List<?> l = null;
		Session session = sessionFactory.openSession();
		tr = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("sno", acc);
			query.setParameter("spwd", pass);
			tr.commit();
			l = query.getResultList();
			if (l.isEmpty() || l == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public Boolean writeVerify(String sno, String verify) {
		Session session = sessionFactory.openSession();
		tr = session.beginTransaction();
		try {
			Verify v = new Verify(sno, verify);
			System.out.println(v.toString());
			session.merge(v);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}

		return true;

	}

	public Boolean deleteVerify(String sno) {
		Session session = null;
		try {
			Verify v = new Verify(sno, null);
			session = sessionFactory.openSession();
			tr = session.beginTransaction();
			Verify verify = session.merge(v);
			session.remove(verify);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public String queryVerify(String sno) {
		Session session = sessionFactory.openSession();
		String r = session.get(Verify.class, sno).getVerify();
		session.close();
		return r;

	}

	public Boolean activeAccount(String sno) {
		Session session = null;
		try {
			Student s = queryStudent(sno);
			session = sessionFactory.openSession();
			tr = session.beginTransaction();
			s.setActive(1);
			session.merge(s);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public String addCookie(String sno, String salt, String encrypted_sno) {
		Session session = sessionFactory.openSession();
		Student s = null;
		try {
			s = queryStudent(sno);
			session.close();
			session = sessionFactory.openSession();
			tr = session.beginTransaction();
			System.out.println(s);
			s.setCookie(encrypted_sno);
			s.setSalt(salt);
			session.merge(s);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return s.getCookie();
	}

	public String queryCookie(String cookie) {
		Session session = sessionFactory.openSession();
		tr = session.beginTransaction();
		try {
			String hql = "from Student where cookie like :cookie";
			List<?> l = null;
			Query query = session.createQuery(hql);
			query.setParameter("cookie", cookie);
			l = query.getResultList();
			tr.commit();
			if (l.isEmpty() || l == null) {
				return null;
			} else {
				Student student = (Student) l.get(0);
				return student.getSno();
			}
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Deprecated
	public Boolean forgetPassword(String sno, String smail) {
		// TODO Auto-generated method stub
		return null;
	}

}
