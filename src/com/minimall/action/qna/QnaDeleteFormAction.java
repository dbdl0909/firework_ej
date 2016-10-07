package com.minimall.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaDeleteFormAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute QnaDeleteFormAction.java");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		
		request.setAttribute("num", num);
		request.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
    	forward.setRedirect(false);
    	forward.setPath("/qnaBoard/qna_delete.jsp");

		return forward;
	}

}
