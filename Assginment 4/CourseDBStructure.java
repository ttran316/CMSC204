import java.io.IOException;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{
	LinkedList<CourseDBElement>[] hashTable;
	
	public CourseDBStructure(int x) {
		hashTable = new LinkedList[x];
	}
	
	public CourseDBStructure(String str, int x) {
		hashTable = new LinkedList[x];
	}
	
	
	public void add(CourseDBElement element) {
		int i = element.hash() % hashTable.length;
		if((element.hash() % hashTable.length) < 0) {
			i +=hashTable.length;
		}
		if(hashTable[i] == null) {
			hashTable[i] = new LinkedList<CourseDBElement>();
			hashTable[i].add(element);
		}else if(hashTable[i].contains(element)) {
			return;
		}else {
			hashTable[i].add(element);
		}
	}

	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement temp = new CourseDBElement();
		temp.setCRN(crn);
		int i = temp.hash() % hashTable.length;
		if((temp.hash() % hashTable.length) < 0) {
			i +=hashTable.length;
		}
		LinkedList<CourseDBElement> list = hashTable[i];
		return list.stream().filter(x -> x.getCRN() == crn).findAny().orElseThrow();
		
	}

	public int getTableSize() {
		return hashTable.length;
	}

}
