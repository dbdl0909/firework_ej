package com.minimall.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minimall.dao.MemberDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class MSelectForUpdate implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberDao dao = new MemberDao();
		
		HttpSession session=request.getSession();
		String mid = (String) session.getAttribute("loginId");
		
		System.out.println(mid);
		MemberDto m =  dao.mSelectforUpdate(mid);
		request.setAttribute("m", m);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("/mypage/mypageUpdate.jsp");
		
		return forward;
	}

}
