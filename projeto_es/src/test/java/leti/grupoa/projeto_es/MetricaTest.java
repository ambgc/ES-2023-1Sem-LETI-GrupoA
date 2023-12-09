package leti.grupoa.projeto_es;

import javax.script.ScriptException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MetricaTest {

	static Metrica m;
	static int[] valores = {20, 5, 3, 10};

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		String s = ("CapacidadeNormal * CapacidadeExtra - QuantidadeAlunos / 2");
		
		m = new Metrica("Max. Capacidade de alunos", s);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testObterOperacao() throws NumberFormatException, ScriptException {

		System.out.println(m.getFormula());
		System.out.println("Qualidade da métrica '"+ m.getName() + "':" + m.calculateQuality(valores));
	}
}
