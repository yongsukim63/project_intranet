package intranet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRegisterDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	/**사원 정보 추가*/ 
	public int insertRegister(AdminRegisterVO vo) {
		try {
			con=DBUtil.getCon();
			String sql=" INSERT INTO emp VALUES(?,?,?,?,?,?,sysdate,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getEmpid());
			ps.setString(2, vo.getEname());
			ps.setInt(3, vo.getDeptno());
			ps.setString(4, vo.getTel());
			ps.setString(5, vo.getRank());
			ps.setString(6, vo.getEmail());
			ps.setString(7, vo.getSex());
			ps.setString(8, vo.getPwd());
			int n=ps.executeUpdate();
			System.out.println(n);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			close();
		}
	}
	
	/**사원 메시지 테이블 & 시퀀스 생성*/
	public int createMsgTable(String msgTableName) {
		try {
			con=DBUtil.getCon();
			String sql="create table "+msgTableName+" (msgid number(5) primary key,"
						+"S_R char(1) NOT NULL,"
						+"empid number(4) NOT NULL,"
						+"title VARCHAR2(50),"
						+"msg VARCHAR2(200),"
						+"sdate DATE NOT NULL,"
						+"RD char(2) NOT NULL,"
						+"CONSTRAINT msg_"+msgTableName+"_fk FOREIGN KEY (empid) REFERENCES emp (empid)"
						+")";
			ps=con.prepareStatement(sql);
			System.out.println(sql);
			int n=ps.executeUpdate();
			System.out.println(n);
			sql="create sequence "+msgTableName+"_seq";
			ps=con.prepareStatement(sql);
			n=ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			close();
		}
	}

	/**사원 메시지 뷰 생성*/
	public int createMsgView(String msgTableName) {
		try {
			con = DBUtil.getCon();
			String sql = "create or replace view " + msgTableName + "_view as "
					+ "select msgid, s_r, m.empid empid, ename, title, msg, sdate, RD from " 
					+ msgTableName + " m, emp e where m.empid=e.empid";
			ps = con.prepareStatement(sql);
			int n = ps.executeUpdate();
			System.out.println(n);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close();
		}
	}

	/**DB 자료 반납*/
	public void close() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}