import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;


/**
 * This is a program to solve Sudoku Puzzle 
 * @author Stein Michael
 * @author Christian Tryti
 * @author Dorna Misaghian
 *
 */
public class SudokuPuzzleSolver {

	/**
	 * @param args
	 */
	
	private ReadFromFile rff;
	private Board realBoard;
	private JFileChooser fc = new JFileChooser();
	private File file;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("txt & rtf text files","txt","gif");

	public void createInstance() {
		int returnVal = 0;
		fc.setFileFilter(filter);
        returnVal = fc.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
           file =  fc.getSelectedFile();
           rff = new ReadFromFile();
           
           realBoard = rff.readFromFile(file,true);
           realBoard.getFirst().fillInRemainingOfBoard();
           
           new SudokuGUI(rff.dimension, rff.height, rff.width, true, realBoard);
        }	
	}
	    
	public void createInstance(String in) {
        File inFile = new File(in);
        rff = new ReadFromFile();
        realBoard = rff.readFromFile(inFile, true);
        realBoard.getFirst().fillInRemainingOfBoard();
       
        new SudokuGUI(rff.dimension, rff.height, rff.width, true, realBoard);
    }
	
	public void createInstance(String in, String out) {
        File inFile = new File(in);
        File outFile = new File(out);
        rff = new ReadFromFile();
        
        realBoard = rff.readFromFile(inFile, true);     
        realBoard.getFirst().fillInRemainingOfBoard();
        
        new SudokuGUI(rff.dimension, rff.height, rff.width, true, realBoard);
        
        realBoard.writeSolutionToFile(outFile);
        System.exit(0);
    }
	
	
	public static void main(String[] args) {
		String in = "";
        String out = "";
        int i = args.length;
        
        SudokuPuzzleSolver solver = new SudokuPuzzleSolver();
        
        if(i == 0) {
        	solver.createInstance();
        } 
        else if(i == 1) {
            in = args[0];
            solver.createInstance(in);
        } 
        else if(i == 2) {
            in = args[0];
            out = args[1];
            solver.createInstance(in, out);
        } 
        else {
            System.out.println("Error: Parameter fault");
        }

	}
		

}
