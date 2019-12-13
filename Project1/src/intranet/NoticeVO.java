package intranet;

import java.sql.Date;

public class NoticeVO {
	
	private int empid;		//���
	
	int noticeid;			//���� �ε��� ��ȣ
	int priority;			//���� ��� �켱����
	String title;			//����
	String text;			//�ۼ���
	java.sql.Date wdate;	//�ۼ���
	
	public NoticeVO() {
		this.noticeid=0;
		this.priority=0;
		this.title=null;
		this.text=null;
		this.empid=0;
		this.wdate=null;
	}
	
	public NoticeVO(int noticeid, int priority, String title, int empid, Date wdate) {
		super();
		this.noticeid=noticeid;
		this.priority=priority;
		this.title=title;
		this.empid=empid;
		this.wdate=wdate;
	}
	
	//��ȣ, �������, ����, �ۼ���, �ۼ���, �ۼ���, ������
	public NoticeVO(int noticeid, int priority, String title, String text, int empid, Date wdate) {
		super();
		this.noticeid=noticeid;
		this.priority=priority;
		this.title=title;
		this.text=text;
		this.empid=empid;
		this.wdate=wdate;
	}
	
	//getter, setter ����
	public int getNoticeId() {
		return noticeid;
	}
	public void setNoticeId(int noticeid) {
		this.noticeid=noticeid;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority=priority;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text=text;
	}
	
	public int getEmpId() {
		return empid;
	}
	public void setEmpId(int empid) {
		this.empid=empid;
	}

	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate=wdate;
	}
	
}
