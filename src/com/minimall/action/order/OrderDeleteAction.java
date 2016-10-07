package com.minimall.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.minimall.dao.MemberDao;
import com.minimall.dao.OrderDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class OrderDeleteAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute OrderDeleteAction.java");
		
		HttpSession session = request.getSession();		
		String delPw = request.getParameter("delPw");
		String loginId = (String)session.getAttribute("loginId");
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		System.out.println(oNo + "<--oNo");
		System.out.println(delPw + "<--delPw");
		System.out.println(loginId + "<--loginId");
		
		MemberDao mdao = new MemberDao();
		MemberDto delchk = mdao.userCheck(loginId, delPw);
		
		ActionForward forward = new ActionForward();
		
		if(delchk == null) {
			
			System.out.println("비밀번호 불일치");
			request.setAttribute("loginChk", 1);
			
			forward.setRedirect(false);
			forward.setPath("/Oli/orderListOne.oo");
			
		} else {

			
		OrderDao odao = new OrderDao();
		odao.orderDelete(oNo);
						
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Oli/orderListOne.oo");
		
		}
		
		return forward;
		
	}
	
}
