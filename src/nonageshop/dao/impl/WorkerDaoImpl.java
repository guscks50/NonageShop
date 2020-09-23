package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nonageshop.dao.WorkerDao;
import nonageshop.dto.Worker;

public class WorkerDaoImpl implements WorkerDao {
    private static final WorkerDaoImpl instance = new WorkerDaoImpl();
    private Connection con;
    
    public static WorkerDaoImpl getInstance() {
        return instance;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    private WorkerDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int workerCheck(Worker worker) {
        String sql = "SELECT 1 FROM worker WHERE id=? AND pwd=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, worker.getId());
            pstmt.setString(2, worker.getPwd());
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

}
