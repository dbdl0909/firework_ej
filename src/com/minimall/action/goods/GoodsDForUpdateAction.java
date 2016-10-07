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

public class GoodsDForUpdateAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsDForUpdateAction.java");
		
		request.setCharacterEncoding("UTF-8");
		String gCode = request.getParameter("gCode");
		System.out.println(gCode + " : GoodsDForUpdateAction.java");
		
		GoodsDao goodsDao = new GoodsDao();
		GoodsDto goodsDto = goodsDao.goodsSelectByGcode(gCode);
		
		/*File file;
		String b64;
		ArrayList<String> b64Array = new ArrayList<String>();
		//String path = request.getServletContext().getRealPath("goodsImage");
		String path = "/home/hosting_users/cynical1031/tomcat/webapps/ROOT/upload/goodsImage";
		
		if(goodsDto.getG_image() != null) {		//이미지가 있다면
			String imageName = goodsDto.getG_image();
			String type = imageName.substring(imageName.lastIndexOf(".") + 1);
			
			System.out.println(path + "/" + imageName + " : path");
			//file = new File(path + "\\" + ImageName);
			file = new File(path + "/" + imageName);
			if(file.exists()) {
				BufferedImage image = ImageIO.read(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image,type,baos);
				baos.flush();
				byte[] imageInByteArray = baos.toByteArray();
				baos.close();
				
				b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
			} else {
				b64 = null;
			}
			b64Array.add(b64);
		}
		System.out.println("b64Array.size() : " + b64Array.size() + " GoodsCustomListAction.java");
		request.setAttribute("b64", b64Array);*/
		
		//String path = request.getServletContext().getRealPath("goodsImage");
		String path = "/home/hosting_users/cynical1031/tomcat/webapps/ROOT/upload/goodsImage";
		request.setAttribute("path", path);
		
		request.setAttribute("goodsDto", goodsDto);		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/goods/goodsUpdateForm.jsp");
		
		return forward;
	}

}
