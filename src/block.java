
public class block {
	public int x;
	public int y;
	
	public block(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int F;
	public int G;
	public int H;
	
	public void calc() {
		this.F=this.G+this.H;
	}
	
	public block parent;
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
