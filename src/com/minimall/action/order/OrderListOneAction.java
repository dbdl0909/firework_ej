package com.minimall.action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minimall.dao.GoodsDao;
import com.minimall.dao.OrderDao;
import com.minimall.dto.GoodsDto;
import com.minimall.dto.OrderDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OrderListOneAction implements ActionInterFace {
	ArrayList<OrderDto> orderList;
	int orderListOneAllCount;
	int page = 1;	// ���� ������ ��ȣ
	int limit = 10; // �� ���������� ������ ����Ʈ ����

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute() orderListOneAction.java");
		HttpSession session = request.getSession();		
		String mId = (String)session.getAttribute("loginId");
		System.out.println(mId + "<-- mId OrderListOne.java");
		/*GoodsDao gdao = new GoodsDao();
		ArrayList<GoodsDto> goodsList = gdao.goodsSelectForCustom();*/
		OrderDao odao = new OrderDao();
		orderListOneAllCount = odao.getListCountOne(mId);
		
		//����¡ ó��		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<OrderDto> orderListOne = odao.orderListOne(mId, page, limit);
		
		int maxPage = (int) ((double)orderListOneAllCount/limit +0.95);	//�� ������ ��
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//���� �������� ������ ���� ������ ��ȣ
		int endPage = startPage +10 -1;								//���� �������� ������ ������ ������ ��ȣ
		if(endPage > maxPage) endPage = maxPage;					//������ ������ ��ȣ�� �� ������ ������ Ŭ ���
																	//endPage�� maxPage�� ���� ��´�.		
		request.setAttribute("orderListOne", orderListOne);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("orderListOneAllCount", orderListOneAllCount);		//������ ���� �� ����
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/order/orderListOne.jsp");
		
		return forward;
	}

}
