import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class EmpSelect {

	public static void main(String[] args) {
		//emp 테이블의 데이터를 변수에 저장해서 읽기
		//숫자 컬럼인 empno, 문자열인 ename, 날짜형식인 hiredate만 읽기
		
		//파일을 읽고 쓰기를 하거나 네트워크 작업 또는 데이터베이스 연동시에는
		//반드시 예외 처리를 해야하고 close를 해야한다.
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user12","user12");
				PreparedStatement pstmt = con.prepareStatement("select empno,ename,hiredate from emp");){
			//sql 문장에 ?가 있었다면 실제데이터로 치환 : Data Binding
			//sql 을 실행
			ResultSet rs = pstmt.executeQuery();
			//결과사용
			while(rs.next()) {
				//전부 문자열로 읽는 것이 가능하다.
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
				//String hiredate = rs.getString("hiredate");
				
				//날짜로 받기
				//Date - java.util(날짜와 시간 모두 저장) , java.sql(날짜만 저장)
				java.util.Date hiredate =rs.getDate("hiredate");
				
				//데이터출력
				System.out.printf("사번: %s 이름 : %s 입사일 : %s\n", empno,ename,hiredate);
				
			}
			
		}catch(Exception e) {
			System.out.printf("예외 : %s\n",e.getMessage());
			e.printStackTrace();
		}

	}

}
