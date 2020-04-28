package it.randomtower.infiltration.ui.map;

public class StringPair {

	private String one;
	private String two;

	public StringPair(String one, String two) {
		this.one = one;
		this.two = two;
	}

	public String getOne() {
		return one;
	}

	public String getTwo() {
		return two;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StringPair) {
			StringPair value = (StringPair) obj;
			return value.getOne().equals(one) && value.getTwo().equals(two)
					|| value.getOne().equals(two) && value.getTwo().equals(one);
		}
		return false;
	}

}
