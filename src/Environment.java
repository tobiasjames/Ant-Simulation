import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Environment {


    ////////////////////////////////      ATTRIBUTES    ///////////////////////////////////////
    private static final int GRID_SIZE = 729;
    private static final int GRID_DIMENSION = 27;
    private Square[] squares = new Square[GRID_SIZE];

    private ColonyView colonyView;

    private ArrayList<Ant> ants;


    /////////////////////////////     CONSTRUCTOR    //////////////////////////////////////////
    public Environment() {

        this.colonyView = new ColonyView(GRID_DIMENSION,GRID_DIMENSION);

        this.ants = new ArrayList<Ant>();

        for (int i = 0; i < GRID_SIZE; i++) {

            int x = i % GRID_DIMENSION + 1;
            int y = i / GRID_DIMENSION + 1;

            this.squares[i] = new Square(x,y);

            Random random = new Random();
            int r = random.nextInt(999);
            this.squares[i].setFood(r);

            this.colonyView.addColonyNodeView(this.squares[i].getNode(), x, y);

        }
    }

    /////////////////////////////    GETTER   ////////////////////////////////////////////////
    public Square getSquareByCoordinate(Coordinate coordinate) {

        for (Square square : this.squares) {
            if (square.getCoordinates().equals(coordinate)) {
                return square;
            }
        }
        return null;
    }

    public ColonyView getColonyView() {
        return colonyView;
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }
}


/////////////////////////////////////////     SQUARE CLASS //////////////////////////////////////////
class Square {

    /*        ############################         ATTRIBUTES   ################################     */
    private boolean isVisible;


    private ArrayList<Ant> ants = new ArrayList<Ant>();
    private ArrayList<Queen> queenAnts = new ArrayList<Queen>();

    private ArrayList<Forager> foragerAnts = new ArrayList<Forager>();

    private ArrayList<Scout> scoutAnts = new ArrayList<Scout>();

    private ArrayList<Soldier> soldierAnts = new ArrayList<Soldier>();

    private ArrayList<Bala> balaAnts = new ArrayList<Bala>();
    private int food;
    private int pheremon;

    private ColonyNodeView node;

    private Coordinate coordinates;

    /*       #################################     CONSTRUCTOR      ############################### */

    public Square(int x, int y) {

        this.coordinates = new Coordinate(x,y);
        this.node = new ColonyNodeView();
        this.setVisibility(false);
        this.node.setVisible(false);

    }


    //////////////////////////////////    GETTERS         /////////////////////////////////////////////////
    public boolean isVisibility() {
        return isVisible;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }


    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public ArrayList<Queen> getQueenAnts() {
        return queenAnts;
    }

    public ArrayList<Forager> getForagerAnts() {
        return foragerAnts;
    }

    public ArrayList<Scout> getScoutAnts() {
        return scoutAnts;
    }

    public ArrayList<Soldier> getSoldierAnts() {
        return soldierAnts;
    }

    public ArrayList<Bala> getBalaAnts() {
        return balaAnts;
    }

    public int getFood() {
        return food;
    }

    public ColonyNodeView getNode() {
        return node;
    }

    public int getPheremon() {
        return pheremon;
    }

    //////////////////////////// SETTERS //////////////////////////////////////


    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }

    public void setQueenAnts(ArrayList<Queen> queenAnts) {
        this.queenAnts = queenAnts;

        if (this.queenAnts.size() == 0) {
            this.getNode().hideQueenIcon();
        } else {
            this.getNode().showQueenIcon();
        }
    }

    public void setForagerAnts(ArrayList<Forager> foragerAnts) {
        this.foragerAnts = foragerAnts;

        if (this.foragerAnts.size() == 0) {
            this.getNode().hideForagerIcon();
        } else {
            this.getNode().showForagerIcon();
        }
    }

    public void setScoutAnts(ArrayList<Scout> scoutAnts) {
        this.scoutAnts = scoutAnts;

        if (this.scoutAnts.size() == 0) {
            this.getNode().hideScoutIcon();
        } else {
            this.getNode().showScoutIcon();
        }
    }

    public void setSoldierAnts(ArrayList<Soldier> soldierAnts) {
        this.soldierAnts = soldierAnts;

        if (this.soldierAnts.size() == 0) {
            this.getNode().hideSoldierIcon();
        } else {
            this.getNode().showSoldierIcon();
        }
    }

    public void setBalaAnts(ArrayList<Bala> balaAnts) {
        this.balaAnts = balaAnts;

        if (this.balaAnts.size() == 0) {
            this.getNode().hideBalaIcon();
        } else {
            this.getNode().showBalaIcon();
        }
    }

    public void setFood(int food) {

        this.food = food;

        this.node.setFoodAmount(food);

    }

    public void setVisibility(boolean visibility) {

        this.isVisible = visibility;

//        if (this.getNode() != null) {
//            System.out.println("( " + this.getCoordinates().getX() + " , " + this.getCoordinates().getY() + " ) \n" );
//        }

        this.node.showNode();

    }
    public void setPheremon(int pheremon) {
        this.pheremon = pheremon;

        this.getNode().setPheromoneLevel(pheremon);
    }

    ////////////////////////////////////////      METHOD     ////////////////////////////////////////////////
    public int countFriendAnts() {

        int friendlyAnts = queenAnts.size() + foragerAnts.size() + scoutAnts.size() + soldierAnts.size();
        return friendlyAnts;
    }

    public int countEnemyAnts() {

        int enemyAnts = balaAnts.size();
        return enemyAnts;
    }

    public void setNodeAntCount () {

        node.setForagerCount(foragerAnts.size());
        node.setScoutCount(scoutAnts.size());
        node.setSoldierCount(soldierAnts.size());
        node.setBalaCount(balaAnts.size());
    }
}

class Coordinate {

    /////////////////////////////////////////    ATTRIBUTES      ///////////////////////////////////////////////////
    private int x;
    private int y;


    //////////////////////////////////////     CONSTRUCTOR      /////////////////////////////////////////////////////
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    ///////////////////////////////////////    GETTERS        ////////////////////////////////////////////////
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    ////////////////////////////////         SETTERS     ////////////////////////////////////////////////////////
    public Coordinate setCoordinate (int x, int y) {

        this.x = x;
        this.y = y;
        return this;
    }


    ////////////////////////////////     METHODS    /////////////////////////////////////////////////////////
    @Override
    public boolean equals (Object object) {

        if (object instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) object;
            return (this.getX() == coordinate.getX() && this.getY() == coordinate.getY());
        }

        return false;
    }
}
