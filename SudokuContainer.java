import java.util.*;


public class SudokuContainer {
	
	private int solutionnumber = 0;
	private LinkedList<int[][]> sudokulist = new LinkedList<int[][]>();
	private Iterator <int[][]> it = sudokulist.iterator();
	
	
	
	public void addSolution(int[][] sol){
		
		if(sol == null){
			throw new NullPointerException();
		}
		
		if(sudokulist.size() < 500){
			sudokulist.add(sol);
		}
		solutionnumber ++;		
	}
	
	public LinkedList<int[][]> getSolutions(){
		return sudokulist;
	}
	
	public int[][] getNextSolution(){
		int[][] tmp = null;
		
		if(it.hasNext()){
			tmp = it.next();
			return tmp;
		}
		else{
			throw new NullPointerException();
		}
		
	}
	
	public int getSolutionsNumber(){
		
		return solutionnumber;
	}
	
	public boolean removeElement(int[][] sol){

		if(sudokulist.contains(sol)){
			sudokulist.remove(sol);
		}
		return false;
	}
		
}
