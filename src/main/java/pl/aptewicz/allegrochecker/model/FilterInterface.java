package pl.aptewicz.allegrochecker.model;

import java.io.Serializable;

import pl.allegro.webapi.FilterOptionsType;

public interface FilterInterface extends Serializable {
	public FilterOptionsType createWsFilter();

	public String getDescription();
}
