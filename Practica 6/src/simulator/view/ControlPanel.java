package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{

	private Controller _ctrl;
	private volatile Thread _thread;
	
	// Atributos para JToolBar, botones, etc
	
	JFrame ventanaToolBar, frame;
	JToolBar toolBar, barraBotones;
	JSpinner numpasos,numpasosDelay;
	JTextField delTime;
	JButton file, glaw, play, stop, close;
	JLabel spinnerDelayLabel, spinnerLabel, delTimeLabel;
	private String[] valores = {
			"Newton's law of universal gravitation (nlug)",
			"Falling to center gravity (ftcg)",
			"No gravity (ng)"
		};
	Double auxNumPasos,auxNumPasosDelay;
	
	ControlPanel(Controller ctrl){
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		//construir la tool bar con todos sus 
		// botones, etc
		toolBar = doToolBar();
	}
	
	private JToolBar doToolBar() {
		barraBotones = new JToolBar();
		file = new JButton();
		file.setToolTipText("Load bodies file into the editor");
		file.setIcon(new ImageIcon("resources/icons/open.png"));
		file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				jButtonFileActionPerformed();
			}			
		});
		
		glaw = new JButton();
		glaw.setToolTipText("Load a gravity laws into the editor");
		glaw.setIcon(new ImageIcon("resources/icons/physics.png"));
		glaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				jButtonGLawActionPerformed();	
			}
		});
			
		play = new JButton();
		play.setToolTipText("Start the simulation.");
		play.setIcon(new ImageIcon("resources/icons/run.png"));
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{				
				try {
					file.setEnabled(false);
					glaw.setEnabled(false);
					play.setEnabled(false);					
					close.setEnabled(false);
					stop.setEnabled(true);
					
					_thread = new Thread(new Runnable() {
						public void run() {
							_ctrl.setDeltaTime(Double.parseDouble(delTime.getText()));
							auxNumPasos = (Double) numpasos.getValue();
							auxNumPasosDelay = (Double) numpasosDelay.getValue();
							try {
								for(int i = 0; i < auxNumPasos.intValue(); ++i) {
									run_sim(i,auxNumPasosDelay.longValue());	
								}
							}
							catch(InterruptedException e) {
								//System.out.println("##### Thread interrupt !");
								_thread.interrupt();
							}								
							file.setEnabled(true);
							glaw.setEnabled(true);
							play.setEnabled(true);
							stop.setEnabled(false);
							close.setEnabled(true);
							_thread = null;
						}
					});
					_thread.start();
				}
				catch(Exception e) {
					final JPanel errorPanel = new JPanel();
					JOptionPane.showMessageDialog(errorPanel, "The application has found an error!", "Error", JOptionPane.ERROR_MESSAGE);
					file.setEnabled(true);
					glaw.setEnabled(true);
					play.setEnabled(true);
					stop.setEnabled(false);
					close.setEnabled(true);
				}					
			}
		});
		
		
		stop = new JButton();
		stop.setToolTipText("Stop the simulation.");
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));		
		stop.setEnabled(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(_thread != null) {
					_thread.interrupt();
				}
			}
		});
		
		spinnerDelayLabel = new JLabel(" Delay: ");
		
		spinnerDelayCreation();
		
		spinnerLabel = new JLabel(" Steps: ");
		
		spinnerCreation();
		
		delTimeLabel = new JLabel(" Delta-Time: ");
		/*
		 * NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter numberFormatter = new NumberFormatter(format);
	    numberFormatter.setValueClass(Double.class);
	    numberFormatter.setMinimum(0);
	    numberFormatter.setMaximum(Double.MAX_VALUE);
	    numberFormatter.setAllowsInvalid(false);
	    numberFormatter.setCommitsOnValidEdit(true);
		delTime = new JFormattedTextField(numberFormatter);
		*/
		delTime = new JTextField("10000.0");
		delTime.setPreferredSize(new Dimension(100,50));
		delTime.setMinimumSize(new Dimension(100,50));
		delTime.setMaximumSize(new Dimension(100,50));
		
		close = new JButton();
		close.setToolTipText("Close the editor.");
		close.setIcon(new ImageIcon("resources/icons/exit.png"));
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int opcion = JOptionPane
						.showConfirmDialog(
								null, 
								"Are you sure?", 
								"Warning!", 
								JOptionPane.YES_NO_OPTION);
				if(opcion == 0) {
					// SI
					System.exit(0);
				}
				// la opción NO no es necesaria hacerla
			}
		});
		
		barraBotones.add(file);
		barraBotones.add(glaw);
		barraBotones.add(play);
		barraBotones.add(stop);
		barraBotones.add(spinnerDelayLabel);
		barraBotones.add(numpasosDelay);
		barraBotones.add(spinnerLabel);
		barraBotones.add(numpasos);
		barraBotones.add(delTimeLabel);
		barraBotones.add(delTime);	
		
		JPanel auxPanel = new JPanel();
		auxPanel.setLayout(new BorderLayout());
		auxPanel.add(close,BorderLayout.EAST);
		barraBotones.add(auxPanel);
		
		barraBotones.setPreferredSize(new Dimension(1065, 50));
		return barraBotones;
	}
	
	public JToolBar getToolBar() {
		return this.toolBar;
	}
	
	private void spinnerDelayCreation() {
		double min = 0.000;
        double value = 1.000;
        double max = 1000.000;
        double stepSize = 1;
        SpinnerNumberModel valueSpinner = new SpinnerNumberModel(value,min,max,stepSize);
		numpasosDelay = new JSpinner(valueSpinner);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor)numpasosDelay.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(0);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
                
        numpasosDelay.setPreferredSize(new Dimension(100,50));
        numpasosDelay.setMinimumSize(new Dimension(100,50));
        numpasosDelay.setMaximumSize(new Dimension(100,50));
	}
	
	private void spinnerCreation() {
		double min = 0.000;
        double value = 25.000;
        double max = 1000000.000;
        double stepSize = 1;
        SpinnerNumberModel valueSpinner = new SpinnerNumberModel(value,min,max,stepSize);
		numpasos = new JSpinner(valueSpinner);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor)numpasos.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(3);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
                
        numpasos.setPreferredSize(new Dimension(100,50));
        numpasos.setMinimumSize(new Dimension(100,50));
        numpasos.setMaximumSize(new Dimension(100,50));
	}
	
	private void jButtonFileActionPerformed() {
		JFileChooser selectorFichero = new JFileChooser();
		
		if(selectorFichero.showOpenDialog(barraBotones) == JFileChooser.APPROVE_OPTION) 
		{
			selectorFichero.setFileSelectionMode(JFileChooser.FILES_ONLY);
			File archivoElegido = selectorFichero.getSelectedFile();
			// muestra error si es inválido
			if(archivoElegido == null)
				JOptionPane.showMessageDialog(selectorFichero, "Nombre invalido del archivo", "Nombre invalido del archivo", JOptionPane.ERROR_MESSAGE);
			try {
			// hacer algo con el archivoElegido
			System.out.println(archivoElegido);
			InputStream in = new FileInputStream(archivoElegido);
			
			_ctrl.reset();
			_ctrl.loadBodies(in);
			}
			catch(IllegalArgumentException e) {
				System.err.println("Error en el fichero : " + archivoElegido);
			}
			catch(FileNotFoundException e) {
				System.err.println("Fichero erroneo");
			}
			catch(Exception e) {
				System.err.println("Fichero erroneo");
			}
		}
	}
	
	private void jButtonGLawActionPerformed() {
		
		JPanel comboPanel1 = new JPanel(new BorderLayout());					
		JPanel comboPanel2 = new JPanel(new FlowLayout());	
		JPanel comboPanel3 = new JPanel(new FlowLayout());	
		JPanel comboPanel4 = new JPanel(new BorderLayout());	
		
		JComboBox myCombo = new JComboBox(valores);				
		JLabel myComboDescription = new JLabel("    Select gravity laws to be used:");
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		
		/////////////////////////
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String leyElegida;
				if(myCombo.getSelectedItem().equals("Newton's law of universal gravitation (nlug)")) {
					leyElegida = "nlug";
				}
				else if(myCombo.getSelectedItem().equals("Falling to center gravity (ftcg)")) {
					leyElegida = "ftcg";
				}
				else {
					leyElegida = "ng";
				}
				for (JSONObject fe : _ctrl.getGravityLawsFactory().getInfo()) 
				{
					if (leyElegida.equals(fe.getString("type"))) {
						//System.out.println("%%%%%% Type: " + fe.getString("type"));
						_ctrl.setGravityLaws(fe);
						break;
					}
				}
				frame.dispose();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
		
		/////////////////////////

        comboPanel1.add(myComboDescription, BorderLayout.WEST);
        comboPanel2.add(myCombo);
        comboPanel3.add(cancelButton);
        comboPanel3.add(okButton);
        comboPanel4.add(comboPanel3, BorderLayout.EAST);
        
        /////////////////////////

		frame = new JFrame("Gravity Laws Selector");
		frame.setLayout(new GridLayout(3, 1)); 
		frame.add(comboPanel1);
		frame.add(comboPanel2);
		frame.add(comboPanel4);
		frame.setSize(500,150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private  void run_sim(int n, long delay) throws InterruptedException {
		// (the current thread has not been intereptred) 	
		while (n > 0) 
		{
			// 1. execute the simulator one step, i.e., call method		
			// _ctrl.run(1) and handle exceptions if any
			try {
				_ctrl.run(1);
			}
			catch(Exception e) {
				// muestra el error con una ventana 				
				final JPanel errorPanel = new JPanel();
				JOptionPane.showMessageDialog(errorPanel, "The application has found an error!", "Error", JOptionPane.ERROR_MESSAGE);								
				return;
			}
			// 2. sleep the current thread for ’delay’ milliseconds
			Thread.sleep(delay);
			n--;
		}			
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
				delTime.setText(Double.toString(dt));		
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
				delTime.setText(Double.toString(dt));		
			}
	    }); 	
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub		
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				delTime.setText(Double.toString(dt));		
			}
	    });
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

}
