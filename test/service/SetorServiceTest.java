package service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SetorServiceTest {

    private SetorService setorService;

    @Before
    public void setUp() {
        setorService = new SetorService();
    }

    @Test
    public void testCalcularPorcentagemOcupacaoSucesso() throws Exception {
        // Cenário 1
        double resultado = setorService.calcularPorcentagemOcupacao(50, 100);
        assertEquals(50.0, resultado, 0.0001);

        // Cenário 2
        double resultado2 = setorService.calcularPorcentagemOcupacao(25, 100);
        assertEquals(25.0, resultado2, 0.0001);
    }

    @Test
    public void testCalcularPorcentagemSetorTotalmenteCheio() throws Exception {
        // Cenário 3
        double resultado = setorService.calcularPorcentagemOcupacao(10, 10);
        assertEquals(100.0, resultado, 0.0001);
    }

    @Test
    public void testCalcularPorcentagemSetorVazio() throws Exception {
        // Cenário 4
        double resultado = setorService.calcularPorcentagemOcupacao(0, 10);
        assertEquals(0.0, resultado, 0.0001);
    }

    @Test(expected = Exception.class)
    public void testCalcularPorcentagemComCapacidadeZeroDeveLancarExcecao() throws Exception {
        setorService.calcularPorcentagemOcupacao(5, 0);
    }

    @Test(expected = Exception.class)
    public void testCalcularPorcentagemComOcupadosMaiorQueTotalDeveLancarExcecao() throws Exception {
        setorService.calcularPorcentagemOcupacao(15, 10);
    }
}