package it.randomtower.infiltration.model.poi;

//TODO: for now basic secret => just add one power on solved
public class BaseSecret implements Secret {

	private String name;
	private String district;

	public BaseSecret(String name, String district) {
		this.name = name;
		this.district = district;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}

}
