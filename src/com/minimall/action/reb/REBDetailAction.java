
package com.minimall.action.reb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.REBDao;
import com.minimall.dto.REBDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class REBDetailAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("UTF-8");
  		
		REBDao rebDao=new REBDao();
		REBDto rebDto=new REBDto();
	   	
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("num:"+num);
		rebDao.setReadCountUpdate(num);
		rebDto=rebDao.getDetail(num);
		//System.out.println(rebDao.getDetail(num));
		System.out.println(rebDto);
	   	if(rebDto == null){
	   		System.out.println("상세보기 실패");
	   		return null;
	   		
	   	}else{
	   		
	   		System.out.println("상세보기 성공");
			System.out.println(rebDto.getReb_no());
		   	request.setAttribute("rebDto", rebDto);
		   	System.out.println(rebDto + ": rebDto");
		   	
		   	ActionForward forward = new ActionForward();
		   	forward.setRedirect(false);
	  		forward.setPath("/reBoard/reBoardView.jsp");
	  		return forward;
	   	}
	   	

	 }
}



