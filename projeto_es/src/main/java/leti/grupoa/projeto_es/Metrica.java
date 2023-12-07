package leti.grupoa.projeto_es;

import java.util.ArrayList;

public class Metrica {

	private String[] operacao;
	private ArrayList<String[]> aulasSelecionadas;

	public Metrica(String formula) {

		operacao = formula.split(" ");
	}

	public String[] getOperacao() {

		return operacao;
	}

	public String getFormula() {

		String formula = "";
		for (int i = 0; i < operacao.length; i++) {
			formula += operacao[i] + " ";
		}
		return "Fórmula: " + formula;
	}

	// introduzir a linha do horário correpondente à aula que se quer add
	public void selecionarAula(String[] aula) {

		aulasSelecionadas.add(aula);
	}

	public ArrayList<String[]> getAulasSelecionadas() {

		return aulasSelecionadas;
	}

	private boolean isNotOperator(int index) {

		boolean b = true;
		String[] operators = { "+", "-", "/", "*" };
		for (int i = 0; i < operators.length; i++) {
			if (operacao[index].equals(operators[i]))
				b = false;
		}
		return b;

	}

	public int calcularOperacao(int[] valores) throws NumberFormatException {

		if (valores.length > (0.5 * operacao.length) + 0.5)
			throw new IllegalArgumentException("Valores a mais.");

		if (operacao.length > (2 * valores.length) - 1)
			throw new IllegalArgumentException("Valores a menos.");

		int currValue = 0;
		String[] aux = new String[operacao.length];

		for (int i = 0; i < aux.length; i++) {
			if (isNotOperator(i)) {
				aux[i] = Integer.toString(valores[currValue]);
				currValue++;
			} else {
				aux[i] = operacao[i];
			}
		}

		String expression = String.join("", aux);

		System.out.println(expression);

		return Integer.parseInt(expression);
	}

}
