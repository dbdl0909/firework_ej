package com.minimall.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dao.QnaDao;
import com.minimall.dto.GoodsDto;
import com.minimall.dto.QnaDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaDetailAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("UTF-8");
  		
		QnaDao qnadao=new QnaDao();
		QnaDto qnadto=new QnaDto();
	   	
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		qnadao.setReadCountUpdate(num);
		qnadto=qnadao.getDetail(num);
		
		GoodsDao goodsDao = new GoodsDao();
		GoodsDto goodsDto = new GoodsDto();
		goodsDto = goodsDao.goodsSelectByGcode(qnadto.getG_code());
		request.setAttribute("g_code", qnadto.getG_code());
		request.setAttribute("g_id", goodsDto.getG_id());
		
	   	if(qnadto == null){
	   		System.out.println("상세보기 실패");
	   		return null;
	   	}else{
	   		System.out.println("상세보기 성공");
			System.out.println(qnadto.getQna_no());
		   	request.setAttribute("qnadto", qnadto);
		   	System.out.println(qnadto + ": qnadto");
		   	
		   	ActionForward forward = new ActionForward();
		   	forward.setRedirect(false);
	  		forward.setPath("/qnaBoard/qna_view.jsp");
	  		return forward;
	   	}
	   	

	 }
}
