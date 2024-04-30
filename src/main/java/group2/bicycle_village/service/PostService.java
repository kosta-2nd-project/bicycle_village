package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.exception.AuthenticationException;

public interface PostService {

	/**
	 * 전체 조회
	 */
	public List<PostDTO> selectAll(int userSeq) throws SQLException, AuthenticationException;
	
	/**
	 * 게시글 삭제
	 */
	void delPost(PostDTO postDto) throws SQLException, AuthenticationException;
}
