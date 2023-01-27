package mediaRentalManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer implements Comparable<Customer> {
	private String name, address, plan;
	private ArrayList<Media> rented;
	private ArrayList<Media> queued;

	public Customer(String name, String address, String plan) {
		this.name = name;
		this.address = address;
		this.plan = plan;
		rented = new ArrayList<>();
		queued = new ArrayList<>();
	}

	@Override
	public int compareTo(Customer customer) {
		return this.name.compareTo(customer.name);
	}

	@Override
	public String toString() {
		String ans = "Name: " + name + ", Address: " + address + ", Plan: " + plan;
		ArrayList<String> queuedList = new ArrayList<>();
		ArrayList<String> rentedList = new ArrayList<>();
		ans += "\nRented: ";
		for (Media m : rented) {
			if (m instanceof Movie) {
				Movie temp = (Movie) m;
				rentedList.add(temp.getTitle());
			} else {
				Album temp = (Album) m;
				rentedList.add(temp.getTitle());
			}
		}
		ans += Arrays.toString(rentedList.toArray());
		ans += "\nQueue: ";
		for (Media m : queued) {
			if (m instanceof Movie) {
				Movie temp = (Movie) m;
				queuedList.add(temp.getTitle());
			} else {
				Album temp = (Album) m;
				queuedList.add(temp.getTitle());
			}
		}
		ans += Arrays.toString(queuedList.toArray());
		return ans + "\n";
	}

	public String getName() {
		return new String(name);
	}

	public ArrayList<Media> getQueue() {
		ArrayList<Media> temp = new ArrayList<>();
		for (Media m : queued) {
			if (m instanceof Movie) {
				Movie movie = new Movie(m);
				temp.add(movie);
			} else {
				Album album = new Album(m);
				temp.add(album);
			}
		}
		return temp;
	}

	public void addToQueue(Media m) {
		queued.add(m);
	}

	public void addToRented(Media m) {
		rented.add(m);
	}

	public void removeFromCustomerQueue(String mediaTitle) {
		for (int i = 0; i < queued.size(); i++) {
			if (queued.get(i) instanceof Movie) {
				Movie temp = (Movie) queued.get(i);
				if (temp.getTitle().equals(mediaTitle)) {
					queued.remove(queued.get(i));
				}
			} else {
				Album temp = (Album) queued.get(i);
				if (temp.getTitle().equals(mediaTitle)) {
					queued.remove(queued.get(i));
				}
			}
		}
	}

	public String getPlan() {
		return plan;
	}

	public int getRentedLength() {
		return rented.size();
	}

	public void removeFromCustomerRented(String mediaTitle) {
		for (int i = 0; i < rented.size(); i++) {
			if (rented.get(i) instanceof Movie) {
				Movie temp = (Movie) rented.get(i);
				if (temp.getTitle().equals(mediaTitle)) {
					rented.remove(rented.get(i));
				}
			} else {
				Album temp = (Album) rented.get(i);
				if (temp.getTitle().equals(mediaTitle)) {
					rented.remove(rented.get(i));
				}
			}
		}
	}
}