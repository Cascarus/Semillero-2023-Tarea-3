package com.Semillero2023.Tarea3.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Semillero2023.Tarea3.entity.Estudiante;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Serializable>{

}
