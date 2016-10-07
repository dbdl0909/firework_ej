package com.minimall.action.reb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dao.REBDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;


public class REBDeleteAction implements ActionInterFace {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	System.out.println("여기로 와라");
				 
				ActionForward forward = new ActionForward();
				request.setCharacterEncoding("utf-8");
				
			   	/*boolean result=false;
			   	boolean usercheck=false;*/
			   	int num=Integer.parseInt(request.getParameter("num"));
			   	String id = request.getParameter("id");
			    String pw = request.getParameter("reb_pass");
			    System.out.println(num + "<- num");
			    System.out.println(id + "<- id");
			    System.out.println(pw + "<- pw");
			   	//HttpSession session = request.getSession();
			   	
			   	//REBDao REBdao=new REBDao();
			   	//usercheck=REBdao.isBoardWriter(num, request.getParameter("REB_pass"));
			   	
			    //String id = (String)session.getAttribute("loginId");
			   				   	
			   	MemberDao memberdao = new MemberDao();
			   	MemberDto boardchk = memberdao.userCheck(id, pw);
			   	
			   	if(boardchk == null){
			   		/*response.setContentType("text/html;charset=euc-kr");
			   		PrintWriter out=response.getWriter();
			   		out.println("<script>");
			   		out.println("alert('삭제할 권한이 없습니다.');");
			   		out.println("window.location.href='/REB/REBList.qn';");
			   		out.println("</script>");
			   		out.close();
			   		return null;*/
					System.out.println("비밀번호 불일치");
					request.setAttribute("loginChk", 1);
					
					forward.setRedirect(false);
					forward.setPath("/Reb/rebDetailAction.reb");
			   	}else{
			   	
			   	//result=REBdao.REBDelete(num);
			   	/*if(result==false){
			   		System.out.println("게시판 삭제 실패");
			   		return null;
			   	}*/
			   		
			   	REBDao rebDao = new REBDao();
			   	rebDao.REBDelete(num);
			   	
			   	System.out.println("게시판 삭제 성공");
			   	forward.setRedirect(true);
		   		forward.setPath(request.getContextPath() + "/Reb/rebList.reb");
		   		
			   	}
			   	
			   	return forward;
			 }
}
