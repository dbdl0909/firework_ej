package com.minimall.action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimall.dao.MemberDao;
import com.minimall.dto.MemberDto;
import com.minimall.forward.ActionForward;
import com.minimall.inter.ActionInterFace;

public class MemberListAction implements ActionInterFace {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MListProAction execute");
		//1단계 화면에서 입력받는 값은 없다. 하지만 검색의 경우는 검색 조건이 입력된다.
		
		//2단계 Mdao통해 생성된 객체내의 select메소드 호출 후
		MemberDao dao= new MemberDao();
		//3단계 리턴값(ArrayList 주소값)을 받는다.
		ArrayList<MemberDto> alm = dao.mAllSelect();
		//4단계 request영역에 셋팅
		request.setAttribute("alm", alm);
		//5단계 셋팅 후 list.jsp 포워드 

		
		ActionForward foward = new ActionForward();
		foward.setRedirect(false);
		foward.setPath("/mypage/mypageMember.jsp");
		
		return foward;
	}

}
