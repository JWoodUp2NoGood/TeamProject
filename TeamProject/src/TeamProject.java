import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class TeamProject extends Application
{
	public void start(Stage stage)
	{
		DisplayPane dispPane = new DisplayPane();
		BorderPane pane = new BorderPane();
		pane.setCenter(dispPane);
		
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
