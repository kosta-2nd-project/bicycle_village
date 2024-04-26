package group2.bicycle_village.dao;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.CommentsDTO;
import group2.bicycle_village.common.dto.PageCnt;
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
	public BoardDTO selectByBoardSeq(int boardSeq) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		BoardDTO board = null;
		
		String sql = "select * from board where board_seq=? and is_seen!=0 ";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardSeq);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				board = new BoardDTO(rs.getInt("board_seq"), rs.getInt("board_edit"), rs.getInt("board_count"),
						rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
						rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));
				
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return board;
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
						new BoardDTO(rs.getInt("board_seq"), rs.getInt("board_edit"), rs.getInt("board_count"),
								rs.getInt("goods_price"), rs.getInt("product_seq"), rs.getInt("user_seq"), rs.getString("board_name"),
								rs.getString("reg_date"), rs.getString("category"), rs.getInt("is_seen"), rs.getString("board_content"), rs.getString("board_addr"));
				
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
						new BoardDTO(rs.getInt("board_seq"), rs.getInt("board_edit"), rs.getInt("board_count"),
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
	public int increamentByReadnum(int board_seq) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql = proFile.getProperty("query.updateReadnum");
		String sql = "update board set board_count=readnum+1 where board_seq=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_seq);
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
    
	@Override
    public int insert(BoardEntity board) throws SQLException{
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
    public int delete(int boardSeq) throws SQLException{
    	Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql= proFile.getProperty("query.delete");
		String sql= "update board set is_seen=0 where board_seq=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, boardSeq);
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }
	
	@Override
    public int update(BoardDTO board) throws SQLException{
    	Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//String sql= proFile.getProperty("query.update");
		String sql= "update board set board_name=?,board_content=?"
				+ ",board_edit=(select nvl(board_edit, 0) + 1 from board)"
				+ ",goods_price=? where board_seq=?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, board.getBoardName());
			ps.setString(2, board.getBoardContent());
			
			ps.setInt(3, board.getGoodsPrice());
			ps.setInt(4, board.getBoardSeq());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps, null);;
		}
		return result;
		
    }

	/**
	 * 댓글정보 가져오기 
	 * */
	private List<CommentsDTO> getReply(Connection con , int boardSeq)throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<CommentsDTO> list = new ArrayList<CommentsDTO>();
		//String sql=proFile.getProperty("query.replyByParentNum");
		String sql = "select * from replies where board_seq=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardSeq);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CommentsDTO reply = new CommentsDTO(rs.getInt("comment_seq"), rs.getInt("parent_comment"), 
						rs.getInt("board_seq"), rs.getInt("user_seq"), rs.getString("reg_date"),  rs.getInt("is_seen"), 
						rs.getString("comment_content"), rs.getString("cor_date"));
				
				list.add(reply);
			}
			
		}finally {
			DbUtil.close(null, ps, rs);
		}
		return list;
	}
}
