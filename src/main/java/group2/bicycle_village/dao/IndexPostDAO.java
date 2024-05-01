package group2.bicycle_village.dao;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;

public interface IndexPostDAO {

	/**
	 * 상위 조회수 게시글 조회
	 */
	List<BoardDTO> selectBest() throws SQLException;
}
