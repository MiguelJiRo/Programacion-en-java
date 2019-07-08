package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	// Añade atributos para todos los componentes
	// ( clases )
	Controller _ctrl;
	ControlPanel _controlPanel;
	BodiesTable _bodiesT;
	Viewer _viewer;
	StatusBar _statusBar;
	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(centerPanel,BoxLayout.Y_AXIS);
		centerPanel.setLayout(boxlayout);
		
		setContentPane(mainPanel);
		_controlPanel = new ControlPanel(_ctrl);
		_bodiesT = new BodiesTable(_ctrl);
		_viewer = new Viewer(_ctrl);
		_statusBar = new StatusBar(_ctrl);
		// Barra de control en PAGE_START
		mainPanel.add(_controlPanel.getToolBar(),BorderLayout.PAGE_START);
		
		// Paneles centrales en CENTER
		centerPanel.add(_bodiesT);
		centerPanel.add(_viewer);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		
		// Barra de estados en PAGE_END
		mainPanel.add(_statusBar,BorderLayout.PAGE_END);
		
		mainPanel.setVisible(true);
		setPreferredSize(new Dimension(1087,600));
		setLocationRelativeTo(null);
		
		this.pack();
	}
	
	// Añade private/protected methods
	
	
}
