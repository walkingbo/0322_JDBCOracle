package usedb;

import java.util.List;

public interface MessageDAO {
	//데이터를 삽입하는 메소드
	public int insertMessage(MessageVO vo);
	//전체 데이터를 가져오는 메소드
	public List<MessageVO> getMessageList();

}
