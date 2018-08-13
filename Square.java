
public abstract class Square {
	
	protected Board board = null;
	protected boolean hasNumber;
	protected int value, index;
	protected Row row;
    protected Column col;
    protected Box box;
    protected static boolean firstSolution;
    protected Square next;
    
    
    public Square(int value, Row row, Column col, Box box){
    	this.value = value;
    	this.row = row;
    	this.col = col;
    	this.box = box;
    }
    
    public int getValue(){
    	return value;
    }
    
    public void setBoard(Board board){
    	this.board = board;
    }
    
    public Board getBoard(){
    	return board;
    }
    
    public void setRow(Row row){
    	this.row = row;
    }
    
    public Row getRow(){
    	return row;
    }
    
    public void setColumn(Column col){
    	this.col = col;
    }
    
    public Column getColumn(){
    	return col;
    }
    
    public void setBox(Box box){
    	this.box = box;
    }
    
    public Box getBox(){
    	return box;
    }
    
    public boolean getFirstSolution(){
    	return firstSolution;
    }
        
    
    public void fillInRemainingOfBoard(){
    	
    	
    }
    
    public void assignFinalNumber(int value){
    	
    }
    
    public boolean containsValue(Square[] s){
    	for(int i = 0; i < s.length; i++){
    		Integer tmp = s[i].getValue();
    		if(tmp != null){
    			return true;
    		}
    	}
    	return false;
    }
    

}
