import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
//imports

public class GUIPanel extends JPanel
{
   private JPanel choices, buttons, bar, status;
   private JTextField dlpathTextfield;
   private JButton browse, dlpathChangeButton;
   private JLabel dlpathLabel;
   private JFileChooser chooser;
   private Timer t;
   private int timerCount = 0;
   private static final Color BACKGROUND = new Color(0xACC720);
   private static final Font dlpathTextfieldFont = new Font("Times New Roman", Font.BOLD, 14);
	//variables
	
   public GUIPanel()
   {
      setLayout(new FlowLayout());
   	//layout
   	
      dlpathLabel = new JLabel("Download folder path: ");
      add(dlpathLabel);
      
      dlpathTextfield = new JTextField(20);
      add(dlpathTextfield);
      dlpathTextfield.setHorizontalAlignment(SwingConstants.CENTER);
      dlpathTextfield.setEditable(true);
      dlpathTextfield.setBackground(Color.WHITE);
      dlpathTextfield.setForeground(Color.BLACK);
      dlpathTextfield.setText(DLSorter.getDLPath());
      dlpathTextfield.getDocument().addDocumentListener(
            new DocumentListener()   {
               public void changedUpdate(DocumentEvent e) {
                  check();
               }
               public void removeUpdate(DocumentEvent e) {
                  check();
               }
               public void insertUpdate(DocumentEvent e) {
                  check();
               }
            
               public void check() {
                  dlpathChangeButton.setEnabled(!dlpathTextfield.getText().equalsIgnoreCase(DLSorter.getDLPath()));
               }
            });
   	
      chooser = new JFileChooser(); 
      chooser.setCurrentDirectory(new File(DLSorter.getDLPath()));
      chooser.setDialogTitle("Select downloads folder location");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);
      
      browse = new JButton("Browse");
      browse.addActionListener(
            new ActionListener()   {
               public void actionPerformed(ActionEvent e)   {
               
               }
            });
      add(browse);
      
      dlpathChangeButton = new JButton("Set");
      dlpathChangeButton.addActionListener(
            new ActionListener()   {
               public void actionPerformed(ActionEvent e)   {
                  DLSorter.setDLPath(dlpathTextfield.getText());
                  dlpathChangeButton.setEnabled(!dlpathTextfield.getText().equalsIgnoreCase(DLSorter.getDLPath()));
               }
            });
      dlpathChangeButton.setEnabled(!dlpathTextfield.getText().equalsIgnoreCase(DLSorter.getDLPath()));
      add(dlpathChangeButton);
   }
	

}