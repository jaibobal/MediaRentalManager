package mediaRentalManager;

public class Album extends Media {
	private String title, artist, songs;
	private int copiesAvailable;

	public Album(String title, int copiesAvailable, String artist, String songs) {
		this.artist = artist;
		this.title = title;
		this.songs = songs;
		this.copiesAvailable = copiesAvailable;
	}

	public Album(Media m) {
		Album temp = (Album) m;
		this.copiesAvailable = temp.copiesAvailable;
		this.title = temp.title;
		this.artist = temp.artist;
		this.songs = temp.songs;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", Copies Available: " 
	+ copiesAvailable + ", Artist: " + artist + ", Songs: "
				+ songs;
	}

	public String getTitle() {
		return title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void rentingOut() {
		copiesAvailable--;
	}

	public void returningIn() {
		copiesAvailable++;
	}

	public String getArtist() {
		return artist;
	}

	public String getSongs() {
		return songs;
	}

}
