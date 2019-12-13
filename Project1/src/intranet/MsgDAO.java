package intranet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MsgDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String msgTableName = null;
	
	public String getMsgTableName() {
		return msgTableName;
	}

	public void setMsgTableName(String msgTableName) {
		this.msgTableName = msgTableName;
	}

	public MsgDAO() {
		super();
	}

	public MsgDAO(String msgTableName) {
		super();
		this.msgTableName = msgTableName;
	}

	private void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {}
	}//---------------------------------


	/**
	 * ���� �޸���� ����ϴ� �޼ҵ� - insert ���� ����
	 */
	public int getLastId() {
		try {
			int id=0;
			con=DBUtil.getCon();
			// select�� �ۼ�
			String sql="select max(msgid) msgid from "+msgTableName;
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				id=rs.getInt("id");
			}
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;	// ���� �߻��ô� ������ ��ȯ
		} finally {
			close();
		}
	}
	
	public ArrayList<MsgVO> makeList(ResultSet rs) 
	throws SQLException
	{
		ArrayList<MsgVO> vo=new ArrayList<MsgVO>();
		while (rs.next()) {
			MsgVO voTemp = new MsgVO(rs.getInt("msgid"), rs.getString("S_R"), 
					rs.getInt("empid"), rs.getString("ename"),
					rs.getString("title"), rs.getString("msg"),
					rs.getDate("sdate"), rs.getString("RD"));
			vo.add(voTemp);
		}
		return vo;
	}
	
	public ArrayList<MsgVO> listMsg(String s_R){
		try {
			con=DBUtil.getCon();
			// select�� �ۼ�
			String sql="select msgid, s_r, empid, ename, title, "
					+ "rpad(msg,100,' ') msg, sdate, rd  from "
					+msgTableName+"_view "
					+"where S_R= ? order by 1 desc";
			ps=con.prepareStatement(sql);
			ps.setString(1, s_R.trim());
			rs=ps.executeQuery();
			ArrayList<MsgVO> vo=makeList(rs);
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	// ���� �߻��ô� null�� ��ȯ
		} finally {
			close();
		}
	}//-----------------------------------

	public ArrayList<MsgVO> findMsg(int type, String s_R, String keyword){
		/*
		 * type
		 * 0: �����ȣ(�ۼ���/������)�� �˻�, �����ε�
		 * 1. �ۼ��ڷ� �˻�, 
		 * 2: �ۼ��Ϸ� �˻�
		 * 3: �޸𳻿����� �˻�
		 */
		try{
			con=DBUtil.getCon();
			//where���� ���� select�� �ۼ�
			String sql="select msgid, ename, s_r, empid, title, rpad(msg,100,' ') msg, sdate, rd "
					+"from "+msgTableName+"_view where s_r= ? and ";
			String colName="";
			keyword="%"+keyword+"%";
			switch (type) {
			case 1:
				colName="ename";
				break;
			case 2:
				colName="sdate";
				break;
			case 3:
				colName="msg";
				break;
			default:
				return null;
			}
			sql+=colName+" like ? order by 1 asc";
			// System.out.println(sql);
			ps=con.prepareStatement(sql);
			ps.setString(1, s_R);
			ps.setString(2, keyword);
			rs=ps.executeQuery();
			ArrayList<MsgVO> vo=makeList(rs);
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	// ���� �߻��ô� null�� ��ȯ
		} finally {
			close();
		}
	}
	
	public ArrayList<MsgVO> findMsg(String s_R, int empID){
		// 0: �����ȣ(�ۼ���/������)�� �˻�
		try{
			con=DBUtil.getCon();
			//where���� ���� select�� �ۼ�
			String sql="select msgid, ename, s_r, empid, title, rpad(msg,100,' ') msg, sdate, rd "
					+"from "+msgTableName+"_view where empid =? and s_r=?";
			ps=con.prepareStatement(sql);
			// System.out.println(sql+", "+empID+", "+s_R);
			ps.setInt(1, empID);
			ps.setString(2, s_R);
			rs=ps.executeQuery();
			ArrayList<MsgVO> vo=makeList(rs);
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	// ���� �߻��ô� null�� ��ȯ
		} finally {
			close();
		}
	}
	
	public int insertMsg(MsgVO msg, int empId) {
		try {
			con=DBUtil.getCon();
			String tableName = "msg"+empId;
			// insert�� �ۼ�
			String sql="insert into "+tableName
					+" values("+tableName+"_seq.nextval,?,?,?,?,sysdate,?)";
			// System.out.println(msg.toString());
			ps=con.prepareStatement(sql);
			// System.out.println(sql);
			// System.out.println(msg.toString());
			ps.setString(1, msg.getS_R());
			ps.setInt(2, msg.getEmpid());
			ps.setString(3, msg.getTitle());
			ps.setString(4, msg.getMsg());
			ps.setString(5, msg.getrD());
			
			int n=ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;	// ���� �߻��ô� ������ ��ȯ
		} finally {
			close();
		}
	}
	
	public int deleteMsg(Integer msgid) {
		try {
			con=DBUtil.getCon();
			// delete�� �ۼ�
			String sql="delete from "+msgTableName+" where msgid=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, msgid);
			int n=ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;	// ���� �߻��ô� ������ ��ȯ
		} finally {
			close();
		}
	}
}
