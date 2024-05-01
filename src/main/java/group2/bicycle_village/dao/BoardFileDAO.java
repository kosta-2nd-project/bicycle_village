package group2.bicycle_village.dao;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardFileDTO;

public interface BoardFileDAO {

	List<Integer> selectBFileSeqsByBoardSeq(long boardSeq) throws SQLException;

	int insert(BoardFileDTO boardFile) throws SQLException;

	int delete(long bfile_seq) throws SQLException;

	String selectImageNamesByBoardSeq(long boardSeq) throws SQLException;

}
