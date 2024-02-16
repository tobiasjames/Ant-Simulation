import java.util.*;

class Ant {

   /*        ############################         ATTRIBUTES   ################################     */

      protected boolean friendly = true;
      protected static final Random random = new Random();

      protected int id;
    protected int life;

    protected Environment environment;

    protected Square square;

    //protected Coordinate coordinate = square.getCoordinates();
    protected ArrayList<Square> path;

    protected boolean alive;

    protected boolean turn;



    ///////////////////////////////////////      GETTERS      ///////////////////////////////////////////


    public boolean isFriendly() {
        return friendly;
    }

    public int getLife() {
         return life;
     }

     public boolean getTurn() {
        return turn;
     }

     public boolean getAlive() {
        return alive;
     }


    //////////////////////////////////////         SETTERS      /////////////////////////////////////

     public void setPath(ArrayList<Square> path) {
         this.path = path;
     }

     public void setLife(int life) {
         this.life = life;
     }

//      public void setCoordinate(Coordinate coordinate) {
//          this.coordinate = coordinate;
//      }

      public void setAlive(boolean alive) {
         this.alive = alive;
     }

     public void setTurn(boolean turn) {
         this.turn = turn;
     }

     public void setEnvironment(Environment environment) {
         this.environment = environment;
     }

      public void setSquare(Square square) {
          this.square = square;
          this.square.getAnts().add((Ant) this);
          if (this instanceof Bala) {
              this.square.getBalaAnts().add((Bala) this);
              this.square.getNode().showBalaIcon();
          } else if (this instanceof Forager) {
              this.square.getForagerAnts().add((Forager) this);
              this.square.getNode().showForagerIcon();
          } else if (this instanceof Queen) {
              this.square.getQueenAnts().add((Queen) this);
              this.square.getNode().showQueenIcon();
          } else if (this instanceof Scout) {
              this.square.getScoutAnts().add((Scout) this);
              this.square.getNode().showScoutIcon();
          } else if (this instanceof Soldier) {
              this.square.getSoldierAnts().add((Soldier) this);
              this.square.getNode().showSoldierIcon();
          }
      }

      ///////////////////////////////////////      METHODS     /////////////////////////////////////
     public void move(Square square) {

         // OLD SQUARE
         this.square.getAnts().remove((Ant) this);
         if (this instanceof Bala) {
             this.square.getNode().hideBalaIcon();
         } else if (this instanceof Forager) {
             this.square.getNode().hideForagerIcon();
         } else if (this instanceof Queen) {
             this.square.getNode().hideQueenIcon();
         } else if (this instanceof Scout) {
             this.square.getNode().hideScoutIcon();
         } else if (this instanceof Soldier) {
             this.square.getNode().hideSoldierIcon();
         }
         this.square.getAnts().remove(this);

         //NEW SQUARE
         this.square = square;      // reassigned Ant's square attribute
         this.square.getAnts().add((Ant) this);
         if (this instanceof Bala) {
             this.square.getNode().showBalaIcon();
         } else if (this instanceof Forager) {
             this.square.getNode().showForagerIcon();
         } else if (this instanceof Queen) {
             this.square.getNode().showQueenIcon();
         } else if (this instanceof Scout) {
             this.square.getNode().showScoutIcon();
         } else if (this instanceof Soldier) {
             this.square.getNode().showSoldierIcon();
         }

     }
 }

class Queen extends Ant {


    ////////////////////////////     ATTRIBUTES      //////////////////////////////////////////
     private static final int MAX_LIFESPAN = 20 * 365;


     /////////////////////////////    CONSTRUCTOR     ///////////////////////////////////////
     public Queen(Square square) {

        this.life = MAX_LIFESPAN;

        this.square = square;
    }

    //////////////////////////////      METHODS     ///////////////////////////////////////////////
    private Ant Hatch() {

        int x = random.nextInt(3);

        Ant[] ants = new Ant[3];
        ants[0] = new Forager(this.square);
        ants[1] = new Scout(this.square);
        ants[2] = new Soldier(this.square);

        return ants[x];
    }

    private void consume() {

        //Square square = this.environment.getSquareByCoordinate(this.coordinate);
        if (square.getFood() == 0) {
            this.setAlive(false);
        } else {
            square.setFood(square.getFood() - 1);
        }
    }

    public void DO() {
         this.consume();
         this.Hatch();
    }
}

class Forager extends Ant {

    //////////////////////////////////         ATTRIBUTES      ////////////////////////////////////
    public boolean mode;

    public int food;


    /////////////////////////////////          CONSTRUCTORS   /////////////////////////////////////////////
    public Forager(Square square) {
        super();

        this.square = square;
        //this.square.getForagerAnts().add(this);
    }


    /////////////////////////////////      GETTERS    ///////////////////////////////////////////////////////

    public int getFood() {
        return food;
    }

    public boolean isMode() {
        return mode;
    }

    /////////////////////////////////        SETTERS     ////////////////////////////////////////////////////

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public void setFood(int food) {
        this.food = food;
    }


    //////////////////////////        METHODS      ///////////////////////////////////////////////////
    private void layPheremon(Square square) {
        square.setPheremon(square.getPheremon() + 1);
    }

    private void forage() {


        //must check for square visibility

        Coordinate position = square.getCoordinates();
        Square north = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() + 1));
        Square northeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() + 1 ));
        Square east = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY()));
        Square southeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() -1 ));
        Square south = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() - 1));
        Square southwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() - 1 ));
        Square west = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY()));
        Square northwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() + 1 ));

        Square[] squares = {north, northeast, east, southeast, south, southwest, west, northwest};

        squares = Arrays.stream(squares).filter(square -> square.isVisibility()).toArray(Square[]::new);

        Arrays.sort(squares, Comparator.comparing(Square::getPheremon));

        this.move(squares[0]);
        this.path.add(squares[0]);

    }

    private void returnToNest() {

        this.layPheremon(this.square);

        this.move(this.path.get(this.path.size() - 1));

        this.path.remove(this.path.get(this.path.size() - 1));
    }

    public void DO() {

        if (this.getFood() == 0) {
            this.setMode(true);
        }

        if (this.isMode()) {
            this.forage();
        } else {
            this.returnToNest();
        }

    }

}

class Scout extends Ant {

    ////////////////////////       CONSTRUCTOR    ///////////////////////////////////////////////
    public Scout(Square square) {

        super();
        this.square = square;
        //this.square.getScoutAnts().add(this);
    }


    ///////////////////////////     METHODS        /////////////////////////////////////////////
    private void reveal() {

        //Square square = this.environment.getSquareByCoordinate(this.coordinate);
        square.setVisibility(true);

    }

    private void scout() {


        Coordinate position = square.getCoordinates();
        Square north = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() + 1));
        Square northeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() + 1 ));
        Square east = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY()));
        Square southeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() -1 ));
        Square south = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() - 1));
        Square southwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() - 1 ));
        Square west = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY()));
        Square northwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() + 1 ));

        Square[] squares = {north, northeast, east, southeast, south, southwest, west, northwest};

        squares = Arrays.stream(squares).filter(square -> square.isVisibility()).toArray(Square[]::new);


        if (squares.length > 0) {
            this.move(squares[0]);
        }

        this.reveal();
    }

    public void DO() {

        this.reveal();

        this.scout();
    }
}

class Soldier extends Ant {

    /////////////////////////    ATTRIBUTES     ////////////////////////////////////////////////////////

    private boolean mode;


    //////////////////////          CONSTRUCTOR        ///////////////////////////////////////////////
    public Soldier(Square square) {
        super();
        this.square = square;
        //this.square.getSoldierAnts().add(this);
    }


    /////////////////////////      GETTERS /////////////////////////////////////////////////////////

    public boolean isMode() {
        return mode;
    }

    /////////////////////////////////       SETTERS       /////////////////////////////////////////////////////

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    //////////////////////////////       METHODS        //////////////////////////////////////////////////////////////
    private void scout() {

        Coordinate position = square.getCoordinates();
        Square north = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() + 1));
        Square northeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() + 1 ));
        Square east = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY()));
        Square southeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() -1 ));
        Square south = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() - 1));
        Square southwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() - 1 ));
        Square west = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY()));
        Square northwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() + 1 ));

        Square[] squares = {north, northeast, east, southeast, south, southwest, west, northwest};

        squares = Arrays.stream(squares).filter(square -> square.isVisibility()).toArray(Square[]::new);

        Arrays.sort(squares, Comparator.comparing(Square::countEnemyAnts));

        this.move(squares[0]);
    }


    // FINISH LOGIC
    private void attack() {

        for (Ant ant: this.square.getAnts()
        ) {
            if (!ant.isFriendly()) {

                ant.setAlive(false);
                this.square.getAnts().remove(ant);
                this.square.getBalaAnts().remove((Bala) ant);

                break;
            }
        }

    }

    public void DO() {

        if (this.square.getBalaAnts().size() != 0) {

            this.setMode(true);

        } else {

            this.setMode(false);

        }

        if (this.mode) {

            this.attack();

        } else {

            this.scout();

        }

    }

}

class Bala extends Ant {


    ////////////////////////////////////    ATTRIBUTES    ///////////////////////////////////////////////////////

    private boolean mode;

    ////////////////////////////////        CONSTRUCTOR         /////////////////////////////////////////////////////
    public Bala(Square square) {
        super();
        this.square = square;
        //this.square.getBalaAnts().add(this);
        this.friendly = false;
    }


    ///////////////////////////////////////   GETTERS    //////////////////////////////////////////////////////////////


    public boolean isMode() {
        return mode;
    }

    ///////////////////////////////////////////      SETTERS         ///////////////////////////////////////////////////


    public void setMode(boolean mode) {
        this.mode = mode;
    }

    //////////////////////////////////////       METHODS        ///////////////////////////////////////////////////////////

    public void scout() {

        Coordinate position = square.getCoordinates();
        Square north = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() + 1));
        Square northeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() + 1 ));
        Square east = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY()));
        Square southeast = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() + 1, position.getY() -1 ));
        Square south = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX(), position.getY() - 1));
        Square southwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() - 1 ));
        Square west = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY()));
        Square northwest = this.environment.getSquareByCoordinate(position.setCoordinate(position.getX() - 1, position.getY() + 1 ));

        Square[] squares = {north, northeast, east, southeast, south, southwest, west, northwest};

        Arrays.sort(squares, Comparator.comparing(Square::countFriendAnts));

        this.move(squares[0]);
    }

    // FINISH LOGIC
    public void attack() {

        for (Ant ant: this.square.getAnts()
             ) {
            if (ant.isFriendly()) {

                ant.setAlive(false);
                this.square.getAnts().remove(ant);
                if (ant instanceof Queen) {
                    this.square.getQueenAnts().remove((Queen) ant);
                } else if (ant instanceof Forager) {
                    this.square.getForagerAnts().remove((Forager) ant);
                } else if (ant instanceof Scout) {
                    this.square.getScoutAnts().remove((Scout) ant);
                } else if (ant instanceof Soldier) {
                    this.square.getSoldierAnts().remove((Soldier) ant);
                }
                break;
            }
        }

    }

    public void DO() {

        if (this.square.getForagerAnts().size() != 0 ||
                this.square.getScoutAnts().size() != 0 ||
                this.square.getSoldierAnts().size() != 0 ||
                this.square.getQueenAnts().size() != 0
        ) {

            this.setMode(true);

        } else {

            this.setMode(false);

        }
        if (this.isMode()) {

            this.attack();

        } else {

            this.scout();

        }

    }



}