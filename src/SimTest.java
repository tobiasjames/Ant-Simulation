import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *	Written by Roger West
 *	University of Illinois at Springfield
 *	Jan 9, 2013
 */
public class SimTest implements SimulationEventListener, ActionListener
{

    //-------------------------------------------------------------------------
    //	Constants
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //	Attributes
    //-------------------------------------------------------------------------

    private int turnCounter;

    private AntSimGUI gui;

    private Timer swingTimer;

    private Simulation_Manager simulationManager;

    //-------------------------------------------------------------------------
    //	Constructors
    //-------------------------------------------------------------------------

    public SimTest(AntSimGUI gui)
    {
        this.gui = gui;
        this.turnCounter = 0;

        gui.addSimulationEventListener(this);

        this.simulationManager = new Simulation_Manager();

        Environment environment = simulationManager.getEnvironment();
        gui.initGUI(environment.getColonyView());

    }

    //-------------------------------------------------------------------------
    //	Methods
    //-------------------------------------------------------------------------

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        AntSimGUI gui = new AntSimGUI();
        SimTest st = new SimTest(gui);

    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        gui.setTime("Turn: " + turnCounter++);
    }

    @Override
    public void simulationEventOccurred(SimulationEvent simEvent)
    {

        if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT)
        {
            // set up initial state of the simulation
            JOptionPane.showMessageDialog(null, "Normal Setup Event", "Normal Setup", JOptionPane.INFORMATION_MESSAGE);

            simulationManager.normalSetup();

        }
        else if (simEvent.getEventType() == SimulationEvent.QUEEN_TEST_EVENT)
        {
            // set up antSim.simulation for testing the queen ant
            JOptionPane.showMessageDialog(null, "Queen Test Event", "Queen Test", JOptionPane.INFORMATION_MESSAGE);

            simulationManager.queenSetup();

        }
        else if (simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT)
        {
            // set up antSim.simulation for testing the scout ant
            JOptionPane.showMessageDialog(null, "Scout Test Event", "Scout Test", JOptionPane.INFORMATION_MESSAGE);

            simulationManager.scoutSetup();

        }
        else if (simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT)
        {
            // set up antSim.simulation for testing the forager ant
            JOptionPane.showMessageDialog(null, "Forager Test Event", "Forager Test", JOptionPane.INFORMATION_MESSAGE);

            simulationManager.foragerSetup();

        }
        else if (simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT)
        {
            // set up antSim.simulation for testing the soldier ant
            JOptionPane.showMessageDialog(null, "Soldier Test Event", "Soldier Test", JOptionPane.INFORMATION_MESSAGE);

            simulationManager.soldierSetup();

        }
        else if (simEvent.getEventType() == SimulationEvent.RUN_EVENT)
        {
            swingTimer = new Timer(1000, this);

            swingTimer.start();
        }
        else if (simEvent.getEventType() == SimulationEvent.STEP_EVENT)
        {
            gui.setTime("Turn: " + turnCounter++);

            simulationManager.step();
        }
        else
        {
            // invalid event occurred

        }
    }


}