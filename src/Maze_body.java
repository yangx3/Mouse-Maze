/**
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;  
import java.io.FileNotFoundException;  

/**
 * @author Xiaoyi Yang
 *
 */
public class Maze_body {

	/**
	 * @param args
	 * 
	 */
	public static int m;
	public static int n;
	public static int xarray[];
	public static int yarray[];
	
	public static int[][] maze;

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		input_maze();
		build_maze();
		print_maze();
		
		
		block start = new block(0,0);
		block end = new block(m-1, n-1);
		System.out.println("A* running");		
		block apath = A_star.findPath(start, end, maze);
		System.out.println("A* end");

		ArrayList<block> path = new ArrayList<block>();
		
		while(apath != null) {
			path.add(new block(apath.x, apath.y));
			apath = apath.parent;
		}
		
		Collections.reverse(path);
		
		for(block blocks: path) {
			System.out.print(blocks.toString());
			}
		
		System.out.println();
		print_solved_maze(path);
		
		System.out.printf("The magic is done!");
		
	}
	
	public static void print_maze() {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length;j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void print_solved_maze(ArrayList<block> path) {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length;j++) {
				if(A_star.isExists(path, i, j)) {
					System.out.print("X ");
				}
				else {
					System.out.print(maze[i][j] + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void build_maze() {
		int temp[][] = new int[m][n];
		for(int i = 0; i < m; i ++) {
			for(int j = 0; j < n; j++) {
				temp[i][j] = 0;
				for(int k = 0; k < xarray.length; k++) {
					if(i == xarray[k] && j == yarray[k]) {
						temp[i][j] = 1;
					}
				}
			}
		}
		maze = temp;
	}
	
	public static void input_maze() {
		String tempX;
		String tempY;
		try {
			File myObj = new File("maze.txt");
			Scanner myReader = new Scanner(myObj);
			m = Integer.parseInt(myReader.nextLine());
			n = Integer.parseInt(myReader.nextLine());
			tempX = myReader.nextLine();
			tempY = myReader.nextLine();
			tempX = tempX.substring(1, tempX.length() - 1);
			tempY = tempY.substring(1, tempY.length() - 1);
			
			String temp[] = tempX.split(",");
			int Xtemp[] = new int[temp.length];
			for(int i = 0;i < temp.length; i++) {
				Xtemp[i] = Integer.parseInt(temp[i]);
			}
			xarray = Xtemp;
			
			temp = tempY.split(",");
			int Ytemp[] = new int[temp.length];
			for(int i = 0;i < temp.length; i++) {
				Ytemp[i] = Integer.parseInt(temp[i]);
			}
			yarray = Ytemp;
			if(xarray.length != yarray.length) {
				System.out.println("Error, empty is not match");
				System.exit(0);
			}
			myReader.close();

			
			
		}catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
	}

}
