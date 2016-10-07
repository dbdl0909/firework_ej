package com.minimall.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.OrderDao;
import com.minimall.dto.OrderDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OrderInsertProAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		System.out.println("execute() OrderInsertProAction.java");
		
		request.setCharacterEncoding("UTF-8");
		String gName = request.getParameter("gName");
		String gId = request.getParameter("gId");
		String mId = request.getParameter("mId");
		String gCode = request.getParameter("gCode");
		int oCount = Integer.parseInt(request.getParameter("oCount"));
		int oTotal = Integer.parseInt(request.getParameter("oTotal"));
		System.out.println(gName + "<- gName");
		System.out.println(gId + "<- gId");
		System.out.println(mId + "<- mId");
		System.out.println(gCode + "<- gCode");
		System.out.println(oCount + "<- oCount");
		System.out.println(oTotal + "<- oTotal");
		
		OrderDto odto = new OrderDto();
		odto.setM_id(mId);
		odto.setG_code(gCode);
		odto.setO_count(oCount);
		odto.setO_total(oTotal);
		
		request.setAttribute("mId", mId);
		
		OrderDao odao = new OrderDao();
		odao.orderInsert(odto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Oli/orderListOne.oo");
		
		return forward;
	}

}
