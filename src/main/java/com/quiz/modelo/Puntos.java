package com.quiz.modelo;
import java.util.List;
import com.quiz.persistencia.Usuario;


public interface Puntos {
	public List<Usuario> getPuntos();

	String setNombre(Usuario usuario);
	
	int setPuntos(Usuario usuario);
	
	int getPuntuaciones();
}
