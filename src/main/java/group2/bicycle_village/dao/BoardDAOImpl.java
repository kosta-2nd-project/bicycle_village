package group2.bicycle_village.dao;

import group2.bicycle_village.common.constant.CommonCode;
import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.util.DbUtil;

import java.util.Properties;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAOImpl implements BoardDao{

    public BoardDAOImpl() {
        try {
            System.out.println(DBQuery.boardSelect);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result=false;
        try {
            con= DbUtil.getConnection();
            ps = con.prepareStatement("SELECT * FROM DBA_TABLES");
            rs = ps.executeQuery();
            if(rs.next()){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(con, ps, rs);
        }
    }
}
