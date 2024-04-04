import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }
    //* Overloaded method to add library */
    public void addBuilding(Library l) {
        System.out.println("Adding library...");
        buildings.add(l);
        System.out.println("-->Successfully added " + l.getName() + " to the map.");
    }

    //* Overloaded method to add house */
    public void addBuilding(House h) {
        System.out.println("Adding house...");
        buildings.add(h);
        System.out.println("-->Successfully added " + h.getName() + " to the map.");
    }

    //* Overloaded method to add cafe */
    public void addBuilding(Cafe c) {
        System.out.println("Adding cafe...");
        buildings.add(c);
        System.out.println("-->Successfully added " + c.getName() + " to the map.");
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        myMap.addBuilding(new House("Albright House", "7 Bedford Terrace Northampton, MA 01063", 4, false, false));
        myMap.addBuilding(new Building("Sage Hall", "144 Green Street Northampton, MA 01063", 3));
        myMap.addBuilding(new Building("Campus Center", "100 Elm Street Northampton, MA 01063", 3));
        myMap.addBuilding(new Library("Neilson Library", "7 Neilson Drive Northampton, MA 01063", 3, true));
        myMap.addBuilding(new Cafe("Compass Cafe", "7 Neilson Drive Northampton, MA 01063", 1, 0, 0, 0, 0));
        myMap.addBuilding(new Building("Burton Hall", "46 College Lane Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Wright Hall", "5 Chapin Way Northampton, MA 01063", 3));
        myMap.addBuilding(new House("Baldwin House", "15 Bedford Terrace Northampton, MA 01063", 4, false, false));
        myMap.addBuilding(new Building("McConnell Hall", "2 Tyler Court Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Hillyer Art Library", "20 Elm Street Northampton, MA 01063", 3));
        System.out.println(myMap);
    }
    
}
