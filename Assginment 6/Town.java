import java.util.ArrayList;

public class Town implements Comparable<Town>{
	private String name;
	private ArrayList<Town> towns;
	
	public Town(String name){
		this.name = name;
	}
	
	public Town(Town town) {
		this.name=town.name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int compareTo(Town o) {
		return this.name.compareTo(o.name);
	}
	
	public boolean equals(Object comp) {
		if(comp == null || !(comp instanceof Town)) {
			return false;
		}else if(comp == this) {
			return true;
		}
		Town t = (Town) comp;
		return this.name.equals(t.name);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return "Town name is " + name;
	}

}
