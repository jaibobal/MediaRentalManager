package mediaRentalManager;

public class Movie extends Media {
	private String title, rating;
	private int copiesAvailable;

	public Movie(String title, int copiesAvailable, String rating) {
		this.copiesAvailable = copiesAvailable;
		this.title = title;
		this.rating = rating;
	}

	public Movie(Media m) {
		Movie temp = (Movie) m;
		this.copiesAvailable = temp.copiesAvailable;
		this.title = temp.title;
		this.rating = temp.rating;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", Copies Available: " 
	+ copiesAvailable + ", Rating: " + rating;
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

	public String getRating() {
		return rating;
	}
}
