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

	private static File directory = new File(".");
	private static String HOME_DIRECTORY = "";

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

		// Sets the absolute path of the current working directory.
		HOME_DIRECTORY = directory.getAbsolutePath();
	}

	/**
	 * Loads the manifest file, the manifest holds the names of every semester
	 * so that the appropriate file can be loaded for each object in the user's
	 * gradebook. The format is as follows:
	 * 
	 * <semester_1>:<class_1>;<gradeable_1>;...;<gradeable_n>,
	 * <class_2>;<gradeable_1>;...;<gradeable_n>...,<class_n>;<gradeable_1>;...;<gradeable_n>:\n
	 * <semester_2>:...\n
	 * ...
	 * <semester_n>:\n
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
					// Splits line to
					// [<semester>][<class_1>;<gradeable_1>;...<gradeable_n>,...,<class_n>]
					String[] splitLine = line.split(":");
					String semester = splitLine[0];

					// Splits splitLine into
					// [<class_1>;<gradeable_1>...<gradeable_n>]...[<class_n>]
					String[] classes = splitLine[1].split(",");

					Semester sem = loadSemester(semester);
					for (String s : classes) {
						// Splits s into
						// [<class>][<gradeable_1>]...[<gradeable_n>]
						String[] class_info = s.split(";");
						Class c = loadClass(s, sem);
						for (int i = 1; i < class_info.length; i++) {
							loadGradeable(class_info[i], c);
						}
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
	 * Saves the manifest according to the format specified by loadManifest.
	 * 
	 * <semester_1>:<class_1>;<gradeable_1>;...;<gradeable_n>,
	 * <class_2>;<gradeable_1>;...;<gradeable_n>...,<class_n>;<gradeable_1>;...;<gradeable_n>:\n
	 * <semester_2>:...\n
	 * ...
	 * <semester_n>:\n
	 * EOF
	 * 
	 * @return
	 * 			true if no errors.
	 */
	public boolean saveManifest() {
		if (file != null) {
			try {
				@SuppressWarnings("resource")
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				for (Semester s : data.getSemesters()) {
					out.write(s.getName());
					out.write(":");
					for (Class c : s.getClasses()) {
						out.write(c.getName());
						out.write(";");
						for (Gradeable g : c.getGradeables()) {
							out.write(g.getName());
							out.write(";");
						}
					}
					out.write("\n");
				}
				return true;
			} catch (Exception e) {

			}

		}
		return false;
	}

	/**
	 * Loads a semester object from the title.
	 * Information is loaded from a file, the file location will always be
	 * HOME_DIRECTORY/<semester_name>.sem
	 * 
	 * File Contents:
	 * NAME\n
	 * 
	 * 
	 * @param title
	 * 			The title of the semester to be loaded from a file.
	 * 
	 * @return
	 * 			true if successful.
	 */
	public Semester loadSemester(String title) {
		// TODO:
		File sem = new File(HOME_DIRECTORY + "/" + title + ".sem");
		Semester s = new Semester(title);
		data.addNewSemester(s);
		return s;
	}

	/**
	 * Loads a class object from the title from a file.
	 * 
	 * @param title
	 * 			title of the class to be loaded.
	 * @param sem
	 * 			the semester to which the class belongs.
	 * @return
	 * 			the loaded class.
	 */
	public Class loadClass(String title, Semester sem) {
		// TODO:
		File cls = new File(HOME_DIRECTORY + "/" + title + ".cls");
		Class c = new Class(title);
		sem.addClass(c);
		return c;
	}

	/**
	 * Loads a gradeable object by title from a file.
	 * 
	 * @param title
	 * 			the title of the gradeable to be loaded.
	 * @param c
	 * 			the class that the gradeable belongs to.
	 * @return
	 * 			the loaded gradeable
	 */
	public Gradeable loadGradeable(String title, Class c) {
		// TODO:
		File gdb = new File(HOME_DIRECTORY + "/" + title + ".gdb");
		Gradeable g = new Gradeable(title);
		c.addGrade(g);
		return g;
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
		return;
	}

}
