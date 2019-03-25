package usedb;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		//DAO 클래스의 인스턴스를 생성
		MessageDAO dao = MessageDAOImpl.getInstance();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.printf("메뉴입력(1.삽입 2.내용출력 3.번호순 5개 보기 4. 더보기 5.페이징 100.종료):");
			String menu =sc.nextLine();
			
			//이름과 내용 그리고 날짜를 저장할 임시변수를 선언
			String name = null;
			String content = null;
			Date writedate = null;
			
			switch(menu) {
			case "1":
				System.out.printf("이름을 입력하세요:");
				name =sc.nextLine();
				System.out.printf("내용을 입력하세요:");
				content = sc.nextLine();
				Calendar cal = new GregorianCalendar();
				writedate = new Date(cal.getTimeInMillis());
				
				MessageVO vo = new MessageVO();
				vo.setName(name);
				vo.setContent(content);
				vo.setWritedate(writedate);
				
				int result =dao.insertMessage(vo);
				if(result>0) {
					System.out.printf("삽입성공\n");
				}else {
					System.out.printf("삽입실패\n");
				}
				System.out.printf("엔터를 치면 다음으로 넘어갑니다.\n");
				sc.nextLine();
				break;
				
			case "2":
				List<MessageVO> list1 =dao.getMessageList();
				for(MessageVO temp : list1) {
					System.out.printf("%s\n",temp);
				}
				
				System.out.printf("엔터를 치면 다음으로 넘어갑니다.\n");
				sc.nextLine();
				break;
				
			case "3":
				List<MessageVO> list2 =dao.fiveList();
				for(MessageVO temp : list2) {
					System.out.printf("%s\n",temp);
				}
				
				System.out.printf("엔터를 치면 다음으로 넘어갑니다.\n");
				sc.nextLine();
				break;
				
			case "4":
				List<MessageVO> list3 =dao.modList();
				for(MessageVO temp : list3) {
					System.out.printf("%s\n",temp);
				}
				
				System.out.printf("엔터를 치면 다음으로 넘어갑니다.\n");
				sc.nextLine();
				break;
				
			case "5":
				//페이지 번호와 데이터 개수 입력 받기
				System.out.printf("페이지 번호를 입력:");
				int page = sc.nextInt();
				System.out.printf("데이터 개수 입력:");
				int cnt = sc.nextInt();
				
				Map<String,Object>map = new HashMap<String,Object>();
				map.put("page", page);
				map.put("cnt",cnt);
				
				//데이터 가져오기
				List<MessageVO> list =dao.pageList(map);
				if(list.size() == 0) {
					System.out.printf("읽어올 데이터가 없습니다.\n");
				}else {
					for(MessageVO vo1 : list) {
						System.out.printf("%s\n",vo1);
						}
				}
				System.out.printf("엔터를 치면 다음으로 넘어갑니다.\n");
				sc.nextLine();
				break;
				
			case "100":
				System.out.printf("프로그램을 종료합니다.\n");
				sc.close();
				System.exit(0);
			}
		}
	
		
		
	}

}
