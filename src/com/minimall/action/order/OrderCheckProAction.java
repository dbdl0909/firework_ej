package com.minimall.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.OrderDao;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OrderCheckProAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() OrderCheckProAction.java");
		
		String[] moneyChkArray = request.getParameterValues("moneyChk");
		System.out.println(moneyChkArray + " : moneyChkArray OrderCheckProAction.java");
		System.out.println(moneyChkArray.length);
		
		OrderDao odao = new OrderDao();
		odao.orderMoneyCheck(moneyChkArray);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Oli/orderList.oo");
		
		return forward;
	}

}
