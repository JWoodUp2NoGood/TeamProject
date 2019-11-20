import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class TeamProject extends Application
{
	public void start(Stage stage)
	{
		//input label, button, textfield, and error messages
		BorderPane inputPane = new BorderPane();
		
		Label inputLabel = new Label("Input file name:");
		inputPane.setTop(inputLabel);
		inputPane.setMargin(inputLabel, new Insets(10, 10, 0, 10));
		
		TextField inputText = new TextField();
		inputPane.setLeft(inputText);
		inputPane.setMargin(inputText, new Insets(10, 10, 10, 10));
		
		Button inputButton = new Button("Format");
		inputPane.setRight(inputButton);
		inputButton.setMinWidth(60);
		inputPane.setMargin(inputButton, new Insets(10, 10, 10, 0));
		
		Label inputError = new Label("error messages for input");
		inputError.setTextFill(Color.RED);
		inputPane.setBottom(inputError);
		inputPane.setMargin(inputError, new Insets(0, 10, 10, 10));

				
		//output label, button, textfield, and error messages
		BorderPane outputPane = new BorderPane();
				
		Label outputLabel = new Label("Save as:");
		outputPane.setTop(outputLabel);
		outputPane.setMargin(outputLabel, new Insets(10, 10, 0, 10));
				
		TextField outputText = new TextField();
		outputPane.setLeft(outputText);
		outputPane.setMargin(outputText, new Insets(10, 10, 10, 10));
				
		Button outputButton = new Button("Save");
		outputButton.setMinWidth(60);
		outputPane.setRight(outputButton);
		outputPane.setMargin(outputButton, new Insets(10, 10, 10, 0));
				
		Label outputError = new Label("error messages for output");
		outputError.setTextFill(Color.RED);
		outputPane.setBottom(outputError);
		outputPane.setMargin(outputError, new Insets(0, 10, 400, 10));
		
		//input and output panes
		BorderPane leftPane = new BorderPane();
		leftPane.setTop(inputPane);
		leftPane.setBottom(outputPane);
		
		//pane for preview
		BorderPane rightPane = new BorderPane();
		
		Label previewLabel = new Label("Preview:");
		rightPane.setTop(previewLabel);
		rightPane.setMargin(previewLabel, new Insets(10, 0, 10, 0));
		
		TextArea preview = new TextArea("123456789 123456789 123456789 123456789 123456789 123456789 123456789 1234567890");
		preview.setEditable(false);
		preview.setMinWidth(520);
		rightPane.setCenter(preview);
		rightPane.setMargin(preview, new Insets(0, 20, 20, 0));
		
		//pane for everything
		BorderPane pane = new BorderPane();
		pane.setLeft(leftPane);
		pane.setRight(rightPane);
		
		Scene scene = new Scene(pane, 900, 600);

		stage.setTitle("TeamProject");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args)
	{
		Application.launch(args);  
	}
}
