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
	int page = 1;	// ���� ������ ��ȣ
	int limit = 10; // �� ���������� ������ ����Ʈ ����

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute OrderListAction.java");
		
		OrderDao odao = new OrderDao();
		orderListAllCount = odao.getListCount();
		
		//����¡ ó��		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		orderList = odao.orderSelectAll(page, limit);
		
		int maxPage = (int) ((double)orderListAllCount/limit +0.95);	//�� ������ ��
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//���� �������� ������ ���� ������ ��ȣ
		int endPage = startPage +10 -1;								//���� �������� ������ ������ ������ ��ȣ
		if(endPage > maxPage) endPage = maxPage;					//������ ������ ��ȣ�� �� ������ ������ Ŭ ���
																	//endPage�� maxPage�� ���� ��´�.		
		request.setAttribute("orderList", orderList);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("orderListAllCount", orderListAllCount);		//������ ���� �� ����
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/order/orderList.jsp");
		
		return forward;
	}

}
