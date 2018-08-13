import java.io.*;


public class ReadFromFile {
	
	public int dimension = 0;
	public int height = 0;
	public int width = 0;
	private Board board= null;
	
	
	public Board readFromFile(File file, boolean showGUI){
		
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			dimension = Integer.parseInt(br.readLine());
			height= Integer.parseInt(br.readLine());
			width = Integer.parseInt(br.readLine());
			
			board = new Board(dimension, height, width, showGUI);
			
			for(int i = 0; i < dimension; i++){	
				String line = br.readLine();
				for(int j = 0; j < line.length(); j++){

					if(line.charAt(j) != '.'){		
						
						board.getTable()[i][j] = new FilledSquare(toInt(line.charAt(j)), board.getRows()[i], board.getCols()[j], board.getBoxes()[i/height][j/width]);
					}
					else{
						board.getTable()[i][j] = new EmptySquare(0, board.getRows()[i], board.getCols()[j], board.getBoxes()[i/height][j/width]);
					}
					
				}
			}
			
			for(int i = 0; i < dimension; i++){
				for(int j = 0; j < dimension; j++){
					
						this.board.getTable()[i][j].setBoard(board);
						board.getRows()[i].getSquare()[j] = board.getTable()[i][j];
						board.getCols()[i].getSquare()[j] = board.getTable()[j][i];
						board.getBoxes()[i/height][j/width].addToStructure(board.getTable()[i][j]);
						
				}
			}
			
			for(int i = 0; i < dimension; i++){
				for(int j = 0; j < dimension; j++){
					if(board.getTable()[i][j].getValue() != 0){
						
						board.getTable()[i][j].assignFinalNumber(board.getTable()[i][j].getValue());
					}
				}
			}
			
			br.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return board;
	}

	private static int toInt(char c) {
		
		if (c < '0' || (c > 'Z' && c < 'a') || c > 'z'){ 
			return 0;
		}
		if (c >= '0' && c <= '9'){
			return c - '0';
		}
		return (c - 'A') % 32 + 10;
		
	}

}
