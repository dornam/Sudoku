
public class Row extends CollectionBoard {
	

	public Row(int size){
		this.dimension = size;
		s = new Square[dimension];
		
	}
	
	public boolean checkPossiblity(int num){
		
		for(int i = 0; i < s.length; i ++){
			//System.out.println(s[i]);
			if(s[i].value == num){
				return false;
			}
		}
		return true;
	}
}
