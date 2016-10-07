package com.minimall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minimall.action.member.MLoginAction;
import com.minimall.action.member.MSelectForUpdate;
import com.minimall.action.member.MUpdateAction;
import com.minimall.action.member.MemberDeleteAction;
import com.minimall.action.member.MemberInsertAction;
import com.minimall.action.member.MemberListAction;
import com.minimall.action.member.MemberLogoutAction;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;



@WebServlet("/Mcontroller")
public class MController extends HttpServlet {	
 
    public MController() { 
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Mcontroller.java");
		doPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Mcontroller.java");
		doPro(request, response);
	}
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPro Mcontroller.java");
		
		String fullUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cutUrl = fullUrl.substring( request.getContextPath().length());
		
		System.out.println(fullUrl + "<-fullUrl");
		System.out.println(contextPath + "<-contextPath");
		System.out.println(cutUrl + "<-cutUrl");

		ActionForward forward = null;		
		ActionInterFace action = null;
		
		if(cutUrl.equals("/Min/mInsert.mo")){
			
			System.out.println("����1�б� : ȸ������ ȭ��");
			forward = new ActionForward();//��ü���� �ϰ� �������� ������ ��ü�� �Ҵ��Ѵ�.
			forward.setRedirect(false);//���������(�����������°�/ȭ���̵������Ѱ� �ּҺ������ ȭ�鸸 �������°͵� ������)
			forward.setPath("/member/mInsertForm.jsp");
			
			
		}else if(cutUrl.equals("/Min/mInsertPro.mo")){
			System.out.println("����2 �б� : ȸ������ ó��");
			
			action  = new MemberInsertAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(cutUrl.equals("/Mli/mList.mo")){
			
			System.out.println("����3 ó����");
			action  = new MemberListAction();
			
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(cutUrl.equals("/Mup/mUpdateForm.mo")){//���� ȭ�� 
			System.out.println("����4 �������");
			
			action  = new MSelectForUpdate();
			
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(cutUrl.equals("/Mup/mUpdatePro.mo")){//���� ó��
			System.out.println("����5 ���þ�����Ʈ");
			
			action  = new MUpdateAction();  
			
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}/*else if(command.equals("/Mseach/m_search_pro.ksmart")){
			
			System.out.println("����7 �˻�");
			
			action  = new MSeachLIstAction();
			
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}			 
			
		}*/else if(cutUrl.equals("/mLogin/mLogin.mo")){ 
			System.out.println("�α��� ó�� ����");
			
			try{
				
				action = new MLoginAction();
				forward = action.execute(request, response);
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
		}else if(cutUrl.equals("/logout/logout.mo")){
			System.out.println("�α׾ƿ� ó��");
/*			try{
				
				action = new MemberLogoutAction();
				forward = action.execute(request, response);
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}*/
			
			HttpSession session=request.getSession();
			session.invalidate();
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getHeader("referer"));
			
		}else if(cutUrl.equals("/member/MemberDeleteAction.mo")){ //member ���� ó��
            System.out.println("member ���� ó��");

            action  = new MemberDeleteAction();

             try {
                 forward = action.execute(request, response);
             } catch (Exception e) { 
                 e.printStackTrace();
             }
        }

		
		if(forward != null){
			if(forward.isRedirect()){
				
				response.sendRedirect(forward.getPath());
				
			}else{
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); 
				System.out.println(forward.getPath() + "<--- forward.getPath()[jsp �̵����]  BoardFrontController.java");
				System.out.println();
				
				dispatcher.forward(request, response);				
				
			}
		}
	}
}

