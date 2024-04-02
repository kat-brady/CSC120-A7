/* This is our library class */
import java.util.*;

public class Library extends Building {
  private Hashtable<String, Boolean> collection; //initializes hashtable for library collection
  static Scanner scanner = new Scanner(System.in); //initializes scanner for user input

    public Library(String name, String address, int nFloors) { //constructs library using attributes from parent class
      super(name, address, nFloors); //imports existing classifications from Building
      this.collection = new Hashtable<String, Boolean>(); //the collection of books in the library
    }
    
    public void addTitle(String title) { //adds a new book to the library collection
      this.collection.put(title, true); //title for the book's name and author; true meaning it is now in the library
      System.out.println(title + " has been added to " + this.name + ".");
    }


    public String removeTitle(String title){ //removes a book from the library collection
      if (this.collection.containsKey(title)) { //checks if the library has the book
        this.collection.remove(title); //if so, it is removed
        System.out.println(title + " has been removed from " + this.name + ".");
        return title;
    } else {
        System.out.println(this.name + " doesn't have a copy of " + title + ". Are you sure you want to try to remove it? (yes/no)"); //checks if action was intended
        String response = scanner.nextLine(); //utilizes scanner
        if (response.equals("yes")) {  //if the user still wants to remove it
          throw new RuntimeException(this.name + " doesn't have a copy of " + title + ", so it could not be removed."); //throw exception since it cannot be removed
        }   if (response.equals("no")) { //if they realize their error, don't end the code with an exception. let it continue
          System.out.println("No book has been removed from " + this.name + ".");
        }   else {
          throw new RuntimeException("You must enter 'yes' or 'no'."); //if they don't enter yes or no, throw exception
        }
      }
      return title;
    }
    
    public void checkOut(String title){ //method to update that a book is checked out and no longer available
      if (!this.collection.containsKey(title)) { //checks if that book is not in the library's collection first
        throw new RuntimeException(this.name + " doesn't have a copy of " + title + ", so it could not be checked out.");
    } if (this.collection.get(title) == false) { //checks if the title has the associated value "false" (i.e. is it currently checked out?)
        System.out.println("Uh oh! Looks like " + title + " has been checked out already! Do you still want to try checking it out? (yes/no)");
        String response = scanner.nextLine(); //utilizes scanner
        if (response.equals("yes")){
          throw new RuntimeException("You cannot check out " + title + " because it has been checked out already!"); //throws exception since they proceeded anyway
        } if (response.equals("no")){
          System.out.println(title + " is not being checked out. Please return when it is available again."); //lets user know the checkout failed without ending the code
        } else {
          throw new RuntimeException("You must enter 'yes' or 'no'."); //if they don't enter yes or no, throw exception
        }
      } else {
        this.collection.replace(title, false); //updates the value to false to indicate that it cannot be checked out
        System.out.println("Success! You have now checked out " + title + ". Please return it in two weeks.");
      }
    }

    public void returnBook(String title){ //method to update that a book is available again
      if (!this.collection.containsKey(title)) { //checks if that book is not in the library's collection first
        System.out.println(this.name + " has never had " + title + " in the collection. Are you sure you want to try to return it? (yes/no)"); //allows user to choose to contiue with error or rectify mistake
        String response = scanner.nextLine(); //utilizes scanner
        if (response.equals("yes")) {
          throw new RuntimeException(this.name + " never had a copy of " + title + ", so you can't return it."); //if user still tries to return, throw exception
        }else if (response.equals("no")) {
          System.out.println(title + " will not be returned to " + this.name + ". You can keep it."); //if user realizes they can't return, the program can proceed
        } else {
          throw new RuntimeException("You must enter 'yes' or 'no'."); //if they don't enter yes or no, throw exception
        }
    } else if (this.collection.get(title) == true) { //checks if the title has the associated value "true" (i.e. has it already been returned?)
        throw new RuntimeException("Huh. Seems like " + title + " wasn't checked out in the first place.");
      } else {
        this.collection.replace(title, true); //updates the value to true to indicate that it can be checked out again now
        System.out.println("Success! You have now returned " + title + ". Thank you!");
      }
    }

    public boolean containsTitle(String title){  // returns true if the title appears as a key in the Libary's collection, false otherwise
      if (this.collection.containsKey(title)) { //if title is in collection
        System.out.println(this.name + " has at least one copy of " + title + ".");
        return true;
      } else { //if title is not in collection
        System.out.println(this.name + " does not have a copy of " + title + ".");
        return false;
      }
    }
    public boolean isAvailable(String title) { // returns true if the title is currently available, false otherwise
      if (!this.collection.containsKey(title)) { //if title is not in collection, have different text to show that no copies will become available
        System.out.println("Unfortunately, " + this.name + " doesn't own any copies of " + title + ".");
        return false;
      }
      if (this.collection.get(title) == true) { //checks if title is checked in (true)
        System.out.println(title + " is currently available to check out. Time to read!");
        return true;
      } else {
        System.out.println("Looks like " + title + " has already been checked out.  You'll have to wait."); //else, title must be checked out
        return false;
      }
    }

    public void printCollection() {// prints out the entire collection in an easy-to-read way (including checkout status)
      for (Map.Entry<String,Boolean> book : collection.entrySet()) { //for each individual book in the full hashtable collection
        System.out.println(this.name + " currently has " + book.getKey() + " in its collection."); //print that the book is available
          isAvailable(book.getKey()); //use preexisting isAvailable function to easily display the book's status
      }
    }

      public static void main(String[] args) {
      Library library = new Library("Forbes Library", "20 West Street", 3);
      System.out.println(library);
      library.addTitle("The Lorax by Dr. Seuss");
      library.checkOut("The Lorax by Dr. Seuss");
      library.addTitle("East of Eden by John Steinbeck");
      library.printCollection();
      library.returnBook("The Lorax by Dr. Seuss");
      library.removeTitle("The Lorax by Dr. Seuss");
      library.removeTitle("The Lorax by Dr. Seuss");
      
      scanner.close();
    }
  
  }