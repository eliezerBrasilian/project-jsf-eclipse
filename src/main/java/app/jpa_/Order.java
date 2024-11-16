package app.jpa_;

public enum Order {
	ASCENDING("ASC"),
	DESCENDING("DESC");

	Order(String string) {
		this.value = string;
	}
	public String value;
}
