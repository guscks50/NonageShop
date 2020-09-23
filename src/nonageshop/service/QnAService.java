package nonageshop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dao.impl.QnADaoImpl;
import nonageshop.ds.JndiDS;
import nonageshop.dto.QnA;

public class QnAService {
    private QnADaoImpl dao = QnADaoImpl.getInstance();
    private Connection con = JndiDS.getConnection();

    public QnAService() {
        dao.setCon(con);
    }
    
    public ArrayList<QnA> listQna(String id){
        return dao.listQna(id);
    }
    
    public QnA getQnA(int no) {
        return dao.getQnA(no);    
    }
    
    public int insertqna(QnA qna) {
        return dao.insertqna(qna);
    }
}
