package com.minimall.action.reb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.REBDao;
import com.minimall.dto.REBDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class REBReplyView implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	ActionForward forward = new ActionForward();
		 	
		 	REBDto rebDto = new REBDto();
			REBDao rebDao = new REBDao();
	   		
	   		int num=Integer.parseInt(request.getParameter("num"));
	   		System.out.println(num + "<--num");
	   		
	   		rebDto=rebDao.getDetail(num);
	   		
	   		if(rebDto==null){
	   			System.out.println("답장 페이지 이동 실패");
	   			return null;
	   		}
	   		System.out.println("답장 페이지 이동 완료");
	   		
	   		request.setAttribute("rebDto", rebDto);
	   		
	   		forward.setRedirect(false);
	   		forward.setPath("/reBoard/reBoardReply.jsp");
	   		return forward;
		}
}
