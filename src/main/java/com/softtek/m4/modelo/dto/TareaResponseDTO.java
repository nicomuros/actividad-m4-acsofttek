package com.softtek.m4.modelo.dto;
import java.util.Objects;

/**
 * Objeto de Transferencia de Datos (DTO) para representar una Tarea en las 
 * respuestas de las solicitudes de listado de Tareas.
 * Este DTO contiene información sobre la identificación, el título y la
 * descripción de una Tarea.
 */
public class TareaResponseDTO {
    
    private Integer id;
    private String titulo;
    private String descripcion;
    
    public TareaResponseDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return 7;
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
        final TareaResponseDTO other = (TareaResponseDTO) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "TareaResponseDTO{" + "id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + '}';
    }
    
}
