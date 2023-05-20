package classdescriptions.address;

import java.util.HashMap;
import java.util.Map;

public class Address {
	private final String streetName;
	private final String building;
	private final String apartment;

	public Address(String streetName, String building, String apartment) {
		this.streetName = streetName;
		this.building = building;
		this.apartment = apartment;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getBuilding() {
		return building;
	}

	public String getApartment() {
		return apartment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Address address = (Address) o;

		if (!getStreetName().equals(address.getStreetName())) return false;
		if (!getBuilding().equals(address.getBuilding())) return false;
		return getApartment() != null ? getApartment().equals(address.getApartment()) : address.getApartment() == null;
	}

	@Override
	public int hashCode() {
		int result = getStreetName().hashCode();
		result = 31 * result + getBuilding().hashCode();
		result = 31 * result + (getApartment() != null ? getApartment().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(streetName).append(" ");
		sb.append(building);
		if (apartment != null){
			sb.append(" ").append(apartment);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// Наподобие "управляющего", ну или в случае квартиры глава домохозяйства (значение Map)
		Map<Address, String> directorAtAddress = new HashMap<>();
		Address sozvezdie = new Address("Плехановская", "д. 14", null);
		Address civilBuilding1A1 = new Address("Плехановская", "д. 33", "кв. 21");
		Address civilBuilding1A2 = new Address("Плехановская", "д. 33", "кв. 22");
		Address civilBuilding2 = new Address("Плехановская", "д. 31", "кв. 11");
		directorAtAddress.put(sozvezdie, "Самодуров");
		directorAtAddress.put(civilBuilding1A1, "Иванов");
		directorAtAddress.put(civilBuilding1A2, "Петров");
		directorAtAddress.put(civilBuilding2, "Зуев");
		directorAtAddress.forEach((key, value) -> System.out.println(key + " -> " + value));
	}
}
