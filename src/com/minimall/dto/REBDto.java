package com.minimall.dto;

import java.sql.Date;

public class REBDto {
	private int reb_no;
	private String reb_subject;
	private String m_id;
	private String reb_content;
	private String reb_secret;
	private String reb_category;
	private Date reb_date;
	private int reb_readcount;
	private String g_code;
	private int reb_ref;

	public int getReb_ref() {
		return reb_ref;
	}
	public void setReb_ref(int reb_ref) {
		this.reb_ref = reb_ref;
	}
	public int getReb_no() {
		return reb_no;
	}
	public void setReb_no(int reb_no) {
		this.reb_no = reb_no;
	}
	public String getReb_subject() {
		return reb_subject;
	}
	public void setReb_subject(String reb_subject) {
		this.reb_subject = reb_subject;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getReb_content() {
		return reb_content;
	}
	public void setReb_content(String reb_content) {
		this.reb_content = reb_content;
	}
	public String getReb_secret() {
		return reb_secret;
	}
	public void setReb_secret(String reb_secret) {
		this.reb_secret = reb_secret;
	}
	public String getReb_category() {
		return reb_category;
	}
	public void setReb_category(String reb_category) {
		this.reb_category = reb_category;
	}
	public Date getReb_date() {
		return reb_date;
	}
	public void setReb_date(Date reb_date) {
		this.reb_date = reb_date;
	}
	public int getReb_readcount() {
		return reb_readcount;
	}
	public void setReb_readcount(int reb_readcount) {
		this.reb_readcount = reb_readcount;
	}
	public String getG_code() {
		return g_code;
	}
	public void setG_code(String g_code) {
		this.g_code = g_code;
	}
	
	
	
}