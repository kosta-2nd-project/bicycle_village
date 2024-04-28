package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.AlarmDTO;
import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.UserDTO;
import group2.bicycle_village.dao.AlarmDAO;
import group2.bicycle_village.dao.AlarmDAOImpl;
import group2.bicycle_village.dao.BoardDAOImpl;
import group2.bicycle_village.dao.BoardDao;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDAO = new BoardDAOImpl();
	private AlarmDAO alarmDAO = new AlarmDAOImpl();

	@Override
	public void insert(BoardEntity board, String url) throws SQLException {
		int result = boardDAO.insert(board);
		if(result==0) throw new SQLException("등록되지 않았습니다");
		else {
			UserDTO userDTO = alarmDAO.userIdAndNickname(board.getUserSeq());
			List<UserDTO> follower = alarmDAO.searchFollower(board.getUserSeq());
			int re = 0;
			for (UserDTO user : follower) {
				re += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(), userDTO.getNickName()+"("+userDTO.getUserId()+")님이 게시물을 작성했습니다.",0, url));
			}
		}
	}

	@Override
	public void delete(int boardSeq) throws SQLException {
		int result = boardDAO.delete(boardSeq);
		if(result==0) throw new SQLException("삭제되지 않았습니다");
	}

	@Override
	public void update(BoardDTO board, String url) throws SQLException {
		int result = boardDAO.update(board);
		if(result==0) throw new SQLException("수정되지 않았습니다");
		else {
			List<UserDTO> dips = alarmDAO.searchDips(board.getBoardSeq());
			int re = 0;
			for (UserDTO user : dips) {
				re += alarmDAO.insertAlarm(new AlarmDTO(user.getUser_seq(), "게시물이 수정되었습니다.",0, url));
			}
		}
	}

	@Override
	public List<BoardDTO> selectAll() throws SQLException {
		List<BoardDTO> list = boardDAO.selectAll();
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
	public BoardDTO selectByBoardSeq(int boardSeq, boolean flag) throws SQLException {
		if(flag) {
			if(boardDAO.increamentByReadnum(boardSeq) == 0) throw new SQLException("조회수 증가 오류로 조회 할 수 없습니다.");
		} // 조회수 증가
		
		BoardDTO board = boardDAO.selectByBoardSeq(boardSeq);
		if(board==null) throw new SQLException("게시글을 조회할수 없습니다");
		return board;
	}
	
}
