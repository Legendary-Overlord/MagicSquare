package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MagicSquare {
	public static final char NO = 'A';
	public static final char NE = 'B';
	public static final char SO = 'C';
	public static final char SE = 'D';
	int order;
	int startingRow;
	int startingColumn;
	char dir;
	
	public MagicSquare(String order, String startingRow, String startingColumn, char dir) {
		
		try {
			this.order = Integer.parseInt(order);
			this.startingRow = Integer.parseInt(startingRow);
			this.startingColumn = Integer.parseInt(startingColumn);
		}catch(NumberFormatException e) {
			Alert alert=new Alert(AlertType.ERROR,"Only numbers.");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		this.dir = dir;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(String string) {
		try {
			this.order = Integer.parseInt(string);
		}catch(NumberFormatException e) {
			Alert alert=new Alert(AlertType.ERROR,"Only numbers.");
			alert.showAndWait();
			e.printStackTrace();
		}
		
	}

	public int getStartingRow() {
		return startingRow;
	}

	public void setStartingRow(String startingRow) {
		try {
			this.startingRow = Integer.parseInt(startingRow);
		}catch (NumberFormatException e) {
			Alert alert=new Alert(AlertType.ERROR,"Only numbers.");
			alert.showAndWait();
			e.printStackTrace();
		}	
	}

	public int getStartingColumn() {
		return startingColumn;
	}

	public void setStartingColumn(String string) {
		try {
			this.startingColumn = Integer.parseInt(string);
		}catch(NumberFormatException e) {
			Alert alert=new Alert(AlertType.ERROR,"Only numbers.");
			alert.showAndWait();
			e.printStackTrace();
		}
		
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}
	public int[][] generateMatrix() throws PairException, NegativeArraySizeException, ArrayIndexOutOfBoundsException{
		if (order%2==0)
			throw new PairException();
		if (order<0)
			throw new NegativeArraySizeException();
		
		int[][] sol = new int[getOrder()][getOrder()];
		int num=0;
		int count=0;
		int di=0;int dj=0;boolean loop=true;
		for (int i=startingRow,j=startingColumn;loop;i+=di,j+=dj) {
			//
			num+=1;
			count++;
			//given the direction, perform
			switch(dir) {
			case NO:
				if (i==order)
					i=0;
				if(i==-1)
					i=order-1;
				if (j==order) 
					j=0;
				if(j==-1)
					j=order-1;
				if (count<order) {
					di=1;dj=-1;
				} else if (count==order){
					count=0;
					di=-1;
					dj=0;
				}
				break;
			case NE:
				if (i==order)
					i=0;
				if(i==-1)
					i=order-1;
				if (j==order) 
					j=0;
				if(j==-1)
					j=order-1;
				if (count<order) {
					di=1;dj=1;
				} else if (count==order){
					count=0;
					di=-1;
					dj=0;
				}
				break;
			case SO:
				if (i==order)
					i=0;
				if(i==-1)
					i=order-1;
				if (j==order) 
					j=0;
				if(j==-1)
					j=order-1;
				if (count<order) {
					di=-1;dj=-1;
				} else if (count==order){
					count=0;
					di=1;
					dj=0;
				}
				break;
			case SE:
				if (i==order)
					i=0;
				if(i==-1)
					i=order-1;
				if (j==order) 
					j=0;
				if(j==-1)
					j=order-1;
				if (count<order) {
					di=-1;dj=1;
				} else if (count==order){
					count=0;
					di=1;
					dj=0;
				}
				break;
			}
			if(sol[i][j]==0) {
				sol[i][j]=num;
			}else {
				loop=false;
			}
		}
		return sol;
		
	}
	public int generateMagicNumber(){
		return (order*((order*order)+1))/2;
	}
	
	public String validate(int [][] sol) throws PairException, ArithmeticException, ArrayIndexOutOfBoundsException, NoMagicSquareException{
		String msj = "";
		if(order%2==0)
			throw new PairException();
		//Actual Magic Square Validation
		boolean valid=true;
		int magicNumber=generateMagicNumber();
		for (int i=0;i<order;i++) {
			int sum=0;
			for (int j=0;j<order;j++) {
				sum+=sol[i][j];
			}
			if (magicNumber!=sum)
				valid=false;
		}
		for (int i=0;i<order;i++) {
			int sum=0;
			for (int j=0;j<order;j++) {
				sum+=sol[j][i];
			}
			if (magicNumber!=sum)
				valid=false;
		}
		int sum=0;
		for (int i=0,j=0;i<order&&j<order;i++,j++) {
			sum+=sol[i][j];
			if (i==(order-1)) {
				if(magicNumber!=sum)
					valid=false;
			}
		}
		sum=0;
		for (int i=0,j=order-1;i<order;i++,j--) {
				sum+=sol[i][j];
				if (i==(order-1)) {
					if(magicNumber!=sum)
						valid=false;
				}		
		}
		if(valid) {
			msj="Magic Square is valid! Magic Number:"+magicNumber;
		}else {
			msj="Magic Square is invalid!";
			throw new NoMagicSquareException();
		}
		return msj;
	}
	

}
