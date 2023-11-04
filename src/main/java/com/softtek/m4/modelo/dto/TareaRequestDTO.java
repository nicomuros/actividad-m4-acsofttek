package com.softtek.m4.modelo.dto;

import java.util.Objects;

/**
 * Objeto de Transferencia de Datos (DTO) utilizado para transportar información 
 * en las solicitudes de <b>creación</b> y <b>modificación</b> de Tareas.
 * Este DTO contiene datos como el título y la descripción de una Tarea.
 */
public class TareaRequestDTO {
    private String titulo;
    private String descripcion;
    
    public TareaRequestDTO(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TareaRequestDTO other = (TareaRequestDTO) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }

    @Override
    public String toString() {
        return "TareaRequestDTO{" + "titulo=" + titulo + ", descripcion=" + descripcion + '}';
    }
    
    
}
