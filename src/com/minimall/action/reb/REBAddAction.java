package com.minimall.action.reb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.REBDao;
import com.minimall.dto.REBDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class REBAddAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("05 execute qnaAddAction.java");
		REBDto rebDto = new REBDto();
		REBDao rebDao = new REBDao();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		String saveFolder = "qnaBoard";
		
		realFolder = request.getServletContext().getRealPath(saveFolder);
		System.out.println(realFolder + "<- realFolder execute메서드 QnaAddAction.java");
		boolean result = false;
		
		try {
			request.setCharacterEncoding("UTF-8");
			rebDto.setReb_subject(request.getParameter("reb_subject"));
			rebDto.setM_id(request.getParameter("mId"));
			rebDto.setReb_content(request.getParameter("reb_content"));
			rebDto.setReb_secret(request.getParameter("reb_secret"));
			rebDto.setReb_category(request.getParameter("reb_category"));
			rebDto.setG_code(request.getParameter("g_code"));
			
			
			System.out.println(rebDto.getReb_subject() + " <- rebDto.getreb_subject()");
			System.out.println(rebDto.getM_id() + " <- rebDto.getM_id()");
			System.out.println(rebDto.getReb_content() + " <- rebDto.getreb_content()");
			System.out.println(rebDto.getReb_secret() + " <- rebDto.getreb_secret()");
			System.out.println(rebDto.getReb_category() + " <- rebDto.getreb_category()");
			System.out.println(rebDto.getG_code() + " <- rebDto.getG_code()");
			
			result=rebDao.boardInsert(rebDto);
			
			if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
			}
			System.out.println("게시판 등록 완료");

	   		forward.setRedirect(true);
	   		forward.setPath(request.getContextPath() + "/Reb/rebList.reb");

	   		
		}catch(Exception ex){
   			ex.printStackTrace();
   		}
   		return forward;
	}
}
