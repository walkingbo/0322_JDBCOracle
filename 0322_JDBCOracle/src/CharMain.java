import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CharMain {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sample WHERE message ='hi'");){
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.printf("번호:%d 메세지:%s 날짜:%s\n", rs.getInt("num"), rs.getString("message").trim()
						,rs.getString("writedate"));
				//번호:1 메세지:hi                                                 날짜:2019-02-06 00:00:00(트림 안했 시)
				
			}
		
				}catch(Exception e) {
			System.out.printf("예외 : %s\n",e.getMessage());
			e.printStackTrace();
		}


	}

}
