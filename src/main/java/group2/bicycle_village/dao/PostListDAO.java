package group2.bicycle_village.dao;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.PostDTO;

public interface PostListDAO {

	/**
	 * 전체 조회
	 */
	List<PostDTO> selectAll(int userSeq) throws SQLException;
	
	/**
	 * 삭제
	 */
	int delPost(PostDTO postDto) throws SQLException;
}
