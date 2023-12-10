package leti.grupoa.projeto_es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A classe {@code Metrica} representa uma metrica associada a um nome e uma
 * formula matematica.
 */
public class Metrica {

	private String name;
	private String[] op;
	private ArrayList<Sala> classList = new ArrayList<>();
	private int[] values;

	 /**
     * Construtor da classe Metrica.
     *
     * @param name    O nome da metrica.
     * @param formula A formula matematica associada a metrica.
     */
	public Metrica(String name, String formula) {
		this.name = name;
		String[] aux = formula.split(" ");
		ArrayList<String> aux2 = new ArrayList<>();
		for (int i = 0; i < aux.length; i++) {
			if (isNotOperator(aux[i]) && !isNumeric(aux[i])) {
				aux2.add(aux[i] + " " + aux[i + 1]);
				i++;
			} else {
				aux2.add(aux[i]);
			}
		}
		op = new String[aux2.size()];
		for (int i = 0; i < op.length; i++) {
			op[i] = aux2.get(i);
		}
		System.out.println(Arrays.toString(op));
	}

	/**
	 * obtem a operação associada à fórmula.
	 *
	 * @return Um vetor de strings representando a operação.
	 */
	public String[] getOperacao() {
		return op;
	}

	/**
	 * obtem o nome da métrica.
	 *
	 * @return O nome da métrica.
	 */
	public String getName() {
		return name;
	}

	/**
	 * obtem a fórmula da métrica.
	 *
	 * @return Uma string representando a fórmula da métrica.
	 */
	public String getFormula() {
		StringBuilder formula = new StringBuilder();
		for (int i = 0; i < op.length; i++) {
			formula.append(op[i]).append(" ");
		}
		return "Fórmula: " + formula.toString();
	}

	/**
	 * Adiciona uma Sala à lista de aulas associada à métrica.
	 *
	 * @param s Um objeto Sala.
	 */
	public void addClass(Sala s) {
		classList.add(s);
	}

	/**
	 * obtem a lista de Salas selecionadas associada à métrica.
	 *
	 * @return A lista de Salas selecionadas.
	 */
	public ArrayList<Sala> getAulasSelecionadas() {
		return classList;
	}

	private boolean isNotOperator(String s) {
		boolean b = true;
		String[] operators = { "+", "-", "/", "*" };
		for (int i = 0; i < operators.length; i++) {
			if (s.equals(operators[i]))
				b = false;
		}
		return b;
	}

	private boolean isNumeric(String s) {
		try {
			int i = Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * obtem os critérios presentes na fórmula da métrica.
	 *
	 * @return Uma lista de strings indicativa dos critérios.
	 */
	public ArrayList<String> getCriteria() {
		ArrayList<String> criteriaList = new ArrayList<>();
		for (int i = 0; i < op.length; i++) {
			if (isNotOperator(op[i]) && !isNumeric(op[i]))
				criteriaList.add(op[i]);
		}
		return criteriaList;
	}

	private String[] matchExpression(int[] inputValues) throws NumberFormatException {
		if (inputValues.length > (0.5 * op.length) + 0.5)
			throw new IllegalArgumentException("Valores a mais.");

		if (op.length > (2 * inputValues.length) - 1)
			throw new IllegalArgumentException("Valores a menos.");

		values = inputValues;
		int currValue = 0;
		String[] expression = new String[op.length];

		for (int i = 0; i < expression.length; i++) {
			if (isNotOperator(op[i]) && !isNumeric(op[i])) {
				expression[i] = Integer.toString(values[currValue]);
				currValue++;
			} else {
				expression[i] = op[i];
			}
		}
		System.out.println(Arrays.toString(expression));
		return expression;
	}

	private int calculate(String v1, String v2, String op) {
		int number1 = Integer.parseInt(v1);
		int number2 = Integer.parseInt(v2);
		int result = 0;

		switch (op) {
		case "+":
			result = number1 + number2;
			System.out.println(number1 + " + " + number2 + " = " + result);
			break;
		case "-":
			result = number1 - number2;
			System.out.println(number1 + " - " + number2 + " = " + result);
			break;
		case "*":
			result = number1 * number2;
			System.out.println(number1 + " * " + number2 + " = " + result);
			break;
		case "/":
			result = number1 / number2;
			System.out.println(number1 + " / " + number2 + " = " + result);
			break;
		default:
			System.out.println("Operação inválida.");
			break;
		}

		return result;
	}

	/**
	 * Elimina espaços entre valores e operadores, formando novamente uma "formula".
	 *
	 * @param expression A expressão a "juntar".
	 * @return A expressão dada como input, mas sem espaço entre caracteres.
	 */

	private String[] closeGap(String[] expression) {
		ArrayList<String> aux = new ArrayList<>();
		String[] newV = new String[expression.length];
		for (int i = 0; i < expression.length; i++) {
			if (expression[i] != null) {
				aux.add(expression[i]);
			}
		}
		for (int i = 0; i < aux.size(); i++) {
			newV[i] = aux.get(i);
		}

		System.out.println(Arrays.toString(newV));
		return newV;
	}

	/**
	 * Calcula a qualidade com base nos valores fornecidos.
	 *
	 * @param inputValues Os valores de entrada para a fórmula da métrica.
	 * @return O resultado do cálculo da qualidade.
	 */
	public int calculateQuality(int[] inputValues) {
		int result = 0;
		String[] expression = matchExpression(inputValues);
		if (expression.length == 1)
			return Integer.parseInt(expression[0]);

		if (expression.length >= 3) {
			while (expression[2] != null) {
				for (int i = 0; i < expression.length; i++) {
					if (expression[i] != null) {

						if (Arrays.stream(expression).anyMatch("/"::equals)
								|| Arrays.stream(expression).anyMatch("*"::equals)) {

							if (expression[i].equals("/") || expression[i].equals("*")) {
								result = calculate(expression[i - 1], expression[i + 1], expression[i]);
								expression[i - 1] = String.valueOf(result);
								expression[i] = null;
								expression[i + 1] = null;
								expression = closeGap(expression);
								i = 0;
							}

						} else if (expression[i].equals("+") || expression[i].equals("-")) {

							result = calculate(expression[i - 1], expression[i + 1], expression[i]);
							expression[i - 1] = String.valueOf(result);
							expression[i] = null;
							expression[i + 1] = null;
							expression = closeGap(expression);
							i = 0;
						}
					}

				}
			}
		}
		return Integer.parseInt(expression[0]);
	}

	/**
	 * obtem a qualidade com base nos critérios da métrica.
	 *
	 * @return Uma lista de inteiros representando a qualidade para cada conjunto de
	 *         critérios.
	 * @throws IOException
	 */
	public ArrayList<Integer> getQuality() throws IOException {

		// POR IMPLEMENTAR
		ArrayList<Integer> qualities = new ArrayList<>();
		ArrayList<String> criteria = getCriteria();

		System.out.println(criteria);
		for (Sala s : classList) {
			int i = 0;
			int[] input = new int[(int) (0.5 * op.length + 0.5)];
			for (i = 0; i < input.length; i++) {
				if (isNumeric(op[i])) {
					input[i] = Integer.parseInt(op[i]);
				} else {
					for (int k = 0; k < criteria.size(); k++) {
						if (criteria.get(k).equals(op[i])) {
							input[i] = s.getCaracValue(criteria.get(k));
							System.out.println(criteria.get(i) + " = " + input[i]);
							System.out.println(Arrays.toString(input));
						}
					}

				}
			}
			int result = calculateQuality(input);
			qualities.add(result);
			System.out
					.println("Qualidade sala " + s.getNome() + " para a formula '" + this.getName() + "' é " + result);
		}
		return qualities;
	}
}