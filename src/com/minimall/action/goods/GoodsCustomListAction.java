package com.minimall.action.goods;

import java.awt.image.BufferedImage;
import java.awt.print.Pageable;
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

public class GoodsCustomListAction implements ActionInterFace {
	ArrayList<GoodsDto> goodsList = null;
	ArrayList<String> b64Array = new ArrayList<String>();
	ArrayList<String> gImageType = new ArrayList<String>();
	File file;
	String b64;
	int goodsListAllCount;	//goods ���̺��� �� ������ ����		
	int goodsListCount;		//goodsList�� size
	int page = 1;		//���� ������ ��ȣ
	int limit = 12;		//�� ���������� ������ ����Ʈ ����
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("01 execute() GoodsCustomListAction.java");
		
		GoodsDto goodsDto = new GoodsDto();
		GoodsDao goodsDao = new GoodsDao();
		goodsListAllCount = goodsDao.getListCount();
		String row = "new";
		
		if(request.getParameter("row") != null) {
			row = request.getParameter("row");
			System.out.println(row + "<- row GoodsCustomListAction.java");
		}			
		
		//����¡ ó��		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		goodsList = goodsDao.goodsSelectForCustom(page, limit, row);
		goodsListCount = goodsList.size();
		
		int maxPage = (int) ((double)goodsListAllCount/limit +0.95);	//�� ������ ��
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//���� �������� ������ ���� ������ ��ȣ
		int endPage = startPage +10 -1;								//���� �������� ������ ������ ������ ��ȣ
		if(endPage > maxPage) endPage = maxPage;					//������ ������ ��ȣ�� �� ������ ������ Ŭ ���
																	//endPage�� maxPage�� ���� ��´�.
		String ImageName;
		String type;
		
		if(goodsListAllCount != 0) {
			//String path = request.getServletContext().getRealPath("goodsImage");
			String path = "/home/hosting_users/cynical1031/tomcat/webapps/ROOT/upload/goodsImage";
			
			System.out.println("������ ������ ���� : " + goodsListCount + " : GoodsCustomListAction.java");
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
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("goodsListAllCount", goodsListAllCount);		//������ ���� �� ����
		
		System.out.println(page + " : page GoodsCustomListAction.java");
		System.out.println(maxPage + " : maxPage GoodsCustomListAction.java");
		System.out.println(startPage + " : startPage GoodsCustomListAction.java");
		System.out.println(endPage + " : endPage GoodsCustomListAction.java");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/goods/goodsCustomList.jsp");
		
		return forward;
	}

}
