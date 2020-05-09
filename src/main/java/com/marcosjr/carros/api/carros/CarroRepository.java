package com.marcosjr.carros.api.carros;

import com.marcosjr.carros.api.carros.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);
}
