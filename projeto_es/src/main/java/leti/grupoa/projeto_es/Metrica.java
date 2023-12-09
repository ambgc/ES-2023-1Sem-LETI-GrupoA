package leti.grupoa.projeto_es;

import java.util.ArrayList;
import java.util.Arrays;

public class Metrica {

	private String name;
	private String[] op;
	private ArrayList<String[]> classList;
	private int[] values;

	public Metrica(String name, String formula) {

		this.name = name;
		op = formula.split(" ");
	}

	public String[] getOperacao() {

		return op;
	}

	public String getName() {
		return name;
	}

	public String getFormula() {

		String formula = "";
		for (int i = 0; i < op.length; i++) {
			formula += op[i] + " ";
		}
		return "Fórmula: " + formula;
	}

	// introduzir a linha do horário correpondente à aula que se quer add
	public void addClass(String[] aula) {

		classList.add(aula);
	}

	public ArrayList<String[]> getAulasSelecionadas() {

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
		return expression;
	}

	private int calculate(String v1, String v2, String op) {

		int number1 = Integer.parseInt(v1);
		int number2 = Integer.parseInt(v2);
		int result = 0;

		switch (op) {

		// caso de soma
		case "+":
			result = number1 + number2;
			System.out.println(number1 + " + " + number2 + " = " + result);
			break;

		// caso de subtração
		case "-":
			result = number1 - number2;
			System.out.println(number1 + " - " + number2 + " = " + result);
			break;

		// caso de multiplicação
		case "*":
			result = number1 * number2;
			System.out.println(number1 + " * " + number2 + " = " + result);
			break;

		// caso de divisão
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

	public int calculateQuality(int[] inputValues) {

		int result = 0;
		String[] expression = matchExpression(inputValues);
		if (expression.length == 1)
			return Integer.parseInt(expression[0]);

		if (expression.length > 3) {
			while (expression[3] != null) {
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

	public ArrayList<Integer> getQuality() {
		// POR IMPLEMENTAR
		ArrayList<Integer> qualities = new ArrayList<>();
		ArrayList<String> criteria = getCriteria();
		// for(Sala c : classList){
		// int i = 0;
		// int[] input = new int[criteria.size()]
		// for(String s : criteria){
		// input[i] = c.getCaracteristicValue(s);
		// i++
		// }
		// qualities.add(calculateQuality(input))
		// }
		return qualities;
	}
}
