package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.BoardFileDTO;
import group2.bicycle_village.dao.BoardFileDAO;
import group2.bicycle_village.dao.BoardFileDAOImpl;

public class BoardFileServiceImpl implements BoardFileService {
	private BoardFileDAO boardFileDAO = new BoardFileDAOImpl();
    public BoardFileServiceImpl() {
    	System.out.println("BoardFileServiceImpl init......");
    }
	@Override
	public List<Integer> selectBFileSeqsByBoardSeq(int boardSeq) throws SQLException {
		List<Integer> list = boardFileDAO.selectBFileSeqsByBoardSeq(boardSeq);
		return list;
	}

	@Override
	public void insert(BoardFileDTO boardFile) throws SQLException {
		int result = boardFileDAO.insert(boardFile);
		if(result==0) throw new SQLException("등록되지 않았습니다");
	}

}
