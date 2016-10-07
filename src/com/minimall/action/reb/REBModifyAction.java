package com.minimall.action.reb;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dao.REBDao;
import com.minimall.dto.MemberDto;
import com.minimall.dto.REBDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;


public class REBModifyAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 	throws Exception{
		 
				 request.setCharacterEncoding("UTF-8");
				 ActionForward forward = new ActionForward();
				 boolean result = false;
				 
				 int num=Integer.parseInt(request.getParameter("reb_no"));
				   	String id = request.getParameter("id");
				    String pw = request.getParameter("reb_pass");
				    System.out.println(num + "<- num");
				    System.out.println(id + "<- id");
				    System.out.println(pw + "<- pw");
				    
				 MemberDao memberdao = new MemberDao();
				 MemberDto boardchk = memberdao.userCheck(id, pw);
				 
				 REBDao rebDao=new REBDao();
				 REBDto rebDto=new REBDto();
				 
				 //boolean usercheck=qnadao.isBoardWriter(num, request.getParameter("reb_pass"));
				 if(boardchk == null){
					 /* response.setContentType("text/html;charset=UTF-8");
				   		PrintWriter out=response.getWriter();
				   		out.println("<script>");
				   		out.println("alert('������ ������ �����ϴ�.');");
				   		out.println("location.href=request.getContextPath+'/Qna/QnaList.qn';");
				   		out.println("</script>");
				   		out.close();
				   		return null;*/
				   		System.out.println("��й�ȣ ����ġ");
						request.setAttribute("loginChk", 1);
						
						forward.setRedirect(false);
						forward.setPath("/Reb/rebDetailAction.reb");
				 } else {
				 
				 try{
					 rebDto.setReb_no(num);
					 rebDto.setReb_subject(request.getParameter("reb_subject"));
					 rebDto.setReb_content(request.getParameter("reb_content"));
					 
					 result = rebDao.boardModify(rebDto);
					/* if(result==false){
				   		System.out.println("�Խ��� ���� ����");
				   		return null;
				   	 }*/
				   	 System.out.println("�Խ��� ���� �Ϸ�");
				   	 
				   	 forward.setRedirect(true);
				   	 forward.setPath("/Reb/rebDetailAction.reb?num="+rebDto.getReb_no());
				   	 return forward;
			   	 }catch(Exception ex){
			   			ex.printStackTrace();	 
				 }
				 }
				 
				 return null;
			 }
}
