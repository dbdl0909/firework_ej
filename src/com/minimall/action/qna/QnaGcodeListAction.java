package com.minimall.action.qna;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.action.goods.GoodsDetailAction;
import com.minimall.dao.GoodsDao;
import com.minimall.dao.QnaDao;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaGcodeListAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리스트 execute호출com.minimall.action.GcodeQnaListAction.java"); 
		QnaDao qnadao=new QnaDao();
		List qnalist=new ArrayList();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		String gCode = request.getParameter("gCode");
		System.out.println("gCode : " + gCode);
		
		//int listcount=qnadao.getListCount(); //총 리스트 수를 받아옴
		qnalist = qnadao.gcodeQnaList(page,limit, gCode); //리스트를 받아옴
		int listcount = qnalist.size();
		
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage+10-1;

		if (endpage> maxpage) endpage= maxpage;
		
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxpage", maxpage); //최대 페이지 수
		request.setAttribute("startpage", startpage); //현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage); //현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount",listcount); //글 수
		request.setAttribute("qnalist", qnalist);
		request.setAttribute("gCode", gCode);
		
		System.out.println(page + " : page");
		System.out.println(listcount+"<-listcount");
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);
		forward.setPath("/qnaBoard/qna_list.jsp");
		return forward;
	}

}
