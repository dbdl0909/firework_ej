package com.minimall.listener;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@WebListener
public class LoginCheck implements HttpSessionBindingListener {
	private static LoginCheck loginManager = null;

	//�α����� �����ڸ� ������� �ؽ����̺�
	private static Hashtable loginUsers = new Hashtable();

	/*
	* �̱��� ���� ���
	*/
	public static synchronized LoginCheck getInstance(){
	if(loginManager == null){
	loginManager = new LoginCheck();
	}
	return loginManager;
	}


	/*
	* �� �޼ҵ�� ������ ��������� ȣ��ȴ�.(session.setAttribute("login", this))
	* Hashtable�� ���ǰ� ������ ���̵� �����Ѵ�.
	*/
	public void valueBound(HttpSessionBindingEvent event) {
	//session���� put�Ѵ�.
	loginUsers.put(event.getSession(), event.getName());
	System.out.println(event.getName() + "���� �α��� �ϼ̽��ϴ�.");
	System.out.println("���� ������ �� : " + getUserCount());
	}


	/*
	* �� �޼ҵ�� ������ �������� ȣ��ȴ�.(invalidate)
	* Hashtable�� ����� �α����� ������ ������ �ش�.
	*/
	public void valueUnbound(HttpSessionBindingEvent event) {
	//session���� ã�Ƽ� �����ش�.
	loginUsers.remove(event.getSession());
	System.out.println(" " + event.getName() + "���� �α׾ƿ� �ϼ̽��ϴ�.");
	System.out.println("���� ������ �� : " + getUserCount());
	}


	/*
	* �Է¹��� ���̵� �ؽ����̺��� ����. 
	* @param userID ����� ���̵�
	* @return void
	*/ 
	public void removeSession(String userId){
	Enumeration e = loginUsers.keys();
	HttpSession session = null;
	while(e.hasMoreElements()){
	session = (HttpSession)e.nextElement();
	if(loginUsers.get(session).equals(userId)){
	//������ invalidate�ɶ� HttpSessionBindingListener�� 
	//�����ϴ� Ŭ������ valueUnbound()�Լ��� ȣ��ȴ�.
	session.invalidate();
	}
	}
	}


	/*
	* ����ڰ� �Է��� ID, PW�� �´��� Ȯ���ϴ� �޼ҵ�
	* @param userID ����� ���̵�
	* @param userPW ����� �н�����
	* @return boolean ID/PW�� ��ġ�ϴ� �� ����
	*/
	public boolean isValid(String userId, String userPw){

	/*
	* �̺κп� Database ������ ����.
	*/
	return true;
	}


	/*
	* �ش� ���̵��� ���� ����� �������ؼ� 
	* �̹� ������� ���̵������� Ȯ���Ѵ�.
	* @param userID ����� ���̵�
	* @return boolean �̹� ��� ���� ��� true, ������� �ƴϸ� false
	*/
	public boolean isUsing(String userID){
	return loginUsers.containsValue(userID);
	}


	/*
	* �α����� �Ϸ��� ������� ���̵� ���ǿ� �����ϴ� �޼ҵ�
	* @param session ���� ��ü
	* @param userID ����� ���̵�
	*/
	public void setSession(HttpSession session, String userId){
	//�̼����� Session Binding�̺�Ʈ�� �Ͼ�� ����
	//name������ userId, value������ �ڱ��ڽ�(HttpSessionBindingListener�� �����ϴ� Object)
	session.setAttribute(userId, this);//login�� �ڱ��ڽ��� ����ִ´�.
	}


	/*
	* �Է¹��� ����Object�� ���̵� �����Ѵ�.
	* @param session : ������ ������� session Object
	* @return String : ������ ���̵�
	*/
	public String getUserID(HttpSession session){
	return (String)loginUsers.get(session);
	}


	/*
	* ���� ������ �� ����� ��
	* @return int ���� ������ ��
	*/
	public int getUserCount(){
	return loginUsers.size();
	}


	/*
	* ���� �������� ��� ����� ���̵� ���
	* @return void
	*/
	public void printloginUsers(){
	Enumeration e = loginUsers.keys();
	HttpSession session = null;
	System.out.println("===========================================");
	int i = 0;
	while(e.hasMoreElements()){
	session = (HttpSession)e.nextElement();
	System.out.println((++i) + ". ������ : " + loginUsers.get(session));
	}
	System.out.println("===========================================");
	}

	/*
	* ���� �������� ��� ����ڸ���Ʈ�� ����
	* @return list
	*/
	public Collection getUsers(){
	Collection collection = loginUsers.values();
	return collection;
	}
}