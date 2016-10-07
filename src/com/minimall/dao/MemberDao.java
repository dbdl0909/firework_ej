package com.minimall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.minimall.dto.MemberDto;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


public class MemberDao {
	
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	MemberDto m = null;
	ArrayList<MemberDto> alm = new ArrayList<MemberDto>();
	
	public MemberDao() {
		
		try{
			Context init = new InitialContext();
			System.out.println(init + " : init GoodsDao.java");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			System.out.println("db연결성공");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("db연결실패");
		}
		
	}
	//회원가입
	public void insertMember(MemberDto m) throws SQLException{
		String sql=null;
		try{
			
			con = ds.getConnection();
			sql="insert into member values(?,?,?,?,?, sysdate(),?)";
			
			pstmt=con.prepareStatement(sql);			
			
			System.out.println(m.getm_level());
			
			pstmt.setString(1, m.getm_id());
			pstmt.setString(2, m.getm_pw());
			pstmt.setString(3, m.getm_level());
			pstmt.setString(4, m.getm_name());
			pstmt.setString(5, m.getm_email());
			pstmt.setString(6, m.getm_addr());
			
			pstmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}	finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}		
	}
	
	public ArrayList<MemberDto> mAllSelect() throws ClassNotFoundException, SQLException{
		System.out.println("05 mAllSelect Mdao.java");
		
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m = new MemberDto();
				System.out.println(m + "<-- m mAllSelect Mdao.java");
				m.setm_id(rs.getString("m_id"));
				m.setm_pw(rs.getString("m_pw"));
				m.setm_level(rs.getString("m_level"));
				m.setm_name(rs.getString("m_name"));
				m.setm_email(rs.getString("m_email"));
				m.setm_date(rs.getDate("m_date"));
				m.setm_addr(rs.getString("m_addr"));
				System.out.println(rs.getString("m_id")+"<--m_id.dao");
				alm.add(m);
				System.out.println(alm + "<-- alm mAllSelect Mdao.java");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}

		return alm;

	}
	
	//로그인 체크
	public MemberDto userCheck(String id, String pw) throws SQLException{
		String sql=null;	
		MemberDto m = null;
		try{
			con = ds.getConnection();
			sql="select * from member where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				String memberpw=rs.getString("m_pw");				
					if(memberpw.equals(pw)){
						String m_id = id;
						String m_level = rs.getString("m_level");
						String m_name = rs.getString("m_name");
						 //rs에담긴 level과 name을 변수에 담아놓음
						System.out.println(m_level);		
						System.out.println(m_name);
						
						m= new MemberDto();
						//멤버 객체를 생성하고
						//변수에 담아놓은 값들을 셋팅하고
						//멤버 객체를 리턴
						m.setm_level(m_level); 
						m.setm_name(m_name);
						m.setm_id(m_id);
				
					}
				}	
		}catch(Exception e){
			e.printStackTrace();
		}	finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
		
		return m;		
		
	}
 
    public int deleteMember(String id, String pw) throws SQLException{
        String sql=null;
        int x = -1;

        try{
            con = ds.getConnection();
            sql="select m_pw from member where m_id=?";

            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs=pstmt.executeQuery();

            if(rs.next()){
                String memberpw = rs.getString("m_pw");
                if(memberpw.equals(pw)){
                    sql="delete from member where m_id=?";
                    pstmt=con.prepareStatement(sql);
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
                    x=1;  
                }else{
                    x=0;
                }

            }   
        }catch(Exception e){
            e.printStackTrace();
        }    finally{
            try{
                if(rs!=null)rs.close();
                if(pstmt!=null)pstmt.close();
                if(con!=null)con.close();
            }catch(Exception ex) {}
        }

        return x;
    }
  //수정 화면 
    public MemberDto mSelectforUpdate(String mid) throws ClassNotFoundException, SQLException{
		System.out.println("04 mSelectforUpdate Mdao.java");
		
		con = ds.getConnection();
		pstmt = con.prepareStatement("select * from member where m_id=?");
		pstmt.setString(1, mid);
		rs = pstmt.executeQuery();	
		if(rs.next()){
			m = new MemberDto();
			m.setm_id(rs.getString("m_id"));
			m.setm_pw(rs.getString("m_pw"));
			m.setm_level(rs.getString("m_level"));
			m.setm_name(rs.getString("m_name"));
			m.setm_email(rs.getString("m_email"));
			m.setm_addr(rs.getString("m_addr"));
			
		}
		rs.close();
		pstmt.close();
		con.close();		
		return m;
	}
    //수정 처리 
    public void mUpdate(MemberDto m) throws SQLException, ClassNotFoundException{
		System.out.println("02 mUpdate Mdao.java");
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(
				"UPDATE member SET m_pw=?, m_level=?, m_name=?, m_email=? ,m_addr=? WHERE m_id=?");
		System.out.println(pstmt + "<-- pstmt 1");
		pstmt.setString(1, m.getm_pw());
		pstmt.setString(2, m.getm_level());
		pstmt.setString(3, m.getm_name());
		pstmt.setString(4, m.getm_email());
		pstmt.setString(5, m.getm_addr());
		pstmt.setString(6, m.getm_id());
		
		System.out.println(pstmt + "<-- pstmt 2");
		
		int result = pstmt.executeUpdate();
		if(result == 1){
			
			System.out.println("업데이트 성공");
			
		}else{
			
			System.out.println("업데이트 실패");
			
		}
		pstmt.close();
		con.close();
	}

}