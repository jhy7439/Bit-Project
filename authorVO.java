package miniProject;

public class authorVO {
	
	
	
	private String authorName;//���ڸ�
	private String debutDate;//��������
	private String debutWork;//������ǰ
	private String auEmail;
	private String auHomeAdress;//Ȩ������ �ּ�
	
	public authorVO() {
		
	}
	public authorVO(String authorName, String debutDate, String debutWork, String auEmail,
			String auHomeAdress){
		this.authorName = authorName;
		this.debutDate = debutDate;
		this.debutWork = debutWork;
		this.auEmail = auEmail;
		this.auHomeAdress = auHomeAdress;

}

	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(String debutDate) {
		this.debutDate = debutDate;
	}

	public String getDebutWork() {
		return debutWork;
	}

	public void setDebutWork(String debutWork) {
		this.debutWork = debutWork;
	}

	public String getAuEmail() {
		return auEmail;
	}

	public void setAuEmail(String auEmail) {
		this.auEmail = auEmail;
	}

	public String getAuHomeAdress() {
		return auHomeAdress;
	}

	public void setAuHomeAdress(String auHomeAdress) {
		this.auHomeAdress = auHomeAdress;
	}
}
