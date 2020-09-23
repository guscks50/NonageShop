package nonageshop.service;

import java.sql.Connection;

import nonageshop.dao.impl.WorkerDaoImpl;
import nonageshop.ds.JndiDS;
import nonageshop.dto.Worker;

public class WorkerService {
    private Connection con = JndiDS.getConnection();
    private WorkerDaoImpl dao = WorkerDaoImpl.getInstance();
    
    public WorkerService() {
        dao.setCon(con);
    }
    
    public int workerCheck(Worker worker) {
        return dao.workerCheck(worker);
    }
}
