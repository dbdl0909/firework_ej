package com.minimall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.action.order.OrderCheckProAction;
import com.minimall.action.order.OrderDeleteAction;
import com.minimall.action.order.OrderInsertFormAction;
import com.minimall.action.order.OrderInsertProAction;
import com.minimall.action.order.OrderListAction;
import com.minimall.action.order.OrderListOneAction;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public OController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println(requestURI);
		System.out.println(contextPath + " contextPath Ocontroller.java");
		System.out.println(contextPath.length());
		System.out.println(command + " command Ocontroller.java");
		System.out.println();
		
		ActionForward forward = null;
		ActionInterFace action = null;
		
		if(command.equals("/Oin/orderInsertForm.oo")){
			System.out.println("조건문 내 /Oin/orderInsertForm.oo Ocontroller.java");
			action = new OrderInsertFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}else if(command.equals("/Oin/orderInsertPro.oo")){
			System.out.println("조건문 내 /Oin/orderInsertPro.oo OController.java");
			action = new OrderInsertProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Oli/orderList.oo")){
			System.out.println("조건문 내 /Oli/orderList.oo OController.java");
			action = new OrderListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Odel/orderDeletePro.oo")){
			System.out.println("조건문 내 /Odel/orderDeletePro.oo OController.java");
			action = new OrderDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Oli/orderListOne.oo")){
			System.out.println("조건문 내 /Oli/orderListOne.oo OController.java");
			action = new OrderListOneAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Ochk/orderCheckPro.oo")){
			System.out.println("조건문 내 /Ochk/orderCheckPro.oo OController.java");
			action = new OrderCheckProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null){
			if(forward.isRedirect()){
				
				response.sendRedirect(forward.getPath());
			}else{
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				System.out.println(forward.getPath() + "<--- forward.getPath()[jsp 이동경로]  OController.java");
				System.out.println();
				dispatcher.forward(request, response);
			}
		}
	}
}