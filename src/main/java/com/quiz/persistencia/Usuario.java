package com.quiz.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "puntos")
public class Usuario {
	// ATRIBUTOS
	@Column(name = "Puntos")
	private int puntos;

	@Id
	@Column(name = "Usuario")
	private String nombre;

	// CONSTRUCTOR
	public Usuario(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}

	// GETTERS AND SETTERS
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Nombre:" + nombre + "Puntos:" + puntos;
	}

}
