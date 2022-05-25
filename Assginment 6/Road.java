
public class Road implements Comparable<Road>{
	private Town pointA, pointB;
	private String name;
	private int distance;
	
	public Road(Town pointA, Town pointB, int distance, String name) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.name = name;
		this.distance = distance;
	}
	
	public Road(Town pointA, Town pointB, String name) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.name = name;
		this.distance = 1;
	}
	
	public int compareTo(Road o) {
		return this.name.compareTo(o.name);
	}
	
	public boolean equals(Object comp) {
		if(comp == null || !(comp instanceof Road)) {
			return false;
		}else if(comp == this) {
			return true;
		}
		Road r = (Road) comp;
		return(this.pointA.equals(r.pointA) && this.pointB.equals(r.pointB)) || (this.pointA.equals(r.pointB) && this.pointB.equals(r.pointA));
	}
	
	public boolean contains(Town temp) {
		return pointA.getName().equals(temp.getName()) || pointB.getName().equals(temp.getName());
	}
	
	public Town getPointA() {
		return pointA;
	}
	
	public Town getPointB() {
		return pointB;
	}
	
	public int getDist() {
		return distance;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return pointA.getName()+ " via " + name + " to " +pointB.getName() + " " + distance+ " mi";
	}
}
