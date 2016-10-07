package com.minimall.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class MemberInsertAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String m_Id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		String m_level = request.getParameter("m_level");
		String m_name = request.getParameter("m_name");
		String m_email = request.getParameter("m_email");
		String m_addr = request.getParameter("m_addr");

		System.out.println(m_Id+" m_Id MemberInsertAction.java");
		MemberDto m = new MemberDto();
		MemberDao mDao = new MemberDao();
		
		m.setm_id(m_Id);
		m.setm_pw(m_pw);
		m.setm_level(m_level);
		m.setm_name(m_name);
		m.setm_email(m_email);
		m.setm_addr(m_addr);
		
		mDao.insertMember(m);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/index.jsp");		
	
		return forward;
		
	}

}
