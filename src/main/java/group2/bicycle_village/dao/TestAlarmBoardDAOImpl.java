package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestAlarmBoardDAOImpl implements TestAlarmBoardDAO {
    @Override
    public int insert(BoardEntity board) throws SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        int result=0;
        //String sql= proFile.getProperty("query.insert");
        String sql= "insert into board values(board_seq.nextval,?,sysdate,?,1,?,?,null,?,?,null,?)";

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, board.getBoardName());
            ps.setInt(2, board.getCategory().getValue() );
            ps.setString(3, board.getContent());
            ps.setString(4, board.getAddr());
            ps.setInt(5, board.getBoardCount());
            ps.setInt(6, board.getPrice());

//			if(board.getProductSeq()!=0) {
//				ps.setLong(7, board.getProductSeq());
//			} else {
//				ps.setNull(7, java.sql.Types.BIGINT);
//			}

            ps.setLong(7, board.getUserSeq());

            result = ps.executeUpdate();
        }finally {
            DbUtil.close(con, ps, null);;
        }
        return result;

    }

    @Override
    public int update(BoardEntity board) throws SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        int result=0;
        //String sql= proFile.getProperty("query.update");
        String sql= "update board set board_name=?,board_content=?"
                + ",board_edit=sysdate,goods_price=? where board_seq=?";
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, board.getBoardName());
            ps.setString(2, board.getContent());

            ps.setInt(3, board.getPrice());
            ps.setLong(4, board.getBoardSeq());

            result = ps.executeUpdate();
        }finally {
            DbUtil.close(con, ps, null);;
        }
        return result;

    }

    @Override
    public long serachBoardSeq(long userSeq) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT board_seq FROM board WHERE user_seq = ? AND reg_date = (SELECT MAX(reg_date) FROM board)";
        long boardSeq = 0;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (int) userSeq);
            rs = ps.executeQuery();
            if (rs.next()) {
                boardSeq = rs.getLong(1);
            }
        } finally {
            DbUtil.close(con, ps, rs);
        }
        return boardSeq;
    }
}
