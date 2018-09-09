import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

//import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

//import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OIDXML {
	
      public OIDXML(String sfile){
    	  
          System.out.println("start OIDXML ...");
    	  /*   DefaultMutableTreeNode top = new DefaultMutableTreeNode(file);         
          try {             
        	  SAXParser saxParser = new SAXParser(); 
              saxParser.parse(new InputSource(new FileInputStream(file)));
              System.out.println( saxParser.toString());
          } catch(Exception ex) {
            top.add(new DefaultMutableTreeNode(ex.getMessage()));
          }
          */
          javax.xml.parsers.SAXParser saxPars = null;
    	  SAXParserFactory factory = SAXParserFactory.newInstance();
          try {
			saxPars = factory.newSAXParser();
          } catch (ParserConfigurationException e) { 
			e.printStackTrace();
          } catch (SAXException e) { 
			e.printStackTrace();
          }
          InputSource is = GetInputSource(sfile);        
          DefaultHandler handler = new DefaultHandler();     
          try {
			saxPars.parse(is , handler);
          } catch (SAXException e) { 
			e.printStackTrace();
          } catch (IOException e) { 
			e.printStackTrace();
          }  
          System.out.println("end OIDXML ...");
      }
      
      static private InputSource GetInputSource(String sFile) {		
    	  
    	  File file = new File(sFile);
          InputStream inputStream = null;
          try {
			inputStream = new FileInputStream(file);
          } catch (FileNotFoundException e) {
			e.printStackTrace();
          }
          Reader reader = null;
          try {
			reader = new InputStreamReader(inputStream, "UTF-8");
          } catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
          }
          InputSource is = new InputSource(reader);
          is.setEncoding("UTF-8");
    	  return is;    	  
      }
      
      public void startElement(String uri, String localName,
              String qName, Attributes attributes) throws SAXException {
    	  attachAttributeList(attributes);
          System.out.println("Start: " + qName);
      }

      public void endElement(String uri, String localName, String qName)
              throws SAXException {
          System.out.println("End: " + qName);
      }
      
      public void startDocument(){ 
          System.out.println("startDocument...");
      }
      
      public void endDocument(){
          System.out.println("startDocument...");
      }
      public void characters(char[] data,int start,int end){
        //     String str = new String(data,start,end);              
         //    if (!str.equals("") && Character.isLetter(str.charAt(0)))
          //       currentNode.add(new DefaultMutableTreeNode(str));           
      }
      
     
      
      private void attachAttributeList(Attributes atts){
              for (int i=0;i<atts.getLength();i++){
                   String name = atts.getLocalName(i);
                   String value = atts.getValue(name);
                   System.out.println(name + " = " + value);
              }
      }
}
