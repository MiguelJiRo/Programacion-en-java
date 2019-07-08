package simulator.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver{

	private static final int _WIDTH = 1000;
	private static final int _HEIGHT = 1000;
	// a�ade componentes para los colores
	private Color rojo = new Color(255,0,0);
	private Color azul = new Color(0,0,255);
	private Color negro = new Color(0,0,0);
	private Color blanco = new Color(255,255,255);
	
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	
	Viewer(Controller ctrl){		
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// Suma border con title
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black,2),
				"Viewer",
				TitledBorder.LEFT,
				TitledBorder.TOP));
		
		setPreferredSize(new Dimension(_WIDTH,_HEIGHT));
		
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch(e.getKeyChar()) {
					case '-':
						_scale = _scale * 1.1;
						break;
					case '+':
						_scale = Math.max(1000.0, _scale / 1.1);
						break;
					case '=':
						autoScale();
						break;
					case 'h':
						_showHelp = !_showHelp;
						break;
					default:
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}			
		});
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// use �gr� to draw not �g�
		
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		
		// TODO draw a cross at center
		gr.setColor(this.rojo);
		int s = 5;
		gr.drawLine(
				this._centerX-s, 
				this._centerY,
				this._centerX+s, 
				this._centerY);
		gr.drawLine(
				this._centerX, 
				this._centerY-s,
				this._centerX, 
				this._centerY+s);
		
		// TODO draw bodies		
		
		for(int i = 0; i < _bodies.size(); ++i) {
			//System.out.println("YYYYY i -> " + i);
			Double cordX = _bodies.get(i).getPosition().coordinate(0);
			Double cordY = _bodies.get(i).getPosition().coordinate(1);
			//System.out.println("YYYYY x -> " + (int) (cordX/_scale));
			//System.out.println("YYYYY y -> " + (int) (cordY/_scale));
			//System.out.println("YYYYY scale -> " + _scale);
			gr.setColor(this.negro);
			gr.drawString(
					_bodies.get(i).getId(), 
					this._centerX+(int) (cordX/_scale), 
					this._centerY+(int) (cordY/_scale));
			gr.setColor(this.azul);			
			gr.fillOval(
					this._centerX+(int) (cordX/_scale), 
					this._centerY+(int) (cordY/_scale), 
					10, 
					10);
		}
		
		// TODO draw help if _showHelp is true
		if (_showHelp)
		{
			gr.setColor(this.rojo);
			gr.drawString(
					"h: toggle help, +: zoom-in, -: zoom-out, =: fit",
					10, 
					25);
			gr.drawString(
					"Scaling ratio: " + _scale,
					10, 
					40);
		}
	}
	
	// other private/protected methods
	// ...
	
	private void autoScale()
	{
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
						Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),(double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	
	// SimulatorObserver methods
	// ...
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		//System.out.println("WWWWW 1 onRegister");
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();		
			}
	    });		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		//System.out.println("WWWWW 2 onReset");
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();		
			}
	    });	
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		//System.out.println("WWWWW 3 onBodyAdded");
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();		
			}
	    });	
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		//System.out.println("WWWWW 4 onAdvance");
		SwingUtilities.invokeLater(new Runnable() 
	    {
			@Override
			public void run() {
				_bodies = bodies;
				repaint();		
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