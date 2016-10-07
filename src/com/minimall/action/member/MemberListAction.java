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
		//1�ܰ� ȭ�鿡�� �Է¹޴� ���� ����. ������ �˻��� ���� �˻� ������ �Էµȴ�.
		
		//2�ܰ� Mdao���� ������ ��ü���� select�޼ҵ� ȣ�� ��
		MemberDao dao= new MemberDao();
		//3�ܰ� ���ϰ�(ArrayList �ּҰ�)�� �޴´�.
		ArrayList<MemberDto> alm = dao.mAllSelect();
		//4�ܰ� request������ ����
		request.setAttribute("alm", alm);
		//5�ܰ� ���� �� list.jsp ������ 

		
		ActionForward foward = new ActionForward();
		foward.setRedirect(false);
		foward.setPath("/mypage/mypageMember.jsp");
		
		return foward;
	}

}
