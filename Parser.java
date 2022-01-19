import java.util.*;
import java.io.*;

public class Parser
{
  private Scanner inputFile;
  private int arg2;
  private String arg1;
  String rawLine;
  int lineNumber;

  public Parser(String infileName)
  {
    try
    {
      inputFile = new Scanner(new FileInputStream(infileName));
    }
    catch(IOException e)
    {
      System.out.println("Error opening file at Parser in Assembler. Terminating.");
      System.exit(0);
    }
  }

  /**
  * Reads the next command from the input and 
  * makes it the current command. Should be called 
  * only if hasMoreCommands is true.
  * Initially there is no current command.
  */
  public void advance()
  {
    if(hasMoreCommands())
    {
      rawLine = inputFile.nextLine();
      lineNumber++;
      // cleanLine(rawLine);
      // parseCommandType();
    }
  }

  // how to determine if has more commands?
  public boolean hasMoreCommands()
  {
    return inputFile.hasNextLine();
  }
}