package test.cafe.dto;

public class CafeDto {
	private int num;
	private String Writer;
	private String title;
	private String content;
	private int viewCount;
	private String regdate;
	//페이징 처리를 위한 필드
	private int startRownum;
	private int endRownum;
	
	public CafeDto() {}

	public CafeDto(int num, String writer, String title, String content, int viewCount, String regdate, int startRownum,
			int endRownum) {
		super();
		this.num = num;
		Writer = writer;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.regdate = regdate;
		this.startRownum = startRownum;
		this.endRownum = endRownum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String writer) {
		Writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getStartRownum() {
		return startRownum;
	}

	public void setStartRownum(int startRownum) {
		this.startRownum = startRownum;
	}

	public int getEndRownum() {
		return endRownum;
	}

	public void setEndRownum(int endRownum) {
		this.endRownum = endRownum;
	}

	
	
}
