package com.minimall.action.goods;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.GoodsDao;
import com.minimall.dto.GoodsDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class GoodsDetailAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsDeleteAction.java");
		
		request.setCharacterEncoding("UTF-8");
		
		String gCode = request.getParameter("gCode"); 
		System.out.println(gCode + " : gCode GoodsInsertPro.java");
		
		GoodsDao goodsDao = new GoodsDao();
		GoodsDto goodsDto = goodsDao.goodsSelectByGcode(gCode);
		String b64 = "";
		//¿ÃπÃ¡ˆ
		//String path = request.getServletContext().getRealPath("goodsImage");
		String path = "/home/hosting_users/dbdl10040909/tomcat/webapps/ROOT/upload/goodsImage";
		ArrayList<String> gImageType = new ArrayList<String>();
		//File file = new File(path + "\\" + imageName);
		File file = new File(path + "/" + goodsDto.getG_image());
		if(file.exists()) {
			BufferedImage image = ImageIO.read(file);
			String type = goodsDto.getG_image().substring(goodsDto.getG_image().lastIndexOf(".") + 1);
			type = type.toLowerCase();
			System.out.println(type + " : type GoodsInsertPro.java");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image,type,baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		}
		request.setAttribute("goods", goodsDto);
		request.setAttribute("b64", b64);
		request.setAttribute("gImageType", gImageType);
		
		ActionForward forward = new ActionForward();
    	forward.setRedirect(false);
    	forward.setPath("/goods/goodsDetail.jsp");
		
		return forward;
	}

}
