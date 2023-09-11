import java.util.Random;
import java.util.Scanner;


public class Main {

	private static final int MAX_VAL = 11;
	
	public static void main(String[] args) {
		int [][] a,b;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter dimention for two Matrix nXm and mXp:");
		System.out.print("n:");
		int n = scan.nextInt();
		System.out.print("m:");
		int m = scan.nextInt();
		System.out.print("p:");
		int p = scan.nextInt();
		a=createMat(n,m);
		b=createMat(m,p);
		printMat(a);
		System.out.print("\n");
		printMat(b);
		Monitor mon = new Monitor(a,b);
		Calculator  c[][] = new Calculator[n][p];
		for (int i=0; i<n;i++)
			for (int j=0;j<p;j++) {
				c[i][j] = new Calculator(mon, i, j);
				c[i][j].start();
			}
	}
	
	public static int[][] createMat(int n, int m){
		
		Random r = new Random();
		int [][] mat = new int[n][m];
		for (int i =0; i<n; i++)
			for (int j=0; j<m;j++) 
				mat[i][j]=r.nextInt(MAX_VAL);
		return mat;
	}
	public static void printMat(int [][] mat) {
		for (int i =0; i< mat.length; i++) {
			for (int j=0; j<mat[0].length;j++)
				System.out.print(mat[i][j]+"  ");
			System.out.print("\n");
		}
	}
	
}
