package nonageshop.dao;

import nonageshop.dto.Member;

public interface MemberDao {
    int confirmID(String userId);
    Member getMember(String id);
    int insertMember(Member member);
}
