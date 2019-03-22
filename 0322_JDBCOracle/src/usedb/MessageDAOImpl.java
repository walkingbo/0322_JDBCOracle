package usedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {
	//생성자는 private으로
	private MessageDAOImpl() {}
	
	//자신의 타입으로 static변수를 선언
	private static MessageDAO messageDAO;
	
	//인스턴스를 만들어서 리턴하는 static 메소드를 생성
	public static MessageDAO getInstance() {
		if(messageDAO==null) {
			messageDAO = new MessageDAOImpl();
		}
		return messageDAO;
	}

	@Override
	public int insertMessage(MessageVO vo) {
		int result = -1;
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("insert into message(num,content,name,writedate) "
						+ "values(messageseq.nextval,?,?,?)");){
			//물음표 데이터 바인
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getName());
			pstmt.setDate(3, vo.getWritedate());
			
			//sql실행
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.printf("예외 : %s\n",e.getMessage());
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public List<MessageVO> getMessageList() {
		List<MessageVO>list =new ArrayList<MessageVO>();
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				//여러 개의 데이터를 가져올 때는 order by가 필
				PreparedStatement pstmt = con.prepareStatement("select * from message order by writedate desc");){
			//select 구문을 수행하고 그 결과를 rs 에 저장
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MessageVO vo = new MessageVO();
				
				//읽은 데이터를 vo에 저장
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setWritedate(rs.getDate("writedate"));
				
				list.add(vo);
				
			}
			
			
		}catch(Exception e) {
			System.out.printf("예외 : %s\n",e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
}
