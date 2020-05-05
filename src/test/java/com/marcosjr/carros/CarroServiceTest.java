package com.marcosjr.carros;

import com.marcosjr.carros.api.exception.ObjectNotFoundException;
import com.marcosjr.carros.domain.Carro;
import com.marcosjr.carros.domain.CarroService;
import com.marcosjr.carros.domain.dto.CarroDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroServiceTest {

	@Autowired
	private CarroService service;

	@Test
	public void testSave() {

		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");

		CarroDTO c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		//Busca o objeto
		c = service.getCarroById(id);
		assertNotNull(c);

		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());

		//Deleta o objeto
		service.delete(id);

		//Verifica se deletou
		try {
			assertNull(service.getCarroById(id));
			fail("O carro nao foi excluido");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}

	@Test
	public void testLista() {
		List<CarroDTO> carros = service.getCarros();

		assertEquals(30, carros.size());
	}

	@Test
	public void testListaPorTipo() {
		assertEquals(10, service.getCarroByTipo("classicos").size());
		assertEquals(10, service.getCarroByTipo("esportivos").size());
		assertEquals(10, service.getCarroByTipo("luxo").size());

		assertEquals(0, service.getCarroByTipo("x").size());
	}

	@Test
	public void testGet() {
		CarroDTO c = service.getCarroById(11L);

		assertNotNull(c);

		assertEquals("Ferrari FF", c.getNome());
	}
}
