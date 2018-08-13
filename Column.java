
public class Column extends CollectionBoard{
	

	public Column(int size){
		this.dimension = size;
		s = new Square[dimension];
		
	}
	
	
	public boolean checkPossiblity(int num){
		for(int i = 0; i < s.length; i ++){
			if(s[i].value == num){
				return false;
			}
		}
		return true;
	}

}
