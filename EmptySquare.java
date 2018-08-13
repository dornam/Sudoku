
public class EmptySquare extends Square {
	
	
	
	public EmptySquare(int value, Row row, Column col, Box box) throws Exception {
		
		super(value, row, col, box);
		this.hasNumber = false;
	}
	
	public void fillInRemainingOfBoard() throws NullPointerException{
		
		
			for(int i = 1; i <= row.s.length; i++){
				if(this.row.checkPossiblity(i) && this.col.checkPossiblity(i) && this.box.checkPossiblity(i)){
					this.assignFinalNumber(i);
					if(board.nextSquare(this) == null){
						board.saveSolution();
					}
					else{
						board.nextSquare(this).fillInRemainingOfBoard();
					}
					remove();
				}	
		    }

    }
	
	public void remove() {
		this.value = 0;
		getRow().s[getColumn().index].value = 0;
    	this.getColumn().s[getRow().index].value = 0;
    	this.getBox().s[((getRow().index % getBox().height) * getBox().width) + (getColumn().index % getBox().width)].value = 0;
		
		
	}

	public void assignFinalNumber(int value){
    	this.value = value;
    	getRow().s[getColumn().index].value = value;
    	this.getColumn().s[getRow().index].value = value;
    	this.getBox().s[((getRow().index % getBox().height) * getBox().width) + (getColumn().index % getBox().width)].value = value;
    }

}
