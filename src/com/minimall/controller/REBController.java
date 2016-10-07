package com.minimall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.action.reb.REBAddAction;
import com.minimall.action.reb.REBDeleteAction;
import com.minimall.action.reb.REBDeleteFormAction;
import com.minimall.action.reb.REBDetailAction;
import com.minimall.action.reb.REBListAction;
import com.minimall.action.reb.REBModifyAction;
import com.minimall.action.reb.REBModifyView;
import com.minimall.action.reb.REBReplyAction;
import com.minimall.action.reb.REBReplyView;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

@WebServlet("/REBcontroller")
 public class REBController extends HttpServlet  {
	 //Servlet ����������Ŭ ���ظ� Ȯ�� ���� �����ڸ޼��� ����
   public REBController() {
        super();
	}

			
	 protected void doPro(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 System.out.println("06 doPro ȣ�� BoardFrontController.java");
		 String RequestURI=request.getRequestURI();
		 String contextPath=request.getContextPath();
		 String command=RequestURI.substring(contextPath.length());
		 System.out.println(RequestURI + "<-- RequestURI BoardFrontController.java");
		 System.out.println(contextPath + "<-- contextPath BoardFrontController.java");
		 System.out.println(contextPath.length() + "<-- contextPath.length() BoardFrontController.java");
		 System.out.println(command + "<-- command BoardFrontController.java");
		 System.out.println("----------BoardFrontController.java----------------");
		 System.out.println();
		 ActionForward forward=null;
		 ActionInterFace action=null;
		 
		 
			if(command.equals("/Reb/rebAddWrite.reb")){		//���ۼ�
		    	System.out.println("04_01 ���ǹ� �� /Reb/QnaAddWrite.qn QController.java");
		    	forward = new ActionForward();		//�ּҰ��� �������
		    	forward.setRedirect(false);
		    	forward.setPath("/reBoard/reBoardWrite.jsp");
		    	forward.toString();
			} else if(command.equals("/Reb/rebAddAction.reb")){		//���ۼ��׼�
		    	System.out.println("04_02 ���ǹ� �� /Reb/QnaAddAction.qn QController.java");
				action = new REBAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebList.reb")){		//����Ʈ
		    	System.out.println("04_03 ���ǹ� �� /Qna/QnaList.qn QController.java");
		    	action = new REBListAction();
		    	try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebDetailAction.reb")) {		//��Ȯ�� �׼�
				System.out.println("04_04 ���ǹ� �� /Qna/QnaView.qn QController.java");
				action = new REBDetailAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebReplyView.reb")){		//���
				System.out.println("04_05 ���ǹ� �� /Qna/QnaReplyView.qn QController.java");
				action = new REBReplyView();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebReplyAction.reb")){	//��۾׼�
		    	System.out.println("04_06 ���ǹ� �� /Qna/QnaReplyAction.qn QController.java");
				action = new REBReplyAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/Reb/rebDeleteForm.reb")){	//�ۻ�����
				action = new REBDeleteFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(command.equals("/Reb/rebDeleteAction.reb")){	//�����׼�
				action = new REBDeleteAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/Reb/rebModify.reb")){		//�ۼ���
				action = new REBModifyView();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
		 	}else if(command.equals("/Reb/rebModifyAction.reb")){	//�����׼�
				action = new REBModifyAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
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