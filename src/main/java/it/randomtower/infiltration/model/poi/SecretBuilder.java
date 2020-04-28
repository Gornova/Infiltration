package it.randomtower.infiltration.model.poi;

public final class SecretBuilder {

	public static final Secret buildBase(String district) {
		Secret secret = new BaseSecret("Lost Knowledge found!", district);
		return secret;
	}

}
