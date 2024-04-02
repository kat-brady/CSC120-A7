/* This is our House class */
import java.util.*;

public class House extends Building{ 
  private ArrayList<String> residents; // Initializes the arraylist for residents
  static Scanner scanner = new Scanner(System.in); //initializes scanner for user input
  private boolean hasDiningRoom; //initializes boolean for dining room
  private boolean hasElevator; //initializes boolean for elevator
  private int nResidents=0; //initializes int for number of residents

  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) { //constructs a house using attributes from parent class Building
    super(name, address, nFloors); //imports existing classifications from Building
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator = hasElevator;
    this.residents = new ArrayList<String>();
  }

  public void moveIn(String residentName){
    if (this.residents.contains(residentName)) { //checks if the resident already lives there so that they cannot move in twice
      throw new RuntimeException(residentName + " already lives in " + this.name); //throw exception if they do
  } //otherwise, proceed in adding them to the list of residents
    residents.add(residentName);
    nResidents+=1;
    System.out.println(residentName + " has successfully moved in!");
  }

  public String moveOut(String residentName){ //enables a resident to be removed from the house
    if (!this.residents.contains(residentName)) { //checks if the resident lives in the house
      throw new RuntimeException(residentName + " doesn't live in " + this.name); //throw exception if they don't
  } //otherwise, proceed in moving out
    residents.remove(residentName); //takes the name out of the arraylist
    nResidents-=1; //decreases the count of residents
    System.out.println(residentName + " has successfully moved out. They'll be missed.");
    return residentName;
    } 

  public boolean hasDiningRoom(){ //checks if a given house has a dining room
    if (!this.hasDiningRoom){ //if there is no dining room found, aka if it is false
      System.out.println( this.name + " does not have a dining room.");
      return false;
    } else {
      System.out.println(this.name + " has a dining room."); //if there is a dining room, or if it is true
      return true;
    }
  }

  public int nResidents(){ //public method to return number of residents in a given house
    if (this.nResidents == 1){ //adjusts text output if there is only 1 resident for better grammar
      System.out.println("There is " + this.nResidents + " resident in " + this.name + "."); 
    }else{
      System.out.println("There are " + this.nResidents + " residents in " + this.name + ".");
    }
    return this.nResidents;
  }

  public boolean isResident(String person){ //public method to check if a specific name is in the arraylist of residents
    if (!this.residents.contains(person)){
      System.out.println(person + " is not a resident of " + this.name + ".");
      return false;
    } else{
      System.out.println(person + " is a resident of " + this.name + ".");
      return true;
    }
  }

  public String toString() { //public adjustment of toString to add on house features
    String desc = super.toString();
    if (this.hasDiningRoom) {
      desc += " It has a dining room.";
    } else {
      desc += " It does not have a dining room.";
    }
    if (this.hasElevator) {
      desc += " It has an elevator.";
    } else{
      desc+= " It does not have an elevator.";
    }
    return desc;
  }

  public void showOptions() { //public adjustment of show options
    super.showOptions();
    System.out.println(" + moveIn(string)\n + moveOut(string)\n + hasDiningRoom()\n + nResidents\n + isResident(string)");

  }

  public void goToFloor(int floorNum) {
    if(hasElevator) {
      super.goToFloor(floorNum);
    }
    else {
      System.out.println(this.name + " does not have an elevator. Do you want to travel by stairs or continue trying to take an elevator? (stairs/elevator)");
      String response = scanner.nextLine(); //utilizes scanner
      if (response.equals("stairs")) {  //if the user says stairs
        int num = floorNum;
        while (num != 0) { //while you have not reached the desired floor
          goUp(); //go up 1 floor
          num-=1; //subtract 1 from the total desired floor
          }
      }else if (response.equals("elevator")) { //if they still try to take the elevator that does not exist
        throw new RuntimeException("There is no elevator. You can't teleport!");
      }else {
        throw new RuntimeException("You must enter 'stairs' or 'elevator'."); //if they don't enter yes or no, throw exception
      }
   }
  }

  public static void main(String[] args) {
    House house= new House("Albright House", "7 Bedford Terrace", 4, false, false);
    System.out.println(house);
    house.showOptions();
    house.moveIn("Kat");
    house.moveIn("Kara");
    house.isResident("Kat");
    house.hasDiningRoom();
    house.nResidents();
    house.enter();
    house.goUp();
    house.goToFloor(4);
  }

}
