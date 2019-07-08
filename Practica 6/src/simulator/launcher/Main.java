package simulator.launcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/*
 * Examples of command-line parameters:
 * 
 *  -h
 *  -i resources/examples/ex4.4body.txt -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl ftcg
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl nlug
 *
 */

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.BasicBodyBuilder;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.FallingToCenterGravityBuilder;
import simulator.factories.MassLosingBodyBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoGravityBuilder;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.view.MainWindow;

public class Main {
	/*
-gl ftcg   	falling to center
-gl nlug 	newton universal 
-gl ng 		no gravity
	 */
	/*
-i resources/examples/ex4.4body.txt
-o resources/examples/ex4.4body.out -s 100 -gl nlug
	*/
	/*
-i resources/examples/ex3.4body.txt
-o resources/examples/ex3.4body.out -s 100 -gl nlug
	*/
	/*
-i resources/examples/ex2.3body.txt
-o resources/examples/ex2.3body.out -s 100 -gl nlug
	*/
	/*
-i resources/examples/ex1.2body.txt
-o resources/examples/ex1.2body.out -s 100 -gl nlug
	*/
	/*
	 PR6
	 -i resources/examples/ex4.4body.txt
	-o resources/examples/ex4.4body.out -s 100 -gl nlug -m gui
	 */
	// default values for some parameters
	//
	private final static Double _dtimeDefaultValue = 2500.0;
	private final static int _dStepsDefaultValue = 150;

	// some attributes to stores values corresponding to command-line parameters
	//
	private static Double _dtime = null;
	private static String _inFile = null;
	private static String _outFile = null;
	private static JSONObject _gravityLawsInfo = null;
	private static int _steps;
	private static String _mode = null;
	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<GravityLaws> _gravityLawsFactory;

	private static void init() {
		// initialize the bodies factory
		// ... body builders
		ArrayList<Builder<Body>> b = new ArrayList<>();
		b.add(new BasicBodyBuilder());
		b.add(new MassLosingBodyBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(b);
		// initialize the gravity laws factory
		// ... gravity laws
		ArrayList<Builder<GravityLaws>> gl = new ArrayList<>();
		gl.add(new NewtonUniversalGravitationBuilder());
		gl.add(new FallingToCenterGravityBuilder());
		gl.add(new NoGravityBuilder());
		_gravityLawsFactory = new BuilderBasedFactory<GravityLaws>(gl);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);

			parseModeOption(line);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseDeltaTimeOption(line);
			parseGravityLawsOption(line);
			parseOutFileOption(line);
			parseStepsOption(line);
			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}
	// revisado
	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		// help
		cmdLineOptions.addOption(Option.builder("h")
				.longOpt("help")
				.desc("Print this message.")
				.build());

		// input file
		cmdLineOptions.addOption(Option.builder("i")
				.longOpt("input").hasArg()
				.desc("Bodies JSON input file.")
				.build());

		// output file
		cmdLineOptions.addOption(Option.builder("o")
				.longOpt("output").hasArg()
				.desc("Bodies JSON output file.")
				.build());
		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());

		// steps
		cmdLineOptions.addOption(Option.builder("s")
				.longOpt("steps").hasArg()
				.desc("Number of steps. Default: 150")
				.build());
		// startBatchMode o startGUIMode
		cmdLineOptions.addOption(Option.builder("m")
				.longOpt("mode").hasArg()
				.desc("Execution Mode. Possible values: 'batch' (Batch mode), 'gui' (Graphical User Interface mode). Default value: 'batch'.")
				.build());
		
		// gravity laws -- there is a workaround to make it work even when
		// _gravityLawsFactory is null. 
		//
		String gravityLawsValues = "N/A";
		String defaultGravityLawsValue = "N/A";
		if (_gravityLawsFactory != null) {
			gravityLawsValues = "";
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gravityLawsValues.length() > 0) {
					gravityLawsValues = gravityLawsValues + ", ";
				}
				gravityLawsValues = gravityLawsValues + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}
			defaultGravityLawsValue = _gravityLawsFactory.getInfo().get(0).getString("type");
		}
		cmdLineOptions.addOption(Option.builder("gl").longOpt("gravity-laws").hasArg()
				.desc("Gravity laws to be used in the simulator. Possible values: " + gravityLawsValues
						+ ". Default value: '" + defaultGravityLawsValue + "'.")
				.build());

		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		//System.out.println("##### mode in: " + _mode);
		if (_inFile == null 
				&& _mode.equals("batch")) {
			throw new ParseException("An input file of bodies is required");
		}
	}
	
	/*
	 * Añade la opción -o (o --output) en la línea 
	 * de comandos para que el usuario pueda escribir 
	 * el nombre del fichero en el cual escribir la salida. 
	 * En caso de que no se especifique el fichero de salida, 
	 * se utilizará la salida por consola System.out
	 */
	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
		//System.out.println("##### mode out: " + _mode);
		if (_outFile == null 
				&& _mode.equals("batch")) {
			throw new ParseException("An output file of bodies is required");
		}
	}
	
	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}
	/*
	 * Añade la opción -s (o --steps) en la línea 
	 * de comandos para que el usuario pueda especificar 
	 * el númeode pasos de simulación. Sino se especifica 
	 * nada, el valor por defecto será de 150.
	 */
	private static void parseStepsOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("s", Integer.toString(_dStepsDefaultValue));
		try {
			_steps = Integer.parseInt(dt);
			assert (_steps > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid steps value: " + dt);
		}
	}
	
	private static void parseGravityLawsOption(CommandLine line) throws ParseException {

		// this line is just a work around to make it work even when _gravityLawsFactory
		// is null, you can remove it when've defined _gravityLawsFactory
		if (_gravityLawsFactory == null)
			return;

		String gl = line.getOptionValue("gl");
		if (gl != null) {
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gl.equals(fe.getString("type"))) {
					_gravityLawsInfo = fe;
					break;
				}
			}
			if (_gravityLawsInfo == null) {
				throw new ParseException("Invalid gravity laws: " + gl);
			}
		} else {
			_gravityLawsInfo = _gravityLawsFactory.getInfo().get(0);
		}
	}
	
	private static void parseModeOption(CommandLine line) throws ParseException {
		String mode = line.getOptionValue("m");
		if (mode != null) {
			//System.out.println("##### mode recibido: XXX" + mode + "XXX");
			String batchmode = "batch";
			String guimode = "gui";
			
			if(mode.equals(batchmode)) {
				_mode = mode;
			}
			else if(mode.equals(guimode)) {
				_mode = mode;
			}
			else {
				throw new ParseException("Invalid mode: " + mode);
			}
		} else {
			_mode = "batch";
		}
	}

	/*
	 * crea una instancia del simulador y del 
	 * controlador; establezca las leyes de 
	 * gravedad del simulador de acuerdo con 
	 * lo especificado a través de la opcion
	 * -gl; cree los ficheros correspondientes 
	 * de entrada y salida teniendo en cuenta 
	 * las opciones -i y -o, añadalos cuerpos 
	 * al simulador (invocando al método 
	 * loadBodies del controlador); e iniciela 
	 * simulación llamando al método run del 
	 * controlador
	 */
	private static void startBatchMode() throws Exception {
		// create and connect components, then start the simulator
		// cree una instancia del simulador 
		// y del controlador
		GravityLaws gl = _gravityLawsFactory
				.createInstance(_gravityLawsInfo);
		PhysicsSimulator physim = 
				new PhysicsSimulator(_dtime, gl);
		Controller controller =
				new Controller(physim, _bodyFactory,_gravityLawsFactory);
		
		// ficheros entrada y salida
		try{
			System.out.println("Fichero entrada: " + _inFile);
			System.out.println("Fichero salida: " + _outFile);
			InputStream in = new FileInputStream(_inFile);
			//System.out.println("#0");
			controller.loadBodies(in);
			//System.out.println("#1");
			OutputStream out = new FileOutputStream(_outFile);
			//System.out.println("#2");
			controller.run(_steps, out);
			//System.out.println("#3");
		}
		catch(IllegalArgumentException e) {
			System.err.println("Error en el fichero : " + _inFile);
		}
		catch(FileNotFoundException e) {
			System.err.println("Fichero erroneo");
		}
		
	}
	
	private static void startGuiMode() throws Exception {
		GravityLaws gl = _gravityLawsFactory
				.createInstance(_gravityLawsInfo);
		PhysicsSimulator physim = 
				new PhysicsSimulator(_dtime, gl);
		Controller controller =
				new Controller(physim, _bodyFactory,_gravityLawsFactory);
		
		//InputStream in = new FileInputStream("resources/examples/ex4.4body.txt");
		if(_inFile != null) {
			InputStream in = new FileInputStream(_inFile);
			controller.loadBodies(in);
		}		
		
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new MainWindow(controller);
			}				
		});
	}

	private static void start(String[] args) throws Exception {
		parseArgs(args);
		String batchmode = "batch";
		String guimode = "gui";
		// BATCH mode
		if(_mode.equals(batchmode))
			startBatchMode();
		// GUI mode
		else if(_mode.equals(guimode)){
			startGuiMode();
		}		
		else {
			System.err.println("ERROR: Modo de juego erroneo: " + _mode);
		}
	}

	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
