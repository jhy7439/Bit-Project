
public class ToiecVO  {
	private String ToeicName; 
	private	String ToeicTime;
	private String ToeicEx;
	private String ToeicPrice;
	public ToiecVO()  {
		
	}
	public String getToeicName() {
		return ToeicName;
	}
	public void setToeicName(String toeicName) {
		ToeicName = toeicName;
	}
	public String getToeicTime() {
		return ToeicTime;
	}
	public void setToeicTime(String toeicTime) {
		ToeicTime = toeicTime;
	}
	public String getToeicEx() {
		return ToeicEx;
	}
	public void setToeicEx(String toeicEx) {
		ToeicEx = toeicEx;
	}
	public String getToeicPrice() {
		return ToeicPrice;
	}
	public void setToeicPrice(String toeicPrice) {
		ToeicPrice = toeicPrice;
	}
	public static void main(String[] args) {
		new ToiecVO();

	}

}
