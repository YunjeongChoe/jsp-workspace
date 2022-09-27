package com.study.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.exception.DaoException;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao{

	@Override
	public int getTotalRowCount(MemberSearchVO searchVO) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT count(*)                  ");
			sb.append(" FROM member                      ");
			sb.append(" WHERE mem_del_yn = 'N'            ");
			
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				switch(searchVO.getSearchType()) {
				//검색어가 있을 때만
				case "NM":
					sb.append(" AND mem_name LIKE '%' || ? || '%' ");
					break;
				case "ID":
					sb.append(" AND mem_id LIKE '%' || ? || '%' ");
					break;
				case "HP":
					sb.append(" AND mem_hp LIKE '%' || ? || '%' ");
				}
			}
			if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
				sb.append(" AND mem_job =   ?   ");
			}
			if(StringUtils.isNotBlank(searchVO.getSearchHobby())) {
				sb.append(" AND mem_hobby =   ?   ");
			}
			
			
			pstmt=conn.prepareStatement(sb.toString());
			int index = 1;
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				pstmt.setString(index++, searchVO.getSearchWord());
			}
			if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
				pstmt.setString(index++, searchVO.getSearchJob());
			}//검색어가 있을 때
			if(StringUtils.isNotBlank(searchVO.getSearchHobby())) {
				pstmt.setString(index++, searchVO.getSearchHobby());
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
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			//쿼리문 만들기 StringBuffer
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT * FROM                   (                   ");
			sb.append(" SELECT rowNum as rnum, a.*  FROM  (                 ");
			sb.append(" SELECT                                              ");
			sb.append("       mem_id      , mem_pass       , mem_name       ");
			sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir     ");
			sb.append("     , mem_zip     , mem_add1       , mem_add2       ");
			sb.append("     , mem_hp      , mem_mail       , mem_job        ");
			sb.append("     , mem_hobby   , mem_mileage    , mem_del_yn     ");
			
			sb.append("     , b.comm_nm as mem_job_nm                       ");
			sb.append("     , b.comm_nm as mem_hobby_nm                       ");

			
			
			sb.append(" FROM member a,  comm_code    b                      ");
			sb.append(" WHERE a.mem_job = b.comm_cd                         ");
			sb.append(" AND mem_del_yn = 'N'            ");
			
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				switch(searchVO.getSearchType()) {
				//검색어가 있을 때만
				case "NM":
					sb.append(" AND mem_name LIKE '%' || ? || '%' ");
					break;
				case "ID":
					sb.append(" AND mem_id LIKE '%' || ? || '%' ");
					break;
				case "HP":
					sb.append(" AND mem_hp LIKE '%' || ? || '%' ");
				}
			}
			if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
				sb.append(" AND mem_job =   ?   ");
			}
			if(StringUtils.isNotBlank(searchVO.getSearchHobby())) {
				sb.append(" AND mem_hobby =   ?   ");
			}
			
			sb.append(" ORDER BY mem_id DESC                       ");
			sb.append("     ) a                                    ");
			sb.append("  ) b                                       ");
			sb.append(" WHERE rnum BETWEEN ? AND ?                 ");
			
			
			
			
			pstmt=conn.prepareStatement(sb.toString());
			int i = 1;
			if(StringUtils.isNotBlank(searchVO.getSearchWord())) {
				pstmt.setString(i++, searchVO.getSearchWord());
			}
			if(StringUtils.isNotBlank(searchVO.getSearchJob())) {
				pstmt.setString(i++, searchVO.getSearchJob());
			}//검색어가 있을 때
			if(StringUtils.isNotBlank(searchVO.getSearchHobby())) {
				pstmt.setString(i++, searchVO.getSearchHobby());
			}//분류가 있을 때
			
			pstmt.setInt(i++, searchVO.getFirstRow());
			pstmt.setInt(i++, searchVO.getLastRow());
				
			//rs = psimim.쿼리실행
			rs = pstmt.executeQuery();
					
			//rs가지고 객체 만들어서 req.setAttr
			List<MemberVO> memberList = new ArrayList<>();
			while(rs.next()){
				MemberVO member = new MemberVO();
				member.setMemId(rs.getString("mem_id"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemHobby(rs.getString("mem_hobby"));
				member.setMemMileage(rs.getInt("mem_mileage"));
				member.setMemDelYn(rs.getString("mem_del_yn"));
				member.setMemJobNm(rs.getString("mem_job_nm"));
				
				memberList.add(member);
			}
			return memberList;
		}catch (SQLException e){
			throw new DaoException("getMemberList" + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	@Override
	public MemberVO getMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append(" SELECT                                              ");
			sb.append("       mem_id      , mem_pass       , mem_name       ");
			sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir     ");
			sb.append("     , mem_zip     , mem_add1       , mem_add2       ");
			sb.append("     , mem_hp      , mem_mail       , mem_job        ");
			sb.append("     , mem_hobby   , mem_mileage    , mem_del_yn     ");
			sb.append("     , b.comm_nm as mem_job_nm                       ");
			sb.append("     , c.comm_nm as mem_hobby_nm                     ");
			sb.append(" FROM  member a, comm_code b, comm_code c            ");
			sb.append(" WHERE     mem_id= ?                                 ");
			sb.append(" AND a.mem_job = b.comm_cd                           ");
			sb.append(" AND a.mem_hobby = c.comm_cd                         ");
				
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				MemberVO member = new MemberVO();
				member.setMemId(rs.getString("mem_id"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemHobby(rs.getString("mem_hobby"));
				member.setMemMileage(rs.getInt("mem_mileage"));
				member.setMemDelYn(rs.getString("mem_del_yn"));
				member.setMemJobNm(rs.getString("mem_job_nm"));
				member.setMemHobbyNm(rs.getString("mem_hobby_nm"));

				
				return member;
			}
			return null;
		}catch (SQLException e){
			throw new DaoException("getMember : "  + e.getMessage ());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	
	
	@Override
	public int updateMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" UPDATE member SET         ");
			sb.append("       mem_pass=?          ");
			sb.append("     , mem_name=?          ");
			sb.append("     , mem_zip=?           ");
			sb.append("     , mem_add1=?          ");
			sb.append("     , mem_add2=?          ");
			sb.append("     , mem_bir=?           ");
			sb.append("     , mem_mail=?          ");
			sb.append("     , mem_hp=?            ");
			sb.append("     , mem_job=?           ");
			sb.append("     , mem_hobby=?         ");
			sb.append(" WHERE mem_id =?          ");

			pstmt = conn.prepareStatement(sb.toString());
			int count = 1;
			pstmt.setString(count++, member.getMemPass());
			pstmt.setString(count++, member.getMemName());
			pstmt.setString(count++, member.getMemZip());
			pstmt.setString(count++, member.getMemAdd1());
			pstmt.setString(count++, member.getMemAdd2());
			pstmt.setString(count++, member.getMemBir());
			pstmt.setString(count++, member.getMemMail());
			pstmt.setString(count++, member.getMemHp());
			pstmt.setString(count++, member.getMemJob());
			pstmt.setString(count++, member.getMemHobby());
			pstmt.setString(count++, member.getMemId());
		
			int cnt = pstmt.executeUpdate();
			return cnt;
		}catch (SQLException e){
			throw new DaoException("updateMember : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	@Override
	public int deleteMember(MemberVO member) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" UPDATE member SET              ");
			sb.append(" 		 mem_del_yn='Y'           ");
			sb.append(" 		WHERE mem_id=?            ");
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, member.getMemId());
		
			
			int cnt = pstmt.executeUpdate();
			return cnt;
			
		}catch (SQLException e){
			throw new DaoException("removeMember : " + e.getMessage());
		}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//연결
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			
			//쿼리문
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO member (                          ");
			sb.append("    mem_id       , mem_pass    , mem_name     ");
			sb.append("    , mem_bir    , mem_zip     , mem_add1     ");
			sb.append("    , mem_add2   , mem_hp      , mem_mail     ");
			sb.append("    , mem_job    , mem_hobby   , mem_mileage  ");
			sb.append("    , mem_del_yn                              ");
			sb.append(") VALUES (                                     ");
			sb.append("    ?             ,?         ,?                ");
			sb.append("   ,?            ,?          ,?                ");
			sb.append("   ,?            ,?          ,?                ");
			sb.append("   ,?            ,?          ,0				  ");
			sb.append("   ,'N'											  ");
			sb.append(")     												  ");

			pstmt= conn.prepareStatement(sb.toString());
			
			//?세팅, 쿼리실행
			int i = 1;
			pstmt.setString(i++, member.getMemId());
			pstmt.setString(i++, member.getMemPass());
			pstmt.setString(i++, member.getMemName());
			pstmt.setString(i++, member.getMemBir());
			pstmt.setString(i++, member.getMemZip());
			pstmt.setString(i++, member.getMemAdd1());
			pstmt.setString(i++, member.getMemAdd2());
			pstmt.setString(i++, member.getMemHp());
			pstmt.setString(i++, member.getMemMail());
			pstmt.setString(i++, member.getMemJob());
			pstmt.setString(i++, member.getMemHobby());

			int cnt = pstmt.executeUpdate();
			return cnt;
			
		}catch (SQLException e){
			throw new DaoException("insertMember : " + e.getMessage());
			}finally{
			if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
			if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
			if(conn!=null) { try{conn.close();} catch(Exception e){}}
		}
	}

	
	
	

}
