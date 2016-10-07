package com.minimall.action.reb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.REBDao;
import com.minimall.dto.REBDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class REBModifyView implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	ActionForward forward = new ActionForward();
		 	request.setCharacterEncoding("UTF-8");
	   		
		 	REBDao rebDao=new REBDao();
		 	REBDto rebDto=new REBDto();
		   	
			int num=Integer.parseInt(request.getParameter("num"));
			rebDto=rebDao.getDetail(num);
		   	
		   	if(rebDto==null){
		   		System.out.println("(수정)상세보기 실패");
		   		return null;
		   	}
		   	System.out.println("(수정)상세보기 성공");
		   	
		   	request.setAttribute("rebDto", rebDto);
		   	forward.setRedirect(false);
	   		forward.setPath("/reBoard/reBoardModify.jsp");
	   		return forward;
	 }
}
