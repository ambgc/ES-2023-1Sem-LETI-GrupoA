package leti.grupoa.projeto_es;

import static org.junit.jupiter.api.Assertions.*;

import javax.script.ScriptException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MetricaTest {

	static Metrica m;
	static int[] valores = { 5,3 ,4 ,5};

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		String s = ("Característica1 - Característica2- Característica2 - Característica2 - Característica2 - Característica2");
		m = new Metrica(s);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testObterOperacao() throws NumberFormatException, ScriptException {

		System.out.println(m.getFormula());
		System.out.println(m.calcularOperacao(valores));

	}

}
