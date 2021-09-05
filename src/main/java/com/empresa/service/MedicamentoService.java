package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {


	public List<Medicamento> listaMedicamento();
	
	public abstract Medicamento registraActualizaMedicamento(Medicamento obj);
		
	public abstract Optional<Medicamento> ObtenerPorID(int IdMedicamento);
}
