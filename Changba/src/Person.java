

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	public ERR_MSG errMsg;
	public enum ERR_MSG {INVALID, NOT_FOUND, FOUND};
	public String address; 
	public String name;
	public String photoAddress;
	public String numOfFans;
	public String numOfFriends;
	public String numOfSongs;
	public List<Song> listOfSongs;
	
	public Person(String addr) {
		
		this.address = addr;
		if (!address.matches("http://changba.com/u/\\d+"))
			errMsg = ERR_MSG.INVALID;
		else {
			String content = Crawler.getSource(address);
			if (!content.contains("个人主页"))
				errMsg = ERR_MSG.NOT_FOUND;
			else {
				errMsg = ERR_MSG.FOUND;
				this.name = Crawler.searchPattern(content, "<title>(.+?)个人主页- 唱吧，最时尚的手机KTV</title>", 1)[0];
				this.photoAddress = Crawler.searchPattern(content, "<span class=\"userPage-relative inline-block\">.+?<img src=\"(.+?)\" alt=\"username\"", 1)[0];
				this.numOfFans = Crawler.searchPattern(content, "<div class=\"userPage-icon-des\">(\\d+)个粉丝", 1)[0];
				this.numOfFriends = Crawler.searchPattern(content, "<div class=\"userPage-icon-des\">(\\d+)个关注", 1)[0];
				this.numOfSongs = Crawler.searchPattern(content, "<div class=\"userPage-icon-des\">(\\d+)个作品", 1)[0];
				listOfSongs = new ArrayList<Song>();
			}
		}
		
		
		
	}
	
	public List<Song> listSongs() {
		String content = Crawler.getSource(address);
		Pattern pattern = Pattern.compile("<a href=\"(/s/(.+?))\"", Pattern.DOTALL); 
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()) {
			String path = "http://changba.com"+matcher.group(1);
			String contentSong = Crawler.getSource(path);
			//Song(String title, String numOfPlayed, String uploadTime, String address)
			String title = Crawler.searchPattern(contentSong, "<title>(.+?) -.+?KTV</title>", 1)[0];
			String numOfPlayed = Crawler.searchPattern(contentSong, "<span class=\"audience info\">.+?<em>(\\d+)</em>", 1)[0];
			String numOfPresents = Crawler.searchPattern(contentSong, "<span class=\"presents info\">.+?<em>(\\d+)</em>", 1)[0];
			String ss[] = Crawler.searchPattern(contentSong, "a=\"(http://.+?/[0-9]{9}.mp3)\"", 1);
			String address = null;
			if (ss!=null)
				address = ss[0];
			else
				continue;
			Song song = new Song(title, numOfPlayed, numOfPresents, address);
		//	System.out.println(song.toString());
			listOfSongs.add(song);
		}
		return listOfSongs;
	}
	
	@Override
	public String toString() {
		return "Name: "+this.name+
				"\nPhotoAddress: "+ this.photoAddress+
				"\nNumber of funs: "+ this.numOfFans+
				"\nNumber of friends: " + this.numOfFriends+
				"\nNumber of songs: "+ this.numOfSongs;
	}
}
