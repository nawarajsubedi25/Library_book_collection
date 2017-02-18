public class SubediBook
{
  // Data Field
   private String title, author, publisher,year,ISBN;
   
  //Method
  /**
   * Initilizes a Subedi object with all properties specified
   * @param title The name of book
   * @param author The name of author
   * @param publisher The name of publisher
   * @param year The name of year
   */
   public SubediBook(String title,String author,String publisher,String year,String ISBN)
   {
      this.title=title;
      this.author=author;
      this.publisher=publisher;
      this.year=year;
      this.ISBN=ISBN;
   }
  /**
   * Get the name of title
   *@return The name of title
   */
   public String getTitle()
   {
      return title;
   }
  /**
   * Get the name of Author
   *@return The name of author
   */
   public String getAuthor()
   {
      return author;
   }
  /**
   * Get the name of publisher
   *@return The name of publisher
   */
   public String getPublisher()
   {
      return publisher;
   }
  /**
   * Get the year when book is published
   *@return The year of book has been published
   */
   public String getYear()
   {
      return year;
   }
   /**
   * Get the ISBN No of book
   *@return A string that represent book information
   */
   public String getISBN()
   {
      return ISBN;
   }
  /**
   * Initializes the name of title
   * @param title the kind of book
   */
   public void setTitle(String title)
   {
      this.title=title;
   }
  /**
   * Initializes the name of publisher
   * @param publisher the name of publisher
   */
   public void setPublisher(String publisher)
   {
      this.publisher=publisher;
   }
  /**
   * Initializes the name of author
   * @param title the name of book
   */
   public void setAuthor(String author)
   {
      this.author=author;
   }
   /**
   * Initializes the ISBN No of book
   * @param ISBN the ISBN No of book
   */
   public void setISBN(String ISBN)
   {
      this.ISBN=ISBN;
   }
  /**
   * Initializes the year of when book is published
   * @param title the time when book is published
   */
   public void setYear(String year)
   {
      this.year=year;
   }
   @Override
   public String toString()
   {
      return
         blank(6)+"Title      : "+this.title+'\n'+
         blank(6)+"Author     : "+this.author+'\n'+
         blank(6)+"Publisher  : "+this.publisher+'\n'+ 
         blank(6)+"Year       : "+this.year+'\n'+
         blank(6)+"ISBN NO    : "+this.ISBN +'\n';
   }
  /**
   * Private method that constructs and returns a string consisting number of
   * blanks specified by the argument.
   * @param n An int
   * @return a String containing no of  blanks
   */
   private String blank (int count)
   {
      String space = "";
      for (int i = 0; i <count; i++)
      {
         space =space +" ";
      }
      return space;
   }
}