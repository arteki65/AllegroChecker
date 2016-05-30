package pl.aptewicz.allegrochecker.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	private final double price;

	private final String title;

	private final String description;

	private final String city;

	private final LocalDateTime endOfOffer;

	public Offer(double price, String title, String description, String city,
			LocalDateTime endOfOffer) {
		this.price = price;
		this.title = title;
		this.description = description;
		this.city = city;
		this.endOfOffer = endOfOffer;
	}

	public double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCity() {
		return city;
	}

	public LocalDateTime getEndOfOffer() {
		return endOfOffer;
	}

	@Override
	public String toString() {
		return "Offer: " + title + ", price: " + price + ", endOfOffer: "
				+ endOfOffer;
	}
}
