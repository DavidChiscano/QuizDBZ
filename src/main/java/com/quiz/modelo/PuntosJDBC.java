package com.quiz.modelo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quiz.persistencia.Usuario;
@Repository
public class PuntosJDBC implements Puntos {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int setPuntos(Usuario usuario){
		return jdbcTemplate.update("INSERT INTO puntos(Usuario, Puntos) values(?,?)",
				usuario.getNombre(), usuario.getPuntos());
	}
	
	@Override
	public List<Usuario> getPuntos() {
		return jdbcTemplate.query("select * from puntos",
				(rs, rowNum) -> new Usuario(rs.getString("Usuario"), rs.getInt("Puntos")));
	}
	@Override
	public int getPuntuaciones() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String setNombre(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
