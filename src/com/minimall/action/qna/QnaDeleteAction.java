package com.minimall.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dao.QnaDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;


public class QnaDeleteAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	System.out.println("����� �Ͷ�");
				 
				ActionForward forward = new ActionForward();
				request.setCharacterEncoding("utf-8");
				
			   	/*boolean result=false;
			   	boolean usercheck=false;*/
			   	int num=Integer.parseInt(request.getParameter("num"));
			   	String id = request.getParameter("id");
			    String pw = request.getParameter("qna_pass");
			    System.out.println(num + "<- num");
			    System.out.println(id + "<- id");
			    System.out.println(pw + "<- pw");
			   	//HttpSession session = request.getSession();
			   	
			   	//QnaDao qnadao=new QnaDao();
			   	//usercheck=qnadao.isBoardWriter(num, request.getParameter("qna_pass"));
			   	
			    //String id = (String)session.getAttribute("loginId");
			   				   	
			   	MemberDao memberdao = new MemberDao();
			   	MemberDto boardchk = memberdao.userCheck(id, pw);
			   	
			   	if(boardchk == null){
			   		/*response.setContentType("text/html;charset=euc-kr");
			   		PrintWriter out=response.getWriter();
			   		out.println("<script>");
			   		out.println("alert('������ ������ �����ϴ�.');");
			   		out.println("window.location.href='/Qna/QnaList.qn';");
			   		out.println("</script>");
			   		out.close();
			   		return null;*/
					System.out.println("��й�ȣ ����ġ");
					request.setAttribute("loginChk", 1);
					
					forward.setRedirect(false);
					forward.setPath("/Qna/QnaDetailAction.qn");
			   	}else{
			   	
			   	//result=qnadao.qnaDelete(num);
			   	/*if(result==false){
			   		System.out.println("�Խ��� ���� ����");
			   		return null;
			   	}*/
			   		
			   	QnaDao qnadao = new QnaDao();
			   	qnadao.qnaDelete(num);
			   	
			   	System.out.println("�Խ��� ���� ����");
			   	forward.setRedirect(true);
		   		forward.setPath(request.getContextPath() + "/Qna/QnaList.qn");
		   		
			   	}
			   	
			   	return forward;
			 }
}
