package usedb;

import java.util.List;
import java.util.Map;

public interface MessageDAO {
	//데이터를 삽입하는 메소드
	public int insertMessage(MessageVO vo);
	//전체 데이터를 가져오는 메소드
	public List<MessageVO> getMessageList();
	
	//테이블에서 num으로 내림차순 정렬한 후 5개 가져오는 메소드
	public List<MessageVO> fiveList();
	
	//테이블에서 num으로 내림차순 정렬한 후 6번 째 데이터 부터 나머지 전부를 가져오는 메소드
	//더보기를 위한 메소드
	public List<MessageVO> modList();
	
	//페이지 처리를 위한 메소드
	//리턴타입은 이전과 동일하게 List
	//매개변수로 페이지 번호와 조회할 데이터 개수를 매개변수로 받는다.
	//모바일에서는 데이터 개수를 정해놓고 가져오지만 pc용 웹을 구현할 때는 
	//데이터 개수를 선택하도록 한다.
	
	//특별한 경우가 아니면 매개변수는 1개로 만드는 것이 좋다.
	//map은 출력할 페이지 번호와 데이터 개수를 갖는 map
	public List<MessageVO> pageList(Map<String,Object> map);
	
	
}
