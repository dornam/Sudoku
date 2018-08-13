
public class FilledSquare extends Square {
	
	
	public FilledSquare(int value, Row row, Column col, Box box) {
		super(value, row, col, box);
		hasNumber = true;
		
	}
	
	public void fillInRemainingOfBoard(){
		
		if(board.nextSquare(this) == null){
			board.saveSolution();
		}
		else{
			board.nextSquare(this).fillInRemainingOfBoard();
		}
		
		
	}
	
	public void assignFinalNumber(int value){

    	this.getRow().getSquare()[getColumn().index].value = value;

    	this.getColumn().getSquare()[getRow().index].value = value;

    	this.getBox().getSquare()[((getRow().index % getBox().height) * getBox().width) + (getColumn().index % getBox().width)].value = value;

	}
	

	
}
