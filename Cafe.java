/* This is the Cafe class */
import java.util.*;

public class Cafe extends Building{ //establishes cafe as a child of parent class building
    private int nCoffeeOunces; // The number of ounces of coffee remaining in inventory
    private int nSugarPackets; // The number of sugar packets remaining in inventory
    private int nCreams; // The number of "splashes" of cream remaining in inventory
    private int nCups; // The number of cups remaining in inventory
    static Scanner scanner = new Scanner(System.in); //initializes scanner for user input

    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) { //establishes cafe using attributes from parent class and specific cafe attributes
        super(name, address, nFloors); //imports attributes from building
        this.nCoffeeOunces= nCoffeeOunces; //the cafe's number of Coffee Ounces
        this.nSugarPackets = nSugarPackets; //the cafe's number of sugar packets
        this.nCreams= nCreams; //the cafe's number of creams
        this.nCups = nCups; //the cafe's number of cups
    }

    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) { //method to restock any supplies needed to sell a coffee
        System.out.println("What supply do you need to restock? (coffee, sugar, creamer, cups)");
        String restockTarget = scanner.nextLine(); //sets scanner's "target"
        if (restockTarget.contains("coffee")) { //if coffee was included in what they ask to restock
            try { //try is used in case the user doesn't input an int
                System.out.println("How many ounces of coffee do you want to add to the inventory? (please input an integer)"); //asks for an int
                int quantity = scanner.nextInt(); //sets the target of scanner
                this.nCoffeeOunces+=quantity; //new number is old number plus new
                System.out.println(this.name + " now has " + this.nCoffeeOunces + " ounce(s) of coffee."); //tells the user the new amount of coffee
            } catch (InputMismatchException nonint) { //catches nonint
                System.out.println("Please input an integer."); //tells the user what's wrong
                }
         }
            else if (restockTarget.contains("sugar")) { //if sugar was included in what they ask to restock
                try { //try is used in case the user doesn't input an int
                    System.out.println("How many packets of sugar do you want to add to the inventory? (please input an integer)"); //asks for an int
                    int quantity = scanner.nextInt(); //sets the target of scanner
                    this.nSugarPackets+=quantity; //new number is old number plus new
                    System.out.println(this.name + " now has " + this.nSugarPackets + " packet(s) of sugar."); //tells the user the new amount of coffee
                } catch (InputMismatchException nonint) { //catches nonint
                    System.out.println("Please input an integer."); //tells the user what's wrong
                    }
                }
            else if (restockTarget.contains("creamer")) { //if creamer was included in what they ask to restock
                try { //try is used in case the user doesn't input an int
                    System.out.println("How many splashes of creamer do you want to add to the inventory? (please input an integer)"); //asks for an int
                    int quantity = scanner.nextInt(); //sets the target of scanner
                    this.nCreams+=quantity; //new number is old number plus new
                    System.out.println(this.name + " now has " + this.nCreams + " splash(es) of coffee."); //tells the user the new amount of coffee
                } catch (InputMismatchException nonint) { //catches nonint
                    System.out.println("Please input an integer."); //tells the user what's wrong
                        }
                }
            else if (restockTarget.contains("cups")) { //if cups were included in what they ask to restock
                try { //try is used in case the user doesn't input an int
                    System.out.println("How many new cups do you want to add to the inventory? (please input an integer)"); //asks for an int
                    int quantity = scanner.nextInt(); //sets the target of scanner
                    this.nCups+=quantity; //new number is old number plus new
                    System.out.println(this.name + " now has " + this.nCups + " coffee cup(s)."); //tells the user the new amount of coffee
                } catch (InputMismatchException nonint) { //catches nonint
                    System.out.println("Please input an integer."); //tells the user what's wrong
                        }
                }
            else {
                throw new RuntimeException("Please input coffee, sugar, creamer, or cups as your response."); //throws error if other response is given

        }
    }

    public void sellCoffee(int size, int nSugarPackets, int nCreams) { //method to sell coffee
        System.out.println("You are trying to sell a coffee. Checking inventory...");
        while (this.nCoffeeOunces < size || this.nSugarPackets < nSugarPackets || this.nCreams < nCreams || this.nCups < 1) { //if there are fewer supplies than the number needed to make the coffee
            if (this.nCoffeeOunces < size) { //if coffee is less than amount needed
                int difference= size - nCoffeeOunces; //name the difference to use it in the instructional statement to follow
                System.out.println("Whoops! Looks like you'll need to restock your coffee first. Add at least " + difference + " ounce(s) of coffee."); //tells the user how much they need to add when restocking
            }
            else if (this.nSugarPackets < nSugarPackets) { //if sugar packets are fewer than needed
                int difference = nSugarPackets - this.nSugarPackets; //name the difference
                System.out.println("Oh no! You don't have enough sugar packets for this order. Please restock at least " + difference + " packet(s) first."); //tells how many packets you need to ass
            }
            else if (this.nCreams < nCreams) { //if creams are fewer than needed
                int difference = nCreams - this.nCreams; //establish name for the difference
                System.out.println("Oops! You need to restock your creamer first with at least " + difference + " cream(s)."); //tell user how many more are needed
            }
            else if (this.nCups < 1) { //if there are no cups
                System.out.println("You can't sell a coffee without a cup! Restock at least 1 cup first."); //tell user to add a cup
            }
            restock(size, nSugarPackets, nCreams, nCreams); //call restock
        }if (this.nCoffeeOunces >= size ||this.nSugarPackets >= nSugarPackets||this.nCreams >= nCreams|| this.nCups >= 1){ //if there is enough of all supplies to make the coffee, using an if instead of else or else if statement so that a successfully restocked cafe can go through this process
            this.nCoffeeOunces-=size; //removes used coffee from inventory
            this.nSugarPackets-=nSugarPackets; //removes used sugar packets from inventory
            this.nCreams-=nCreams; //removes used creams from inventory
            this.nCups-=1; //removes the used cup from inventory
            System.out.println("Success! " + this.name + " has just sold a coffee made with " + size + " ounce(s) of coffee, " + nSugarPackets + " packet(s) of sugar, and " + nCreams + " splash(es) of creamer.");
            System.out.println("The new inventory is " + this.nCoffeeOunces + " ounce(s) of coffee, " + this.nSugarPackets + " packet(s) of sugar, " + this.nCreams + " splash(es) of cream, and " + this.nCups + " available cup(s).");
        }else { //if you get to this else statement, you restocked incorrectly the first time, so you have to do it again
            restock(size, nSugarPackets, nCreams, nCreams); //calls restock
        }
    }

    
    public String toString() { //public adjustment of toString to add extra details to readout of cafe
        String desc = super.toString(); //sets the default desc to have the attributes from the parent, but will allow to add more details
        if (this.nCoffeeOunces == 0) { //if there is no coffee, use custom text to share that
            desc+= "\n" + this.name + " is all out of coffee,";
        } else if (this.nCoffeeOunces == 1) { //if there is 1 oz, use grammatically accurate text
            desc+= "\n" + this.name + " has " + nCoffeeOunces + " ounce of coffee, ";
        } else { //otherwise, this default phrase will make sense
            desc+= "\n" + this.name + " has " + nCoffeeOunces + " ounces of coffee, ";
        }
        if (this.nSugarPackets == 0) { //if there is no sugar, use custom text to share that
            desc+= "no sugar packets, ";
        } else if (this.nSugarPackets == 1) { //if there is 1 packet, use grammatically accurate text
            desc+= nSugarPackets + " packet of sugar, ";
        } else { //otherwise, this default phrase will make sense
            desc+= nSugarPackets + " packets of sugar, ";
        }
        if (this.nCreams == 0) { //if there is no cream, use custom text to share that
            desc+= "no creamer, ";
        } else if (this.nCreams == 1) { //if there is 1 cream, use grammatically accurate text
            desc+= nCreams + " splash of creamer, ";
        } else { //otherwise, this default phrase will make sense
            desc+= nCreams + " splashes of creamer, ";
        }
        if (this.nCups == 0) { //if there are no cups, use custom text to share that
            desc+= "and no coffee cups.";
        } else if (this.nCups == 1) { //if there is 1 cup, use grammatically accurate text
            desc+= "and " + nCups + " cup left.";
        } else { //otherwise, this default phrase will make sense
            desc+= "and " + nCups + " cups left.";
        }
        return desc;
    }

    public static void main(String[] args) {
        Cafe cafe =new Cafe("The Roost", "1 Market Street", 1, 30, 100, 50, 25);
        System.out.println(cafe);
        cafe.sellCoffee(100, 1, 1);
    }
    
}
