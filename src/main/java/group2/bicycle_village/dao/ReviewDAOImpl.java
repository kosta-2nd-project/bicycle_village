package group2.bicycle_village.dao;

import group2.bicycle_village.common.dto.BoardEntity;
import group2.bicycle_village.common.dto.ReviewEntity;
import group2.bicycle_village.common.dto.UserEntity;
import group2.bicycle_village.common.util.DbUtil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static group2.bicycle_village.common.constant.CommonCode.*;

public class ReviewDAOImpl implements ReviewDAO{


    @Override
    public void createReview(ReviewEntity review) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO review (reviewing, reviewer, board_seq, review_content, review_rate, review_date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = DbUtil.getConnection();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, (int) review.getReviewing().getUserSeq()); // reviewing
            preparedStatement.setInt(2, (int) review.getReviewer().getUserSeq()); // reviewer
            preparedStatement.setInt(3, (int) review.getBoard().getBoardSeq()); // board_seq
            preparedStatement.setString(4, review.getReviewContent()); // review_content
            preparedStatement.setInt(5, review.getReviewScore().getValue()); // review_rate
            preparedStatement.setDate(6, new Date(System.currentTimeMillis())); // review_date

            // 쿼리 실행
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into review table.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(con, preparedStatement, null);
        }
    }

    @Override
    public ReviewEntity getReviewByReviewSeq(long reviewSeq) {
        ReviewEntity result = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM review WHERE review_seq = ?";

        try {
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,(int) reviewSeq);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new ReviewEntity.Builder()
                        .reviewSeq((long) resultSet.getInt("review_seq"))
                        .reviewing(new UserEntity.Builder().userSeq(resultSet.getInt("reviewing")).build())
                        .reviewer(new UserEntity.Builder().userSeq(resultSet.getInt("reviewer")).build())
                        .board(new BoardEntity.Builder().boardSeq((long) resultSet.getInt("board_seq")).build())
                        .reviewContent(resultSet.getString("review_content"))
                        .reviewScore(reviewScore.getScore(resultSet.getInt("review_rate")))
                        .reviewDate(resultSet.getDate("review_date"))
                        .modificationDate(resultSet.getDate("modification_date"))
                        .build();
            } else {
                System.out.println("No review found with review_seq: " + reviewSeq);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(connection, preparedStatement, resultSet);
        }
        return result;
    }

    @Override
    public List<ReviewEntity> getReviewListByReviewingWithUserSeq(long userSeq) {
        List<ReviewEntity> result = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM review WHERE reviewing = ? ORDER BY COALESCE(modification_date, review_date) DESC";

        try {
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) userSeq);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new ReviewEntity.Builder()
                                .reviewSeq((long) resultSet.getInt("review_seq"))
                                .reviewing(new UserEntity.Builder().userSeq(resultSet.getInt("reviewing")).build())
                                .reviewer(new UserEntity.Builder().userSeq(resultSet.getInt("reviewer")).build())
                                .board(new BoardEntity.Builder().boardSeq((long) resultSet.getInt("board_seq")).build())
                                .reviewContent(resultSet.getString("review_content"))
                                .reviewScore(reviewScore.getScore(resultSet.getInt("review_rate")))
                                .reviewDate(resultSet.getDate("review_date"))
                                .modificationDate(resultSet.getDate("modification_date"))
                                .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(connection, preparedStatement, resultSet);
        }
        return result;
    }

    @Override
    public List<ReviewEntity> getReviewListByReviewerWithUserSeq(long userSeq) {
        List<ReviewEntity> result = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM review WHERE reviewer = ? ORDER BY COALESCE(modification_date, review_date) DESC";

        try {
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) userSeq);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new ReviewEntity.Builder()
                        .reviewSeq((long) resultSet.getInt("review_seq"))
                        .reviewing(new UserEntity.Builder().userSeq(resultSet.getInt("reviewing")).build())
                        .reviewer(new UserEntity.Builder().userSeq(resultSet.getInt("reviewer")).build())
                        .board(new BoardEntity.Builder().boardSeq((long) resultSet.getInt("board_seq")).build())
                        .reviewContent(resultSet.getString("review_content"))
                        .reviewScore(reviewScore.getScore(resultSet.getInt("review_rate")))
                        .reviewDate(resultSet.getDate("review_date"))
                        .modificationDate(resultSet.getDate("modification_date"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(connection, preparedStatement, resultSet);
        }
        return result;
    }

    @Override
    public void putReview(ReviewEntity reviewEntity) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE review SET reviewing = ?, reviewer = ?, board_seq = ?, review_content = ?, review_rate = ?, modification_date = ? WHERE review_seq = ?";
        try {
            con = DbUtil.getConnection();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, (int) reviewEntity.getReviewing().getUserSeq());
            preparedStatement.setInt(2, (int) reviewEntity.getReviewer().getUserSeq());
            preparedStatement.setInt(3, (int) reviewEntity.getBoard().getBoardSeq());
            preparedStatement.setString(4, reviewEntity.getReviewContent());
            preparedStatement.setInt(5, reviewEntity.getReviewScore().getValue());
            preparedStatement.setDate(6, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(7, Math.toIntExact(reviewEntity.getReviewSeq()));

            // 쿼리 실행
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into review table.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(con, preparedStatement, null);
        }
    }

    @Override
    public void deleteReviewByReviewSeq(long reviewSeq) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM review WHERE review_seq = ?";
        try {
            con = DbUtil.getConnection();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, (int) reviewSeq);
            // 쿼리 실행
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into review table.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(con, preparedStatement, null);
        }
    }
    /**/
}
