package com.minimall.action.order;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OrderInsertFormAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute orderInsertFormAction.java");
		GoodsDao gdao = new GoodsDao();
		String gCode = request.getParameter("gCode");
		GoodsDto goods = gdao.goodsSelectByGcode(gCode);
		request.setAttribute("goods", goods);
		
		ActionForward forward = new ActionForward();
    	forward.setRedirect(false);
    	forward.setPath("/order/orderInsertForm.jsp");

		return forward;
	}

}
