package leti.grupoa.projeto_es;

/**
 * A interface {@code ColumnGetter} fornece métodos para obter colunas a partir de uma String.
 * As classes que implementam esta interface devem definir o comportamento do método {@link #getColumn()}.
 * Além disso, uma implementação padrão é fornecida para obter colunas a partir de um String de entrada.
 *
 * <p>
 * Exemplo de utilização:
 * <pre>
 * {@code
 * ColumnGetter columnGetter = new SuaImplementacao();
 * String[] colunas = columnGetter.getColumn();
 * }
 * </pre>
 * </p>
 */
public interface ColumnGetter {

    /**
     * Obtém um vetor de Strings.
     *
     * @return Um vetor de strings que representa uma coluna.
     */
    String[] getColumn();

    /**
     * Obtém uma matriz de colunas a partir do texto de entrada especificado.
     * Espera-se que o texto de entrada esteja formatado usando o caractere '|' como delimitador.
     *
     * @param texto O texto de entrada contendo colunas separadas por '|'.
     * @return Um vetor de strings representando colunas.
     * @throws IllegalArgumentException Se o argumento estiver vazio.
     */
    default String[] getColumn(String texto) throws IllegalArgumentException {
        if (!texto.isEmpty()) {
            // Divide o texto de entrada usando '|' como delimitador
            String[] colunas = texto.split("\\|");

            // Remove espaços em branco no início e no final de cada coluna
            for (int i = 0; i < colunas.length; i++) {
                colunas[i] = colunas[i].trim();
            }

            return colunas;
        } else {
            // Lança uma exceção se o texto de input estiver vazio
            throw new IllegalArgumentException("Texto de entrada inválido. O texto de entrada não pode estar vazio.");
        }
    }
}