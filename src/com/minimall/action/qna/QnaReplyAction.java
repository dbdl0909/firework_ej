package com.minimall.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.QnaDao;
import com.minimall.dto.QnaDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaReplyAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("UTF-8");
		 	ActionForward forward = new ActionForward();
		 	
		 	QnaDao qnadao=new QnaDao();
		 	QnaDto qnadto=new QnaDto();
	   		int result=0;
	   		
	   		qnadto.setQna_ref(Integer.parseInt(request.getParameter("qna_ref")));
	   		qnadto.setQna_subject(request.getParameter("qna_subject"));
	   		qnadto.setM_id(request.getParameter("m_id"));
	   		qnadto.setQna_content(request.getParameter("qna_content"));
	   		qnadto.setQna_secret(request.getParameter("qna_secret"));
	   		qnadto.setQna_category(request.getParameter("qna_category"));
	   		
	   		System.out.println(qnadto.getQna_ref());
	   		System.out.println(qnadto.getQna_subject());
	   		System.out.println(qnadto.getM_id());
	   		System.out.println(qnadto.getQna_content());
	   		System.out.println(qnadto.getQna_secret());
	   		System.out.println(qnadto.getQna_category());
	   		
	   		result=qnadao.QnaReply(qnadto);
	   		if(result==0){
	   			System.out.println("답장 실패");
	   			return null;
	   		}
	   		System.out.println("답장 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath(request.getContextPath() + "/Qna/QnaList.qn");
	   		return forward;
 		}
}
