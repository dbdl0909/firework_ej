package com.minimall.action.goods;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GoodsUpdateAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsUpdateAction.java");
		
		/*request.setCharacterEncoding("UTF-8");
		String gCode = request.getParameter("gCode");
		String gName = request.getParameter("gName");
		String gCate = request.getParameter("gCate");
		int gPrice = Integer.parseInt(request.getParameter("gPrice"));
		String gSangse = request.getParameter("gSangse");
		System.out.println(gCode + " : GoodsUpdateAction.java");
		System.out.println(gName + " : gName GoodsUpdateAction.java");
		System.out.println(gCate + " : gCate GoodsUpdateAction.java");
		System.out.println(gPrice + " : gPrice GoodsUpdateAction.java");
		System.out.println(gSangse + " : gSangse GoodsUpdateAction.java");*/
		
		//String path = request.getServletContext().getRealPath("goodsImage");
		String path = "/home/hosting_users/cynical1031/tomcat/webapps/ROOT/upload/goodsImage"; 
		System.out.println(path);
		int maxSize = 500*500*100;
		String encType = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, path, maxSize, encType, new DefaultFileRenamePolicy());
		String gCode = multipartRequest.getParameter("gCode");
		String gName = multipartRequest.getParameter("gName");
		String gCate = multipartRequest.getParameter("gCate");
		int gPrice = Integer.parseInt(multipartRequest.getParameter("gPrice"));
		String gSangse = multipartRequest.getParameter("gSangse");
		
		String gImageFileSysName = multipartRequest.getFilesystemName("myImage");
		String gImageOriginName = multipartRequest.getOriginalFileName("myImage");
		
		System.out.println(gCode + " : gId GoodsInsertPro.java");
		System.out.println(gName + " : gName GoodsInsertPro.java");
		System.out.println(gCate + " : gCate GoodsInsertPro.java");
		System.out.println(gPrice + " : gPrice GoodsInsertPro.java");
		System.out.println(gSangse + " : gSangse GoodsInsertPro.java");
		
		System.out.println(gImageFileSysName + " : gImageFileSysName GoodsInsertPro.java");
		System.out.println(gImageOriginName + " : gImgageOriginName GoodsInsertPro.java");
		
		File file = multipartRequest.getFile("myImage");
		System.out.println(file);
		
		GoodsDto goodsDto = new GoodsDto();
		goodsDto.setG_code(gCode);
		goodsDto.setG_name(gName);
		goodsDto.setG_cate(gCate);
		goodsDto.setG_price(gPrice);
		goodsDto.setG_sangse(gSangse);
		goodsDto.setG_image(gImageFileSysName);

		GoodsDao goodsDao = new GoodsDao();
		goodsDao.goodsUpdateByGcode(goodsDto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Glist/goodsSellerList.go");
		
		return forward;
	}

}
