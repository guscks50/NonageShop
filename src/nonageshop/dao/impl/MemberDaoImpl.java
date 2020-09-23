package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import nonageshop.dao.MemberDao;
import nonageshop.dto.Member;

public class MemberDaoImpl implements MemberDao {
    private static final MemberDaoImpl instance = new MemberDaoImpl();
    private Connection con;
    
    public static MemberDaoImpl getInstance() {
        return instance;
    }

    private MemberDaoImpl() {}
    
    public void setCon(Connection con) {
        this.con = con;
    }
    
    @Override
    public int confirmID(String userId) {
        String sql = "select 1 from member where id=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql);){ 
                pstmt.setString(1, userId);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
    }

    @Override
    public Member getMember(String id) {
        String sql = "select ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE, LEAVE_YN, JOIN_DATE" + 
                     "  from member where id=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql);){ 
            pstmt.setString(1, id);
        try(ResultSet rs = pstmt.executeQuery()){
            if (rs.next()) {
                return getMember(rs);
            }
        }
    } catch (SQLException e) {
        throw new CustomSQLException(e);
    }
        return null;
    }

    private Member getMember(ResultSet rs) throws SQLException {
        String id = rs.getString("ID");
        String pwd = rs.getString("PWD");
        String name = rs.getString("NAME");
        String email = rs.getString("EMAIL");
        String zipNum = rs.getString("ZIP_NUM");
        String address = rs.getString("ADDRESS");
        String phone = rs.getString("PHONE");
        String leave_yn = rs.getString("LEAVE_YN");
        Date joinDate = rs.getTimestamp("JOIN_DATE");
        return new Member(id, pwd, name, email, zipNum, address, phone, leave_yn, joinDate);
    }

    @Override
    public int insertMember(Member member) {
        String sql = "insert into member(id, pwd, name, zip_num, address, phone, email) " + 
                     "values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPwd());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getZipNum());
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getPhone());
            pstmt.setString(7, member.getEmail());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

}
