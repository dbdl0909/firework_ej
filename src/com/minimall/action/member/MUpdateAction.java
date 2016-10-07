package com.minimall.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class MUpdateAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("MUpdateAction execute");
		
		MemberDao dao = new MemberDao();
		
		MemberDto m = new MemberDto();
		
		m.setm_id(request.getParameter("m_id"));
		m.setm_pw(request.getParameter("m_pw"));
		m.setm_level(request.getParameter("m_level"));
		m.setm_name(request.getParameter("m_name"));
		m.setm_email(request.getParameter("m_email"));		
		m.setm_addr(request.getParameter("m_addr"));
		
		dao.mUpdate(m);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/Mup/mUpdateForm.mo");
		return forward;
	}

}
