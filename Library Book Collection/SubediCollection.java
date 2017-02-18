import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
public class SubediCollection
{
  // Data Field
   Map<String, SubediBook> map; 
   private final char NEW_LINE ='\n';
   SubediBook abook;
   String ISBN="",checking="";
   private int max_length=0;
   
 /**
  * Constructor to create and to initilized empty Map
  */
   public SubediCollection()
   {
      this.map =new HashMap<String, SubediBook>();   
   }
  /**
   * A boolean method that read the line containg  the information of books 
   * and store it in map,If it read succesefully then it return true,
   * else it throw the exception and return false
   * @param filename A string file which contain the information about a book,one book per line
   */
   public boolean buildMap(String filename)
   {
      boolean x=false;
      map = new HashMap<String, SubediBook>();
      try {
         File infile=new File (filename);
         Scanner sc=new Scanner(infile);   
         while(sc.hasNextLine())
         {
            String line =sc.nextLine();
            Scanner read=new Scanner(line);
            read.useDelimiter(",");
            String title=read.next() ;
            String author=read.next();
            String publisher=read.next();
            String year=read.next();
            if (read.hasNext())
            {
               ISBN=read.next();
            }
            //*****************************************************
            //****Create length of line for to make good output****
            //*****************************************************
            if (ISBN.length()>max_length)
            {
               max_length=ISBN.length();
            }
            abook=new SubediBook(title,author,publisher,year,ISBN);
           /******************************************************
           /*******To put information in map *********************
           /******************************************************/
            if(ISBNcheck(ISBN))
            {
               map.put(ISBN,abook);
            }            
         }
         sc.close(); 
         x=true;   
      } 
      catch(FileNotFoundException e)
      {
      //************************************
      //prints the stack trace to System.err
      //************************************
         e.printStackTrace();
         x=false;
      }
      return x;
   }
   /**
   * Method to print all information of book  that are available in our collection
   * @return String that contain the Information of all books
   */
   public String  printMap()
   { 
      StringBuilder print =new StringBuilder();
      Iterator<String> iter = map.keySet().iterator();
      int i=0;
      while (iter.hasNext()) 
      {          String booksDetail = iter.next();
         SubediBook read = map.get(booksDetail);
         print.append(read.getISBN()+space(max_length-booksDetail.length()+5)+ read.getTitle()+'\n');
         i++;
      }
      print.append("\nTotal No of books Available in our Collection :"+i);
      return print.toString();
   }  
  /**
   * Method to determine whethere a String (ISBN) is given valid or not
   *@param check String to be tested
   *@return true if check is valid ISBN for to store book in collection
   */
   public boolean ISBNcheck( String check)
   {
      boolean x=false;
      String isbNum=check.replaceAll("[\\s\\-()]", "");
      int sum=0,count=0;
      String  last=isbNum.substring(9,10);
      for(int i=1;i<=9;i++)
      {
         String readnum = isbNum.substring(count,count+1);
         count++;
         int number = Integer.parseInt(readnum);
         sum += i*number;
      }
      int num=sum%11;
      String str = String.valueOf(num);
      if((num<10&&str.equals(last))||(num<=10&&last.equals("X")))
      {
         x=true;
      } 
      return x;   
   }
  /**
   * Method to display all of the information concering the book with the isbn 
   * that is passed as argument.
   * @param isbn A String that contaion the information of book
   * @return Information containg string
   */
   public String Display(String isbn)
   {
      StringBuilder output = new StringBuilder();
      SubediBook display = map.get(isbn);
      if(display !=null)
      {
         output.append(display.toString());
      }
      else
      {
         output.append("ISBN No : "+isbn+" DOESN'T EXIT IN OUR COLLECTION"+NEW_LINE);
      }
      return (output.toString());
   }
  /**
   * Method to remove all of the information concering the book with the isbn 
   * that is passed as argument.
   * @param delete A String that contain the reference of book
   * @return String that contain the Information  of removed book
   */
   public String removeISBN(String delete)
   {
      StringBuilder remove=new StringBuilder("");
      Iterator<String> iter = map.keySet().iterator();
      while (iter.hasNext()) 
      {
         if(iter.next().equalsIgnoreCase(delete))
         {
            SubediBook removed=map.get(delete);
            iter.remove();
            remove.append(delete+space(2)+removed.getTitle()); 
         }
      }
      if ((remove.toString()).equalsIgnoreCase(""))
      {
         remove.append("ISBN No :"+delete+"...doesn't exist");
      }
      return remove.toString();
   }
  /**
   * Method to search all of the information concering the book with the string 
   * that is passed as argument.
   * @param found A String that contain the part of book
   * @return String that contain the title of book
   */
   public String bookSearch(String found)
   { 
      String output="\n";
      int i=1;
      Iterator<String> iter = map.keySet().iterator();
      while (iter.hasNext()) 
      {
         String needSearch=iter.next();
         SubediBook display = map.get(needSearch);
         if((display.getTitle().toLowerCase()).contains(found))
         {
            String num=Integer.toString(i);
            output+=space(6)+i+"."+space(4-num.length())+needSearch+
                    space(max_length-needSearch.length()+5)+display.getTitle()+NEW_LINE;
            i++;
         }
      }
      if (output.equalsIgnoreCase("\n"))
      {
         output="None\n";
      }
      return output;
   } 
   /**
   * Method to checkout the book with the isbn that is passed as argument.
   * If available this method checkout otherwise it say somebody already loned or not available
   * @param delete A String that contain the reference of book
   * @return String that contain the Information  of removed book
   */
   public String checkOut(String checkout)
   {
      String output="\n";
      boolean x=false;
      Iterator<String> iter = map.keySet().iterator();
      while (iter.hasNext()) 
      {
         if(iter.next().equalsIgnoreCase(checkout))
         {
            x=true;
            SubediBook check=map.get(checkout);
            if(x==true&&(isCheckedout(checkout)))
            {
               output=checkout+space(1)+check.getTitle()+space(1)+"is not available";
            }
            else if(x==true&&(!isCheckedout(checkout)))
            {
               output="Checking out ..."+checkout+space(1)+check.getTitle();
               checking+=checkout+"";
            }
         }
      }
      if (output.equals("\n"))
      {
         output="Book with ISBN "+checkout+space(1)+"doesn't exist";
      }
   
      return output;
   }
  /**
   * Method to determine whether a String is available 
   * @param checked String to be tested
   * @return true if String is availabe 
   */
   private boolean isCheckedout(String checked)
   { 
      return checking.contains(checked);
   }
  /**
   * Method to determine whether a String is available 
   * @param checked String to be tested
   * @return true if String is availabe 
   */
   public String returnIsbn(String returnbook)
   {
      String output="";
      if(isCheckedout(returnbook))
      {
         checking=checking.replaceAll(returnbook+"","");
         SubediBook returned=map.get(returnbook);
         output=space(1)+returned.getTitle()+space(1)+"Successfully returned";
      }
      else
      { 
         output=" doesn't exist";
      }
      return output;
   }
   /**
   * Method to print all information of book  that are available in our collection
   * @return String that contain the Information of all books
   */
   public String  showAll()
   { 
      StringBuilder showall =new StringBuilder();
      Iterator<String> iter = map.keySet().iterator();
      int i=1;
      while (iter.hasNext()) 
      {
         String booksDetail = iter.next();
         SubediBook read = map.get(booksDetail);
         String num=Integer.toString(i);
         showall.append(space(1)+i+"."+space(4-num.length())+read.getISBN()+
                     space(max_length-booksDetail.length()+5)+ read.getTitle()+'\n');
         i++;
      }
      showall.append("\nTotal books available :"+(i-1)+'\n');
      return showall.toString();
   }  
  /**
   * Private method that constructs and returns a string consisting number of
   * blanks specified by the argument.
   * @param n An int
   * @return a String containing no of  blanks
   */
   private String space (int count)
   {
      String result = "";
      for (int i = 0; i <count; i++)
      {
         result = result +" ";
      }
      return result;
   }
}