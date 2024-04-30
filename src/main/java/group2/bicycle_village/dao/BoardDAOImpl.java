package group2.bicycle_village.dao;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.CommentEntity;
import group2.bicycle_village.common.dto.CommentsDTO;
import group2.bicycle_village.common.dto.PageCnt;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.common.util.DbUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAOImpl implements BoardDao{

private Properties proFile = new Properties();
	
	public BoardDAOImpl() {
		System.out.println("BoardDAOImpl 생성자 호출됨...");
		/*try {
			//dbQuery를 준비한 ~.properties파일을 로딩해서 Properties 자료구조에 저장한다.
			InputStream is = getClass().getClassLoader().getResourceAsStream("dbQuery.properties");
			proFile.load(is);
			System.out.println(DBQuery.boardSelect);
			
			System.out.println("query.select = " +proFile.getProperty("query.select"));
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	@Override
	public void test(){
		BoardEntity board = new BoardEntity.Builder().addr("하하").content("ㅁㄴㅇㄹ").category(CommonCode.BoardCategory.valueOf("자유게시판")).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리 내용에 매칭되는 int 저장
		//BoardEntity board = new BoardEntity.Builder().addr("하하").content("ㅁㄴㅇㄹ").category(CommonCode.BoardCategory.getStatus(1)).build(); // 주소,내용,카테고리만 추가된 dto 생성하는데 카테고리는 int에 매칭되는 내용 저장
		
		
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result=false;
        try {
            con= DbUtil.getConnection();
            ps = con.prepareStatement("SELECT * FROM DBA_TABLES");
            rs = ps.executeQuery();
            if(rs.next()){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(con, ps, rs);
        }
	}
	
	@Override
	public BoardDTO selectByBoardSeq(long boardSeq) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		BoardDTO board = null;
		
		String sql = "select * from board where board_seq=? and is_seen!=0 ";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, boardSeq);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				board = new BoardDTO(rs.getInt("board_seq"), rs.getString("board_edit"), rs.getInt("board_count"),
						rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
						rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));
				
				
				//user_seq에해당하는 user의정보 조회해서 저장
				board.setUserDTO(getUserByUserSeq(rs.getInt("user_seq")));
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return board;
	}
	
	/**
	 * userSeq에 해당하는 정보 조회하기 
	 * */
	private UserDTO getUserByUserSeq(long userSeq) throws SQLException {
		//BoardEntity entity = new BoardEntity.Builder().addr("").category(CommonCode.BoardCategory.getStatus(0)).build();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDTO dbDTO =null;
		
		String sql= "select nickname from member where user_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, userSeq);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				dbDTO = new UserDTO();
				dbDTO.setNickName(rs.getString(1));
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return dbDTO;
	}

	
	@Override
	public List<BoardDTO> selectAll() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql= "select * from board where is_seen!=0 order by reg_date desc";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO board = 
						new BoardDTO(rs.getInt("board_seq"), rs.getString("board_edit"), rs.getInt("board_count"),
								rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
								rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));
				
				//user_seq에해당하는 user의정보 조회해서 저장
				board.setUserDTO(getUserByUserSeq(rs.getInt("user_seq")));
				
			   list.add(board);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	@Override
	public List<BoardDTO> selectByCateory(int category) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql= "select * from board where is_seen!=0 and category=? order by reg_date desc";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, category);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO board = 
						new BoardDTO(rs.getInt("board_seq"), rs.getString("board_edit"), rs.getInt("board_count"),
								rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
								rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));

				//user_seq에해당하는 user의정보 조회해서 저장
				board.setUserDTO(getUserByUserSeq(rs.getInt("user_seq")));
				
			   list.add(board);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	@Override
	public List<BoardDTO> getBoardListByCateory(int category, int pageNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		//String sql = proFile.getProperty("query.pagingSelect");
		String sql = "select * from  (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM board where is_seen!=0 and category=? ORDER BY reg_date desc) a) where  rnum>=? and rnum <=?";
		try {
			
			
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//전체레코드수를 구한다.
			int totalCount = this.getTotalCount(con);
			int totalPage = totalCount%PageCnt.pagesize==0 ? totalCount/PageCnt.pagesize : (totalCount/PageCnt.pagesize)+1;
			
			PageCnt pageCnt = new PageCnt();
			pageCnt.setPageCnt(totalPage);
			pageCnt.setPageNo(pageNo);
			
			ps = con.prepareStatement(sql);
			// 2개에 set설정
			ps.setInt(1, category);
			ps.setInt(2, (pageNo-1) * pageCnt.pagesize +1); //시작
			ps.setInt(3, pageNo * pageCnt.pagesize);//끝
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = 
						new BoardDTO(rs.getInt("board_seq"), rs.getString("board_edit"), rs.getInt("board_count"),
								rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
								rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));
				
				//user_seq에해당하는 user의정보 조회해서 저장
				board.setUserDTO(getUserByUserSeq(rs.getInt("user_seq")));
				
			   list.add(board);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	@Override
	public List<BoardDTO> getBoardList(int pageNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		//String sql = proFile.getProperty("query.pagingSelect");
		String sql = "select * from  (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM board where is_seen!=0 ORDER BY reg_date desc) a) where  rnum>=? and rnum <=?";
		try {
			
			
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//전체레코드수를 구한다.
			int totalCount = this.getTotalCount(con);
			int totalPage = totalCount%PageCnt.pagesize==0 ? totalCount/PageCnt.pagesize : (totalCount/PageCnt.pagesize)+1;
			
			PageCnt pageCnt = new PageCnt();
			pageCnt.setPageCnt(totalPage);
			pageCnt.setPageNo(pageNo);
			
			ps = con.prepareStatement(sql);
			// 2개에 set설정
			ps.setInt(1, (pageNo-1) * pageCnt.pagesize +1); //시작
			ps.setInt(2, pageNo * pageCnt.pagesize);//끝
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = 
						new BoardDTO(rs.getInt("board_seq"), rs.getString("board_edit"), rs.getInt("board_count"),
								rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
								rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));
				
			   list.add(board);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	/**
	 * 전체레코드 수 검색하기
	 * */
	private int getTotalCount(Connection con) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result=0;
		//String sql= proFile.getProperty("query.totalCount");
		String sql= "select count(*) from board";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally {
			DbUtil.close(null, ps, rs);
		}
		return result;
	}
	
	@Override
	public int increamentByReadnum(long board_seq) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql = proFile.getProperty("query.updateReadnum");
		String sql = "update board set board_count=(select board_count from board where board_seq=?)+1 where board_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, board_seq);
			ps.setLong(2, board_seq);
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
    
	@Override
    public int insert(BoardEntity board) throws SQLException{ // 인서트 성공하면 보드시퀀스 반환
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql= proFile.getProperty("query.insert");
		String sql= "insert into board values(board_seq.nextval,?,sysdate,?,1,?,?,null,?,?,null,?)";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, board.getBoardName());
			ps.setInt(2, board.getCategory().getValue() );
			ps.setString(3, board.getContent());
			ps.setString(4, board.getAddr());
			ps.setInt(5, board.getBoardCount());
			ps.setInt(6, board.getPrice());
			
//			if(board.getProductSeq()!=0) {
//				ps.setLong(7, board.getProductSeq());
//			} else {
//				ps.setNull(7, java.sql.Types.BIGINT);
//			}
			
			ps.setLong(7, board.getUserSeq());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }
    
	@Override
    public int delete(long boardSeq) throws SQLException{
    	Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql= proFile.getProperty("query.delete");
		String sql= "update board set is_seen=0 where board_seq=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, boardSeq);
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }
	
	@Override
    public int update(BoardEntity board) throws SQLException{
    	Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql= proFile.getProperty("query.update");
		String sql= "update board set board_name=?,board_content=?"
				+ ",board_edit=sysdate,goods_price=? where board_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, board.getBoardName());
			ps.setString(2, board.getContent());
			
			ps.setInt(3, board.getPrice());
			ps.setLong(4, board.getBoardSeq());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }

	@Override
	public long searchBoardSeq(long userSeq) throws SQLException { // 가장 최근 생성된 보드시퀀스 반환
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT board_seq FROM board WHERE user_seq = ? AND reg_date = (SELECT MAX(reg_date) FROM board)";
		long boardSeq = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) userSeq);
			rs = ps.executeQuery();
			if (rs.next()) {
				boardSeq = rs.getLong(1);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return boardSeq;
	}
	
//================댓글======================================= 
	
	/**
	 * 댓글정보 가져오기 
	 * */
	@Override
	public List<CommentsDTO> getComment(long boardSeq) throws SQLException{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<CommentsDTO> list = new ArrayList<CommentsDTO>();
		String sql = "select * from comments where board_seq=? and is_seen=1 and parent_comment is null";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, boardSeq);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CommentsDTO comment = new CommentsDTO(rs.getLong("comment_seq"), rs.getLong("parent_comment"), 
						rs.getLong("board_seq"), rs.getLong("user_seq"), rs.getString("reg_date"),  rs.getInt("is_seen"), 
						rs.getString("comment_content"), rs.getString("cor_date"));
				
				list.add(comment);
			}
			
		}finally {
			DbUtil.close(null, ps, rs);
		}
		return list;
	}
	
	/**
	 * 대댓글정보 가져오기 
	 * */
	@Override
	public List<CommentsDTO> getReComment(Long commentSeq)throws SQLException{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<CommentsDTO> list = new ArrayList<CommentsDTO>();
		String sql = "select * from comments where board_seq=? and is_seen=1 and parent_comment=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, commentSeq);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CommentsDTO comment = new CommentsDTO(rs.getLong("comment_seq"), rs.getLong("parent_comment"), 
						rs.getLong("board_seq"), rs.getLong("user_seq"), rs.getString("reg_date"),  rs.getInt("is_seen"), 
						rs.getString("comment_content"), rs.getString("cor_date"));
				
				list.add(comment);
			}
			
		}finally {
			DbUtil.close(null, ps, rs);
		}
		return list;
	}
	
	/**
	 * 댓글정보 입력
	 * */
	@Override
	public int insertComment(CommentEntity comment)throws SQLException{
		Connection con = null;
		PreparedStatement ps=null;
		int result = 0;
		String sql = "insert into comments values(comment_seq.nextval,?,?,?,sysdate,1,?,null)";
		
		try {
			con = DbUtil.getConnection();
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, comment.getParentCommentSeq());
			ps.setLong(2, comment.getBoardSeq());
			ps.setLong(3, comment.getUserSeq());
			ps.setInt(4, comment.getIsSeen());
			ps.setString(5, comment.getCommentContent());
			ps.setString(6, comment.getCorDate());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
	}
	
	/**
	 * 댓글 삭제 (is_seen 상태 변경)
	 * */
	@Override
	public int deleteComment(long commentSeq) throws SQLException{
		Connection con = null;
		PreparedStatement ps=null;
		int result = 0;
		String sql = "update comments set is_seen=0 where comment_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, commentSeq);
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	 * 댓글 수정
	 * */
	@Override
	public int updateComment(CommentEntity comment)throws SQLException{
		Connection con = null;
		PreparedStatement ps=null;
		int result = 0;
		String sql = "update comments comment_content=? where comment_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, comment.getCommentSeq());
			ps.setString(1, comment.getCommentContent());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
}
