package com.softtek.m4.repositorio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.entidades.Tarea;
import java.util.List;

public interface TareaDao {
    void create(Tarea tarea);
    void edit(Tarea tarea) throws NonexistentEntityException, Exception;
    void destroy(Integer id) throws NonexistentEntityException;
    List<Tarea> findTareaEntities();
    List<Tarea> findTareaEntities(int maxResults, int firstResult);
    Tarea findTarea(Integer id);
    int getTareaCount();
}
