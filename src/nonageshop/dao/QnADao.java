package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.QnA;

public interface QnADao {
    ArrayList<QnA> listQna(String id);  
    QnA getQnA(int no);                
    int insertqna(QnA qna);
}
