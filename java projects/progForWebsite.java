import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

class OpenVLCPlayer{

 public static void main(String[] args)  {
  try {
   
    URI uri= new URI("http://www.youtube.com");
    java.awt.Desktop.getDesktop().browse(uri);
    System.out.println("Web page opened in browser");
 
  }catch(Exception e) {
   
   e.printStackTrace();
  }
 }

}