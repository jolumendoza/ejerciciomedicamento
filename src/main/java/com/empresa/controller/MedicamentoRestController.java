package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@RestController
@RequestMapping("/rest/medicamento")
public class MedicamentoRestController {


	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	@ResponseBody  //salida tipo json
	public ResponseEntity<List<Medicamento>> listaMedicamento(){
		
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
	}	
	
	
	@PostMapping
	@ResponseBody //retorna datos en json .. inserta y devuelve el objeto insertado
	public ResponseEntity<Medicamento> registraMedicamento(@RequestBody  Medicamento obj){
	
		if(obj==null)
		{
			return ResponseEntity.noContent().build();
		}
		else
		{	
			obj.setIdMedicamento(0);
			Medicamento objSalida = service.registraActualizaMedicamento(obj);
			return ResponseEntity.ok(objSalida);
		}
	}
	

	@PutMapping
	@ResponseBody //retorna datos en json .. inserta y devuelve el objeto insertado
	public ResponseEntity<Medicamento> actualizaMedicamento(@RequestBody  Medicamento obj){
		
		
		if(obj==null)
		{
			return ResponseEntity.noContent().build();
		}
		else
		{	
			Optional<Medicamento> optMedicamento =service.ObtenerPorID(obj.getIdMedicamento());
			 
			if(optMedicamento.isEmpty())
			{
				return ResponseEntity.noContent().build();
			}
			else
			{	
				Medicamento objSalida = service.registraActualizaMedicamento(obj);
				return ResponseEntity.ok(objSalida);
			}
		}
	}
	
	 
	
	
	
	
}
