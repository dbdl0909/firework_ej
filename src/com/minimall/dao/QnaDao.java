package com.minimall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.minimall.dto.QnaDto;

public class QnaDao {
	DataSource ds;
	Connection con;
	PreparedStatement pstmtSelect;
	PreparedStatement pstmt;
	ResultSet rs;
	QnaDto qnadto = null;
	
	public QnaDao() {
		try {
			Context init = new InitialContext();
			System.out.println(init + " : init GoodsDao.java");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	//상품코드별 리스트
	public List gcodeQnaList(int page,int limit, String gCode){ 
		//String gcode_qna_list_sql="select qna_no,qna_subject,m_id,qna_content,qna_secret,qna_category,qna_date,qna_readcount,g_code,qna_ref from qna_board where g_code=?";
		
		String gcode_qna_list_sql = "SELECT q.qna_ref as qna_ref, q.qna_no as qna_no, q.qna_subject as qna_subject, q.m_id as m_id, q.qna_content as qna_content, q.qna_secret as qna_secret,"+
				" q.qna_category as qna_category, q.qna_date as qna_date, q.qna_readcount as qna_readcount, q.g_code as g_code"+
				" FROM (SELECT qna_ref, qna_no,qna_subject,m_id, qna_content,qna_secret,qna_category,qna_date, qna_readcount, g_code"+
				" FROM (SELECT * FROM qna_board ORDER BY qna_ref DESC) as qq) as q WHERE g_code=? LIMIT ?,?";
		
		List list = new ArrayList();
		System.out.println(gcode_qna_list_sql + "<-- gcode_qna_list_sql gcodeQnaList QnaDAO.java");
		System.out.println(page + "<-- page gcodeQnaList QnaDAO.java");
		System.out.println(limit + "<-- limit gcodeQnaList QnaDAO.java");
		System.out.println();
		
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호.
		int endrow=startrow+limit-1; //읽을 마지막 row 번호.	
		
		System.out.println(startrow + "<-- startrow gcodeQnaList QnaDAO.java");
		System.out.println(endrow + "<-- endrow gcodeQnaList QnaDAO.java");
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(gcode_qna_list_sql);
			pstmt.setString(1, gCode);
			pstmt.setInt(2, startrow-1);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				QnaDto qnadto = new QnaDto();
				qnadto.setQna_ref(rs.getInt("qna_ref"));
				qnadto.setQna_no(rs.getInt("qna_no"));
				//System.out.println(qnadto.getQna_no());
				qnadto.setQna_subject(rs.getString("qna_subject"));
				qnadto.setM_id(rs.getString("m_id"));
				qnadto.setQna_content(rs.getString("qna_content"));
				qnadto.setQna_secret(rs.getString("qna_secret"));
				qnadto.setQna_category(rs.getString("qna_category"));
				qnadto.setQna_date(rs.getDate("qna_date"));
				qnadto.setQna_readcount(rs.getInt("qna_readcount"));
				qnadto.setG_code(rs.getString("g_code"));
				//System.out.println(qnadto.getG_code());
				list.add(qnadto);
			}
		}catch(Exception ex){
			System.out.println("gcodeQnaList 에러 : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return list;		
	}

	//글 수정
	public boolean boardModify(QnaDto pna) throws Exception{
		
		String sql="update qna_board set qna_subject=?,qna_content=? where qna_no=?";
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pna.getQna_subject());
			pstmt.setString(2, pna.getQna_content());
			pstmt.setInt(3, pna.getQna_no());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("boardModify 에러 : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
			}
		return false;
	}
	
	//글 삭제
	public boolean qnaDelete(int num){
		
		String qna_delete_sql="delete from qna_board where qna_no=?";
		
		int result=0;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(qna_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardDelete 에러 : "+ex);
		}	finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null) con.close();
				}
				catch(Exception ex){}
		}
		
		return false;
	}
	
	//글 답변
		public int QnaReply(QnaDto qna){
			
			String board_max_sql="select max(qna_no) from qna_board";
			String sql="";
			int num=0;
			int result=0;
			
			/*int re_ref=qnadto.getBOARD_RE_REF();
			int re_lev=board.getBOARD_RE_LEV();
			int re_seq=board.getBOARD_RE_SEQ();*/
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(board_max_sql);
				rs = pstmt.executeQuery();
				if(rs.next())num =rs.getInt(1)+1;
				else num=1;
				
				/*sql="update QNA_BOARD set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? ";
				sql+="and BOARD_RE_SEQ>?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,re_ref);
				pstmt.setInt(2,re_seq);
				result=pstmt.executeUpdate();
				
				re_seq = re_seq + 1;
				re_lev = re_lev+1;*/
				
				sql="insert into qna_board (qna_no,qna_subject,m_id,qna_content,qna_secret,qna_category,qna_date,qna_ref) values (?,?,?,?,?,?,sysdate(),?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num+1);
				pstmt.setString(2, qna.getQna_subject());
				pstmt.setString(3, qna.getM_id());
				//pstmt.setString(3, "id001");
				pstmt.setString(4, qna.getQna_content());
				pstmt.setString(5, qna.getQna_secret());
				pstmt.setString(6, qna.getQna_category());
				pstmt.setInt(7, qna.getQna_ref());
				pstmt.executeUpdate();

				
				return num;
			}catch(SQLException ex){
				System.out.println("boardReply 에러 : "+ex);
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			return 0;
		}
		
	//글 목록 보기
		public List getQnaList(int page,int limit){ 
			//오라클
			/*String qna_list_sql="select * from "+
			"(select rownum rnum,qna_no,qna_subject,m_id, qna_content,qna_secret,qna_category,qna_date, qna_readcount from "+
			"(select * from qna_board order by qna_no desc)) where rnum>=? and rnum<=?";*/

			//mySql
			String qna_list_sql = "SELECT q.qna_no as qna_no, q.qna_subject as qna_subject, q.m_id as m_id, q.qna_content as qna_content, q.qna_secret as qna_secret,"+
			" q.qna_category as qna_category, q.qna_date as qna_date, q.qna_readcount as qna_readcount, q.g_code as g_code"+
			" FROM (SELECT qna_no,qna_subject,m_id, qna_content,qna_secret,qna_category,qna_date, qna_readcount, g_code"+
			" FROM (SELECT * FROM qna_board ORDER BY qna_ref DESC) as qq) as q LIMIT ?,?";
			
			List list = new ArrayList();
			System.out.println(qna_list_sql + "<-- board_list_sql getQnaList QnaDAO.java");
			System.out.println(page + "<-- page getQnaList QnaDAO.java");
			System.out.println(limit + "<-- limit getQnaList QnaDAO.java");
			System.out.println();
			
			int startrow=(page-1)*10+1; //읽기 시작할 row 번호.
			int endrow=startrow+limit-1; //읽을 마지막 row 번호.	
			
			System.out.println(startrow + "<-- startrow getQnaList QnaDAO.java");
			System.out.println(endrow + "<-- endrow getQnaList QnaDAO.java");
			
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(qna_list_sql);
				pstmt.setInt(1, startrow-1);
				pstmt.setInt(2, endrow);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					QnaDto qnadto = new QnaDto();
					qnadto.setQna_no(rs.getInt("qna_no"));
					//System.out.println(qnadto.getQna_no());
					qnadto.setQna_subject(rs.getString("qna_subject"));
					qnadto.setM_id(rs.getString("m_id"));
					qnadto.setQna_content(rs.getString("qna_content"));
					qnadto.setQna_secret(rs.getString("qna_secret"));
					qnadto.setQna_category(rs.getString("qna_category"));
					qnadto.setQna_date(rs.getDate("qna_date"));
					qnadto.setQna_readcount(rs.getInt("qna_readcount"));
					qnadto.setG_code(rs.getString("g_code"));
					//System.out.println(qnadto.getG_code());
					list.add(qnadto);
				}
			}catch(Exception ex){
				System.out.println("getQnaList 에러 : " + ex);
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			return list;
		}
	
	//글 내용 보기.
		public QnaDto getDetail(int num) throws Exception{
			
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement("select qna_no,qna_subject,m_id,qna_content,qna_secret,qna_category,qna_date,qna_readcount,g_code,qna_ref from qna_board where qna_no = ?");
				pstmt.setInt(1, num);
				
				rs= pstmt.executeQuery();
				
				if(rs.next()){
					qnadto = new QnaDto();
					qnadto.setQna_no(rs.getInt("qna_no"));				
					qnadto.setQna_subject(rs.getString("qna_subject"));
					qnadto.setM_id(rs.getString("m_id"));
					System.out.println("m_id : " + qnadto.getM_id());
					qnadto.setQna_content(rs.getString("qna_content"));
					qnadto.setQna_secret(rs.getString("qna_secret"));
					qnadto.setQna_category(rs.getString("qna_category"));
					qnadto.setQna_date(rs.getDate("qna_date"));
					qnadto.setQna_readcount(rs.getInt("qna_readcount"));
					qnadto.setQna_ref(rs.getInt("qna_ref"));
				}
			}catch(Exception ex){
				System.out.println("getDetail 에러 : " + ex);
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			return qnadto;
		}
		
	//글의 개수 구하기
		public int getListCount() {
			int x= 0;
			
			try{
				con=ds.getConnection();
				System.out.println("getConnection");
				pstmt=con.prepareStatement("select count(*) from qna_board");
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					x=rs.getInt(1);
				}
			}catch(Exception ex){
				System.out.println("getListCount 에러: " + ex);			
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			System.out.println(x + " : 글 갯수");
			return x;
		}	
	
	//조회수 업데이트
		public void setReadCountUpdate(int num) throws Exception{
			
			String sql="update qna_board set qna_readcount = qna_readcount+1 where qna_no = "+num;
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(SQLException ex){
				System.out.println("setReadCountUpdate 에러 : "+ex);
			}finally{
				try{
					if(pstmt!=null)pstmt.close();
					if(con!=null) con.close();
				} catch(Exception ex){
				}	
			}
		}
	//글 등록
	public boolean boardInsert(QnaDto qna){
		
		int num = 0;
		String sql = "";
		
		int result = 0;
		boolean re = false;
		try{
			con = ds.getConnection();
			
			System.out.println(con + "<-- con boardInsert() QnaDAO.java");
			pstmtSelect=con.prepareStatement("select max(qna_no) from qna_board");
			rs = pstmtSelect.executeQuery();
			
			if(rs.next()) {
				num =rs.getInt(1)+1;
			} else {
				num=1;
			}
			System.out.println(num + " : num");
			pstmtSelect.close();
			
			if(qna.getQna_secret() == null) {
				qna.setQna_secret("n");
			}
			sql="insert into qna_board (qna_no,qna_subject,m_id,qna_content,qna_secret,qna_category,qna_date,g_code,qna_ref) values (?,?,?,?,?,?,sysdate(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num+1);
			pstmt.setString(2, qna.getQna_subject());
			pstmt.setString(3, qna.getM_id());
			pstmt.setString(4, qna.getQna_content());
			pstmt.setString(5, qna.getQna_secret());
			pstmt.setString(6, qna.getQna_category());
			pstmt.setString(7, qna.getG_code());
			pstmt.setInt(8, num+1);
			
			result=pstmt.executeUpdate();
			
			if(result==0){
				re = false;
			}else {
				re = true;
			}
		}catch(Exception ex){
			System.out.println("boardInsert 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return re;
	}
	//글쓴이인지 확인
	public boolean isBoardWriter(int num,String pass){
			
			String qna_sql="select qna_no,qna_subject,m_id,qna_content,qna_secret,qna_category,qna_date,qna_readcount,g_code,qna_ref from qna_board where qna_no=?";
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(qna_sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				rs.next();
				
				/*if(pass.equals(rs.getString("qna_PASS"))){
					return true;
				}*/
			}catch(SQLException ex){
				System.out.println("isBoardWriter 에러 : "+ex);
			}
		finally{
				try{
				if(pstmt!=null)pstmt.close();
				if(con!=null) con.close();
				}
				catch(Exception ex){}
			
		}
			return false;
		}
}
