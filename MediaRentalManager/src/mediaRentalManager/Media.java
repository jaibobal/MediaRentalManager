package mediaRentalManager;

public class Media implements Comparable<Media> {

	public int compareTo(Media m) {
		if (this instanceof Movie) {
			Movie thisTemp = (Movie) this;
			if (m instanceof Movie) {
				Movie paramTemp = (Movie) m;
				return thisTemp.getTitle().compareTo(paramTemp.getTitle());
			} else {
				Album paramTemp = (Album) m;
				return thisTemp.getTitle().compareTo(paramTemp.getTitle());
			}
		} else {
			Album thisTemp = (Album) this;
			if (m instanceof Movie) {
				Movie paramTemp = (Movie) m;
				return thisTemp.getTitle().compareTo(paramTemp.getTitle());
			} else {
				Album paramTemp = (Album) m;
				return thisTemp.getTitle().compareTo(paramTemp.getTitle());
			}
		}
	}

}
