package com.minimall.dto;

import java.sql.Date;

public class MemberDto { 

	private String m_id;
	private String m_pw;
	private String m_level;
	private String m_name;
	private String m_email;
	private Date m_date;
	private String m_addr;
	
	public String getm_id() {
		return m_id;
	}
	public void setm_id(String m_id) {
		this.m_id = m_id;
	} 
	public String getm_pw() {
		return m_pw;
	}
	public void setm_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getm_level() {
		return m_level;
	}
	public void setm_level(String m_level) {
		this.m_level = m_level;
	}
	public String getm_name() {
		return m_name;
	}
	public void setm_name(String m_name) {
		this.m_name = m_name;
	}
	public String getm_email() {
		return m_email;
	}
	public void setm_email(String m_email) {
		this.m_email = m_email;
	}
	public Date getm_date() {
		return m_date;
	}
	public void setm_date(Date m_date) {
		this.m_date = m_date;
	}
	public String getm_addr() {
		return m_addr;
	}
	public void setm_addr(String m_addr) {
		this.m_addr = m_addr;
	}


}
