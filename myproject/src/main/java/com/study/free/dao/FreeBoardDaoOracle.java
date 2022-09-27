package com.study.free.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.exception.DaoException;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class FreeBoardDaoOracle implements IFreeBoardDao {
	
		@Override
		public int getTotalRowCount(FreeBoardSearchVO searchVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT count(*)                  ");
			sb.append(" FROM free_board                  ");
			sb.append(" WHERE bo_del_yn = 'N'             ");
			
				//null도 아니고, ''도 아니고
				if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
					switch(searchVO.getSearchType()) {
					//검색어가 있을 때만
					case "T":
						sb.append(" AND bo_title LIKE '%' || ? || '%' ");
						break;
				
					case "W":
						sb.append(" AND bo_writer LIKE '%' || ? || '%' ");
						break;
					case "C":
						sb.append(" AND bo_content LIKE '%' || ? || '%' ");
					}
				}
				if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
					sb.append(" AND bo_category =   ?   ");
				}//분류가 있을 때
				
				
			
			
			pstmt=conn.prepareStatement(sb.toString());
			int index = 1;
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				pstmt.setString(index++, searchVO.getSearchWord());
			}//검색어가 있을 때
			if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				pstmt.setString(index++, searchVO.getSearchCategory());
			}//분류가 있을 때
			
			rs=pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count=rs.getInt(1);
			}
			return count;
		}catch (SQLException e){
			throw new DaoException("getTotalRowCount" + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
		}

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			//쿼리문 만들기 StringBuffer
			StringBuffer sb = new StringBuffer();
			
			
			sb.append(" SELECT * FROM                   (                          ");
			sb.append(" SELECT rowNum as rnum, a.*  FROM  (                        ");
			sb.append(" SELECT                                                     ");
			sb.append("       bo_no         , bo_title, bo_category                ");
			sb.append("     , bo_writer     , bo_pass , bo_content                 ");
			sb.append("     , bo_hit                                               ");
			sb.append("     , TO_CHAR(bo_reg_date, 'YYYY-MM-dd') as bo_reg_date    ");
			sb.append("     , TO_CHAR(bo_mod_date, 'YYYY-MM-dd') as bo_mod_date    ");
			sb.append("     , bo_del_yn                                            ");
			
			sb.append("     , b.comm_nm as bo_category_nm                          ");
			
			sb.append(" FROM  free_board a, comm_code b                            ");
			sb.append(" WHERE a.bo_category = b.comm_cd                            ");
			sb.append(" AND bo_del_yn = 'N'                                        ");
			
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				switch(searchVO.getSearchType()) {
				//검색어가 있을 때만
				case "T":
					sb.append(" AND bo_title LIKE '%' || ? || '%' ");
					break;
			
				case "W":
					sb.append(" AND bo_writer LIKE '%' || ? || '%' ");
					break;
				case "C":
					sb.append(" AND bo_content LIKE '%' || ? || '%' ");
				}
			}
			if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				sb.append(" AND bo_category =   ?   ");
			}//분류가 있을 때	
			
			
			
			sb.append(" ORDER BY bo_no DESC                                        ");
			sb.append("     ) a                                                    ");
			sb.append("  ) b                                                       ");
			sb.append(" WHERE rnum BETWEEN ? AND ?                                 ");
			//join
			
			//pstmt만들기
			pstmt = conn.prepareStatement(sb.toString());
			
			//?세팅 (있는 경우만)
			int i = 1;
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				pstmt.setString(i++, searchVO.getSearchWord());
			}//검색어가 있을 때
			if(StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				pstmt.setString(i++, searchVO.getSearchCategory());
			}//분류가 있을 때
			
			
			
			pstmt.setInt(i++, searchVO.getFirstRow());
			pstmt.setInt(i++, searchVO.getLastRow());
			
			
			//rs = pstmt.쿼리실행
			rs = pstmt.executeQuery();
					
			//rs가지고 객체 만들어서 req.setAttr
			List<FreeBoardVO> freeBoardList = new ArrayList<>();
			while(rs.next()){
				FreeBoardVO freeBoard = new FreeBoardVO();
				freeBoard.setBoNo(rs.getInt("bo_no"));
				freeBoard.setBoTitle(rs.getString("bo_title"));
				freeBoard.setBoCategory(rs.getString("bo_category"));
				freeBoard.setBoWriter(rs.getString("bo_writer"));
				freeBoard.setBoContent(rs.getString("bo_content"));
				freeBoard.setBoPass(rs.getString("bo_pass"));
				freeBoard.setBoHit(rs.getInt("bo_hit"));
				freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
				freeBoard.setBoModDate(rs.getString("bo_mod_date"));
				freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
				freeBoard.setBoCategoryNm(rs.getString("bo_category_nm"));
				freeBoardList.add(freeBoard);
			}
			return freeBoardList;
			
		}catch (SQLException e){
			//1번. 아무것도 안함
			//2번. e.printStackTrace(); -> SQLException이 콘솔에 찍히긴 하지만 코드는 정상 실행(?) 원래는 파란화면 나오는게 정상
			//3번. 예외 전환 = 파란 화면 띄우면 됨
			//SQLException을 DaoException바꿔서 throw하기
			throw new DaoException("getBoardList" + e.getMessage());
			
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
		
	}

	
	
	
	@Override
	public FreeBoardVO getBoard(int boNo) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");

			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT                                                     ");
			sb.append("       bo_no         , bo_title, bo_category                ");
			sb.append("     , bo_writer     , bo_pass , bo_content                 ");
			sb.append("     , bo_hit                                               ");
			sb.append("     , TO_CHAR(bo_reg_date, 'YYYY-MM-dd') as bo_reg_date    ");
			sb.append("     , TO_CHAR(bo_mod_date, 'YYYY-MM-dd') as bo_mod_date    ");
			sb.append("     , bo_del_yn                                            ");
			
			sb.append("     , b.comm_nm as bo_category_nm");
			
			sb.append(" FROM free_board a, comm_code b                             ");
			sb.append(" WHERE bo_no=   ?                                           ");
			sb.append(" AND a.bo_category = b.comm_cd                              ");
			
			//pstmt만들기
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, boNo);
			rs = pstmt.executeQuery();
			
			//rs가 1줄임
			if(rs.next()){
				FreeBoardVO freeBoard = new FreeBoardVO();
				freeBoard.setBoCategoryNm(rs.getString("bo_category_nm"));
				freeBoard.setBoNo(rs.getInt("bo_no"));
				freeBoard.setBoTitle(rs.getString("bo_title"));
				freeBoard.setBoCategory(rs.getString("bo_category"));
				freeBoard.setBoWriter(rs.getString("bo_writer"));
				freeBoard.setBoPass(rs.getString("bo_pass"));
				freeBoard.setBoContent(rs.getString("bo_content"));
				freeBoard.setBoHit(rs.getInt("bo_hit"));
				freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
				freeBoard.setBoModDate(rs.getString("bo_mod_date"));
				freeBoard.setBoDelYn(rs.getString("bo_del_yn"));

				
				return freeBoard;
			}
			return null;
		}catch (SQLException e){
			throw new DaoException("getBoard : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	@Override
	public int increaseHit(int boNo) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" UPDATE free_board SET     ");
			sb.append(" bo_hit = bo_hit+1         ");
			sb.append(" WHERE bo_no = ?           ");

			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1,  boNo);
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		}catch (SQLException e){
			throw new DaoException("increaseHit : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}

	}

	@Override
	public int updateBoard(FreeBoardVO freeBoard) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" UPDATE free_board SET              ");
			sb.append(" 		 bo_title=?                  ");
			sb.append(" 		,bo_category=?               ");
			sb.append(" 		,bo_content=?                ");
			sb.append(" 		,bo_mod_date=sysdate         ");
			sb.append(" 		WHERE bo_no=?                ");
			
			pstmt=conn.prepareStatement(sb.toString());
			int count = 1;
			pstmt.setString(count++, freeBoard.getBoTitle());
			pstmt.setString(count++, freeBoard.getBoCategory());
			pstmt.setString(count++, freeBoard.getBoContent());
			pstmt.setInt(count++, freeBoard.getBoNo());
			
			int cnt = pstmt.executeUpdate();
			return cnt;		
		}catch (SQLException e){
			throw new DaoException("updateboard : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
		
	}

	@Override
	public int deleteBoard(FreeBoardVO freeBoard) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" UPDATE free_board SET               ");
			sb.append(" 		 bo_del_yn='Y'                ");
			sb.append(" 		WHERE bo_no=?                 ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, freeBoard.getBoNo());
			
			int cnt = pstmt.executeUpdate();
			return cnt;		
		}catch (SQLException e){
			throw new DaoException("deleteBoard : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	
	
	
	
	@Override
	public int insertBoard(FreeBoardVO freeBoard) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//연결
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			
			//쿼리문
			StringBuffer sb = new StringBuffer();
			sb.append(" INSERT INTO free_board (                         ");
			sb.append("       bo_no     , bo_title      , bo_category    ");
			sb.append("     , bo_writer , bo_pass       , bo_content     ");
			sb.append("     , bo_hit    , bo_reg_date   , bo_mod_date    ");
			sb.append("     , bo_del_yn                                  ");
			sb.append(" ) VALUES (                                       ");
			sb.append("   seq_free_board.nextval   ,?      ,?            ");
			sb.append("           ,?               ,?      ,?            ");
			sb.append("           ,0      ,sysdate         ,null         ");
			sb.append("           ,'N'                                   ");
			sb.append(" )                                                ");

			pstmt= conn.prepareStatement(sb.toString());
			
			//?세팅, 쿼리실행
			int i = 1;
			pstmt.setString(i++, freeBoard.getBoTitle());
			pstmt.setString(i++, freeBoard.getBoCategory());
			pstmt.setString(i++, freeBoard.getBoWriter());
			pstmt.setString(i++, freeBoard.getBoPass());
			pstmt.setString(i++, freeBoard.getBoContent());
			int cnt = pstmt.executeUpdate();
			return cnt;
			
		}catch (SQLException e){
			throw new DaoException("insert : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

}
