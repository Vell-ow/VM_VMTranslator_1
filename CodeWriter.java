import java.io.*;
import java.util.*;

public class CodeWriter
{
  String fileName;
  PrintWriter outputFile;
  private int jumpNum = 1;
  private final String temp = "temp" + jumpNum;


  public void close()
  {
    outputFile.write("@END");
    outputFile.println();    
    outputFile.write("0;JMP");
    outputFile.println();
    outputFile.close();
  }

  public CodeWriter(String fileName)
  {
    try
    {
      this.fileName = fileName;
      outputFile = new PrintWriter(fileName);
    }
    catch(FileNotFoundException fnfexc)
    {
      System.err.println("File not found. Throwing exception and terminating to prevent fatal error. ;(");
      System.exit(0);
    }
  }

  public void writeArithmetic(String command)
  {
    if(command.equalsIgnoreCase("add"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1");
      outputFile.println();
      outputFile.write("D=M");
      outputFile.println();
      outputFile.write("A=A-1");
      outputFile.println();
      outputFile.write("M=M+D");
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("sub"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1");
      outputFile.println();
      outputFile.write("D=M");
      outputFile.println();
      outputFile.write("A=A-1");
      outputFile.println();
      outputFile.write("M=M-D");
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("push"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("C_PUSH");
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("pop"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("D=A");
      outputFile.write("@THAT");
      outputFile.write("D=D+M");
      outputFile.write("@R10");
      outputFile.write("M=D");
      outputFile.write("@SP");
      outputFile.write("A=M-1");
      outputFile.write("D=M");
      outputFile.write("@SP");
      outputFile.write("M=M-1");
      outputFile.write("@R10");
      outputFile.write("A=M");
      outputFile.println();
      outputFile.write("M=D");
      outputFile.println();

      close();
    }        
    else if(command.equalsIgnoreCase("eq"))
    {
      outputFile.write("@SP"); 
      outputFile.println();
      outputFile.write("A=M-1"); // A = SP - 1 
      // the above statement takes the pointer to the top of the
      // stack
      outputFile.println();
      outputFile.write("A=A-1"); // A = SP - 2
      outputFile.println();
      outputFile.write("D=M"); // D = RAM[SP - 2]
      outputFile.println(); 
      outputFile.write("A=A+1"); // A = SP – 1
      outputFile.println(); 
      outputFile.write("D=D-M"); // D = RAM[SP - 2] – RAM[SP - 1]
      outputFile.println(); 
      outputFile.write("@_1"); // A = ROM address at label: “_1”
      outputFile.println(); 
      outputFile.write("D;JEQ"); // if result == 0, goto “_1”; else fall thru 
      outputFile.println();
      outputFile.write("@_2"); // A = ROM address at label: “_2”
      outputFile.println(); 
      outputFile.write("D=0;JMP"); // D = “false” (0); goto “_2”
      outputFile.println();
      outputFile.write("(_1)");
      outputFile.println();
      outputFile.write("D=-1"); // D = “true” (-1)
      outputFile.println(); 
      outputFile.write("(_2)"); // D is T or F, depending on how we got here
      outputFile.println(); 
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1"); // discard 2 ops., leaving SP post push
      outputFile.println(); 
      outputFile.write("A=A-1"); // Set A to new SP - 1
      outputFile.println(); 
      outputFile.write("M=D"); // push D (T or F) onto stack
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("gt"))
    {
      outputFile.write("@SP"); 
      outputFile.println();
      outputFile.write("A=M-1"); // A = SP - 1 
      outputFile.println();
      outputFile.write("A=A-1"); // A = SP - 2
      outputFile.println();
      outputFile.write("D=M"); // D = RAM[SP - 2]
      outputFile.println(); 
      outputFile.write("A=A+1"); // A = SP – 1
      outputFile.println(); 
      outputFile.write("D=D-M"); // D = RAM[SP - 2] – RAM[SP - 1]
      outputFile.println(); 
      outputFile.write("@_1"); // A = ROM address at label: “_1”
      outputFile.println(); 
      outputFile.write("D;JGT"); // if result > 0, goto “_1”; else fall thru 
      outputFile.println();
      outputFile.write("@_2"); // A = ROM address at label: “_2”
      outputFile.println(); 
      outputFile.write("D=0;JMP"); // D = “false” (0); goto “_2”
      outputFile.println();
      outputFile.write("(_1)");
      outputFile.println();
      outputFile.write("D=-1"); // D = “true” (-1)
      outputFile.println(); 
      outputFile.write("(_2)"); // D is T or F, depending on how we got here
      outputFile.println(); 
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1"); // discard 2 ops., leaving SP post push
      outputFile.println(); 
      outputFile.write("A=A-1"); // Set A to new SP - 1
      outputFile.println(); 
      outputFile.write("M=D"); // push D (T or F) onto stack
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("lt"))
    {
      outputFile.write("@SP"); 
      outputFile.println();
      outputFile.write("A=M-1"); // A = SP - 1 
      outputFile.println();
      outputFile.write("A=A-1"); // A = SP - 2
      outputFile.println();
      outputFile.write("D=M"); // D = RAM[SP - 2]
      outputFile.println(); 
      outputFile.write("A=A+1"); // A = SP – 1
      outputFile.println(); 
      outputFile.write("D=D-M"); // D = RAM[SP - 2] – RAM[SP - 1]
      outputFile.println(); 
      outputFile.write("@_1"); // A = ROM address at label: “_1”
      outputFile.println(); 
      outputFile.write("D;JLT"); // if result < 0, goto “_1”; else fall thru 
      outputFile.println();
      outputFile.write("@_2"); // A = ROM address at label: “_2”
      outputFile.println(); 
      outputFile.write("D=0;JMP"); // D = “false” (0); goto “_2”
      outputFile.println();
      outputFile.write("(_1)");
      outputFile.println();
      outputFile.write("D=-1"); // D = “true” (-1)
      outputFile.println(); 
      outputFile.write("(_2)"); // D is T or F, depending on how we got here
      outputFile.println(); 
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1"); // discard 2 ops., leaving SP post push
      outputFile.println(); 
      outputFile.write("A=A-1"); // Set A to new SP - 1
      outputFile.println(); 
      outputFile.write("M=D"); // push D (T or F) onto stack
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("neg"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("A=M-1");
      outputFile.println();
      outputFile.write("M=-M"); 
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("and"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1");
      outputFile.println();
      outputFile.write("D=M");
      outputFile.println();
      outputFile.write("A=A-1");
      outputFile.println();
      outputFile.write("M=M&D");
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("or"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("AM=M-1");
      outputFile.println();
      outputFile.write("D=M");
      outputFile.println();
      outputFile.write("A=A-1");
      outputFile.println();
      outputFile.write("M=M|D");
      outputFile.println();
      close();
    }
    else if(command.equalsIgnoreCase("not"))
    {
      outputFile.write("@SP");
      outputFile.println();
      outputFile.write("A=M-1");
      outputFile.println();
      outputFile.write("M=!M"); 
      outputFile.println();
      close();
    }    
    else
    {
      System.err.println("Not a valid instruction.");
      System.exit(0);
    }
  }

  public void writePushPop(CommandType commandType, String segment, int index)
  {

  }
}