package com.minimall.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.minimall.dto.REBDto;


public class REBDao {
	DataSource ds;
	Connection con;
	PreparedStatement pstmtSelect;
	PreparedStatement pstmt;
	ResultSet rs;
	REBDto rebDto;
	
	public REBDao() {
		try{
			Context init = new InitialContext();
			System.out.println(init + " : init GoodsDao.java");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
	  	  System.out.println("db연결성공 ");
	  		
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	//글 수정
		public boolean boardModify(REBDto pna) throws Exception{
			
			String sql="update reb_board set reb_subject=?,reb_content=? where reb_no=?";
			
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pna.getReb_subject());
				pstmt.setString(2, pna.getReb_content());
				pstmt.setInt(3, pna.getReb_no());
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
		public boolean REBDelete(int num){
			
			String reb_delete_sql="delete from reb_board where reb_no=?";
			
			int result=0;
			
			try{
				con = ds.getConnection();
				pstmt=con.prepareStatement(reb_delete_sql);
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
			public int REBReply(REBDto qna){
				
				String board_max_sql="select max(reb_no) from reb_board";
				String sql="";
				int num=0;
				int result=0;
				
				/*int re_ref=REBDto.getBOARD_RE_REF();
				int re_lev=board.getBOARD_RE_LEV();
				int re_seq=board.getBOARD_RE_SEQ();*/
				
				try{
					con = ds.getConnection();
					pstmt=con.prepareStatement(board_max_sql);
					rs = pstmt.executeQuery();
					if(rs.next())num =rs.getInt(1)+1;
					else num=1;
					
					/*sql="update reb_BOARD set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? ";
					sql+="and BOARD_RE_SEQ>?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1,re_ref);
					pstmt.setInt(2,re_seq);
					result=pstmt.executeUpdate();
					
					re_seq = re_seq + 1;
					re_lev = re_lev+1;*/
					
					sql="insert into reb_board (reb_no,reb_subject,m_id,reb_content,reb_secret,reb_category,reb_date,reb_ref) values (?,?,?,?,?,?,sysdate(),?)";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num+1);
					pstmt.setString(2, qna.getReb_subject());
					pstmt.setString(3, qna.getM_id());
					//pstmt.setString(3, "id001");
					pstmt.setString(4, qna.getReb_content());
					pstmt.setString(5, qna.getReb_secret());
					pstmt.setString(6, qna.getReb_category());
					pstmt.setInt(7, qna.getReb_ref());
					pstmt.executeUpdate();
					System.out.println(qna.getM_id());
					
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
			public List getRebList(int page,int limit){ 
				//오라클
				/*String reb_list_sql="select * from "+
				"(select rownum rnum,reb_no,reb_subject,m_id, reb_content,reb_secret,reb_category,reb_date, reb_readcount from "+
				"(select * from reb_board order by reb_no desc)) where rnum>=? and rnum<=?";*/

				//mySql
				String reb_list_sql = "SELECT q.reb_no as reb_no, q.reb_subject as reb_subject, q.m_id as m_id, q.reb_content as reb_content, q.reb_secret as reb_secret,"+
				" q.reb_category as reb_category, q.reb_date as reb_date, q.reb_readcount as reb_readcount, q.g_code as g_code"+
				" FROM (SELECT reb_no,reb_subject,m_id, reb_content,reb_secret,reb_category,reb_date, reb_readcount, g_code"+
				" FROM (SELECT * FROM reb_board ORDER BY reb_no DESC) as qq)as q LIMIT ?,?";
				
				List list = new ArrayList();
				System.out.println(reb_list_sql + "<-- board_list_sql getQnaList QnaDAO.java");
				System.out.println(page + "<-- page getQnaList QnaDAO.java");
				System.out.println(limit + "<-- limit getQnaList QnaDAO.java");
				System.out.println();
				
				int startrow=(page-1)*10+1; //읽기 시작할 row 번호.
				int endrow=startrow+limit-1; //읽을 마지막 row 번호.	
				
				System.out.println(startrow + "<-- startrow getQnaList QnaDAO.java");
				System.out.println(endrow + "<-- endrow getQnaList QnaDAO.java");
				
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement(reb_list_sql);
					pstmt.setInt(1, startrow-1);
					pstmt.setInt(2, endrow);
					rs = pstmt.executeQuery();
					
					while(rs.next()){
						rebDto = new REBDto();
						rebDto.setReb_no(rs.getInt("reb_no"));
						rebDto.setReb_subject(rs.getString("reb_subject"));
						rebDto.setM_id(rs.getString("m_id"));
						rebDto.setReb_content(rs.getString("reb_content"));
						rebDto.setReb_secret(rs.getString("reb_secret"));
						rebDto.setReb_category(rs.getString("reb_category"));
						rebDto.setReb_date(rs.getDate("reb_date"));
						rebDto.setReb_readcount(rs.getInt("reb_readcount"));
						rebDto.setG_code(rs.getString("g_code"));
						System.out.println(rebDto.getG_code());
						list.add(rebDto);
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
			public REBDto getDetail(int num) throws Exception{
				
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement("select * from reb_board where reb_no = ?");
					pstmt.setInt(1, num);
					
					rs= pstmt.executeQuery();
				
					if(rs.next()){
						rebDto= new REBDto();
						rebDto.setReb_no(rs.getInt("reb_no"));				
						System.out.println(rebDto.getReb_no());
						rebDto.setReb_subject(rs.getString("reb_subject"));
						rebDto.setM_id(rs.getString("m_id"));
						System.out.println("m_id : " + rebDto.getM_id());
						rebDto.setReb_content(rs.getString("reb_content"));
						rebDto.setReb_secret(rs.getString("reb_secret"));
						rebDto.setReb_category(rs.getString("reb_category"));
						rebDto.setReb_date(rs.getDate("reb_date"));
						rebDto.setReb_readcount(rs.getInt("reb_readcount"));
					}
					
				}catch(Exception ex){
					System.out.println("getDetail 에러 : " + ex);
				}finally{
					if(rs!=null)try{rs.close();}catch(SQLException ex){}
					if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
					if(con!=null) try{con.close();}catch(SQLException ex){}
				}
				return rebDto;
			}
			
		//글의 개수 구하기
			public int getListCount() {
				int x= 0;
				
				try{
					con=ds.getConnection();
					System.out.println("getConnection");
					pstmt=con.prepareStatement("select count(*) from reb_board");
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
				
				String sql="update reb_board set reb_readcount = reb_readcount+1 where reb_no = "+num;
				
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
		public boolean boardInsert(REBDto qna){
			
			int num = 0;
			String sql = "";
			
			int result = 0;
			boolean re = false;
			try{
				con = ds.getConnection();
				
				System.out.println(con + "<-- con boardInsert() QnaDAO.java");
				pstmtSelect=con.prepareStatement("select max(reb_no) from reb_board");
				rs = pstmtSelect.executeQuery();
				
				if(rs.next()) {
					num =rs.getInt(1)+1;
				} else {
					num=1;
				}
				System.out.println(num + " : num");
				pstmtSelect.close();
				
				if(qna.getReb_secret() == null) {
					qna.setReb_secret("n");
				}
				sql="insert into reb_board (reb_no,reb_subject,m_id,reb_content,reb_secret,reb_category,reb_date,g_code,reb_ref) values (?,?,?,?,?,?,sysdate(),?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num+1);
				pstmt.setString(2, qna.getReb_subject());
				pstmt.setString(3, qna.getM_id());
				pstmt.setString(4, qna.getReb_content());
				pstmt.setString(5, qna.getReb_secret());
				pstmt.setString(6, qna.getReb_category());
				pstmt.setString(7, qna.getG_code());
				pstmt.setInt(8, qna.getReb_ref());
				
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
				
				String reb_sql="select * from reb_board where reb_no=?";
				
				try{
					con = ds.getConnection();
					pstmt=con.prepareStatement(reb_sql);
					pstmt.setInt(1, num);
					rs=pstmt.executeQuery();
					rs.next();
					
					/*if(pass.equals(rs.getString("reb_PASS"))){
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
	






















