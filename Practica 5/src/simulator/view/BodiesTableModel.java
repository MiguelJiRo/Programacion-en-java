package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver{

	// añade los nombres de columnas
	private List<Body> _bodies;
	private Controller _ctrl;
	private String[] columnNames = {
			"Id",
			"Mass",
			"Position",
			"Velocity",
			"Acceleration"
			};
	
	BodiesTableModel(Controller ctrl){
		_ctrl = ctrl;
		_bodies = new ArrayList<>();
		_ctrl.addObserver(this);
		_bodies = _ctrl.getListaBodies();
	}
	
	////////////////////////////////////////////
	////// Metodos de AbstractTableModel ///////
	////////////////////////////////////////////
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return _bodies.size();
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// int rowIndex, int columIndex
	@Override
	public Object getValueAt(int rowIndex, int columIndex) {
		// TODO Auto-generated method stub
		Body b = _bodies.get(rowIndex);
		switch(columIndex)
		{
			case 0:
				return b.getId();
			case 1:
				return b.getMass();
			case 2:
				return b.getPosition();
			case 3:
				return b.getVelocity();
			case 4:
				return b.getAcceleration();				
		}		
		return "ERROR";
	}
	
	////////////////////////////////////////////
	/////// Metodos de SimulatorObserver ///////
	////////////////////////////////////////////
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_bodies = bodies;
				fireTableStructureChanged();
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
				// TODO Auto-generated method stub
				_bodies = bodies;
				fireTableStructureChanged();
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
				// TODO Auto-generated method stub
				_bodies = bodies;
				fireTableStructureChanged();
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
				// TODO Auto-generated method stub
				_bodies = bodies;
				fireTableStructureChanged();
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
		
	}
}
