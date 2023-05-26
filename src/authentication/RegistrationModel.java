package authentication;

public class RegistrationModel {
	 String userID, password, question, answer;

	private String getUserID() {
		return userID;
	}

	private void setUserID(String userID) {
		this.userID = userID;
	}

	private String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private String getQuestion() {
		return question;
	}

	private void setQuestion(String question) {
		this.question = question;
	}

	private String getAnswer() {
		return answer;
	}

	private void setAnswer(String answer) {
		this.answer = answer;
	}

}
