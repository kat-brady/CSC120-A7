/* This is our library class */
import java.util.*;

public class Library extends Building {
  private Hashtable<String, Boolean> collection; //initializes hashtable for library collection
  static Scanner scanner = new Scanner(System.in); //initializes scanner for user input
  private boolean hasElevator; //initializes boolean for elevator

  //*Library constructor */
    public Library(String name, String address, int nFloors, boolean hasElevator) { //constructs library using attributes from parent class
      super(name, address, nFloors); //imports existing classifications from Building
      this.collection = new Hashtable<String, Boolean>(); //the collection of books in the library
      this.hasElevator = hasElevator;
    }

    //*Public method to addTitle */
    public void addTitle(String title) { //adds a new book to the library collection
      this.collection.put(title, true); //title for the book's name and author; true meaning it is now in the library
      System.out.println(title + " has been added to " + this.name + ".");
    }

    //*Overloaded method to addTitle */
    public void addTitle() {
      this.collection.put("The Color Purple by Alice Walker", true); //if the user forgets to input the title of the book they want to add, add a default
      System.out.println("Looks like you forgot to specify which book you wanted to add. " + this.name + " decided to add a copy of The Color Purple by Alice Walker in your name.");
    }

    //* Public method to removeTitle */
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

     //* overloaded method to remove all titles */
     public void removeTitle(){ //removes all books from the library collection
      this.collection.clear();
      System.out.println(this.name + " now has " + this.collection.size() + " books in its collection. How sad!");
    }
    
    //* Public method to checkOut */
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
    
    //* Public method to returnBook */
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

    //* public method containsTitle */
    public boolean containsTitle(String title){  // returns true if the title appears as a key in the Libary's collection, false otherwise
      if (this.collection.containsKey(title)) { //if title is in collection
        System.out.println(this.name + " has at least one copy of " + title + ".");
        return true;
      } else { //if title is not in collection
        System.out.println(this.name + " does not have a copy of " + title + ".");
        return false;
      }
    }

    //* Public method isAvailable */
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

    //* public method printCollection */
    public void printCollection() {// prints out the entire collection in an easy-to-read way (including checkout status)
      for (Map.Entry<String,Boolean> book : collection.entrySet()) { //for each individual book in the full hashtable collection
        System.out.println(this.name + " currently has " + book.getKey() + " in its collection."); //print that the book is available
          isAvailable(book.getKey()); //use preexisting isAvailable function to easily display the book's status
      } if (this.collection.size() == 0) {
        System.out.println("Oh, my! There are no books in " + this.name + "'s collection!");
      }
    }

    //*Modifies method goToFloor to account for hasElevator != true */
    public void goToFloor(int floorNum) {
      if(hasElevator) {
        super.goToFloor(floorNum);
      }
      else {
        if (this.activeFloor == -1) {
          throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
         else if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
       } else {
          System.out.println(this.name + " does not have an elevator. Must an old library. Do you want to travel by stairs or continue trying to take an elevator? (stairs/elevator)");
          String response = scanner.nextLine(); //utilizes scanner
          if (response.equals("elevator")) { //if they still try to take the elevator that does not exist
            throw new RuntimeException("There is no elevator. You can't teleport!");
          }
          else if (response.contains("stairs")) {  //if the user says stairs
            int num = floorNum;
            while (num != 1) { //while you have not reached the desired floor
              if (this.activeFloor == -1) {
                throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
            }
              if (floorNum < 1 || floorNum > this.nFloors) {
                throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
            } else{
              System.out.println("Walking up...");
              System.out.println("You are now on floor #" + (floorNum-num+2) + " of " + this.name);
              this.activeFloor = floorNum;
              num-=1; //subtract 1 from the total desired floor
              }
          }
          }else {
            throw new RuntimeException("You must enter 'stairs' or 'elevator'."); //if they don't enter yes or no, throw exception
            }
         }
      }
    }
    //* Public modification of showOptions specific to library class */
    public void showOptions() {
      super.showOptions();
      System.out.println(" + addTitle(string)\n + removeTitle(string)\n + checkOut(string)\n + returnBook(string)\n + containsTitle(string)\n + isAvailable(string)\n + printCollection()");
  }

      public static void main(String[] args) {
      Library library = new Library("Forbes Library", "20 West Street", 3, true);
      System.out.println(library);
      library.showOptions();
      library.enter();
      library.goToFloor(2);
      library.addTitle("The Lorax by Dr. Seuss");
      library.checkOut("The Lorax by Dr. Seuss");
      library.addTitle("East of Eden by John Steinbeck");
      library.printCollection();
      library.removeTitle();
      library.addTitle();
      library.printCollection();
    }
  
  }