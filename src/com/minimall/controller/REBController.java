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
	 //Servlet 라이프사이클 이해를 확인 위한 생성자메서드 선언
   public REBController() {
        super();
	}

			
	 protected void doPro(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 System.out.println("06 doPro 호출 BoardFrontController.java");
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
		 
		 
			if(command.equals("/Reb/rebAddWrite.reb")){		//글작성
		    	System.out.println("04_01 조건문 내 /Reb/QnaAddWrite.qn QController.java");
		    	forward = new ActionForward();		//주소값이 담겨있음
		    	forward.setRedirect(false);
		    	forward.setPath("/reBoard/reBoardWrite.jsp");
		    	forward.toString();
			} else if(command.equals("/Reb/rebAddAction.reb")){		//글작성액션
		    	System.out.println("04_02 조건문 내 /Reb/QnaAddAction.qn QController.java");
				action = new REBAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebList.reb")){		//리스트
		    	System.out.println("04_03 조건문 내 /Qna/QnaList.qn QController.java");
		    	action = new REBListAction();
		    	try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebDetailAction.reb")) {		//글확인 액션
				System.out.println("04_04 조건문 내 /Qna/QnaView.qn QController.java");
				action = new REBDetailAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebReplyView.reb")){		//답글
				System.out.println("04_05 조건문 내 /Qna/QnaReplyView.qn QController.java");
				action = new REBReplyView();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			} else if(command.equals("/Reb/rebReplyAction.reb")){	//답글액션
		    	System.out.println("04_06 조건문 내 /Qna/QnaReplyAction.qn QController.java");
				action = new REBReplyAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/Reb/rebDeleteForm.reb")){	//글삭제폼
				action = new REBDeleteFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(command.equals("/Reb/rebDeleteAction.reb")){	//삭제액션
				action = new REBDeleteAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/Reb/rebModify.reb")){		//글수정
				action = new REBModifyView();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
		 	}else if(command.equals("/Reb/rebModifyAction.reb")){	//수정액션
				action = new REBModifyAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(forward != null){			//널이 아니면 다시 조건문으로 돌아감
				if(forward.isRedirect()){
					response.sendRedirect(forward.getPath());	//리다이렉트
				}else{
					RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());	
					System.out.println(forward.getPath() + "<--- forward.getPath()[jsp 이동경로] QController.java");
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