package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver{

	// ...
	private Controller _ctrl;
	
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	private JLabel _currTimeName,_currLawsName,_numOfBodiesName;
	private List<Body> _bodies;
	private double _time;
	private String _gLawsDesc;
	StatusBar(Controller ctrl) {
		
		_ctrl = ctrl;		
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		// TODO complete the code to build the tool bar
		_currTime = new JLabel(" Time: ");
		_numOfBodies = new JLabel("           Bodies: ");
		_currLaws = new JLabel("           Laws: ");
		
		_currTimeName = new JLabel();
		_numOfBodiesName = new JLabel();
		_currLawsName = new JLabel();
		
		//_currTimeName.setText(Double.toString(_time)); 
		//_numOfBodiesName.setText(Integer.toString(_bodies.size()));
		//_currLawsName.setText(_gLawsDesc); 
		
		this.add(_currTime);
		this.add(_currTimeName);
		this.add(_numOfBodies);
		this.add(_numOfBodiesName);
		this.add(_currLaws);
		this.add(_currLawsName);
	}
	
	// other private/protected methods
	// ...
	
	// SimulatorObserver methods
	// ...
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub		
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_currTimeName.setText(Double.toString(time)); 
				_numOfBodiesName.setText(Integer.toString(bodies.size()));
				_currLawsName.setText(gLawsDesc); 			
			}
	    });
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub		
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_currTimeName.setText(Double.toString(time)); 
				_numOfBodiesName.setText(Integer.toString(bodies.size()));
				_currLawsName.setText(gLawsDesc); 			
			}
	    });
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub				
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_numOfBodiesName.setText(Integer.toString(bodies.size()));			
			}
	    });
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub		
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_currTimeName.setText(Double.toString(time)); 
				_numOfBodiesName.setText(Integer.toString(bodies.size()));			
			}
	    });
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_currLawsName.setText(gLawsDesc); 			
			}
	    });
	}

}
