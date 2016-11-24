package com.minimall.action.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GoodsInsertPro implements ActionInterFace {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsInsertPro.java");
		
		/*request.setCharacterEncoding("euc-kr");
		String gId = request.getParameter("gId");
		String gName = request.getParameter("gName");
		String gCate = request.getParameter("gCate");
		int gPrice = Integer.parseInt(request.getParameter("gPrice"));
		String gSangse = request.getParameter("gSangse");*/
		
		//String imagePath = request.getServletContext().getRealPath("minimall");		
		
		/*//goodsImage 폴더 생성
		File imageDirectory = new File();

		if(!imageDirectory.exists()){ 
			imageDirectory.mkdirs();
			System.out.println("goodsImage 디렉토리를 생성했습니다.");
		} else {
		    System.out.println("goodsImage 디렉토리가 존재합니다.");
		}*/
		
		//String path = request.getServletContext().getRealPath("goodsImage");
		String path = "/home/hosting_users/dbdl10040909/tomcat/webapps/ROOT/upload/goodsImage"; 
		System.out.println(path);
		int maxSize = 500*500*100;
		String encType = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, path, maxSize, encType, new DefaultFileRenamePolicy());

		String gId = multipartRequest.getParameter("gId");
		String gName = multipartRequest.getParameter("gName");
		String gCate = multipartRequest.getParameter("gCate");
		int gPrice = Integer.parseInt(multipartRequest.getParameter("gPrice"));
		String gSangse = multipartRequest.getParameter("gSangse");
		
		String gImageFileSysName = multipartRequest.getFilesystemName("myImage");
		String gImageOriginName = multipartRequest.getOriginalFileName("myImage");
		
		System.out.println(gId + " : gId GoodsInsertPro.java");
		System.out.println(gName + " : gName GoodsInsertPro.java");
		System.out.println(gCate + " : gCate GoodsInsertPro.java");
		System.out.println(gPrice + " : gPrice GoodsInsertPro.java");
		System.out.println(gSangse + " : gSangse GoodsInsertPro.java");
		
		System.out.println(gImageFileSysName + " : gImageFileSysName GoodsInsertPro.java");
		System.out.println(gImageOriginName + " : gImgageOriginName GoodsInsertPro.java");
		
		File file = multipartRequest.getFile("myImage");
		System.out.println(file);
		String gImageFile = file.toString();
		
		GoodsDto goodsDto = new GoodsDto();
		goodsDto.setG_id(gId);
		goodsDto.setG_name(gName);
		goodsDto.setG_cate(gCate);
		goodsDto.setG_price(gPrice);
		goodsDto.setG_sangse(gSangse);
		goodsDto.setG_image(gImageFileSysName);
		
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.goodsInsert(goodsDto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/Glist/goodsSellerList.go");
		
		return forward;
	}
	
}
