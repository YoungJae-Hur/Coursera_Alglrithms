import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] grid;
    private WeightedQuickUnionUF qu;
    private WeightedQuickUnionUF qu2;
    private int lm; // length of matrix
    private int numOpenSites;

    // creates n-by-n grid, with all sites initially blocked. 0 = closed, 1= open
    private Percolation(int n) {
        grid = new boolean [n][n];
        if (n <= 0) throw new IllegalArgumentException("The input n <= 0");
        qu = new WeightedQuickUnionUF(n*n + 1);
        lm = n;
        System.out.println(qu);
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }*/
        //System.out.println(Arrays.deepToString(grid));
    }

    // opens the site (row, col) if it is not open already. 0 = closed, 1 = open
    public void open(int row, int col) {

        //if (!grid[row][col])
          //  grid[row][col] = true;
        if (checkValidIndex(row, col)){
            if(!isOpen(row, col)){
                grid[row][col] = true;
            }
            if(row == 1){
                qu.union(col-1, lm*lm);
            }
        }else{
            throw new IndexOutOfBoundsException("error1");
        }
    }
    // checks the spot whether the index is valid or not
    private boolean checkValidIndex(int row, int col) {
        if(row < 1 || row > lm || col < 1 || col > lm) return false;
        return true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (checkValidIndex(row, col)){
            return grid[col][row];
        }else{
            throw new IndexOutOfBoundsException("error2");
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(checkValidIndex(row,col)){
            qu2.connected(row*lm+col,0);
        }else{
            throw new IndexOutOfBoundsException("error3");
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qu.connected(lm*lm, lm*lm+1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
    }
}
