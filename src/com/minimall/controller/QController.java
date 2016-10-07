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

		String RequestURI=request.getRequestURI();					//전체경로 추출
		String contextPath=request.getContextPath();			  	// /Qna/QnaAddAction.qn
		String command=RequestURI.substring(contextPath.length());	//전체 문자열 중에서 인덱스 이후의 문자열
		System.out.println(RequestURI + "<-- RequestURI QController.java");
		System.out.println(contextPath + "<-- contextPath QController.java");
		System.out.println(contextPath.length() + "<-- contextPath.length() QController.java");
		System.out.println(command + "<-- command QController.java");
		System.out.println("----------QController.java----------------");
		System.out.println();
	
		ActionForward forward = null;
		ActionInterFace action = null;
		
		if(command.equals("/Qna/QnaAddWrite.qn")){		//글작성
	    	System.out.println("04_01 조건문 내 /Qna/QnaAddWrite.qn QController.java");
	    	forward = new ActionForward();		//주소값이 담겨있음
	    	forward.setRedirect(false);
	    	forward.setPath("/qnaBoard/qna_board_write.jsp?gCode="+request.getParameter("gCode"));
	    	forward.toString();
		} else if(command.equals("/Qna/QnaAddAction.qn")){		//글작성액션
	    	System.out.println("04_02 조건문 내 /Qna/QnaAddAction.qn QController.java");
			action = new QnaAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaList.qn")){		//리스트
	    	System.out.println("04_03 조건문 내 /Qna/QnaList.qn QController.java");
	    	action = new QnaListAction();
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaDetailAction.qn")) {		//글확인 액션
			System.out.println("04_04 조건문 내 /Qna/QnaView.qn QController.java");
			action = new QnaDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaReplyView.qn")){		//답글
			System.out.println("04_05 조건문 내 /Qna/QnaReplyView.qn QController.java");
			action = new QnaReplyView();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/Qna/QnaReplyAction.qn")){	//답글액션
	    	System.out.println("04_06 조건문 내 /Qna/QnaReplyAction.qn QController.java");
			action = new QnaReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaDeleteForm.qn")){	//글삭제폼
	    	System.out.println("04_07 조건문 내 /Qna/QnaDeleteForm.qn QController.java");
			action = new QnaDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaDeleteAction.qn")){	//삭제액션
	    	System.out.println("04_08 조건문 내 /Qna/QnaDeleteAction.qn QController.java");
			action = new QnaDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaModify.qn")){		//글수정
	    	System.out.println("04_09 조건문 내 /Qna/QnaModify.qn QController.java");
			action = new QnaModifyView();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}else if(command.equals("/Qna/QnaModifyAction.qn")){	//수정액션
	    	System.out.println("04_09 조건문 내 /Qna/QnaModifyAction.qn QController.java");
			action = new QnaModifyAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/Qna/QnaGcodeList.qn")) {	//상품별 리스트
			System.out.println("04_10 조건문 내 /Qna/QnaGcodeList.qn QController.java");
	    	action = new QnaGcodeListAction();
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
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