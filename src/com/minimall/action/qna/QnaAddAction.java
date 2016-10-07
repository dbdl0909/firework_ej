package com.minimall.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.QnaDao;
import com.minimall.dto.QnaDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaAddAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("05 execute qnaAddAction.java");
		QnaDto qnadto = new QnaDto();
		QnaDao qnadao = new QnaDao();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		String saveFolder = "qnaBoard";
		
		realFolder = request.getServletContext().getRealPath(saveFolder);
		System.out.println(realFolder + "<- realFolder execute메서드 QnaAddAction.java");
		boolean result = false;
		
		try {
			request.setCharacterEncoding("UTF-8");
			qnadto.setQna_subject(request.getParameter("qna_subject"));
			qnadto.setM_id(request.getParameter("mId"));
			qnadto.setQna_content(request.getParameter("qna_content"));
			qnadto.setQna_secret(request.getParameter("qna_secret"));
			qnadto.setQna_category(request.getParameter("qna_category"));
			qnadto.setG_code(request.getParameter("gCode"));
			
			System.out.println(qnadto.getQna_subject() + " <- qnadto.getQna_subject()");
			System.out.println(qnadto.getM_id() + " <- qnadto.getM_id()");
			System.out.println(qnadto.getQna_content() + " <- qnadto.getQna_content()");
			System.out.println(qnadto.getQna_secret() + " <- qnadto.getQna_secret()");
			System.out.println(qnadto.getQna_category() + " <- qnadto.getQna_category()");
			System.out.println(qnadto.getG_code() + " <- qnadto.getG_code()");
			
			result=qnadao.boardInsert(qnadto);
			
			if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
			}
			System.out.println("게시판 등록 완료");

	   		forward.setRedirect(true);
	   		forward.setPath(request.getContextPath() + "/Gdetail/goodsDetailAction.go?gCode="+request.getParameter("gCode"));


		}catch(Exception ex){
   			ex.printStackTrace();
   		}
   		return forward;
	}
}
