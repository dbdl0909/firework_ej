package com.minimall.dto;

import java.sql.Date;

public class QnaDto {
	private int qna_no;
	private String qna_subject;
	private String m_id;
	private String qna_content;
	private String qna_secret;
	private String qna_category;
	private Date qna_date;
	private int qna_readcount;
	private String g_code;
	private int qna_ref;
	
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_secret() {
		return qna_secret;
	}
	public void setQna_secret(String qna_secret) {
		this.qna_secret = qna_secret;
	}
	public String getQna_category() {
		return qna_category;
	}
	public void setQna_category(String qna_category) {
		this.qna_category = qna_category;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}
	public int getQna_readcount() {
		return qna_readcount;
	}
	public void setQna_readcount(int qna_readcount) {
		this.qna_readcount = qna_readcount;
	}
	public String getG_code() {
		return g_code;
	}
	public void setG_code(String g_code) {
		this.g_code = g_code;
	}
	public int getQna_ref() {
		return qna_ref;
	}
	public void setQna_ref(int qna_ref) {
		this.qna_ref = qna_ref;
	}
	
	@Override
	public String toString() {
		return "QnaDto [qna_no=" + qna_no + ", qna_subject=" + qna_subject + ", m_id=" + m_id + ", qna_content="
				+ qna_content + ", qna_secret=" + qna_secret + ", qna_category=" + qna_category + ", qna_date="
				+ qna_date + ", qna_readcount=" + qna_readcount + ", g_code=" + g_code + ", qna_ref=" + qna_ref + "]";
	}
}
