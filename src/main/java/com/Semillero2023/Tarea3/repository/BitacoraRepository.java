package com.Semillero2023.Tarea3.repository;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Semillero2023.Tarea3.entity.Bitacora;

@Repository("BitacoraRepository")
public interface BitacoraRepository extends JpaRepository<Bitacora, Serializable>{
	
	public Page<Bitacora> findByFechaEquals(Date Fecha, Pageable pageable);
	public Page<Bitacora> findAll(Pageable pageable);
	
	
}
