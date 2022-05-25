
public class CourseDBElement implements Comparable{
	String courseID, roomNumber, instructorName;
	int CRN, credits;
	
	public CourseDBElement() {
		this.courseID = "";
		this.CRN = 0;
		this.credits = 0;
		this.roomNumber = "";
		this.instructorName = "";
	}
	
	public CourseDBElement(String courseID, int CRN, int credits, String roomNumber, String instructorName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.credits = credits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}
	
	public String getCourseID() {
		return courseID;
	}
	
	public int getCRN() {
		return CRN;
	}
	
	public int getCredits() {
		return credits;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}
	
	public String getInstructorName() {
		return instructorName;
	}
	
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	
	public int hash() {
		String temp = String.valueOf(getCRN());
		double result = 0;
		int x = 31;
		for(int i = 0; i < temp.length(); i++) {
			result = (x*result)+temp.charAt(i);
		}
		return (int) result;
	}
	
	public String toString() {
		return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructorName + " Room:" + roomNumber;
	}

	public int compareTo(CourseDBElement compare) {
		return Integer.compare(this.getCRN(), compare.getCRN());
	}
}
