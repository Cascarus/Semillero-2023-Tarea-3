package com.Semillero2023.Tarea3.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.Semillero2023.Tarea3.entity.Bitacora;
import com.Semillero2023.Tarea3.entity.Carrera;
import com.Semillero2023.Tarea3.entity.Estudiante;
import com.Semillero2023.Tarea3.entity.Factura;
import com.Semillero2023.Tarea3.entity.Usuario;
import com.Semillero2023.Tarea3.repository.BitacoraRepository;
import com.Semillero2023.Tarea3.repository.CarreraRepository;
import com.Semillero2023.Tarea3.repository.EstudianteRepository;
import com.Semillero2023.Tarea3.repository.FacturaRepository;
import com.Semillero2023.Tarea3.repository.UsuarioRepository;
import com.Semillero2023.Tarea3.wsint.ServicioTarea2Int;

@Component
public class ServiceTarea3 implements ServicioTarea2Int{

	@Autowired
	BitacoraRepository bitacoraRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	FacturaRepository facturaRepository;
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	NamedParameterJdbcTemplate npjt;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public String test() {
		return "todo bien";
	}
	
	@Override
	public List<Bitacora> getBitacora() {
		// TODO Auto-generated method stub
		return bitacoraRepository.findAll();
	}

	@Override
	public Bitacora postBitacora(Bitacora bitacora) {
		// TODO Auto-generated method stub
		return bitacoraRepository.save(bitacora);
	}
	
	public ResponseEntity<String> deleteBitacora(Integer idBitacora) {
		Optional <Bitacora> log = bitacoraRepository.findById(idBitacora);
		
		if(log.isPresent()) {
			bitacoraRepository.delete(log.get());
			return new ResponseEntity<>("todo bien",  HttpStatus.OK);
		}
		
		return new ResponseEntity<>("La bitacora no existe",  HttpStatus.NOT_FOUND);
	}

	@Override
	public List<Usuario> getUsuario() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario postUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Factura> getFactura() {
		// TODO Auto-generated method stub
		return (List<Factura>) facturaRepository.findAll();
	}

	@Override
	public Factura postFactura(Factura factura) {
		// TODO Auto-generated method stub
		return facturaRepository.save(factura);
	}

	@Override
	public List<Carrera> getCarrera() {
		// TODO Auto-generated method stub
		return carreraRepository.findAll();
	}

	@Override
	public Carrera postCarrera(Carrera carrera) {
		// TODO Auto-generated method stub
		return carreraRepository.save(carrera);
	}

	@Override
	public List<Estudiante> getEstudiante() {
		// TODO Auto-generated method stub
		return estudianteRepository.findAll();
	}

	@Override
	public Estudiante postEstudiante(Estudiante estudiante) {
		// TODO Auto-generated method stub
		return estudianteRepository.save(estudiante);
	}

	@Override
	public ResponseEntity<String> deleteUsuario(Integer idUsuario) {
		Optional <Usuario> usr = usuarioRepository.findById(idUsuario);
		
		if(usr.isPresent()) {
			facturaRepository.deleteAll(usr.get().getFacturas());
			usuarioRepository.delete(usr.get());
			return new ResponseEntity<>("El usuario se ha eliminado exitosamente",  HttpStatus.OK);
		}
		
		return new ResponseEntity<>("El usuario no existe",  HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> deleteFactura(Integer idFactura) {
		Optional <Factura> fact = facturaRepository.findById(idFactura);
		
		if(fact.isPresent()) {
			facturaRepository.delete(fact.get());
			return new ResponseEntity<>("La factura se ha eliminado exitosamente",  HttpStatus.OK);
		}
		
		return new ResponseEntity<>("El factura no existe",  HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> deleteCarrera(Integer idCarrera) {
		Optional <Carrera> carrera = carreraRepository.findById(idCarrera);
		
		if(carrera.isPresent()) {
			carreraRepository.delete(carrera.get());
			return new ResponseEntity<>("La carrera se ha eliminado exitosamente",  HttpStatus.OK);
		}
		
		return new ResponseEntity<>("El carrera no existe",  HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> deleteEstudiante(Integer idCarrera) {
		Optional <Estudiante> estudiante = estudianteRepository.findById(idCarrera);
		
		if(estudiante.isPresent()) {
			estudianteRepository.delete(estudiante.get());
			return new ResponseEntity<>("La estudiante se ha eliminado exitosamente",  HttpStatus.OK);
		}
		
		return new ResponseEntity<>("El estudiante no existe",  HttpStatus.NOT_FOUND);
	}

	@Override
	public Page<Usuario> getCorreo(Integer page) {
		Pageable paging = PageRequest.of(page, 3);
		return usuarioRepository.findAll(paging);
	}
	
	@Override
	public List<Factura> getFactByFecha(String fecha) throws ParseException {
        Date fecha2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		return facturaRepository.findByFechaAfter(fecha2);
	}
	
	@Override
	public Page<Bitacora> getLogByFecha(Integer page, Integer size) throws ParseException {
        Pageable paging = PageRequest.of(page, size);
		return bitacoraRepository.findAll(paging);
	}
	
	@Override
	public Usuario getUsrByNit(Integer nit){
		return usuarioRepository.findByNitEquals(nit);
	}

	@Override
	public Page<Factura> getFactByNit(Integer nit, Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		return facturaRepository.findByNitEquals(nit, paging);
	}

	
	
}
