import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CourseDBManager implements CourseDBManagerInterface{
	public CourseDBStructure structure;
	
	public CourseDBManager() {
		structure = new CourseDBStructure(10);
	}
	
	public void add(String courseId, int crn, int credits, String roomNum, String instructorName) {
		CourseDBElement element = new CourseDBElement(courseId, crn, credits, roomNum, instructorName);
		structure.add(element);
	}

	public CourseDBElement get(int crn) {
		try {
			return structure.get(crn);
		}catch(IOException e) {
			e.getMessage();
		}
		return null;
	}

	public void readFile(File input) throws FileNotFoundException {
		InputStream x = new FileInputStream(input);
		BufferedReader y = new BufferedReader(new InputStreamReader(x));
		java.util.List<String[]> stringList = y.lines().map(i -> i.split(" ")).collect(Collectors.toList());
		for(String[] array : stringList) {
			if(array.length > 5) {
				StringBuilder combine = new StringBuilder();
				for(int i = 4; i < array.length; i++) {
					combine.append(array[i] + " ");
				}
				structure.add(new CourseDBElement(array[0], Integer.valueOf(array[1]), Integer.valueOf(array[2]), array[3], combine.toString().trim()));
			}else {
				structure.add(new CourseDBElement(array[0], Integer.valueOf(array[1]), Integer.valueOf(array[2]), array[3], array[4]));
			}
		}
		
	}

	public ArrayList<String> showAll() {
		ArrayList<CourseDBElement> temp = new ArrayList<>();
		ArrayList<String> list;
		for(int i = 0; i < structure.getTableSize(); i++) {
			if(structure.hashTable[i] != null) {
				temp.addAll(structure.hashTable[i]);
			}
		}
		list = (ArrayList<String>) temp.stream().map(s -> s.toString()).collect(Collectors.toList());
		return list;
	}
	
}
