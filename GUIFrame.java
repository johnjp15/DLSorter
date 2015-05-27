//John Park, 05/05/15
/* The GUI for this program should be able to:
 *  - change download folder file path
 *  - allow user to change preferences for handling of duplicates
 *  - 
 */

import javax.swing.*;
import java.awt.*;

public class GUIFrame extends JFrame
{
   private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
   
   public GUIFrame()
   {
      setTitle("DLSorter");
      setSize(200, 100);
      setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      try   {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }  
      catch(Exception e)   {
         e.printStackTrace();
      };
      setContentPane(new GUIPanel());
      setVisible(true);
   }
   
   public static void main(String[] args)
   {
      new GUIFrame();
   }
}