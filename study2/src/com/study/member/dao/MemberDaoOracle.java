package com.study.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.exception.DaoException;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao{

	@Override
	public List<MemberVO> getMemberList() {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			//쿼리문 만들기 StringBuffer
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT                                              ");
			sb.append("       mem_id      , mem_pass       , mem_name       ");
			sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir     ");
			sb.append("     , mem_zip     , mem_add1       , mem_add2       ");
			sb.append("     , mem_hp      , mem_mail       , mem_job        ");
			sb.append("     , mem_hobby   , mem_mileage    , mem_del_yn     ");
			sb.append(" FROM                                                ");
			sb.append("     member                                          ");
			
			//pstmt만들기
			pstmt = conn.prepareStatement(sb.toString());
			
			//?세팅 (있는 경우만)
				
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
			sb.append(" FROM                                                ");
			sb.append("     member                                          ");
			sb.append(" WHERE     mem_id= ?                                 ");
				
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
