package usedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<MessageVO> fiveList() {
		List<MessageVO>list =new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("select * from(select rownum rnum, num, name, content, writedate from(select * from message order by num desc))"
						+ "where rnum<=5");){
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					MessageVO vo = new MessageVO();
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

	@Override
	public List<MessageVO> modList() {
		List<MessageVO>list =new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("select * from(select rownum rnum, num, name, content, writedate from(select * from message order by num desc))"
						+ "where rnum >5");){
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					MessageVO vo = new MessageVO();
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

	@Override
	public List<MessageVO> pageList(Map<String, Object> map) {
		List<MessageVO>list =new ArrayList<MessageVO>();
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("select * from(select rownum rnum, num, name, content, writedate from(select * from message order by num desc))"
						+ "where rnum >= ? and rnum<= ?");){
			
			//map에서 가져온 이름은 앞에서 만들어 줘야한다.
			//이름이 틀리면 NullPointerException이 발생 
			int page = (Integer)map.get("page");
			int cnt = (Integer)map.get("cnt");
			
			//페이지 번호와 페이지당 데이터 개수를 이용해서 
			//가져올 데이터의 시작 번호와 끝 번호를 생성 
			pstmt.setInt(1, cnt*(page-1)+1);
			pstmt.setInt(2, cnt*(page));
			
			
				ResultSet rs = pstmt.executeQuery();
				//읽은 데이터가 없으면 while문이 한번도 수행되지 않아서 
				//List의 size가 0 이 됩니다.
				while(rs.next()) {	
					MessageVO vo = new MessageVO();
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
