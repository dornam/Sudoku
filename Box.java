
public class Box extends CollectionBoard {
		

		public int width, height;
		public int indexX;
		public int indexY;
		private int i = 0;
		
		public Box(int dimension, int height, int width) { 
	        this.dimension = dimension;
	        
	        this.height = height;
	        this.width = width;
	        s = new Square[width * height];
	    }	
		
		public void addToStructure(Square sq) {
	        s[i] = sq;
	        i++;
	    }
		
		public boolean checkPossiblity(int num){
			for(int i = 0; i < s.length; i ++){
				if(s[i].getValue() == num){
					return false;
				}
			}
			return true;
		}
		
		public int getIndexX(){
			return indexX;
		}
		
		public int getIndexY(){
			return indexY;
		}
		
}
