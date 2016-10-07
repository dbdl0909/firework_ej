package com.minimall.action.goods;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class GoodsSellerListAction implements ActionInterFace {
	ArrayList<GoodsDto> goodsList = null;
	int goodsListAllCount;	//goods 테이블의 총 데이터 갯수	
	int page = 1;		//현재 페이지 번호
	int limit = 10;		//한 페이지에서 보여줄 리스트 갯수

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsSellerListAction.java");
		
		request.setCharacterEncoding("UTF-8");
		//String sellerId = request.getParameter("send_id");
		HttpSession session = request.getSession();
		String sellerId = (String) session.getAttribute("loginId");
		System.out.println(sellerId + " : sellerId GoodsSellerListAction.java");
		
		GoodsDao goodsDao = new GoodsDao();
		goodsListAllCount = goodsDao.getListCountOne(sellerId);
		
		
		//페이징 처리		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		goodsList = goodsDao.goodsSelectForSeller(sellerId, page, limit);
		int maxPage = (int) ((double)goodsListAllCount/limit +0.95);	//총 페이지 수
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//현재 페이지에 보여줄 시작 페이지 번호
		int endPage = startPage +10 -1;								//현재 페이지에 보여줄 마지막 페이지 번호
		if(endPage > maxPage) endPage = maxPage;					//마지막 페이지 번호가 총 페이지 수보다 클 경우
																	//endPage에 maxPage의 값을 담는다.		
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("goodsListAllCount", goodsListAllCount);		//보여줄 글의 총 갯수
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/mypage/mypage_goods.jsp");
		
		return forward;
	}

}
