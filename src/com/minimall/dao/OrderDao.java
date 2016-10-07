package com.minimall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.minimall.dto.GoodsDto;
import com.minimall.dto.OrderDto;

public class OrderDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	PreparedStatement pstmt_select;
	GoodsDto goodsDto;
	ResultSet rs;
	ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();
	ArrayList<OrderDto> orderListOne = new ArrayList<OrderDto>();
	
	public OrderDao() {
		try {
			Context init = new InitialContext();
			System.out.println(init + " : init GoodsDao.java");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}	
	
	//구매화면 메서드
	public void orderInsert(OrderDto odto) throws SQLException {
		System.out.println("orderInsert OrderDao.java");
		
		conn = ds.getConnection();		
		int o_no = 0;
		
		pstmt_select = conn.prepareStatement("SELECT MAX(o_no) FROM orders");
		System.out.println(pstmt_select + " : pstmt_select orderInsert() OrderDao.java");
		rs = pstmt_select.executeQuery();
		
		int result = 0;
		if(rs.next()){
			result = rs.getInt(1) + 1;
		}
		
		rs.close();
		pstmt_select.close();
		
		o_no = result;
		System.out.println(o_no + " : o_no OrderInsert() OrderDao.java");
		
		String sql=null;
		sql="insert into orders values(?,?,sysdate(),?,?,?,'입금예정')";
		pstmt = conn.prepareStatement(sql);
			
		pstmt.setInt(1, o_no);
		pstmt.setString(2, odto.getM_id());
		pstmt.setString(3, odto.getG_code());
		pstmt.setInt(4, odto.getO_count());
		pstmt.setInt(5, odto.getO_total());		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	//주문리스트 조회 메서드
	public ArrayList<OrderDto> orderSelectAll(int page, int limit) throws SQLException{
		System.out.println("orderSelectAll OrderDao.java");
		String sql = null;
		conn = ds.getConnection();
		
		int startRow = (page-1) *10 +1;
		int endRow = startRow +limit -1;
		System.out.println(startRow + "<- startRow");
		System.out.println(endRow + "<- endRow");
		
		sql = "select o.o_no, m.m_id, g.g_id, date_format(o.o_date, '%y-%m-%d') as o_date, g.g_code, o.o_count, o.o_total, o.o_state, g.g_name, g.g_price, m.m_name, m.m_addr "
				+ "from orders o inner join goods g on o.g_code = g.g_code inner join member m on m.m_id = o.m_id order by o.o_no asc limit ?,?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow-1);
		pstmt.setInt(2, endRow);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			OrderDto oDto = new OrderDto();
			oDto.setO_no(rs.getInt("o_no"));
			oDto.setM_id(rs.getString("m_id"));
			oDto.setG_id(rs.getString("g_id"));
			oDto.setO_date(rs.getString("o_date"));
			oDto.setG_code(rs.getString("g_code"));
			oDto.setO_count(rs.getInt("o_count"));
			oDto.setO_total(rs.getInt("o_total"));
			oDto.setO_state(rs.getString("o_state"));
			oDto.setG_name(rs.getString("g_name"));
			oDto.setG_price(rs.getInt("g_price"));
			oDto.setM_name(rs.getString("m_name"));
			oDto.setM_addr(rs.getString("m_addr"));
			
			orderList.add(oDto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return orderList;
	}
	
	//구매자 조회 리스트 메서드
	public ArrayList<OrderDto> orderListOne(String mId, int page, int limit) throws SQLException {
		System.out.println("orderListOne OrderDao.java");
		String sql = null;
		conn = ds.getConnection();
		
		int startRow = (page-1) *10 +1;
		int endRow = startRow +limit -1;
		System.out.println(startRow + "<- startRow");
		System.out.println(endRow + "<- endRow");
		
		sql = "select o.o_no, m.m_id, g.g_id, date_format(o.o_date, '%y-%m-%d') as o_date, g.g_code, "
				+ "o.o_count, o.o_total, o.o_state, g.g_name, g.g_price, m.m_name, m.m_addr "
				+ "from orders o inner join goods g on o.g_code = g.g_code inner join "
				+ "member m on m.m_id = o.m_id where o.m_id = ? order by o.o_no asc limit ?, ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mId);
		pstmt.setInt(2, startRow-1);
		pstmt.setInt(3, endRow);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			OrderDto oDto = new OrderDto();
			oDto.setO_no(rs.getInt("o_no"));
			oDto.setM_id(rs.getString("m_id"));
			oDto.setG_id(rs.getString("g_id"));
			oDto.setO_date(rs.getString("o_date"));
			oDto.setG_code(rs.getString("g_code"));
			oDto.setO_count(rs.getInt("o_count"));
			oDto.setO_total(rs.getInt("o_total"));
			oDto.setO_state(rs.getString("o_state"));
			oDto.setG_name(rs.getString("g_name"));
			oDto.setG_price(rs.getInt("g_price"));
			oDto.setM_name(rs.getString("m_name"));
			oDto.setM_addr(rs.getString("m_addr"));
			
			orderListOne.add(oDto);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return orderListOne;
	}
	
	//주문취소 메서드
	public void orderDelete(int oNo) throws SQLException{
		System.out.println("OrderDelete OrderDao.java");
		String sql = null;
		conn = ds.getConnection();
		sql = "delete from orders where o_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, oNo);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();		
	}
	
	//입금확인 메서드
	public void orderMoneyCheck(String[] moneyChkArray) throws SQLException {
		System.out.println("OrderMoneyCheck OrderDao.java");
		int[] orderMoneyCheck = new int[moneyChkArray.length];	
		// String 타입 배열 moneyChkArry 의 길이과 같은 길이의 int 타입 배열 orderMoneyChk를 만들어 준다
		
		String sql = "update orders set o_state = '입금완료' where o_no = ?";
		conn = ds.getConnection();
		
		for(int i = 0; i < moneyChkArray.length; i ++) {
			orderMoneyCheck[i]	= Integer.parseInt(moneyChkArray[i]);
			// moneyChkArray의 길이만큼 반복문을 돌려 출력되는 결과를 int로 형변환 후 쿼리문에 삽입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderMoneyCheck[i]);
			pstmt.executeUpdate();
		}
		
		pstmt.close();
		conn.close();
		
	}
	
	//전체 주문리스트 글 개수 구하기
	public int getListCount() throws SQLException {
		System.out.println("getListCount() OrderDao.java");
		int count = 0;
		
		conn = ds.getConnection();
		
		//orders테이블의 전체 데이터 갯수 가져오는 select 쿼리문 입니다.
		pstmt = conn.prepareStatement("SELECT count(*) FROM orders");
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		System.out.println(count + " : 글 갯수");
		
		return count;
	}
	
	//특정 회원 주문리스트 글 개수 구하기
	public int getListCountOne(String mId) throws SQLException {
		System.out.println("getListCountOne() OrderDao.java");
		int count = 0;
		
		conn = ds.getConnection();
		
		//orders테이블에서 특정 회원의 전체 주문 리스트를 가져오는 select 쿼리문 입니다.
		pstmt = conn.prepareStatement("SELECT count(*) FROM orders WHERE m_id = ?");
		pstmt.setString(1, mId);
		rs = pstmt.executeQuery();		
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		System.out.println(count + " : 글 갯수");
		
		return count;
	}
}
