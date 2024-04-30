package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.CommentEntity;
import group2.bicycle_village.common.dto.CommentsDTO;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;
import group2.bicycle_village.dao.BoardDAOImpl;
import group2.bicycle_village.dao.BoardDao;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDAO = new BoardDAOImpl();
	private AlarmDAO alarmDAO = new AlarmDAOImpl();

	@Override
	public int insert(BoardEntity board) throws SQLException { // 인서트 성공하면 board_seq 값 반환

		int result = boardDAO.insert(board);
		if(result==0) throw new SQLException("등록되지 않았습니다");
		/*else {
			UserDTO userDTO = alarmDAO.userIdAndNickname(board.getUserSeq());
			List<UserDTO> follower = alarmDAO.searchFollower(board.getUserSeq());
			int re = 0;
			for (UserDTO user : follower) {
				re += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(), userDTO.getNickName()+"("+userDTO.getUserId()+")님이 게시물을 작성했습니다.",0, url));
			}
		}*/
		
		return result;
	}
	
	@Override
	public long searchBoardSeq(long userSeq) throws SQLException {
		long result = boardDAO.searchBoardSeq(userSeq);
		if(result==0) throw new SQLException("등록되지 않았습니다");
		
		return result;
	}

	@Override
	public void delete(long boardSeq) throws SQLException {
		int result = boardDAO.delete(boardSeq);
		if(result==0) throw new SQLException("삭제되지 않았습니다");
	}

	@Override

	public void update(BoardEntity board) throws SQLException {

		int result = boardDAO.update(board);
		if(result==0) throw new SQLException("수정되지 않았습니다");
		else {
			List<UserDTO> dips = alarmDAO.searchDips(board.getBoardSeq());
			int re = 0;
			for (UserDTO user : dips) {
				//re += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(), "게시물이 수정되었습니다.",0, url));
			}
		}
	}

	@Override
	public List<BoardDTO> selectAll() throws SQLException {
		List<BoardDTO> list = boardDAO.selectAll();
		return list;
	}
	
	@Override
	public List<BoardDTO> selectByCateory(int category) throws SQLException {
		List<BoardDTO> list = boardDAO.selectByCateory(category);
		return list;
	}
	
	@Override
	public List<BoardDTO> getBoardListByCateory(int category, int pageNo) throws SQLException {
		List<BoardDTO> list = boardDAO.getBoardListByCateory(category, pageNo);
		return list;
	}

	/**
	 *	페이지 번호에 맞는 게시글 목록 호출
	 **/
	@Override
	public List<BoardDTO> selectAll(int pageNo) throws SQLException {
		List<BoardDTO> list = boardDAO.getBoardList(pageNo);
		return list;
	}

	@Override
	public BoardDTO selectByBoardSeq(long boardSeq, boolean flag) throws SQLException {
		if(flag) {
			if(boardDAO.increamentByReadnum(boardSeq) == 0) throw new SQLException("조회수 증가 오류로 조회 할 수 없습니다.");
		} // 조회수 증가
		
		BoardDTO board = boardDAO.selectByBoardSeq(boardSeq);
		if(board==null) throw new SQLException("게시글을 조회할수 없습니다");
		return board;
	}
	
	
	/**
	 *  댓글 조회
	 * */
	@Override
	public List<CommentsDTO> getComment(long boardSeq) throws SQLException {
		List<CommentsDTO> list = boardDAO.getComment(boardSeq);
		return list;
	}
	
	/**
	 *  댓글 작성
	 * */
	@Override
	public int insertComment(CommentEntity comment) throws SQLException {
		int result = boardDAO.insertComment(comment);
		if(result==0) throw new SQLException("등록되지 않았습니다");
		return result;
	}
	
	/**
	 *  댓글 삭제
	 * */
	@Override
	public int deleteComment(long boardSeq) throws SQLException {
		int result = boardDAO.deleteComment(boardSeq);
		if(result==0) throw new SQLException("등록되지 않았습니다");
		return result;
	}
	
	/**
	 *  댓글 수정
	 * */
	@Override
	public int updateComment(CommentEntity comment) throws SQLException {
		int result = boardDAO.updateComment(comment);
		if(result==0) throw new SQLException("등록되지 않았습니다");
		return result;
	}
	
}
