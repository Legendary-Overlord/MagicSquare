package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.MagicSquare;
import model.NoMagicSquareException;
import model.PairException;

public class MagicSquareController {
	//variables
	MagicSquare sq;
	private int order;
	private char dir;
	private int stRow;
	private int stCol;
	
	@FXML
    private BorderPane parentBorderPane;
	@FXML
    private VBox centerVbox;
    @FXML
    private TextField txtOrder;

    @FXML
    private TextField txtSRow;

    @FXML
    private TextField txtSColumn;

    @FXML
    private RadioButton noRadioButton;

    @FXML
    private ToggleGroup directionToggleGroup;

    @FXML
    private RadioButton neRadioButton;

    @FXML
    private RadioButton soRadioButton;

    @FXML
    private RadioButton seRadioButton;
    
    @FXML
    private Button buttonSubmit;
    
    public void initialize() {
    	noRadioButton.setUserData(MagicSquare.NO);
    	neRadioButton.setUserData(MagicSquare.NE);
    	soRadioButton.setUserData(MagicSquare.SO);
    	seRadioButton.setUserData(MagicSquare.SE);
    	sq=new MagicSquare("3","0","0", MagicSquare.NO);
    }
    @FXML
    void selectDirectionToggle(ActionEvent event) {
    	dir = (char) directionToggleGroup.getSelectedToggle().getUserData();
    }
    
    @FXML
    void submitButtonPressed(ActionEvent event) {
    	//Get values for GUI
    	sq.setOrder(txtOrder.getText());
    	sq.setStartingColumn(txtSColumn.getText());
    	sq.setStartingRow(txtSRow.getText());
    	sq.setDir(dir);
    	centerVbox.getChildren().clear();
    	int[][] magic;
		try {
			magic = sq.generateMatrix();
			GridPane matrix = new GridPane();
	    	for (int i=0;i<magic.length;i++) {
	    		for (int j=0;j<magic[0].length;j++) {
	    			Label tf = new Label();
	    			tf.setText(Integer.toString(magic[i][j]));
	    			matrix.setRowIndex(tf,i);
	    			matrix.setColumnIndex(tf, j);
	    			matrix.getChildren().add(tf);
	    		}
	    	}
	    	matrix.setPadding(new Insets(10,10,10,10));
	    	matrix.setGridLinesVisible(true);
	    	centerVbox.getChildren().add(matrix);
	    	Label l1 = new Label();
	    	l1.setText(sq.validate(magic));
	    	centerVbox.getChildren().add(l1);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			Alert alert=new Alert(AlertType.ERROR,"One of the starting positions is negative.");
			alert.showAndWait();
			e.printStackTrace();
		} catch (NegativeArraySizeException e) {
			// TODO Auto-generated catch block
			Alert alert=new Alert(AlertType.ERROR,"The order of the magic square has to be positive!");
			alert.showAndWait();
			e.printStackTrace();
		} catch (PairException e) {
			// TODO Auto-generated catch block
			Alert alert=new Alert(AlertType.ERROR,"The order of the magic square has to be impair!");
			alert.showAndWait();
			e.printStackTrace();
		} catch(ArithmeticException e) {
			Alert alert=new Alert(AlertType.ERROR,"Woops! Something happened.");
			alert.showAndWait();
			e.printStackTrace();
		} catch(NoMagicSquareException e){
			Alert alert=new Alert(AlertType.ERROR,"The given data cannot create a MagicSquare!");
			alert.showAndWait();
			e.printStackTrace();
		}catch(NumberFormatException e){
			e.printStackTrace();
		}	finally {
		
			Alert ok =new Alert(AlertType.CONFIRMATION,"Complete.",ButtonType.OK);
			ok.showAndWait();
		}
    	
    }

}

