import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * The starting place for the Gradebook program. 
 * This class initializes the program by loading
 * data from the files saved by previous instances
 * of the program or by initializing the manifest file
 * on the first set up. Also opens a JFrame window that
 * will serve as the starting place for interacting
 * with the GUI for the program.
 * 
 * 
 * @author Blake Whaley
 * 
 */
public class MainMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = null;
	private JMenu fileMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem openMenuItem = null;
	private JMenuItem saveMenuItem = null;
	private JMenuItem closeMenuItem = null;
	private JMenuItem quitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private static final String HELP = "WHAT THE HECK IS THIS??";
	private ApplicationData data;

	private File file = null;

	/**
	 * Constructor
	 * 
	 * @param title
	 * 			The title to be show for the JFrame.
	 */
	public MainMenu(String title) {
		menuBar = new JMenuBar();

		// File menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		// File->Open menu item
		openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(this);
		openMenuItem.setMnemonic(KeyEvent.VK_O);
		openMenuItem.setActionCommand("O");
		fileMenu.add(openMenuItem);

		// File->Save menu item
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(this);
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.setActionCommand("S");
		fileMenu.add(saveMenuItem);

		// File->Close menu close
		closeMenuItem = new JMenuItem("Close");
		closeMenuItem.addActionListener(this);
		closeMenuItem.setMnemonic(KeyEvent.VK_W);
		closeMenuItem.setActionCommand("W");
		fileMenu.add(closeMenuItem);

		// File->Quit menu item
		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(this);
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.setActionCommand("Q");
		fileMenu.add(quitMenuItem);

		menuBar.add(fileMenu);

		// Help menu
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		// Help->About menu item
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(this);
		aboutMenuItem.setMnemonic(KeyEvent.VK_A);
		aboutMenuItem.setActionCommand("A");
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		// "data" is the model for the program.
		data = new ApplicationData();
	}

	/**
	 * Sets up the frame for the main menu.
	 * 
	 * @param args
	 * 			arguments passed to program on start.
	 */
	public static void main(String[] args) {
		MainMenu frame = new MainMenu("Gradebook");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.setVisible(true);
	}

	/**
	 * Loads the manifest file, the manifest holds the names of every semester
	 * so that the appropriate file can be loaded for each object in the user's
	 * gradebook. The format is as follows:
	 * 
	 * <semester_1>,<class_1>;<class_2>;...;<class_n>,<gradeable_1>;...;<gradeable_n;\n
	 * <semester_2>...\n
	 * ...
	 * <semester_n>\n
	 * EOF
	 * 
	 * After parsing the manifest all of the data for each object listed in the manifest
	 * is loaded into the correct spot in the hierarchy with all of the corresponding
	 * information that the user has input.
	 * 
	 * @return
	 * 		true if no errors occur.
	 */
	public boolean loadManifest() {
		// Select a file using a JFileCHooser object
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			try {

				// Create input stream (a BufferdReader object) from the
				// file.
				BufferedReader inputStream = new BufferedReader(new FileReader(
						file));

				@SuppressWarnings("resource")
				Scanner fullLine = new Scanner(inputStream);
				fullLine.useDelimiter("\n");
				// Read each line and parse according to format specified above.
				while (fullLine.hasNext()) {
					String line = fullLine.next();
					String[] splitLine = line.split(",");
					String semester = splitLine[0];
					String[] classes = splitLine[1].split(";");
					String[] gradeables = splitLine[2].split(";");
					loadSemester(semester);
					for (String s : classes) {
						loadClass(s);
					}
					for (String s : gradeables) {
						loadGradeable(s);
					}
				}
				inputStream.close();
			} catch (IOException ex) {
				System.err.println(ex);
				return false;
			}
		}
		return true;
	}

	/**
	 * Loads a semester object from the title.
	 * 
	 * @param title
	 * 			The title of the semester to be loaded from a file.
	 * 
	 * @return
	 * 			true if successful.
	 */
	public boolean loadSemester(String title) {
		// TODO:
		return false;
	}

	/**
	 * Loads a class object from the title from a file.
	 * 
	 * @param title
	 * 			title of the class to be loaded.
	 * @return
	 * 			true if successful.
	 */
	public boolean loadClass(String title) {
		// TODO:
		return false;
	}

	/**
	 * Loads a gradeable object by title from a file.
	 * 
	 * @param title
	 * 			the title of the gradeable to be loaded.
	 * @return
	 * 			true if successful.
	 */
	public boolean loadGradeable(String title) {
		// TODO:
		return false;
	}

	/**
	 * Saves the manifest according to the format specified by loadManifest.
	 * 
	 * @return
	 * 			true if no errors.
	 */
	public boolean saveManifest() {
		// TODO:
		if (file != null) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				return true;
			} catch (Exception e) {

			}

		}
		return false;
	}

	/**
	 * Handles the actions from the menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) source;
			switch (menuItem.getActionCommand()) {
			case "O":
				if (!loadManifest()) {
					showAlert("Could not load manifest.");
				}
				break;
			case "S":
				saveManifest();
				break;
			case "W":
				file = null;
				break;
			case "Q":
				System.exit(0);
				break;
			case "A":
				// Show the about dialog.
				JOptionPane.showMessageDialog(this, HELP);
				break;
			}
		}

	}

	/**
	 * Shows an alert pop up box with a message explaining why an alert
	 * needed to be shown.
	 * 
	 * @param message
	 * 			the message to be shown.
	 */
	public void showAlert(String message) {
		// TODO:
	}

}
