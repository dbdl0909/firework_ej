package com.minimall.action.goods;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class GoodsDeleteAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsDeleteAction.java");
		
		request.setCharacterEncoding("UTF-8");
		
		String gCode = request.getParameter("gCode");
		System.out.println(gCode + " : gCode GoodsInsertPro.java");
		
		//String path = request.getServletContext().getRealPath("goodsImage");
		String path = "/home/hosting_users/cynical1031/tomcat/webapps/ROOT/upload/goodsImage";
		
		GoodsDao goodsDao = new GoodsDao();
		GoodsDto goodsDto = goodsDao.goodsSelectForDeleteByGCode(gCode);

		String imageName = goodsDto.getG_image();
		
		//File file = new File(path + "\\" + imageName);
		File file = new File(path + "/" + imageName);
		goodsDao.goodsDeleteByGcode(gCode);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println(imageName + " 이미지 삭제 완료!");
				
			} else {
				System.out.println(imageName + " 이미지 삭제 실패!");
			}
		} else {
			System.out.println(imageName + " 이미지가 없습니다!");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Glist/goodsSellerList.go");
		
		return forward;
	}

}
