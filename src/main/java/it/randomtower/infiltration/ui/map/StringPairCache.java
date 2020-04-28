package it.randomtower.infiltration.ui.map;

import java.util.ArrayList;
import java.util.List;

public class StringPairCache {

	private List<StringPair> cache = new ArrayList<>();

	public boolean contains(StringPair value) {
		return cache.contains(value);
	}

	public void put(StringPair value) {
		cache.add(value);
	}

}
