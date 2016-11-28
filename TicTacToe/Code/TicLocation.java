
public class TicLocation {

	
	private int row;
	private int column;
	
	///************************************************************************/
	
	public TicLocation(int row, int column){
		this.row=row;
		this.column=column;
	}
	
	public int getRow(){		
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public String toString(){
		return"("+getRow()+","+getColumn()+")";
	}
	
	public static TicLocation create(int row, int column){		
		return new TicLocation(row, column);		
	}	
}
