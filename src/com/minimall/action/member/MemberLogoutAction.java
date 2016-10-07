package com.minimall.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minimall.dao.MemberDao;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class MemberLogoutAction implements ActionInterFace {
 
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session=request.getSession();
		session.invalidate(); 
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getHeader("referer"));
		return forward;
	}

}
