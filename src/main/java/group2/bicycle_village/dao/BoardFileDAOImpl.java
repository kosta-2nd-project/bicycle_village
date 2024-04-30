package group2.bicycle_village.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.BoardFileDTO;
import group2.bicycle_village.common.util.DbUtil;

public class BoardFileDAOImpl implements BoardFileDAO {
	
	public BoardFileDAOImpl() {
		System.out.println("BoardFileDAOImpl 생성자 호출됨....");
	}
	
	@Override
	public List<Integer> selectBFileSeqsByBoardSeq(long boardSeq) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> list = new ArrayList<Integer>();
		
		String sql = "select bfile_seq from boardfile where board_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, boardSeq);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				list.add(rs.getInt("bfile_seq"));
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	@Override
    public int insert(BoardFileDTO boardFile) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		System.out.println("#######"+boardFile.getBoardSeq());
		System.out.println("!!!!!!!!!!1"+boardFile.getImageName());
		System.out.println("&&&&&&&&&&&&"+boardFile.getSaveNumber());
		
		//String sql= proFile.getProperty("query.insert");
		String sql= "insert into boardfile values(bfile_seq.nextval,?,?,?)";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, boardFile.getBoardSeq());
			ps.setString(2, boardFile.getImageName());
			ps.setInt(3, boardFile.getSaveNumber());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }
	
	@Override
    public int delete(long bfile_seq) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql= proFile.getProperty("query.insert");
		String sql= "delete boardfile where bfile_seq=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, bfile_seq);
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }
	
}
