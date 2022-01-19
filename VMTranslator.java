public class VMTranslator
{
  final static String FILE_NAME = "BasicTest.vm";
  final static String FILE_NAME_2 = "BasicTest.asm";
    
  public static void main(String[] args) 
  {
    CodeWriter cw = new CodeWriter(FILE_NAME);
    Parser par = new Parser(FILE_NAME_2);
  }
}