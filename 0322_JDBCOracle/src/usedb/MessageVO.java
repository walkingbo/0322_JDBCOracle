package usedb;

import java.sql.Date;

public class MessageVO {
	private int num; 
	private String name ;
	private String content; 
	private Date writedate;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	
	@Override
	public String toString() {
		return "MessageVO [num=" + num + ", name=" + name + ", content=" + content + ", writedate=" + writedate + "]";
	}
	

}
