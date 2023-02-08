package com.Semillero2023.Tarea3.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Semillero2023.Tarea3.entity.Usuario;

@Repository("UsuarioRepository")
public interface UsuarioRepository extends CrudRepository<Usuario, Serializable>{
	
	public Usuario findByNitEquals(Integer nit);
	public Page<Usuario> findAll(Pageable pageable);
}
