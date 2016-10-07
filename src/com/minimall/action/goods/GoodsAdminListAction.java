package com.minimall.action.goods;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class GoodsAdminListAction implements ActionInterFace {
	ArrayList<GoodsDto> goodsList = null;
	int goodsListAllCount;	//goods ���̺��� �� ������ ����	
	int page = 1;		//���� ������ ��ȣ
	int limit = 10;		//�� ���������� ������ ����Ʈ ����
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsListAction.java");
		
		GoodsDao goodsDao = new GoodsDao();
		goodsListAllCount = goodsDao.getListCount();
		
		//����¡ ó��		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		goodsList = goodsDao.goodsSelectAll(page, limit);
		
		int maxPage = (int) ((double)goodsListAllCount/limit +0.95);	//�� ������ ��
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//���� �������� ������ ���� ������ ��ȣ
		int endPage = startPage +10 -1;								//���� �������� ������ ������ ������ ��ȣ
		if(endPage > maxPage) endPage = maxPage;					//������ ������ ��ȣ�� �� ������ ������ Ŭ ���
																	//endPage�� maxPage�� ���� ��´�.		
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("goodsListAllCount", goodsListAllCount);		//������ ���� �� ����
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/goods/goodsAdminList.jsp");
		
		return forward;
	}
	
}
