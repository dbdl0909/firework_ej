package com.minimall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.inter.ActionInterFace;
import com.minimall.action.qna.QnaAddAction;
import com.minimall.action.qna.QnaDeleteAction;
import com.minimall.action.qna.QnaDeleteFormAction;
import com.minimall.action.qna.QnaDetailAction;
import com.minimall.action.qna.QnaGcodeListAction;
import com.minimall.action.qna.QnaListAction;
import com.minimall.action.qna.QnaModifyAction;
import com.minimall.action.qna.QnaModifyView;
import com.minimall.action.qna.QnaReplyAction;
import com.minimall.action.qna.QnaReplyView;
import com.minimall.forward.ActionForward;

@WebServlet("/QController")
public class QController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QController() {
        super();
    }
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("03 doPro() QController.java");

		String RequestURI=request.getRequestURI();					//��ü��� ����
		String contextPath=request.getContextPath();			  	// /Qna/QnaAddAction.qn
		String command=RequestURI.substring(contextPath.length());	//��ü ���ڿ� �߿��� �ε��� ������ ���ڿ�
		System.out.println(RequestURI + "<-- RequestURI QController.java");
		System.out.println(contextPath + "<-- contextPath QController.java");
		System.out.println(contextPath.length() + "<-- contextPath.length() QController.java");
		System.out.println(command + "<-- command QController.java");
		System.out.println("----------QController.java----------------");
		System.out.println();
	
		ActionForward forward = null;
		ActionInterFace action = null;
		
		if(command.equals("/Qna/QnaAddWrite.qn")){		//���ۼ�
	    	System.out.println("04_01 ���ǹ� �� /Qna/QnaAddWrite.qn QController.java");
	    	forward = new ActionForward();		//�ּҰ��� �������
	    	forward.setRedirect(false);
	    	forward.setPath("/qnaBoard/qna_board_write.jsp?gCode="+request.getParameter("gCode"));
	    	forward.toString();
		} else if(command.equals("/Qna/QnaAddAction.qn")){		//���ۼ��׼�
	    	System.out.println("04_02 ���ǹ� �� /Qna/QnaAddAction.qn QController.java");
			action = new QnaAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaList.qn")){		//����Ʈ
	    	System.out.println("04_03 ���ǹ� �� /Qna/QnaList.qn QController.java");
	    	action = new QnaListAction();
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaDetailAction.qn")) {		//��Ȯ�� �׼�
			System.out.println("04_04 ���ǹ� �� /Qna/QnaView.qn QController.java");
			action = new QnaDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaReplyView.qn")){		//���
			System.out.println("04_05 ���ǹ� �� /Qna/QnaReplyView.qn QController.java");
			action = new QnaReplyView();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaReplyAction.qn")){	//��۾׼�
	    	System.out.println("04_06 ���ǹ� �� /Qna/QnaReplyAction.qn QController.java");
			action = new QnaReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaDeleteForm.qn")){	//�ۻ�����
	    	System.out.println("04_07 ���ǹ� �� /Qna/QnaDeleteForm.qn QController.java");
			action = new QnaDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaDeleteAction.qn")){	//�����׼�
	    	System.out.println("04_08 ���ǹ� �� /Qna/QnaDeleteAction.qn QController.java");
			action = new QnaDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaModify.qn")){		//�ۼ���
	    	System.out.println("04_09 ���ǹ� �� /Qna/QnaModify.qn QController.java");
			action = new QnaModifyView();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}else if(command.equals("/Qna/QnaModifyAction.qn")){	//�����׼�
	    	System.out.println("04_09 ���ǹ� �� /Qna/QnaModifyAction.qn QController.java");
			action = new QnaModifyAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaGcodeList.qn")) {	//��ǰ�� ����Ʈ
			System.out.println("04_10 ���ǹ� �� /Qna/QnaGcodeList.qn QController.java");
	    	action = new QnaGcodeListAction();
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null){			//���� �ƴϸ� �ٽ� ���ǹ����� ���ư�
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());	//�����̷�Ʈ
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());	
				System.out.println(forward.getPath() + "<--- forward.getPath()[jsp �̵����] QController.java");
				System.out.println();
				dispatcher.forward(request, response);
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("01 doGet() QController.java");
		doPro(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("02 doPost() QController.java");
		doPro(request, response);
	}
}