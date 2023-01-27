package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {
	private ArrayList<Customer> customers = new ArrayList<>();
	private ArrayList<Media> medias = new ArrayList<>();
	private static int limitedPlanValue = 2;

	@Override
	public void addCustomer(String name, String address, String plan) {
		customers.add(new Customer(name, address, plan));
	}

	@Override
	public void addMovie(String title, int copiesAvailable, String rating) {
		Media newMovie = new Movie(title, copiesAvailable, rating);
		medias.add(newMovie);
	}

	@Override
	public void addAlbum(String title, int copiesAvailable,
			String artist, String songs) {
		Media newAlbum = new Album(title, copiesAvailable, artist, songs);
		medias.add(newAlbum);
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		limitedPlanValue = value;
	}

	@Override
	public String getAllCustomersInfo() {
		String answer = "***** Customers' Information *****\n";
		Collections.sort(customers);
		for (Customer customer : customers) {
			answer += customer.toString();
		}
		return answer;
	}

	@Override
	public String getAllMediaInfo() {
		String answer = "***** Media Information *****\n";
		Collections.sort(medias);
		for (Media m : medias) {
			if (m instanceof Movie) {
				Movie temp = (Movie) m;
				answer += temp.toString();
				answer += "\n";
			} else {
				Album temp = (Album) m;
				answer += temp.toString();
				answer += "\n";
			}
		}
		return answer;
	}

	@Override
	public boolean addToQueue(String customerName, String mediaTitle) {
		for (Customer customer : customers) {
			if (customer.getName().equals(customerName)) {
				for (Media media : customer.getQueue()) {
					if (media instanceof Movie) {
						Movie temp = (Movie) media;
						if (temp.getTitle().equals(mediaTitle)) {
							return false;
						}
					} else {
						Album temp = (Album) media;
						if (temp.getTitle().equals(mediaTitle)) {
							return false;
						}
					}
				}
				for (Media media : medias) {
					if (media instanceof Movie) {
						Movie temp = (Movie) media;
						if (temp.getTitle().equals(mediaTitle)) {
							customer.addToQueue(temp);
							return true;
						}
					} else {
						Album temp = (Album) media;
						if (temp.getTitle().equals(mediaTitle)) {
							customer.addToQueue(temp);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName().equals(customerName)) {
				for (Media media : customers.get(i).getQueue()) {
					if (media instanceof Movie) {
						Movie temp = (Movie) media;
						if (temp.getTitle().equals(mediaTitle)) {
							customers.get(i).removeFromCustomerQueue(mediaTitle);
							return true;
						}
					} else {
						Album temp = (Album) media;
						if (temp.getTitle().equals(mediaTitle)) {
							customers.get(i).removeFromCustomerQueue(mediaTitle);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public String processRequests() {
		String mediaTitle = "", customerName = "", result = "";
		Collections.sort(customers);
		ArrayList<ArrayList<String>> allToRemove = new ArrayList<>();
		for (Customer customer : customers) {
			ArrayList<String> toRemove = new ArrayList<>();
			customerName = customer.getName();
			if (customer.getPlan().equals("LIMITED")) {
				for (Media media : customer.getQueue()) {
					if (media instanceof Movie) {
						Movie temp = (Movie) media;
						mediaTitle = temp.getTitle();
						if (customer.getRentedLength() < limitedPlanValue &&
								temp.getCopiesAvailable() > 0) {
							customer.addToRented(media);
							for (Media m : medias) {
								if (m instanceof Movie) {
									if (((Movie) m).getTitle().equals(mediaTitle)) {
										((Movie) m).rentingOut();
									}
								} else {
									if (((Album) m).getTitle().equals(mediaTitle)) {
										((Album) m).rentingOut();
									}
								}
							}
							result += "Sending " + mediaTitle + " to " 
							+ customerName + "\n";
							toRemove.add(mediaTitle);
						}
					} else {
						Album temp = (Album) media;
						mediaTitle = temp.getTitle();
						if (customer.getRentedLength() < limitedPlanValue &&
								temp.getCopiesAvailable() > 0) {
							customer.addToRented(media);
							for (Media m : medias) {
								if (m instanceof Movie) {
									if (((Movie) m).getTitle().equals(mediaTitle)) {
										((Movie) m).rentingOut();
									}
								} else {
									if (((Album) m).getTitle().equals(mediaTitle)) {
										((Album) m).rentingOut();
									}
								}
							}
							result += "Sending " + mediaTitle + " to " +
							customerName + "\n";
							toRemove.add(mediaTitle);
						}
					}
				}
			} else {
				for (Media media : customer.getQueue()) {
					if (media instanceof Movie) {
						Movie temp = (Movie) media;
						mediaTitle = temp.getTitle();
						if (temp.getCopiesAvailable() > 0) {
							customer.addToRented(media);
							for (Media m : medias) {
								if (m instanceof Movie) {
									if (((Movie) m).getTitle().equals(mediaTitle)) {
										((Movie) m).rentingOut();
									}
								} else {
									if (((Album) m).getTitle().equals(mediaTitle)) {
										((Album) m).rentingOut();
									}
								}
							}
							result += "Sending " + mediaTitle + " to " 
							+ customerName + "\n";
							toRemove.add(mediaTitle);
						}
					} else {
						Album temp = (Album) media;
						mediaTitle = temp.getTitle();
						if (temp.getCopiesAvailable() > 0) {
							customer.addToRented(media);
							for (Media m : medias) {
								if (m instanceof Movie) {
									if (((Movie) m).getTitle().equals(mediaTitle)) {
										((Movie) m).rentingOut();
									}
								} else {
									if (((Album) m).getTitle().equals(mediaTitle)) {
										((Album) m).rentingOut();
									}
								}
							}
							result += "Sending " + mediaTitle + " to " 
							+ customerName + "\n";
							toRemove.add(mediaTitle);
						}
					}
				}
			}
			allToRemove.add(toRemove);
		}
		for (int i = 0; i < customers.size(); i++) {
			String name = customers.get(i).getName();
			for (String queuedMedia : allToRemove.get(i)) {
				String media = queuedMedia;
				removeFromQueue(name, media);
			}
		}
		return result;
	}

	@Override
	public boolean returnMedia(String customerName, String mediaTitle) {
		for (Customer customer : customers) {
			if (customer.getName().equals(customerName)) {
				customer.removeFromCustomerRented(mediaTitle);
				for (Media m : medias) {
					if (m instanceof Movie) {
						if (((Movie) m).getTitle().equals(mediaTitle)) {
							((Movie) m).returningIn();
						}
					} else {
						if (((Album) m).getTitle().equals(mediaTitle)) {
							((Album) m).returningIn();
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> searchMedia(String title, String rating,
			String artist, String songs) {
		Collections.sort(medias);
		ArrayList<String> mediaTitles = new ArrayList<>();
		if (title == null && rating == null && artist == null && songs == null) {
			for (Media m : medias) {
				if (m instanceof Movie) {
					Movie temp = (Movie) m;
					mediaTitles.add(temp.getTitle());
				} else {
					Album temp = (Album) m;
					mediaTitles.add(temp.getTitle());
				}
			}
			return mediaTitles;
		}
		for (Media m : medias) {
			if (m instanceof Movie) {
				Movie temp = (Movie) m;
				if (title == null && rating == null) {
				} else if (title == null) {
					if (temp.getRating().equals(rating)) {
						mediaTitles.add(temp.getTitle());
					}
				} else if (rating == null) {
					if (temp.getTitle().equals(title)) {
						mediaTitles.add(temp.getTitle());
					}
				} else {
					if (temp.getTitle().equals(title) || temp.getRating()
							.equals(rating)) {
						mediaTitles.add(temp.getTitle());
					}
				}
			} else if (m instanceof Album) {
				Album temp = (Album) m;
				if (title == null && artist == null && songs == null) {

				} else if (title != null && artist == null && songs == null) {
					if (temp.getTitle().equals(title)) {
						mediaTitles.add(temp.getTitle());
					}
				} else if (title == null && artist != null && songs == null) {
					if (temp.getArtist().equals(artist)) {
						mediaTitles.add(temp.getTitle());
					}
				} else if (title == null && artist == null && songs != null) {
					if (temp.getSongs() != null) {
						if (temp.getSongs().contains(songs)) {
							mediaTitles.add(temp.getTitle());
						}
					}
				} else if (title != null && artist != null && songs == null) {
					if (temp.getTitle().equals(title) || temp.getArtist()
							.equals(artist)) {
						mediaTitles.add(temp.getTitle());
					}
				} else if (title == null && artist != null && songs != null) {
					if (temp.getArtist().equals(artist) || temp.getSongs()
							.contains(songs)) {
						mediaTitles.add(temp.getTitle());
					}
				} else if (title != null && artist == null && songs != null) {
					if (temp.getTitle().equals(title) || temp.getSongs()
							.contains(songs)) {
						mediaTitles.add(temp.getTitle());
					}
				} else {
					if (temp.getTitle().equals(title) || temp.getArtist()
							.equals(artist)
							|| temp.getSongs().contains(songs)) {
						mediaTitles.add(temp.getTitle());
					}
				}

			}
		}
		return mediaTitles;
	}

}
