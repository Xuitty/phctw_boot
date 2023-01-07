package spring.bean;

public class SendTest {
	private String message;
	
	public SendTest(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SendTest [message=" + message + "]";
	}
	
	
}
