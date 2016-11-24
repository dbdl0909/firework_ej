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
	int goodsListAllCount;	//goods 테이블의 총 데이터 갯수		
	int goodsListCount;		//goodsList의 size
	int page = 1;		//현재 페이지 번호
	int limit = 12;		//한 페이지에서 보여줄 리스트 갯수
	
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
		
		//페이징 처리		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		goodsList = goodsDao.goodsSelectForCustom(page, limit, row);
		goodsListCount = goodsList.size();
		
		int maxPage = (int) ((double)goodsListAllCount/limit +0.95);	//총 페이지 수
		int startPage = ((int) (((double)page/10+0.9)) -1) *10 +1;	//현재 페이지에 보여줄 시작 페이지 번호
		int endPage = startPage +10 -1;								//현재 페이지에 보여줄 마지막 페이지 번호
		if(endPage > maxPage) endPage = maxPage;					//마지막 페이지 번호가 총 페이지 수보다 클 경우
																	//endPage에 maxPage의 값을 담는다.
		String ImageName;
		String type;
		
		if(goodsListAllCount != 0) {
			//String path = request.getServletContext().getRealPath("goodsImage");
			String path = "/home/hosting_users/dbdl10040909/tomcat/webapps/ROOT/upload/goodsImage";
			
			System.out.println("가져온 데이터 갯수 : " + goodsListCount + " : GoodsCustomListAction.java");
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
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("goodsListAllCount", goodsListAllCount);		//보여줄 글의 총 갯수
		
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
