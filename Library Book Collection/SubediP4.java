 /**
  * A Main method program to test the SubediCollection class methods and 
  * display the appropriate out in readable formate
  * @author Nawaraj Subedi
  * @version 11/26/2014
  */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class SubediP4
{
   public static void main(String[] args)
   { 
      String underscore="-",star="*";
      SubediCollection raj  = new SubediCollection();
      raj.buildMap("books.txt");
      
     /****************************************************************
       To print all the books if they valid under certain specifiction 
      *****************************************************************/
      required(underscore,90);
      System.out.println("**********************Nawaraj Book Collection********************************");
      required(underscore,90);
      System.out.printf("%n%10s%41s%n","ISBN No","Title");
      required(underscore,90);
      System.out.println(raj.printMap());
      required(star,90);
      //****************************************
      //*********To Read Command line **********
      //****************************************
      try
      {
         File file =new File("input.txt");
         Scanner inFile = new Scanner(file);
         while(inFile.hasNextLine())
         { 
            String words=inFile.nextLine();
            Scanner read=new Scanner(words);
            String required=read.next();
           //****************************
           // Read next String from file
           //**************************** 
            if ((required.equals("display")))
            {
               
               String needDisplay=read.next();
               System.out.println("\n---> PROCESSING COMMAND ...display..."+needDisplay+'\n'+ raj.Display(needDisplay)+'\n');
            } 
            else if ((required.equals("remove")))
            {
               String needRemove=read.next();
               System.out.print("\n---> PROCESSING COMMAND ...remove... "+needRemove+"..."+'\n'+
                  "Removing...."+raj.removeISBN(needRemove)+'\n');
            }
            else if ((required.equals("search")))
            {
               String need=read.next();
               System.out.println("\n---> PROCESSING COMMAND ..."+required+"..."+ need+'\n'+
                  "BOOKS WITH TITLE CONTAINING "+need+"..."+raj.bookSearch(need));
            }
            else if((required.equals("checkout")))
            {
               String checkout=read.next();
               System.out.println("\n---> PROCESSING COMMAND ...checkout..."+checkout+'\n'+
                  raj.checkOut(checkout));   
            }  
            else if((required.equalsIgnoreCase("return")))
            {
               String retur=read.next();
               System.out.println("\n---> PROCESSING COMMAND ...return..."+retur+"\nBook with ISBN "+
                  retur+raj.returnIsbn(retur)+'\n'); 
            }
            else
            { 
               if ((required.equalsIgnoreCase("show")&&read.next().equalsIgnoreCase("all")))
               {   
                  required(star,90);  
                  System.out.println("\n---> PROCESSING COMMAND ...show all");
                  System.out.printf("%n%4s%14s%35s%n","No:","ISBN No","Title");
                  required(underscore,90);
                  System.out.print(raj.showAll());
                  required(star,90);
               }
            }   
         }
         inFile.close();
      }
      catch(FileNotFoundException e) {
      //************************************
      //prints the stack trace to System.err
      //************************************
         e.printStackTrace();
      }
   }
  /**
   * Private method that Display the  string consisting number of
   * blanks specified by the argument.
   * @param n An int
   */
   public static void  required (String spa,int count)
   {
      String result = "";
      for (int i = 0; i <count; i++)
      {
         result = result +spa;
      }
      System.out.print( result+'\n');
   }
}
