package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.dao.PostListDAO;
import group2.bicycle_village.dao.PostListDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class PostServiceImpl implements PostService {
	
	PostListDAO postlist = new PostListDAOImpl();

	@Override
	public List<PostDTO> selectAll(int userSeq) throws SQLException, AuthenticationException {
		return postlist.selectAll(userSeq);
	}

	@Override
	public void delPost(PostDTO postDto) throws SQLException, AuthenticationException {
		int result = postlist.delPost(postDto);
		if(result==0) {
			throw new AuthenticationException("찜 삭제 실패");
		}
	}

}
