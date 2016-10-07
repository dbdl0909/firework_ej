package com.minimall.action.qna;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.action.goods.GoodsDetailAction;
import com.minimall.dao.GoodsDao;
import com.minimall.dao.QnaDao;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class QnaGcodeListAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("����Ʈ executeȣ��com.minimall.action.GcodeQnaListAction.java"); 
		QnaDao qnadao=new QnaDao();
		List qnalist=new ArrayList();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		String gCode = request.getParameter("gCode");
		System.out.println("gCode : " + gCode);
		
		//int listcount=qnadao.getListCount(); //�� ����Ʈ ���� �޾ƿ�
		qnalist = qnadao.gcodeQnaList(page,limit, gCode); //����Ʈ�� �޾ƿ�
		int listcount = qnalist.size();
		
		//�� ������ ��
		int maxpage=(int)((double)listcount/limit+0.95); //0.95�� ���ؼ� �ø� ó��
		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		//���� �������� ������ ������ ������ ��(10, 20, 30 ��...)
		int endpage = startpage+10-1;

		if (endpage> maxpage) endpage= maxpage;
		
		request.setAttribute("page", page); //���� ������ ��
		request.setAttribute("maxpage", maxpage); //�ִ� ������ ��
		request.setAttribute("startpage", startpage); //���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpage", endpage); //���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcount",listcount); //�� ��
		request.setAttribute("qnalist", qnalist);
		request.setAttribute("gCode", gCode);
		
		System.out.println(page + " : page");
		System.out.println(listcount+"<-listcount");
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);
		forward.setPath("/qnaBoard/qna_list.jsp");
		return forward;
	}

}
