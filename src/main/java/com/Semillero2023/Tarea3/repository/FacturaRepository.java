package com.Semillero2023.Tarea3.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Semillero2023.Tarea3.entity.Factura;

@Repository("FacturaRepository")
public interface FacturaRepository extends CrudRepository<Factura, Serializable>{
	
	public List<Factura> findByTotalGreaterThanEqualOrderByTotalDesc(Double total);
	
	public List<Factura> findByFechaAfter(Date Fecha);
	
	public Page<Factura> findByNitEquals(Integer nit, Pageable pageable);
}
