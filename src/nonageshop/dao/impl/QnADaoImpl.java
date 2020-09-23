package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.QnADao;
import nonageshop.dto.QnA;

public class QnADaoImpl implements QnADao {
    private static final QnADaoImpl instance = new QnADaoImpl();
    private Connection con;

    private QnADaoImpl() {
    }

    public static QnADaoImpl getInstance() {
        return instance;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @Override
    public ArrayList<QnA> listQna(String id) {
        String sql = "SELECT NO, SUBJECT, CONTENT, REP, ID, REP_YN, WRITE_DATE FROM QNA WHERE ID=? ORDER BY NO DESC";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ArrayList<QnA> list = new ArrayList<QnA>();
                    do {
                        list.add(getQnA(rs));
                    } while (rs.next());
                    return list;
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    private QnA getQnA(ResultSet rs) throws SQLException {
        int no = rs.getInt("NO");
        String subject = rs.getString("SUBJECT");
        String content = rs.getString("CONTENT");
        String rep = rs.getString("REP");
        String id = rs.getString("ID");
        String repYN = rs.getString("REP_YN");
        Date writeDate = rs.getDate("WRITE_DATE");
        return new QnA(no, subject, content, rep, id, repYN, writeDate);
    }

    @Override
    public QnA getQnA(int no) {
        String sql = "SELECT NO, SUBJECT, CONTENT, REP, ID, REP_YN, WRITE_DATE FROM QNA WHERE NO=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, no);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getQnA(rs);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public int insertqna(QnA qna) {
        String sql = "INSERT INTO QNA (SUBJECT, CONTENT, ID) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, qna.getSubject());
            pstmt.setString(2, qna.getContent());
            pstmt.setString(3, qna.getId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

}
