import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UseTransaction {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("insert into sample(num,message) values(?,?)");){
			
			//commit 이나 rollback을 직접 수행할 수 있도록 설정 
			con.setAutoCommit(false);
			pstmt.setInt(1, 15);
			pstmt.setString(2, "삼성은 돈을써라!");
			
			pstmt.executeUpdate();
			System.out.printf("삽입성공");
			//현재까지의 작업 내용을 바로 반영
			con.commit();
			Thread.sleep(30000);//이렇게 했을 때 삽입성공 메세지 후 dbeaver가보면 반영 전 왜냐 스레드가 끝나야 프로그램이 끝난거고commit
			//작업 취소
			//con.rollback();
					
				}catch(Exception e) {
			System.out.printf("예외 : %s\n",e.getMessage());
			e.printStackTrace();
		}


	}

}
