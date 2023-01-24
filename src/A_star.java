import java.util.ArrayList;
import java.util.List;


public class A_star {

	
	public static int [][] maze;
	
	public static void build_maze(int initmaze[][]) {
		maze = initmaze;
	}
	
	private static ArrayList<block> openList = new ArrayList<block>();
	private static ArrayList<block> closeList = new ArrayList<block>();
	
	public static ArrayList<block> findNearBlock(block thisblock){
		ArrayList<block> arrayList = new ArrayList<block>();
		
		
		//top, down, left, right
		int X = thisblock.x;
		int Y = thisblock.y+1;
		if(isEmpty(X, Y)&& !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		X = thisblock.x;
		Y = thisblock.y-1;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		X = thisblock.x+1;
		Y = thisblock.y;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		X = thisblock.x-1;
		Y = thisblock.y;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		//4 diagonal
		X = thisblock.x+1;
		Y = thisblock.y+1;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		X = thisblock.x+1;
		Y = thisblock.y-1;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		X = thisblock.x-1;
		Y = thisblock.y+1;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		X = thisblock.x-1;
		Y = thisblock.y-1;
		if(isEmpty(X, Y) && !isExists(closeList, X, Y)){
			arrayList.add(new block(X, Y));
		}
		
		return arrayList;
	}
	
	public static block findPath(block start, block end, int initmaze[][]) {
		openList.add(start);
		maze = initmaze;
		
		
		while(openList.size()>0) {
			//int time = 0;
			//System.out.println("its "+time);
			block thisblock = findMinFinOpenList();
			openList.remove(thisblock);
			closeList.add(thisblock);
			ArrayList<block> nearblock = findNearBlock(thisblock);
			
			for(block temp : nearblock) {
				//System.out.println(temp.x + "," + temp.y);
				if(isExists(openList, temp.x, temp.y)) {
					int partenG = temp.parent != null ? temp.parent.G : 0;
					int G = partenG + 10;
					if(temp.G > G) {
						temp.parent = thisblock;
						temp.G = G;
						temp.calc();
					}
				}
				else {
					temp.parent = thisblock;
					temp.G = temp.parent != null ? temp.parent.G : 0;
					temp.G += 10;
					temp.H = 10 * (Math.abs(temp.x-end.x)+Math.abs(temp.y-end.y));
					temp.calc();
					openList.add(temp);
				}
			}
			if(find(openList, end)!= null) {
				return find(openList, end);
			}
			//time++;
			//if(time > 100) {
			//	break;
			//}
		}
		
		return find(openList, end);
	}
	
	public static block find(List<block> Block, block thisblock) {
		for(block temp : Block) {
			if(temp.x == thisblock.x && temp.y == thisblock.y) {
				return temp;
			}
			
		}
		
		return null;
	}
	
	public static boolean isExists(List<block> Block, int x, int y) {
		for(block temp : Block) {
			if(temp.x == x && temp.y == y) {
				return true;
			}
			
		}
		return false;
	}
	
	public static boolean isEmpty(int x, int y) {
		if (x >=0 && x < maze.length && y >= 0 && y < maze[0].length) {
			return maze[x][y] == 0;//reference to https://blog.csdn.net/ruils/article/details/40780657
		}
		return false;
	}
	
	
	public static block findMinFinOpenList() {
		block temp = openList.get(0);
		for (block Block : openList) {
			if (Block.F < temp.F) {
				temp = Block;
			}
		}
		return temp;
	}
	
	

}
