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
	int goodsListAllCount;	//goods ���̺��� �� ������ ����
	int goodsListCount;		//goodsList�� size
	
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
				
				//����Ǿ��ִ� �̹����� Ȯ���ڸ� �߶� ������ ��� �빮�ڸ� �ҹ��ڷ� ��ȯ
				ImageName = goodsDto.getG_image();
				type = ImageName.substring(ImageName.lastIndexOf(".") + 1);
				
				//DB���� ������ �������� �̹����� �̸��� ��θ� �ٿ� File�� ����� ByteArrayOutputStream.toByteArray()�� ���� byte[]�� �����.
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
					//byte[]�� String���� ��ȯ
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
		request.setAttribute("goodsListAllCount", goodsListAllCount);		//������ ���� �� ����
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/goods/temp.jsp");
		
		return forward;
	}

}
