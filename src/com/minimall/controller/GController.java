	package com.minimall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.action.goods.GoodsAdminChkAction;
import com.minimall.action.goods.GoodsAdminListAction;
import com.minimall.action.goods.GoodsCustomListAction;
import com.minimall.action.goods.GoodsDForUpdateAction;
import com.minimall.action.goods.GoodsDeleteAction;
import com.minimall.action.goods.GoodsDetailAction;
import com.minimall.action.goods.GoodsInsertPro;
import com.minimall.action.goods.GoodsSellerListAction;
import com.minimall.action.goods.GoodsUpdateAction;
import com.minimall.action.goods.indexGoodsList;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/GController")
@MultipartConfig
public class GController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("01 doGet() GController.java");
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("02 doPost() GController.java");
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("03 doProcess() GController.java");
		
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		System.out.println(RequestURI + "<-- RequestURI GController.java");
		System.out.println(contextPath + "<-- contextPath GController.java");
		System.out.println(contextPath.length() + "<-- contextPath.length() GController.java");
		System.out.println(command + "<-- command GController.java");
		System.out.println("----------GController.java----------------");
		System.out.println();
		
		ActionForward forward = null;
		ActionInterFace action = null;
		
		if(command.equals("/index.go")) {
			System.out.println("03_00 /index");
			
			action = new indexGoodsList();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Gin/goodsInsertForm.go")) {
			System.out.println("03_01 /Gin/goodsInsertForm.go");
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/goods/goodsInsertForm.jsp");
			
		} else if(command.equals("/Gin/goodsInsertPro.go")) {
			System.out.println("03_02 /Gin/goodsInsertPro.go");
			
			action = new GoodsInsertPro();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Glist/goodsAdminList.go")) {
			System.out.println("03_03 /Glist/goodsAdminList.go");
			
			action = new GoodsAdminListAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Glist/goodsCustomList.go")) {
			System.out.println("03_04 /Glist/goodsCustomList.go");
			
			action = new GoodsCustomListAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Gchk/GoodsAdminChk.go")) {
			System.out.println("03_05 /Gchk/GoodsAdminChk.go");
			
			action = new GoodsAdminChkAction();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Glist/goodsSellerList.go")) {
			System.out.println("03_06 /Glist/goodsSellerList.go");
			
			action = new GoodsSellerListAction();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Gup/goodsUpdateForm.go")) {
			System.out.println("03_07 /Gup/goodsUpdateForm.go");
			
			action = new GoodsDForUpdateAction();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Gup/GoodsUpdateAtion.go")) {
			System.out.println("03_08 /Gup/GoodsUpdateAtion.go");
			
			action = new GoodsUpdateAction();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Gdel/goodsDeleteAction.go")) {
			System.out.println("03_09 /Gdel/goodsDeleteAction.go");
			
			action = new GoodsDeleteAction();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Gdetail/goodsDetailAction.go")) {
			System.out.println("03_10 /Gdetail/goodsDetailAction.go");
			
			action = new GoodsDetailAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
