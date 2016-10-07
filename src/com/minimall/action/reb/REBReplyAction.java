package com.minimall.action.reb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.REBDao;
import com.minimall.dto.REBDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class REBReplyAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("UTF-8");
		 	ActionForward forward = new ActionForward();
		 	
		 	REBDao rebDao=new REBDao();
		 	REBDto rebDto=new REBDto();
	   		int result=0;
	   		
	   		rebDto.setReb_no(Integer.parseInt(request.getParameter("reb_ref")));
	   		rebDto.setReb_subject(request.getParameter("reb_subject"));
	   		rebDto.setM_id(request.getParameter("m_id"));
	   		rebDto.setReb_content(request.getParameter("reb_content"));
	   		rebDto.setReb_secret(request.getParameter("reb_secret"));
	   		rebDto.setReb_category(request.getParameter("reb_category"));
	   		
	   		System.out.println(rebDto.getReb_subject());
	   		System.out.println(rebDto.getM_id());
	   		System.out.println(rebDto.getReb_content());
	   		System.out.println(rebDto.getReb_secret());
	   		System.out.println(rebDto.getReb_category());
	   		
	   		result=rebDao.REBReply(rebDto);
	   		if(result==0){
	   			System.out.println("답장 실패");
	   			return null;
	   		}
	   		System.out.println("답장 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath(request.getContextPath() + "/Reb/rebList.reb");
	   		return forward;
 		}
}
