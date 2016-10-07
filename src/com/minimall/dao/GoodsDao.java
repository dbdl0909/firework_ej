package com.minimall.dao;

import java.io.IOException;
import java.sql.Blob;
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

public class GoodsDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	PreparedStatement pstmt_select;
	ResultSet rs;
	GoodsDto goodsDto;
	ArrayList<GoodsDto> goodsList = new ArrayList<GoodsDto>();
	Blob blob = null;
	
	
	public GoodsDao() {
		try {
			Context init = new InitialContext();
			System.out.println(init + " : init GoodsDao.java");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	
	//상품 등록
	public void goodsInsert(GoodsDto goodsDto) throws SQLException {
		System.out.println("01 goodsInsert() GoodsDao.java");
		
		conn = ds.getConnection();
		
		String g_code = "gcode_1";
		String temp = "gcode_";
		
		//g_code 중 마지막 숫자(가장 큰 숫자)를 가져오기 위한 select 쿼리문 입니다.
		//pstmt_select = conn.prepareStatement("SELECT MAX(TO_NUMBER(SUBSTR(g_code,7))) FROM goods");
		pstmt_select = conn.prepareStatement("SELECT MAX(CONVERT(SUBSTRING(g_code,7), UNSIGNED)) FROM goods");
		System.out.println(pstmt_select + " : pstmt_select goodsInsert() GoodsDao.java");
		rs = pstmt_select.executeQuery();
		
		int result = 0;
		if(rs.next()){
			result = rs.getInt(1);
			System.out.println(result + " : result goodsInsert() GoodsDao.java");
			result = result + 1;
		}
		rs.close();
		pstmt_select.close();
		
		//result에 담겨있는 숫자와 temp에 담겨있는 "gcode_" 문자열을 합침.
		g_code = temp + result;
		System.out.println(g_code + " : g_code goodsInsert() GoodsDao.java");
		
		//goods테이블에 상품을 등록하는 insert 쿼리문 입니다.
		String sql = "INSERT INTO goods";
		sql += "(g_code,g_name,g_id,g_cate,g_sangse,g_price,g_date,g_agree,g_image)";
		sql += " VALUES(?,?,?,?,?,?,sysdate(),'N',?)";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, g_code);
		pstmt.setString(2, goodsDto.getG_name());
		pstmt.setString(3, goodsDto.getG_id());
		pstmt.setString(4, goodsDto.getG_cate());
		pstmt.setString(5, goodsDto.getG_sangse());
		pstmt.setInt(6, goodsDto.getG_price());
		pstmt.setString(7, goodsDto.getG_image());
		
		int getResult = pstmt.executeUpdate();
		if(getResult != 0) {
			System.out.println("INSERT 성공!");
		} else {
			System.out.println("INSERT 실패!");
		}
		pstmt.close();
		conn.close();
	}

	//상품 전체 SELECT
	public ArrayList<GoodsDto> goodsSelectAll(int page, int limit) throws SQLException, IOException {
		System.out.println("02_0 goodsSelectAll() GoodsDao.java");
		
		conn = ds.getConnection();
		
		int startRow = (page-1) *10 +1;
		int endRow = startRow +limit -1;
		
		//goods테이블의 전체 데이터를 가져오는 select 쿼리문 입니다.
		String sql = "SELECT g_code, g_name, g_id, g_cate, g_sangse, g_price, g_date, g_agree, g_image FROM goods limit ?, ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow-1);
		pstmt.setInt(2, endRow);
		System.out.println(pstmt + " : pstmt goodsSelectAll() GoodsDao.java");
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			goodsDto = new GoodsDto();
			goodsDto.setG_code(rs.getString("g_code"));
			System.out.println(goodsDto.getG_code());
			goodsDto.setG_name(rs.getString("g_name"));
			goodsDto.setG_id(rs.getString("g_id"));
			goodsDto.setG_cate(rs.getString("g_cate"));
			goodsDto.setG_sangse(rs.getString("g_sangse"));
			goodsDto.setG_price(rs.getInt("g_price"));
			goodsDto.setG_date(rs.getString("g_date"));
			goodsDto.setG_agree(rs.getString("g_agree").charAt(0));
			
			goodsList.add(goodsDto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return goodsList;
	}
	
	//index.jsp 에 보일 최신 상품 리스트를 가져오는 메서드 입니다.
	public ArrayList<GoodsDto> goodsSelectOrderByDate() throws SQLException {
		System.out.println("02_1 getListCount() GoodsDao.java");
		
		conn = ds.getConnection();
		
		String sql = "SELECT g.g_code as g_code, g.g_name as g_name, g.g_cate as g_cate,"+
		"g.g_sangse as g_sangse, g.g_price as g_price, g.g_image as g_image, g.g_date as g_date FROM"+
		" (SELECT g_code, g_name, g_cate, g_sangse, g_price, g_image, g_date"+
		" FROM (SELECT * FROM goods WHERE g_date >= DATE_ADD(NOW(), INTERVAL -1 MONTH) ORDER BY g_date DESC) as g2"+
		" WHERE g_agree LIKE 'Y') as g limit 0, 12";
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + " : pstmt goodsSelectForCustom() GoodsDao.java");
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			goodsDto = new GoodsDto();
			goodsDto.setG_code(rs.getString("g_code"));
			goodsDto.setG_name(rs.getString("g_name"));
			goodsDto.setG_cate(rs.getString("g_cate"));
			goodsDto.setG_sangse(rs.getString("g_sangse"));
			goodsDto.setG_price(rs.getInt("g_price"));
			goodsDto.setG_image(rs.getString("g_image"));
			goodsDto.setG_date(rs.getString("g_date"));
			
			goodsList.add(goodsDto);
		}
		rs.close();
		pstmt.close();
		conn.close();
			
		return goodsList;
	}
	
	//글 갯수 구하기
	public int getListCount() throws SQLException {
		System.out.println("03_0 getListCount() GoodsDao.java");
		int count = 0;
		
		conn = ds.getConnection();
		
		//goods테이블의 전체 데이터 갯수 가져오는 select 쿼리문 입니다.
		pstmt = conn.prepareStatement("SELECT count(*) FROM goods");
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
	public int getListCountOne(String sellerId) throws SQLException {
		System.out.println("03_1 getListCountOne() GoodsDao.java");
		int count = 0;
		
		conn = ds.getConnection();
		
		//goods 테이블에서 특정 회원의 전체 주문 리스트를 가져오는 select 쿼리문 입니다.
		pstmt = conn.prepareStatement("SELECT count(*) FROM goods WHERE g_id = ?");
		pstmt.setString(1, sellerId);
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
	
	//구매자를 위한 select! 상품 중 승인여부가 Y인 것만 가져오기
	public ArrayList<GoodsDto> goodsSelectForCustom(int page, int limit, String row) throws SQLException {
		System.out.println("03_2 goodsSelectForCustom() GoodsDao.java");
		
		conn = ds.getConnection();
		
		String sql = null;
		int startRow = (page-1) *10 +1;
		int endRow = startRow +limit -1;
		
		System.out.println(startRow + " : startRow goodsSelectForCustom GoosDao.java");
		System.out.println(endRow + " : endRow goodsSelectForCustom GoosDao.java");
		
		//goods테이블의 전체 데이터 중 승인여부가 'Y' 인 것만 가져오는 select 쿼리문 입니다.
		/*String sql = "SELECT g_code, g_name, g_id, g_cate, g_sangse, g_price, to_char(g_date, 'yyyy-mm-dd') as g_date, g_image FROM goods";
		sql += " WHERE g_agree LIKE 'Y'";*/
		
		/*String sql = "select * from";				
		sql += " (select rownum rnum, g_code, g_name, g_id, g_cate, g_sangse, g_price, TO_CHAR(g_date, 'yyyymmdd') as g_date, g_image from";
		sql += "  (select * from goods order by TO_NUMBER(SUBSTR(g_code,7)) desc)";
		sql	+= " where g_agree LIKE 'Y')";
		sql += " where rnum>=? and rnum<=?";*/
		
		if(row.equals("up")){
			
			sql = "SELECT g.g_code as g_code, g.g_name as g_name, g.g_id as g_id, g.g_cate as g_cate, g.g_sangse as g_sangse, g.g_price as g_price, g.g_date as g_date, g.g_image as g_image FROM";
			sql += " (SELECT g_code, g_name, g_id, g_cate, g_sangse, g_price, date_format(g_date, '%y%m%d') as g_date, g_image";
			sql += " FROM (SELECT * FROM goods ORDER BY g_price DESC)as g2 WHERE g_agree LIKE 'Y') as g LIMIT ?,?";
			
		}else if(row.equals("down")){
			
			sql = "SELECT g.g_code as g_code, g.g_name as g_name, g.g_id as g_id, g.g_cate as g_cate, g.g_sangse as g_sangse, g.g_price as g_price, g.g_date as g_date, g.g_image as g_image FROM";
			sql += " (SELECT g_code, g_name, g_id, g_cate, g_sangse, g_price, date_format(g_date, '%y%m%d') as g_date, g_image";
			sql += " FROM (SELECT * FROM goods ORDER BY g_price ASC)as g2 WHERE g_agree LIKE 'Y') as g LIMIT ?,?";
		
		}else if(row.equals("new")){
			
			sql = "SELECT g.g_code as g_code, g.g_name as g_name, g.g_id as g_id, g.g_cate as g_cate, g.g_sangse as g_sangse, g.g_price as g_price, g.g_date as g_date, g.g_image as g_image FROM";
			sql += " (SELECT g_code, g_name, g_id, g_cate, g_sangse, g_price, date_format(g_date, '%y%m%d') as g_date, g_image";
			sql += " FROM (SELECT * FROM goods ORDER BY g_date DESC)as g2 WHERE g_agree LIKE 'Y') as g LIMIT ?,?";
			
		}
		
		pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		pstmt.setInt(1, startRow-1);
		pstmt.setInt(2, endRow);
		System.out.println(pstmt + " : pstmt goodsSelectForCustom() GoodsDao.java");
		rs = pstmt.executeQuery();
		
		rs.last();
		int rowCount = rs.getRow();
		System.out.println(rowCount);
		rs.beforeFirst();
		if(rowCount == 0) {
			return null;
		}
		
		while(rs.next()) {
			goodsDto = new GoodsDto();
			goodsDto.setG_code(rs.getString("g_code"));
			goodsDto.setG_name(rs.getString("g_name"));
			goodsDto.setG_id(rs.getString("g_id"));
			goodsDto.setG_cate(rs.getString("g_cate"));
			goodsDto.setG_sangse(rs.getString("g_sangse"));
			goodsDto.setG_price(rs.getInt("g_price"));
			goodsDto.setG_date(rs.getString("g_date"));
			goodsDto.setG_image(rs.getString("g_image"));
			
			goodsList.add(goodsDto);
		}
		rs.close();
		pstmt.close();
		conn.close();
			
		return goodsList;
	}

	//관리자가 상품 승인여부를 체크하면 값을 Y로 바꿔주는 메서드 입니다.
	public void goodsCheckAgree(String[] gCodeArray) throws SQLException {
		System.out.println("04 goodsCheckAgree() GoodsDao.java");
		System.out.println(gCodeArray + " : gCodeArray goodsCheckAgree() GoodsDao.java");
		
		conn = ds.getConnection();
		
		int result = 0;
		
		//관리자가 체크한 승인여부 컬럼의 값 N을 Y로 바꿔주는 UPDATE 쿼리문 입니다.
		String sql = "UPDATE goods SET";
		sql += " g_agree=? WHERE g_code=?";
		for(int i=0; i<gCodeArray.length; i++) {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Y");
			pstmt.setString(2, gCodeArray[i]);
			result += pstmt.executeUpdate();
		}
		
		System.out.println(result + " : 번 UPDATE");
		
		pstmt.close();
		conn.close();
	}
	
	//구매자가 선택한 한 개의 상품을 가져오는 메서드 입니다.
	public GoodsDto goodsSelectByGcode(String gCode) throws SQLException {
		System.out.println("05 goodsSelectByGcode() GoodsDao.java");
		System.out.println(gCode + " : gCode goodsSelectByGcode() GoodsDao.java");
		
		conn = ds.getConnection();
		
		//g_code이 gCode 값에 해당하는 한 개 상품 데이터를 가져오는 select 쿼리문 입니다.
		String sql = "SELECT g_code, g_name, g_cate, g_sangse, g_id, g_price, g_image FROM goods";
		sql += " WHERE g_code=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, gCode);
		System.out.println(pstmt + " : pstmt goodsSelectAll() GoodsDao.java");
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			goodsDto = new GoodsDto();
			goodsDto.setG_code(rs.getString("g_code"));
			System.out.println(goodsDto.getG_code());
			goodsDto.setG_name(rs.getString("g_name"));
			goodsDto.setG_cate(rs.getString("g_cate"));
			goodsDto.setG_sangse(rs.getString("g_sangse"));
			goodsDto.setG_id(rs.getString("g_id"));
			goodsDto.setG_price(rs.getInt("g_price"));
			goodsDto.setG_image(rs.getString("g_image"));
			System.out.println(goodsDto.getG_image());
		}
		rs.close();
		pstmt.close();
		conn.close();
			
		return goodsDto;
	}

	//판매자가 등록한 상품을 가져오는 메서드 입니다.
	public ArrayList<GoodsDto> goodsSelectForSeller(String sellerId, int page, int limit) throws SQLException {
		System.out.println("06 goodsSelectForSeller() GoodsDao.java");
		System.out.println(sellerId + " : sellerId goodsSelectForSeller() GoodsDao.java");
		
		conn = ds.getConnection();
		
		int startRow = (page-1) *10 +1;
		int endRow = startRow +limit -1;
		
		//goods테이블의 데이터 중 판매자 아이디에 해당하는 것만 가져오는 select 쿼리문 입니다.
		String sql = "SELECT g_code, g_name, g_cate, g_sangse, g_price, g_date FROM goods";
		sql += " WHERE g_id=? limit ?, ?";	
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, sellerId);
		pstmt.setInt(2, startRow-1);
		pstmt.setInt(3, endRow);
		System.out.println(pstmt + " : pstmt goodsSelectForSeller() GoodsDao.java");
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			goodsDto = new GoodsDto();
			goodsDto.setG_code(rs.getString("g_code"));
			System.out.println(goodsDto.getG_code());
			goodsDto.setG_name(rs.getString("g_name"));
			goodsDto.setG_cate(rs.getString("g_cate"));
			goodsDto.setG_sangse(rs.getString("g_sangse"));
			goodsDto.setG_price(rs.getInt("g_price"));
			goodsDto.setG_date(rs.getString("g_date"));
			
			goodsList.add(goodsDto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return goodsList;
		
	}

	//판매자가 선택한 gcode에 해당하는 데이터를 업데이트하는 메서드 입니다.
	public void goodsUpdateByGcode(GoodsDto goodsDto) throws SQLException {
		System.out.println("07 goodsUpdateByGcode() GoodsDao.java");
		System.out.println(goodsDto + " : gCode goodsUpdateByGcode() GoodsDao.java");
		
		conn = ds.getConnection();
		
		//gcode에 해당하는 데이터를 입력한 값에 따라 수정하는 update 쿼리문 입니다.
		String sql = "UPDATE goods SET";
		sql += " g_name=?, g_cate=?, g_sangse=?, g_price=?, g_image=?";
		sql += " WHERE g_code=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, goodsDto.getG_name());
		pstmt.setString(2, goodsDto.getG_cate());
		pstmt.setString(3, goodsDto.getG_sangse());
		pstmt.setInt(4, goodsDto.getG_price());
		pstmt.setString(5, goodsDto.getG_image());
		pstmt.setString(6, goodsDto.getG_code());
		System.out.println(pstmt + " : pstmt goodsUpdateByGcode() GoodsDao.java");
		
		int result = pstmt.executeUpdate();
		if(result != 0) {
			System.out.println("UPDATE 성공!");
		} else {
			System.out.println("UPDATE 실패!");
		}
		pstmt.close();
		conn.close();
	}

	//판매자가 선택한 gCode에 해당하는 상품을 삭제하는 메서드입니다.
	public void goodsDeleteByGcode(String gCode) throws SQLException {
		System.out.println("08 goodsDeleteByGcode() GoodsDao.java");
		System.out.println(gCode + " : gCode goodsDeleteByGcode() GoodsDao.java");
		
		conn = ds.getConnection();
		
		//gcode에 해당하는 데이터를 삭제하는 delete 쿼리문 입니다.
		String sql = "DELETE FROM goods WHERE g_code=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, gCode);
		System.out.println(pstmt + " : pstmt goodsDeleteByGcode() GoodsDao.java");
		
		int result = pstmt.executeUpdate();
		if(result != 0) {
			System.out.println("DELETE 성공!");
		} else {
			System.out.println("DELETE 실패!");
		}
		pstmt.close();
		conn.close();
	}
	
	//판매자가 선택한 gCode에 해당하는 상품의 이미지 이름을 가져오는 메서드입니다.
	public GoodsDto goodsSelectForDeleteByGCode(String gCode) throws SQLException {
		System.out.println("09 goodsSelectForDeleteByGCode() GoodsDao.java");
		System.out.println(gCode + " : gCode goodsSelectForDeleteByGCode() GoodsDao.java");
		
		conn = ds.getConnection();
		
		//gcode에 해당하는 이미지 이름을 가져오는 select 쿼리문 입니다.
		String sql = "SELECT g_code, g_image FROM goods WHERE g_code=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, gCode);
		System.out.println(pstmt + " : pstmt goodsSelectForDeleteByGCode() GoodsDao.java");
		
		rs = pstmt.executeQuery();
		if(rs.next()) {
			goodsDto = new GoodsDto();
			rs.getString("g_code");
			rs.getString("g_image");
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return goodsDto;
	}
}
