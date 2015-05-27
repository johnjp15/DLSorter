//John Park, 1/5/13

import java.util.*;
import java.io.*;
import java.text.*;

public class DLSorter
{
   private static String dlfolderpath;
   private static	final	String DLPATH = "C:/Users/" +	System.getProperty("user.name") + "/Downloads/.files";//	+ "/Desktop/DLOrganizer/DLFolder";
   private static	int moves =	0;
   private static	FileWriter log;
   
   public DLSorter()
   {
      this("C:/Users/" + System.getProperty("user.name") + "/Downloads/");
   }
   
   public DLSorter(String path)
   {
      setDLPath(path);
   }
   
   	
   public static String getDLPath()
   {
      return dlfolderpath;
   }
   
   public static void setDLPath(String path)
   {
      dlfolderpath = path;
   }
   
   private static void startGUI()
   {
      GUIFrame g = new GUIFrame();
   }
   
   private static void editLog() throws Exception
   {
   	File logFile =	new File("log.txt");
      log =	new FileWriter("log.txt", true);	
      if(logFile.length() == 0L)	{
         printLog("===========================================\r\n");
         printLog("=============DLSorter log file=============\r\n");
         printLog("============Coded by John Park=============\r\n");
         printLog("===========================================\r\n\r\n");
      }
      DateFormat dateFormat =	new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Date date =	new Date();
      printLog("-------------------------------------------\r\n");
      printLog(dateFormat.format(date)	+ "\r\n");
      printLog("\r\n");
   		
   		//Directory	path here
      String path	= "C:/Users/" + System.getProperty("user.name")	+ "/Downloads"; //+ "/Desktop/DLOrganizer";
      dlfolderpath = path;
   		//checks	for program	folder, creates it if not exist
      File dlfolder = new File(DLPATH);
      if(!dlfolder.exists())	{
         dlfolder.mkdir();
      }
   		
      File folder	= new	File(path);
      File[] listOfFiles =	folder.listFiles();
   		
      LinkedList<File> filelist = new LinkedList<File>();
      for(File	f : listOfFiles)	{
         filelist.add(0, f);
      }
      boolean error = false;
   		
      ListIterator<File> lister = filelist.listIterator(filelist.size());
      ArrayList<File> folders	= new	ArrayList<File>();
   		
      while(lister.hasPrevious())	{
         File f =	lister.previous();
         String fname =	f.getName();
         if(f.isFile() && fname.lastIndexOf('.') != -1)	{
            String ext = fname.substring(fname.lastIndexOf('.'), fname.length()).toLowerCase();
            if(!folders.contains(ext))	{
               File extfolder	= new	File(DLPATH	+ "/"	+ ext);
               extfolder.mkdir();
               folders.add(extfolder);
            }
            File dest =	new File(DLPATH +	"/" +	ext +	"/" +	fname);
            if(!f.renameTo(dest))	{
               int copy	= 1;
               fname	= fname.substring(0,	fname.lastIndexOf('.'))	+ " (" +	copy + ")" + ext;
               dest = new File(DLPATH + "/" + ext + "/" + fname);
               while(!f.renameTo(dest))	{
                  if(copy > 10)	{
                     error	= true;
                     break;
                  }
                  fname	= fname.substring(0,	fname.lastIndexOf('('))	+ "("	+ copy +	")" +	ext;
                  dest = new File(DLPATH + "/" + ext + "/" + fname);
                  copy++;
               }
               if(error)	{
                  printLog("Failed to move '" +	fname	+ "'\r\n");
                  error	= false;
               }
               else	{
                  moves++;
                  printLog("Successfully moved '" + fname +	"'\r\n");
               }
            }
            else	{
               moves++;
               printLog("Successfully moved '" + fname +	"'\r\n");
            }
         }
      }
   		
      File credit	= new	File(DLPATH	+ "/files sorted by DLSorter");	
      if(!credit.exists())	{
         credit.createNewFile();
      }
      if(moves	> 0 || error)	{
         printLog("\r\n");
      }
      if(moves	==	1)	{
         printLog(moves	+ " file moved successfully.");
      }
      else	{
         printLog(moves	+ " files moved successfully.");
      }
      printLog("\r\n-------------------------------------------");
      printLog("\r\n\r\n");
      log.close();
   }
	
   private static void printLog(String s) throws Exception
   {
      log.write(s, 0, s.length());
   }
   
   public static void main(String[]	args)
   {
      new DLSorter();
      
      if(args.length > 0)  {
   	for(int i =	0;	i < args.length; i++)	{
   		switch(args[i])	{
            case "nogui":
   			case "-nogui":
   							try	{
      								editLog();
   							}	catch(Exception e)	{
   								e.printStackTrace();
   							}
   				break;
   			
            default:
            case "gui":
   			case "-gui":
               startGUI();
               break;
   		}
   	}
      }  else  {
         startGUI();
      }
   	
   }

}