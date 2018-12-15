/*
 * Find string from Given 2D string,  you can find next character from current point in four directions,  left,right, up and down
 * The character in 2D string can not be used only once
 */
public class FindString2D {

	public static void print2D(int [][] int2D) {
		for(int i=0; i<int2D.length; i++) {
			for(int j=0; j<int2D[i].length; j++) {
				System.out.print(int2D[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static boolean findStringFrom2DStartWith(char[][] str2D, String pattern, int[][] foot, int row, int col, int index) {
		int rows = str2D.length;
		int cols = str2D[0].length;
		
		
		if(row >= rows || col >= cols || row < 0 || col < 0)
			return false;
		
		if(index >= pattern.length()) {
			print2D(foot);
			System.out.println("Found string and exit!");
			return true;
		}

		if(foot[row][col] == 1)
			return false;
		
		boolean bFound = false;
		if(str2D[row][col] == pattern.charAt(index) ) {
			System.out.println("row:" + row + ", col:" + col + "=" + str2D[row][col]);
			foot[row][col] = 1;
			//up, down, left, right
			bFound = findStringFrom2DStartWith(str2D, pattern, foot, row-1, col, index+1);
			
			if(!bFound)
				bFound = findStringFrom2DStartWith(str2D, pattern, foot, row+1, col, index + 1);
			
			if(!bFound)
				bFound = findStringFrom2DStartWith(str2D, pattern, foot, row, col-1, index + 1);
			
			if(!bFound)
				bFound = findStringFrom2DStartWith(str2D, pattern, foot, row, col+1, index + 1);
		}
		foot[row][col] = 0;
		return bFound;
	}
	public static boolean findStringFrom2D(char [][] str2D, String pattern) {
		int [][] footPrint = new int[str2D.length][str2D[0].length];
		//init footPrint		
		
		for(int i=0; i<str2D.length; i++) {
			for(int j=0; j<str2D[i].length; j++) {
				footPrint[i][j] = 0;
			}
		}
		for(int i=0; i<str2D.length; i++) {
			for(int j=0; j<str2D[i].length; j++) {
				boolean b = findStringFrom2DStartWith(str2D, pattern, footPrint, i, j, 0);
				if(b == true) {
					//print2D(footPrint);
					return true;
				}
			}
		}		
			
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char str2D[][]= {
				{'a','b','c','r','e'},
				{'f','g','h','i','j'},
				{'k','l','c','n','o'},
				{'v','q','r','s','t'},
				{'w','v','u','x','y'}
		};
		//find string vursnihcr from 2d string
		String pattern = "vursnihcre";
		
		boolean bFind = findStringFrom2D(str2D, pattern);


	}
}
