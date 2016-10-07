package com.minimall.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.QnaDao;
import com.minimall.dto.QnaDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaModifyView implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	ActionForward forward = new ActionForward();
		 	request.setCharacterEncoding("UTF-8");
	   		
		 	QnaDao qnadao=new QnaDao();
		 	QnaDto qnadto=new QnaDto();
		   	
			int num=Integer.parseInt(request.getParameter("num"));
			
			qnadto=qnadao.getDetail(num);
			
		   	if(qnadto==null){
		   		System.out.println("(수정)상세보기 실패");
		   		return null;
		   	}
		   	System.out.println("(수정)상세보기 성공");
		   	
		   	request.setAttribute("qnadto", qnadto);
		   	forward.setRedirect(false);
	   		forward.setPath("/qnaBoard/qna_modify.jsp");
	   		return forward;
	 }
}
