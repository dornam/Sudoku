import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JTextField;


public class Board {

	private Square[][] table;
    private Row[] rows;
    private Column[] cols;
    private Box[][] boxes;
    private Square nextSquare;
    private int dimension, boxH, boxW;
	private SudokuContainer sudokucont;
	public SudokuGUI gui;
    
    public Board(int dimension, int boxH, int boxW, boolean showGUI) throws Exception{
    	
    	if((dimension % boxW) != 0 || (dimension % boxH) != 0 || boxW >= dimension || boxH >= dimension || (boxH * boxW) != dimension){

    		throw new Exception();
    	}
    	
    	this.dimension = dimension;
    	this.boxH = boxH;
    	this.boxW = boxW;
    	this.nextSquare = null;
    	
    	
    	sudokucont = new SudokuContainer();
    	
    	rows = new Row[dimension];
    	cols = new Column[dimension];
    	boxes = new Box[boxW][boxH];
    	table = new Square[dimension][dimension];
    	
    	
    	for(int i = 0; i < dimension; i++){
    		for(int j = 0; j < dimension; j++){
    			
    			rows[i] = new Row(dimension);
    			rows[i].index = i;
    			cols[j] = new Column(dimension);
    			cols[j].index = j;
    			boxes[i/boxH][j/boxW] = new Box(dimension, boxH, boxW);
    			boxes[i/boxH][j/boxW].indexX = i/boxH;
    			boxes[i/boxH][j/boxW].indexY = j/boxW;
    			
    			

    		}
    	}
    }
    
    public Square[][] getTable(){
    	return table;
    }
	
	public void setTable(Square [][] s){
		table = s;
	}
	
	public Square getFirst(){
		return table[0][0];
	}
	
	public SudokuContainer getContainer(){
		return sudokucont;
	}
	
	public int getBoxW(){
		return boxW;
	}
	
	public int getBoxH(){
		return boxH;
	}
		
	public int getDimension(){
		return dimension;
	}
	
	public Row[] getRows() {
		return rows;
	}

	public void setRows(Row[] rows) {
		this.rows = rows;
	}

	public Column[] getCols() {
		return cols;
	}

	public void setCols(Column[] cols) {
		this.cols = cols;
	}

	public Box[][] getBoxes() {
		return boxes;
	}

	public void setBoxes(Box[][] boxes) {
		this.boxes = boxes;
	}
	
	public void saveSolution() {
		int[][] savesol = new int[dimension][dimension];
		
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				savesol[i][j] = table[i][j].getValue();
			}
		}
		
		sudokucont.addSolution(savesol);
	}
	
	public Square nextSquare(Square currentSquare) {
		
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table.length; j++){
				
				Square sq = table[i][j];
				
				if(sq == currentSquare){
					
					if(i < table.length - 1 && j == table.length - 1){
						nextSquare = table[i+1][0];
					}
					else if(i == table.length - 1 && j == table.length - 1){
						nextSquare = null;
					}
					else{
						nextSquare = table[i][j+1];
					}
				}
			}
		}
		return nextSquare;
		
	}
	
public void writeSolutionToFile(File file){
		
		int counter = 1;
		LinkedList<int[][]> save = sudokucont.getSolutions();
		Iterator<int[][]> it = save.iterator();
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			while(it.hasNext() && counter < 501){
				int[][] sol = it.next();
				bw.write("Solution NO. " +counter + ": ");
				for(int i = 0; i < sol.length; i++){
					for(int j = 0; j < sol.length; j++){
						bw.write(sol[i][j]);
						if(j == sol.length-1){
							bw.write("// ");
						}     
					}
				}
				
				bw.newLine();
                counter++;
			}
			bw.close();
		}
		catch(Exception e){
			
		}
		
	}
	
}
