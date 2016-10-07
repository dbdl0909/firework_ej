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

public class indexGoodsList implements ActionInterFace {
	ArrayList<GoodsDto> goodsList = null;
	ArrayList<String> b64Array = new ArrayList<String>();
	ArrayList<String> gImageType = new ArrayList<String>();
	File file;
	String b64;
	int goodsListAllCount;	//goods 테이블의 총 데이터 갯수
	int goodsListCount;		//goodsList의 size
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() indexGoodsList.java");
		
		GoodsDto goodsDto = new GoodsDto();
		GoodsDao goodsDao = new GoodsDao();
		goodsListAllCount = goodsDao.getListCount();
		
		String ImageName;
		String type;
		
		String path = request.getServletContext().getRealPath("goodsImage");
		//String path = "/home/hosting_users/cynical1031/tomcat/webapps/ROOT/upload/goodsImage";
		
		goodsList = goodsDao.goodsSelectOrderByDate();
		goodsListCount = goodsList.size();
		
		if(goodsListAllCount != 0) {
			for(int i=0; i<goodsListCount; i++) {
				goodsDto = goodsList.get(i);
				
				//저장되어있는 이미지의 확장자만 잘라서 변수에 담고 대문자를 소문자로 변환
				ImageName = goodsDto.getG_image();
				type = ImageName.substring(ImageName.lastIndexOf(".") + 1);
				
				//DB에서 가져온 여러개의 이미지의 이름에 경로를 붙여 File로 만든뒤 ByteArrayOutputStream.toByteArray()를 통해 byte[]로 만든다.
				System.out.println(path + "\\" + ImageName + " : path");
				//file = new File(path + "\\" + ImageName);
				file = new File(path + "/" + ImageName);
				if(file.exists()) {
					BufferedImage image = ImageIO.read(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(image,type,baos);
					baos.flush();
					byte[] imageInByteArray = baos.toByteArray();
					baos.close();
					//byte[]를 String으로 변환
					b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
				} else {
					b64 = null;
				}
				b64Array.add(b64);
				gImageType.add(type);
			}
			System.out.println("goodsList.size() : " + goodsList.size() + ", b64Array.size() : " + b64Array.size() + " GoodsCustomListAction.java");
			
		}
		request.setAttribute("b64", b64Array);
		request.setAttribute("gImageType", gImageType);
		
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("goodsListAllCount", goodsListAllCount);		//보여줄 글의 총 갯수
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/goods/temp.jsp");
		
		return forward;
	}

}
