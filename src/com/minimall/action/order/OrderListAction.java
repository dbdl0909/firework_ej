package com.minimall.action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.OrderDao;
import com.minimall.dto.OrderDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OrderListAction implements ActionInterFace {
	ArrayList<OrderDto> orderList;
	int orderListAllCount;
	int page = 1;	// 현재 페이지 번호
	int limit = 10; // 한 페이지에서 보여줄 리스트 갯수

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute OrderListAction.java");
		
		OrderDao odao = new OrderDao();
		orderListAllCount = odao.getListCount();
		
		//페이징 처리		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		orderList = odao.orderSelectAll(page, limit);
		
		int maxPage = (int) ((double)orderListAllCount/limit +0.95);	//총 페이지 수
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//현재 페이지에 보여줄 시작 페이지 번호
		int endPage = startPage +10 -1;								//현재 페이지에 보여줄 마지막 페이지 번호
		if(endPage > maxPage) endPage = maxPage;					//마지막 페이지 번호가 총 페이지 수보다 클 경우
																	//endPage에 maxPage의 값을 담는다.		
		request.setAttribute("orderList", orderList);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("orderListAllCount", orderListAllCount);		//보여줄 글의 총 갯수
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/order/orderList.jsp");
		
		return forward;
	}

}
