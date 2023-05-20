package classdescriptions.notebook;

import java.util.Arrays;

public class Notebook {
	private final String name;
	private final int ram;
	private final int price;

	public Notebook(String name, int ram, int price) {
		this.name = name;
		this.ram = ram;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getRam() {
		return ram;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return name + ", " + ram + " Гб, " + price + " т.р.";
	}

	public static void main(String[] args) {
		Notebook[] sampleNotebooks = new Notebook[]{
			new Notebook("Notebook1", 8, 37),
			new Notebook("Notebook2", 4, 25),
			new Notebook("Notebook3", 4, 33),
			new Notebook("Notebook4", 8, 55),
			new Notebook("Notebook5", 2, 18),
			new Notebook("Notebook7", 8, 45),
			new Notebook("Notebook8", 16, 54),
			new Notebook("Notebook9", 16, 62),
		};
		Arrays.sort(sampleNotebooks, (n1, n2) -> {
			int firstPrice = n1.getPrice();
			int secondPrice = n2.getPrice();

			int firstRam = n1.getRam();
			int secondRam = n2.getRam();

			if (firstPrice < 50 && secondPrice >= 50) {
				return -1;
			}
			if (secondPrice < 50 && firstPrice >= 50){
				return 1;
			}
			if (secondRam < firstRam) return -1;
			if (firstRam < secondRam) return 1;
			// Кажется в задании очепятка при сравнении ноутбуков 2 и 3 - у 2 цена меньше,
			// значит он должен быть первым
			return Integer.compare(firstPrice, secondPrice);
		});
		Arrays.stream(sampleNotebooks).forEach(System.out::println);
	}
}
