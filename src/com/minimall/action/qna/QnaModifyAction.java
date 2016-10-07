package com.minimall.action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dao.QnaDao;
import com.minimall.dto.MemberDto;
import com.minimall.dto.QnaDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;


public class QnaModifyAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 	throws Exception{
		 
				 request.setCharacterEncoding("UTF-8");
				 ActionForward forward = new ActionForward();
				 boolean result = false;
				 
				 int num=Integer.parseInt(request.getParameter("qna_no"));
				   	String id = request.getParameter("m_id");
				    String pw = request.getParameter("qna_pass");
				    System.out.println(num + "<- num");
				    System.out.println(id + "<- id");
				    System.out.println(pw + "<- pw");
				    
				 MemberDao memberdao = new MemberDao();
				 MemberDto boardchk = memberdao.userCheck(id, pw);
				 
				 QnaDao qnadao=new QnaDao();
				 QnaDto qnadto=new QnaDto();
				 
				 //boolean usercheck=qnadao.isBoardWriter(num, request.getParameter("qna_pass"));
				 if(boardchk == null){
					 /* response.setContentType("text/html;charset=UTF-8");
				   		PrintWriter out=response.getWriter();
				   		out.println("<script>");
				   		out.println("alert('수정할 권한이 없습니다.');");
				   		out.println("location.href=request.getContextPath+'/Qna/QnaList.qn';");
				   		out.println("</script>");
				   		out.close();
				   		return null;*/
				   		System.out.println("비밀번호 불일치");
						request.setAttribute("loginChk", 1);
						
						forward.setRedirect(false);
						forward.setPath("/Qna/QnaDetailAction.qn");
				 } else {
						 try{
							 qnadto.setQna_no(num);
							 qnadto.setQna_subject(request.getParameter("qna_subject"));
							 qnadto.setQna_content(request.getParameter("qna_content"));
							 
							 result = qnadao.boardModify(qnadto);
							/* if(result==false){
						   		System.out.println("게시판 수정 실패");
						   		return null;
						   	 }*/
						   	 System.out.println("게시판 수정 완료");
						   	 
						   	 forward.setRedirect(true);
						   	 forward.setPath(request.getContextPath()+"/Qna/QnaDetailAction.qn?num="+qnadto.getQna_no());
						   	 return forward;
					   	 }catch(Exception ex){
					   			ex.printStackTrace();	 
						 }
				 }
				 
				 return null;
			 }
}
