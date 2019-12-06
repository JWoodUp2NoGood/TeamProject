import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import java.io.*;
import java.util.Scanner;

public class DisplayPane extends BorderPane
{		
	private BorderPane inputPane, outputPane, leftPane, rightPane;
	private Label inputLabel, inputError, outputLabel, outputError, previewLabel;
	private TextField inputText, outputText;
	private Button inputButton, outputButton;
	private TextArea preview;
	
	public DisplayPane()
	{
		//input label, button, textfield, and error messages
		inputPane = new BorderPane();
			
		inputLabel = new Label("Input file name:");
		inputPane.setTop(inputLabel);
		inputPane.setMargin(inputLabel, new Insets(10, 10, 0, 10));
			
		inputText = new TextField();
		inputPane.setLeft(inputText);
		inputPane.setMargin(inputText, new Insets(10, 10, 10, 10));
			
		inputButton = new Button("Format");
		inputPane.setRight(inputButton);
		inputButton.setMinWidth(60);
		inputPane.setMargin(inputButton, new Insets(10, 10, 10, 0));
			
		inputError = new Label(" ");
		inputError.setTextFill(Color.RED);
		inputPane.setBottom(inputError);
		inputPane.setMargin(inputError, new Insets(0, 10, 10, 10));

					
		//output label, button, textfield, and error messages
		outputPane = new BorderPane();
				
		outputLabel = new Label("Save as:");
		outputPane.setTop(outputLabel);
		outputPane.setMargin(outputLabel, new Insets(10, 10, 0, 10));
					
		outputText = new TextField();
		outputPane.setLeft(outputText);
		outputPane.setMargin(outputText, new Insets(10, 10, 10, 10));
					
		outputButton = new Button("Save");
		outputButton.setMinWidth(60);
		outputPane.setRight(outputButton);
		outputPane.setMargin(outputButton, new Insets(10, 10, 10, 0));
					
		outputError = new Label(" ");
		outputError.setTextFill(Color.RED);
		outputPane.setBottom(outputError);
		outputPane.setMargin(outputError, new Insets(0, 10, 400, 10));
			
		//input and output panes
		leftPane = new BorderPane();
		leftPane.setTop(inputPane);
		leftPane.setBottom(outputPane);
			
		//pane for preview
		rightPane = new BorderPane();
			
		previewLabel = new Label("Preview:");
		rightPane.setTop(previewLabel);
		rightPane.setMargin(previewLabel, new Insets(10, 0, 10, 0));
			
		preview = new TextArea("");
		preview.setEditable(false);
		preview.setMinWidth(520);
		rightPane.setCenter(preview);
		rightPane.setMargin(preview, new Insets(0, 20, 20, 0));
			
		setLeft(leftPane);
		setRight(rightPane);
		
		//for event handling:
		inputButton.setOnAction(new Handler());
		outputButton.setOnAction(new Handler());
	}
	
	private class Handler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			Object source = e.getSource();
			String output = "";
			
			if(source == inputButton) //if the format button is pressed
			{
				String line;
				String justification; //left, right, center, or title
				String spacing; //single or double
				String indent; //none, indent, or block
				String columns; //one or two
				int characters; //number of characters on the line
				
				//try to read the file
				try
				{
					File inputFile = new File(inputText.getText());
					Scanner input = new Scanner(inputFile);
					// default settings
					inputError.setText("");
					justification = "left";
					spacing = "single";
					indent = "none";
					columns = "one";
					characters = 80;
					
					//for the entire input file:
					while(input.hasNext())
					{
						//check for new flags
						line = input.nextLine();
						if(line.equals("-l")) { justification = "left"; }						
						else if(line.equals("-r")) { justification = "right"; }
						else if(line.equals("-c")) { justification = "center"; }
						else if(line.equals("-t")) { justification = "title"; }
						else if(line.equals("-s")) { spacing = "single"; }
						else if(line.equals("-d")) { spacing = "double"; }
						else if(line.equals("-i")) { indent = "indent"; 
							justification = "left"; }
						else if(line.equals("-b")) { indent = "block"; 
							justification = "left"; }
						else if(line.equals("-n")) { indent = "none"; }
						else if(line.equals("-1")) { columns = "one"; }
						else if(line.equals("-2")) { columns = "two"; }
						else if(line.equals("-e"))
						{
							output = output + "\n";
						} else {
							//format the text
							if(justification.equals("left"))
							{
								characters = line.length();
								//left justified, no indent
								if(indent.equals("none"))
								{
									if(characters <= 80)
									{
										output = line;
									}
									else
									{
										
									}
								}
								//left justified, single indent
								else if(indent.equals("indent"))
								{
									output = "     "; // 5 spaces used
									if(characters <= 75)
									{
										output = output + line;
									}
									else
									{
										
									}
								}
								//left justified, block indent
								else if(indent.equals("block"))
								{
									output = "          "; // 10 spaces used
									if(characters <= 70)
									{
										output = output + line;
									}
									else
									{
										
									}
								}
								//left justified, 2 columns
								else if(columns.equals("two"))
								{
									
								}
								//account for if double spaced
								
							} else if(justification.equals("right")) {
								int spaceNumber = 0;
								characters = line.length();
								//right justified, no indent
								if(indent.equals("none"))
								{
									if(characters <= 80)
									{
										spaceNumber = 80 - characters;
										for(int i = 0; i < spaceNumber; i++)
										{
											output += " ";
										}
										output += line;
									}
									else
									{
										
									}
								}
								//right justified, 2 columns
								else if(columns.equals("two"))
								{
									
								}
								//account for if double spaced
								
							} else if(justification.equals("center")) {
								//Note: for center justified, take the number
								//	of characters left over and divide by the
								//	number of spaces to figure out how many
								//	extra spaces you need between the words
								//	so you get 80 characters for the line
								
								characters = line.length();
								//center justified, no indent
								if(indent.equals("none"))
								{
									
								}
								//center justified, 2 columns
								else if(columns.equals("two"))
								{
									
								}
								//account for if double spaced
								//else if()
							} else if(justification.equals("title")) {
								characters = 80 - line.length();
							
								for(int i = 0; i < characters / 2; i++)
								{
									line = " " + line;
								}
								characters = characters - (characters / 2);
								for(int i = 0; i < characters; i++)
								{
									line = line + " ";
								}
								output = output + line + "\n";
								
								if(spacing.equals("double"))
								{
									output = output + "\n";
								}
							}				
						}
					}
					
					preview.setText(output);
					input.close();
				}
				//if the file is not found, display the error message
				catch(FileNotFoundException exception)
				{
					inputError.setText("File not found");
				}
			} else if(source == outputButton) { //if the save button is pressed
				String filename = outputText.getText();
				if(!filename.substring(filename.length() - 4).equals(".txt"))
				{
					outputError.setText("Invalid file type");
				} else {
					try
					{
						File inputFile = new File(filename);
						Scanner input = new Scanner(inputFile);
						
						outputError.setText("File already exists");
						
						input.close();
					}
					catch(FileNotFoundException exception)
					{
						try
						{
							File outputFile = new File(filename);
							PrintWriter pw = new PrintWriter(outputFile);
							pw.print(preview.getText());
							outputError.setText("");
						
							pw.close();
						}
						catch(FileNotFoundException exception2)
						{
							outputError.setText("Failure to save file");
						}
					}
				}
			}
			
			
		}
	}
}
