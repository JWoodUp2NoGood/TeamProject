// Names: Aashik Dhilipkumar, Khoa Nguyen, Pranav Suresh, Jessica Wood
// CSE 360
//Team Project

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
				
				int charCount; //number of characters on the line
				
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
								int charPerLine; // number of characters per line limit
								int currentLineSize;
								charCount = line.length();
								
								//left justified, no indent, single spaced, one column
								if(indent.equals("none") && spacing.equals("single") && columns.equals("one"))
								{
									currentLineSize = currentLineSize(line);
									// second 80 is character line size without additional spacing added
									output += left_SingleSpaced_oneColumn(line, "", 80, 80, currentLineSize);
								}
								//left justified, single indent, single spaced, one column
								else if(indent.equals("indent") && spacing.equals("single") && columns.equals("one"))
								{
									currentLineSize = currentLineSize(line);
									output += left_SingleSpaced_oneColumn(line, "     ", 80, 75, currentLineSize);
								}
								//left justified, block indent, single spaced, one column
								else if(indent.equals("block") && spacing.equals("single") && columns.equals("one"))
								{
									currentLineSize = currentLineSize(line);
									output += left_SingleSpaced_oneColumn(line, "          ", 80, 70, currentLineSize);
								}
								//left justified, 2 columns
								else if(columns.equals("two") )
								{
									
								}
								//account for if double spaced
								
								// left justified, no indent, double spaced, one column
								if(indent.equals("none") && spacing.equals("double") && columns.equals("one"))
								{
									currentLineSize = currentLineSize(line);
									output += left_DoubleSpaced_oneColumn(line, "", 80, 80, currentLineSize);
								}
								//left justified, double indent, single spaced, one column
								else if(indent.equals("indent") && spacing.equals("double") && columns.equals("one"))
								{
									currentLineSize = currentLineSize(line);
									output += left_DoubleSpaced_oneColumn(line, "     ", 80, 75, currentLineSize);
								}
								//left justified, block indent, single spaced, one column
								else if(indent.equals("block") && spacing.equals("single") && columns.equals("one"))
								{
									currentLineSize = currentLineSize(line);
									output += left_DoubleSpaced_oneColumn(line, "          ", 80, 70, currentLineSize);
								}
								
							} else if(justification.equals("right")) {
								int spaceNumber = 0;
								int charPerLine; // number of characters per line limit
								charCount = line.length();
								//right justified, no indent
								if(indent.equals("none") && spacing.equals("single") && columns.equals("one"))
								{
									charPerLine = 80;
									
									if(charCount <= charPerLine)
									{
										spaceNumber = charPerLine - charCount;
										
										// inserts spaceNumber of spaces before inserting in line
										for(int i = 0; i < spaceNumber; i++)
										{
											output += " ";
										}
										output += line;
									}
									else
									{
										int loopNumber = 0;
										int index = 0; // number of positions to subtract from the line
										int breakPoint = 0; // index of a space
										int nextStartingPoint = 0;
										
										while(nextStartingPoint + charPerLine < charCount)
										{
											if(breakPoint == 0)
											{
												while(line.charAt((charPerLine - 1) * (loopNumber + 1) - index) != ' ')
												{ 
													index += 1;
												}
												breakPoint = (charPerLine - 1) * (loopNumber + 1) - index;
											}
											else
											{
												while(line.charAt(nextStartingPoint + charPerLine) - index != ' ')
												{
													index += 1;
												}
												breakPoint = nextStartingPoint + charPerLine - index;
											}
											
											for(int j = nextStartingPoint; j < breakPoint; j++)
											{
												output += line.charAt(j);
											}
											output += "\n";
											index = 0;
											nextStartingPoint = breakPoint + 1;
											loopNumber += 1;
										}
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
		
		private String left_SingleSpaced_oneColumn(String line, String intro, int lineLimit, int lineCharLength, int currentLineSize)
		{
			String output = "";
			int charPerLine = lineCharLength;
			int charCount = line.length();
			
			// if line can fit in the current line
			if(charCount + currentLineSize <= charPerLine)
			{
				output += line + " ";
			}
			else // if line can not fit in the current line
			{
				int loopNumber = 0;
				int index = 0; // number of positions to subtract from the line
				int breakPoint = 0; // index of a space
				int nextStartingPoint = 0;
				int remainingSpots = lineLimit - currentLineSize;
				
				// initial check if a perfect fit can be entered into the current line
				if(line.charAt(remainingSpots) == ' ')
				{
					for(int i = 0; i < remainingSpots; i ++)
					{
						output += line.charAt(i);
					}
					output += intro;
					nextStartingPoint = remainingSpots + 1;
					currentLineSize = 0;
					remainingSpots = 80;
				}
				else
				{
					int counter = 0;
					//while(currentLineSize != 0)
					//{
						for(int i = 0; i < remainingSpots; i++)
						{
							if(line.charAt(i) == ' ')
							{
								counter = i;
							}
						}
					//}
					if(counter != 0)
					{
						for(int i = 0; i < counter; i++)
						{
							output += line.charAt(i);
						}
						output += intro;
						nextStartingPoint = counter + 1;
						currentLineSize = 0;
						remainingSpots = 80;
					}
					else
					{
						output += "\n" + intro;
					}
					
					// if there are more parts to the line
					while(nextStartingPoint + charPerLine < charCount)
					{
						if(breakPoint == 0)
						{
							while(line.charAt((charPerLine - 1) * (loopNumber + 1) - index) != ' ')
							{ 
								index += 1;
							}
							breakPoint = (charPerLine - 1) * (loopNumber + 1) - index;
						}
						else
						{
							while(line.charAt(nextStartingPoint + charPerLine) - index != ' ')
							{
								index += 1;
							}
							breakPoint = nextStartingPoint + charPerLine - index;
						}
						
						for(int j = nextStartingPoint; j < breakPoint; j++)
						{
							output += line.charAt(j);
						}
						output += "\n";
						index = 0;
						nextStartingPoint = breakPoint + 1;
						loopNumber += 1;
					}
					
				}
			}
			return output;
		}
		private String left_DoubleSpaced_oneColumn(String line, String intro, int lineLimit, int lineCharLength, int currentLineSize)
		{
			String output = "";
			int charPerLine = lineCharLength;
			int charCount = line.length();
			
			// if line can fit in the current line
			if(charCount + currentLineSize <= charPerLine)
			{
				output += line + " ";
			}
			else // if line can not fit in the current line
			{
				int loopNumber = 0;
				int index = 0; // number of positions to subtract from the line
				int breakPoint = 0; // index of a space
				int nextStartingPoint = 0;
				int remainingSpots = lineLimit - currentLineSize;
				
				// initial check if a perfect fit can be entered into the current line
				if(line.charAt(remainingSpots) == ' ')
				{
					for(int i = 0; i < remainingSpots; i ++)
					{
						output += line.charAt(i);
					}
					output += intro;
					nextStartingPoint = remainingSpots + 1;
					currentLineSize = 0;
					remainingSpots = 80;
				}
				else
				{
					int counter = 0;
					//while(currentLineSize != 0)
					//{
						for(int i = 0; i < remainingSpots; i++)
						{
							if(line.charAt(i) == ' ')
							{
								counter = i;
							}
						}
					//}
					if(counter != 0)
					{
						for(int i = 0; i < counter; i++)
						{
							output += line.charAt(i);
						}
						output += intro;
						nextStartingPoint = counter + 1;
						currentLineSize = 0;
						remainingSpots = 80;
					}
					else
					{
						output += "\n\n" + intro;
					}
					
					// if there are more parts to the line
					while(nextStartingPoint + charPerLine < charCount)
					{
						if(breakPoint == 0)
						{
							while(line.charAt((charPerLine - 1) * (loopNumber + 1) - index) != ' ')
							{ 
								index += 1;
							}
							breakPoint = (charPerLine - 1) * (loopNumber + 1) - index;
						}
						else
						{
							while(line.charAt(nextStartingPoint + charPerLine) - index != ' ')
							{
								index += 1;
							}
							breakPoint = nextStartingPoint + charPerLine - index;
						}
						
						for(int j = nextStartingPoint; j < breakPoint; j++)
						{
							output += line.charAt(j);
						}
						output += "\n\n";
						index = 0;
						nextStartingPoint = breakPoint + 1;
						loopNumber += 1;
					}
					
				}
			}
			return output;
		}
		
		// returns the last index of a new line
		int currentLineSize(String line)
		{
			int size = 0;
			
			for(int i = line.lastIndexOf('\n'); i < line.length(); i++)
			{
				size += 1;
			}
			return size;
		}
	}
}
