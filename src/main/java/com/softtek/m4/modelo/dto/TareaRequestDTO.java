package com.softtek.m4.modelo.dto;

import java.util.Objects;

// TODO: Documentar
public class TareaRequestDTO {
    private String titulo;
    private String descripcion;
    
    public TareaRequestDTO(){}
    public TareaRequestDTO(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
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
        int hash = 3;
        return hash;
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
