package leti.grupoa.projeto_es;

import java.io.IOException;

import javax.script.ScriptException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MetricaTest {

	static Metrica m;
	static int[] valores = { 20, 5, 3, 10 };

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testObterOperacao() throws NumberFormatException, ScriptException {

		System.out.println(m.getFormula());
		System.out.println("Qualidade da métrica '" + m.getName() + "':" + m.calculateQuality(valores));
	}

	@Test
	void testAllmetrica() throws IOException, Exception {

		Sala s1 = new Sala("AA2.23");
		Sala s2 = new Sala("C1.01");

		Metrica m1 = new Metrica("Capacidade diff.", "Capacidade Normal - 14 * No caracteristicas + Capacidade Exame");
		m1.addClass(s1);
		m1.addClass(s2);
		s1.caractChecker();
		m1.getQuality();
	//	System.out.println(s1.getCaracValue("Capacidade Normal"));
	//	System.out.println(m1.getCriteria());
			
                	}
}
