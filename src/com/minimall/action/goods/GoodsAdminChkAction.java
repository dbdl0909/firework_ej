package com.minimall.action.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class GoodsAdminChkAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsAdminChkAction.java");
		
		String[] gCodeArray = request.getParameterValues("agreeChange");
		System.out.println(gCodeArray + " : gCodeArray GoodsAdminChkAction.java");
		System.out.println(gCodeArray.length);

		GoodsDao goodsDao = new GoodsDao();
		goodsDao.goodsCheckAgree(gCodeArray);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Glist/goodsAdminList.go");
		
		return forward;
	}
}
