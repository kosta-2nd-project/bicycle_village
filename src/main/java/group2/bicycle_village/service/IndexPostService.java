package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.exception.AuthenticationException;

public interface IndexPostService {

	/**
	 * 상위 조회수 게시글 조회
	 */
	public List<PostDTO> selectBest() throws SQLException, AuthenticationException;
}
