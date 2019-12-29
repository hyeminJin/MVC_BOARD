package edu.bit.ex.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.bit.ex.DTO.DTO;

public class DAO {
	DataSource dataSource;
	
	public DAO(){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<DTO> list() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "Select * from mvc_board order by bGroup desc, bStep asc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bId = rs.getInt("bId");
				int bGroup = rs.getInt("bGroup");
				int bHit = rs.getInt("bHit");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				DTO dto = new DTO(bName,bTitle,bContent,bId,bHit,bGroup,bStep,bIndent,bDate);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}

	public void write(String bName, String bTitle, String bContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "Insert into mvc_board(bId,bName,bTitle,bContent,bGroup,bStep,bIndent,bHit)"
					+ "values(mvc_board_seq.nextval,?,?,?,mvc_board_seq.currval,0,0,0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}

	public DTO contentView(String strbId) {
		DTO dto = null;
		upHit(strbId);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String query = "Select * from mvc_board where bId=?";
					
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strbId));
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int bId = rs.getInt("bId");
				int bGroup = rs.getInt("bGroup");
				int bHit = rs.getInt("bHit");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
			dto = new DTO(bName,bTitle,bContent,bId,bHit,bGroup,bStep,bIndent,bDate);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	private void upHit(String strbId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "Update mvc_board set bHit=bHit+1 where bId=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1,Integer.parseInt(strbId));
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}

	public void delete(String bId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "delete from mvc_board where bId=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
			
			if(rn == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}

	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "Update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4,Integer.parseInt(bId));
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}

	public DTO reply_view(String strbId) {
		
		DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String query = "Select * from mvc_board where bId=?";
					
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strbId));
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int bId = rs.getInt("bId");
				int bGroup = rs.getInt("bGroup");
				int bHit = rs.getInt("bHit");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
			dto = new DTO(bName,bTitle,bContent,bId,bHit,bGroup,bStep,bIndent,bDate);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
		
	}

	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup,  String bStep,String bIndent) {
		
		replyShape(bStep,bGroup);
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "Insert into mvc_board(bId,bName,bTitle,bContent,bGroup,bStep,bIndent)"
					+ "values(mvc_board_seq.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);

			int rn = pstmt.executeUpdate();
			
			if(rn == 1) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}

	private void replyShape(String bStep, String bGroup) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "update mvc_board set bStep=bStep+1 where bGroup=? and bStep > ?"; //? 에 0이 들어오면서 step이 증가하는 쿼리
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, Integer.parseInt(bGroup));
			pstmt.setInt(2, Integer.parseInt(bStep));
		
			int rn = pstmt.executeUpdate();
			
			if(rn==1) {
				System.out.println("정상");
			}else {
				System.out.println("비정상");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2){
				e2.printStackTrace();
			}
		}
	}
		
	
}