package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardFileDTO;

public interface BoardFileService {
	List<Integer> selectBFileSeqsByBoardSeq(int boardSeq) throws SQLException;
	
	void insert(BoardFileDTO boardFile) throws SQLException;
}
