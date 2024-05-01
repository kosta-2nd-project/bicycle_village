package group2.bicycle_village.service;

import java.sql.SQLException;
import java.util.List;

import group2.bicycle_village.common.dto.BoardDTO;
import group2.bicycle_village.common.dto.PostDTO;
import group2.bicycle_village.dao.IndexPostDAO;
import group2.bicycle_village.dao.IndexPostDAOImpl;
import group2.bicycle_village.exception.AuthenticationException;

public class IndexPostServiceImpl implements IndexPostService {
	
	IndexPostDAO indexPostDao = new IndexPostDAOImpl();

	@Override
	public List<PostDTO> selectBest() throws SQLException, AuthenticationException {
		return indexPostDao.selectBest();
	}

}
