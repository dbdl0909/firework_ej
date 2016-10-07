package com.minimall.action.reb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.REBDao;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class REBListAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("리스트 execute호출com.minimall.action.QnaListAction"); 
		REBDao rebDao=new REBDao();
		List reblist=new ArrayList();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=rebDao.getListCount(); //총 리스트 수를 받아옴
		reblist = rebDao.getRebList(page,limit); //리스트를 받아옴
		
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
		request.setAttribute("reblist", reblist);
		
		System.out.println(page + " : page");
		System.out.println(listcount+"<-listcount");
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);
		forward.setPath("/reBoard/reBoardList.jsp");		
		return forward;
		
	 }

}
