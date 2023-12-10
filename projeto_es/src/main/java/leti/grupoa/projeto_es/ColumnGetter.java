package leti.grupoa.projeto_es;

public interface ColumnGetter {

	String[] getColumn();

	default String[] getColumn(String text) {
		if (!text.isEmpty()) {
			String[] columns = text.split("\\|");
			for (int i = 0; i < columns.length; i++) {
				columns[i] = columns[i].trim();
			}
			return columns;
		} else {
			throw new IllegalArgumentException("Invalid input text.");
		}
	}
	
	
}
