import java.util.ArrayList;

public class Simulation_Manager {


    //////////////////////////////////       ATTRIBUTES            //////////////////////////////////////////////

    private Environment environment;

    private ColonyView colonyView;

    private ArrayList<Ant> ants;



    ////////////////////////////////////////     CONTRUCTORS          //////////////////////////////////////////////////////////

    public Simulation_Manager() {

        this.environment = new Environment();
        this.colonyView = this.environment.getColonyView();
        this.ants = this.environment.getAnts();

    }


    ///////////////////////////////////////////          GETTERS         ////////////////////////////////////////////////////////
    public Environment getEnvironment() {
        return environment;
    }

    public ColonyView getColonyView() {
        return colonyView;
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }

    ///////////////////////////////////////////             SETTERS          ////////////////////////////////////////////////////
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setColonyView(ColonyView colonyView) {
        this.colonyView = colonyView;
    }

    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }

    ////////////////////////////////////////      SETUP    METHODS             ///////////////////////////////////////////////

    // return COLONYVIEW, not ENVIRONMENT
    public void normalSetup() {

        for (int i = 0; i < 729; i++) {

            ColonyNodeView colonyNodeView = new ColonyNodeView();
            colonyNodeView.setID("( " + (i % 27) + " , " + (i / 27) + " )");
            this.colonyView.addColonyNodeView(colonyNodeView, i % 27, i / 27);

        }

        Coordinate[] centerCoordinates = {

                new Coordinate(13, 13),
                new Coordinate(13, 14),
                new Coordinate(14, 14),
                new Coordinate(14, 13),
                new Coordinate(14, 12),
                new Coordinate(13, 12),
                new Coordinate(12, 12),
                new Coordinate(12, 13),
                new Coordinate(12, 14)};

        //make center gridsquares visible
        for (Coordinate coordinate : centerCoordinates
        ) {

            Square square = environment.getSquareByCoordinate(coordinate);
            square.setVisibility(true);

        }

        //set center gridsquare initial food level
        Square center = environment.getSquareByCoordinate(centerCoordinates[0]);

        center.setFood(999);

        // 1 queen, 10 soldiers, 50 foragers, and 4 scouts
        ants.add(new Queen(center));


        for (int i = 0; i < 10; i++) {

            ants.add(new Soldier(center));

        }

        for (int i = 0; i < 50; i++) {

            ants.add(new Forager(center));

        }

        for (int i = 0; i < 4; i++) {

            ants.add(new Scout(center));

        }



        //place all ants at the center
        for (Ant ant : ants
        ) {
            ant.setSquare(center);
            ant.setEnvironment(environment);
        }

        center.setAnts(ants);

    }

    public void queenSetup() {

        // set up simulation for testing the queen ant

        Coordinate[] centerCoordinates = {

                new Coordinate(13, 13),
                new Coordinate(13, 14),
                new Coordinate(14, 14),
                new Coordinate(14, 13),
                new Coordinate(14, 12),
                new Coordinate(13, 12),
                new Coordinate(12, 12),
                new Coordinate(12, 13),
                new Coordinate(12, 14)};

        //make center gridsquares visible
        for (Coordinate coordinate : centerCoordinates
        ) {

            Square square = environment.getSquareByCoordinate(coordinate);
            square.setVisibility(true);

        }

        //set center gridsquare initial food level
        Square center = environment.getSquareByCoordinate(centerCoordinates[0]);

        center.setFood(999);

        for (int i = 0; i < 729; i++) {

            ColonyNodeView colonyNodeView = new ColonyNodeView();
            colonyNodeView.setID("( " + (i % 27) + " , " + (i / 27) + " )");
            this.colonyView.addColonyNodeView(colonyNodeView, i % 27, i / 27);

        }

        // 1 queen, 10 soldiers, 50 foragers, and 4 scouts
        ants.add(new Queen(center));


        //place all ants at the center
        for (Ant ant : ants
        ) {
            ant.setEnvironment(environment);
        }

        center.setAnts(ants);

    }

    public void foragerSetup() {

        // set up simulation for testing the forager ant


        for (int i = 0; i < 729; i++) {

            ColonyNodeView colonyNodeView = new ColonyNodeView();
            colonyNodeView.setID("( " + (i % 27) + " , " + (i / 27) + " )");
            this.colonyView.addColonyNodeView(colonyNodeView, i % 27, i / 27);

        }

        Coordinate[] centerCoordinates = {

                new Coordinate(13, 13),
                new Coordinate(13, 14),
                new Coordinate(14, 14),
                new Coordinate(14, 13),
                new Coordinate(14, 12),
                new Coordinate(13, 12),
                new Coordinate(12, 12),
                new Coordinate(12, 13),
                new Coordinate(12, 14)};

        //make center gridsquares visible
        for (Coordinate coordinate : centerCoordinates
        ) {

            Square square = environment.getSquareByCoordinate(coordinate);
            square.setVisibility(true);

        }

        //set center gridsquare initial food level
        Square center = environment.getSquareByCoordinate(centerCoordinates[0]);

        center.setFood(999);

        // 1 queen, 10 soldiers, 50 foragers, and 4 scouts
        ants.add(new Queen(center));


        for (int i = 0; i < 50; i++) {

            ants.add(new Forager(center));

        }

        //place all ants at the center
        for (Ant ant : ants
        ) {
            ant.setSquare(center);
            ant.setEnvironment(environment);
        }

        center.setAnts(ants);

    }

    public void soldierSetup() {

        // set up simulation for testing the soldier ant


        for (int i = 0; i < 729; i++) {

            ColonyNodeView colonyNodeView = new ColonyNodeView();
            colonyNodeView.setID("( " + (i % 27) + " , " + (i / 27) + " )");
            this.colonyView.addColonyNodeView(colonyNodeView, i % 27, i / 27);

        }

        Coordinate[] centerCoordinates = {

                new Coordinate(13, 13),
                new Coordinate(13, 14),
                new Coordinate(14, 14),
                new Coordinate(14, 13),
                new Coordinate(14, 12),
                new Coordinate(13, 12),
                new Coordinate(12, 12),
                new Coordinate(12, 13),
                new Coordinate(12, 14)};

        //make center gridsquares visible
        for (Coordinate coordinate : centerCoordinates
        ) {

            Square square = environment.getSquareByCoordinate(coordinate);
            square.setVisibility(true);

        }

        //set center gridsquare initial food level
        Square center = environment.getSquareByCoordinate(centerCoordinates[0]);

        center.setFood(999);

        // 1 queen, 10 soldiers, 50 foragers, and 4 scouts
        ants.add(new Queen(center));


        for (int i = 0; i < 10; i++) {

            ants.add(new Soldier(center));

        }


        //place all ants at the center
        for (Ant ant : ants
        ) {
            ant.setSquare(center);
            ant.setEnvironment(environment);
        }

        center.setAnts(ants);
    }

    public void scoutSetup() {

        // set up simulation for testing the scout ant

        Coordinate[] centerCoordinates = {

                new Coordinate(13, 13),
                new Coordinate(13, 14),
                new Coordinate(14, 14),
                new Coordinate(14, 13),
                new Coordinate(14, 12),
                new Coordinate(13, 12),
                new Coordinate(12, 12),
                new Coordinate(12, 13),
                new Coordinate(12, 14)};

        //make center gridsquares visible
        for (Coordinate coordinate : centerCoordinates
        ) {

            Square square = environment.getSquareByCoordinate(coordinate);
            square.setVisibility(true);

        }

        //set center gridsquare initial food level
        Square center = environment.getSquareByCoordinate(centerCoordinates[0]);

        center.setFood(999);

        for (int i = 0; i < 729; i++) {

            ColonyNodeView colonyNodeView = new ColonyNodeView();
            colonyNodeView.setID("( " + (i % 27) + " , " + (i / 27) + " )");
            this.colonyView.addColonyNodeView(colonyNodeView, i % 27, i / 27);

        }

        // 1 queen, 10 soldiers, 50 foragers, and 4 scouts
        ants.add(new Queen(center));

        for (int i = 0; i < 4; i++) {

            ants.add(new Scout(center));

        }

        //place all ants at the center
        for (Ant ant : ants
        ) {
            ant.setSquare(center);
            ant.setEnvironment(environment);
        }

        center.setAnts(ants);

    }


    ///////////////////////////////////////////    RUN    METHODS        ///////////////////////////////////////////////
    public void run () {


    }

    public void step () {

        for (Ant ant: this.getEnvironment().getAnts()
             ) {

            if (ant instanceof Queen) {
                ((Queen) ant).DO();
            } else if (ant instanceof Scout) {
                ((Scout) ant).DO();
            } else if (ant instanceof Forager) {
                ((Forager) ant).DO();
            } else if (ant instanceof Soldier) {
                ((Soldier) ant).DO();
            } else if (ant instanceof Bala) {
                ((Bala) ant).DO();
            }


        }

    }
}
