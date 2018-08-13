import java.util.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

public class SudokuGUI extends JFrame implements ActionListener{

	private final int sqSize = 80;
	private final int topPlace = 80;
	
	private JTextField[][] board;
	private int dimension;
	private int verticalInBox;
	private int horizontalInBox;
    private JButton nextButton; 
    private Board realBoard;

    
    private boolean first = true;
    LinkedList<int[][]> list;
    Iterator<int[][]> it;
	
	private int cntNextCall = 0;
	
	
	
	
	public SudokuGUI(int dimension, int verticalInBox, int horizontalInBox, boolean showGUI, Board realBoard) {
		this.dimension = dimension;
		this.verticalInBox = verticalInBox;
		this.horizontalInBox = horizontalInBox;
		this.realBoard = realBoard;
		
    	board = new JTextField[dimension][dimension];
    	setPreferredSize(new Dimension(dimension * sqSize, dimension  * sqSize + topPlace));
    	setTitle("Sudoku (" + dimension +" x "+ dimension + ")");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(new BorderLayout());
    	
    	JPanel buttonPanel = createButtons();
    	JPanel boardPanel = createBoard();
    	getContentPane().add(buttonPanel,BorderLayout.NORTH);
    	getContentPane().add(boardPanel,BorderLayout.CENTER);
    	
    	showInitialCondition();
    	
    	pack();
    	setVisible(showGUI);
	}
	

	public void showInitialCondition() {
	
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				if(realBoard.getTable()[i][j].getValue() != 0){
					board[i][j].setText(Integer.toString(realBoard.getTable()[i][j].getValue()));
	    		}				
			}
		}
	
	}

	public JPanel createBoard() {
		int top, left;
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(dimension,dimension));
		boardPanel.setAlignmentX(CENTER_ALIGNMENT);
		boardPanel.setAlignmentY(CENTER_ALIGNMENT);
		setPreferredSize(new Dimension(new Dimension(dimension * sqSize, 
	                                                             dimension * sqSize)));		
		for(int i = 0; i < dimension; i++) {
			/* Check if given row needs a thicker topline*/
			
			top = (i % verticalInBox == 0 && i != 0) ? 4 : 1;
			for(int j = 0; j < dimension; j++) {
			    /* Check if "theSquare" is a part of a column 
				that needs a thicker leftline*/
			    left = (j % horizontalInBox == 0 && j != 0) ? 4 : 1;

			    JTextField theSquare = new JTextField();
			    theSquare.setBorder(BorderFactory.createMatteBorder(top,left,1,1, Color.black));
			    theSquare.setHorizontalAlignment(SwingConstants.CENTER);
			    theSquare.setPreferredSize(new Dimension(sqSize, sqSize));
			    theSquare.setText("");
			    board[i][j] = theSquare;
			    boardPanel.add(theSquare);
			}
    	}
	    return boardPanel;
		
	}

	public JPanel createButtons() {
		
		JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    	
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);

    	
        return buttonPanel;
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == nextButton) {
        	
        	if (realBoard.getContainer().getSolutions().isEmpty()) {
        		nextButton.setEnabled(false);
        	} else {
        		nextSolution();
        		cntNextCall++;
        		if(cntNextCall == realBoard.getContainer().getSolutions().size()) {
            		nextButton.setEnabled(false);
            		System.out.println("Number of solutions: "+ realBoard.getContainer().getSolutionsNumber());
            	}
        	}
        }
    }

	private void nextSolution() {
		if(first){
			this.list = realBoard.getContainer().getSolutions();
			it = list.iterator();
		}
		first = false;
		if(it.hasNext()){
			int[][] tmp = it.next();
			for(int i = 0; i < tmp.length; i++){
				for(int j = 0; j < tmp.length; j++){
					board[i][j].setText(Integer.toHexString(Integer.parseInt(Integer.toString(tmp[i][j]))));
				}
			}
		}
	}

	public Board getRealBoard(){
		return this.realBoard;
	}

	public void setRealBoard(Board board){
		realBoard = board;
	}
	
	public JTextField[][] getBoard() {
		
		return this.board;
	}
	
}
