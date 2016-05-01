


import java.net.MalformedURLException;
import java.net.URL;
  
public class Song {  
	
    public String title;   
    public String numOfPlayed;
    public String numOfPresents;
    public int size;
    public URL mediaUrl;  
    public String address; 
   
    public Song(String title, String numOfPlayed, String numOfPresents, String address) {   
        this.title = title;
        this.numOfPlayed = numOfPlayed;
        this.numOfPresents = numOfPresents;
        this.address = address;
        this.size = 0;
    }  
    
    public URL getMediaAddress() {
        try {
			mediaUrl = new URL(address);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        return mediaUrl;
    }

    @Override  
    public String toString() {  
        return "Title: " + title + 
        		"\nNumber of played times: " + numOfPlayed + 
        		"\nAddress: " + address + 
        		"\nPresents: " + numOfPresents +
        		"\nMedia address: "+ mediaUrl+"\n";  
    }  
}  