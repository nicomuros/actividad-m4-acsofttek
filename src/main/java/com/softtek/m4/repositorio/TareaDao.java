package com.softtek.m4.repositorio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.entidades.Tarea;
import java.util.List;

/**
 * Interfaz que define operaciones de acceso a datos para las entidades de Tarea.
 */
public interface TareaDao {

    /**
     * Crea una nueva Tarea en el repositorio.
     *
     * @param tarea La Tarea a ser creada.
     */
    void create(Tarea tarea);

    /**
     * Edita una Tarea existente en el repositorio.
     *
     * @param tarea La Tarea a ser modificada.
     * @throws NonexistentEntityException Si la Tarea no existe en el repositorio.
     * @throws Exception Si ocurre algún error durante la edición.
     */
    void edit(Tarea tarea) throws NonexistentEntityException, Exception;

    /**
     * Elimina una Tarea del repositorio mediante su identificador.
     *
     * @param id El identificador de la Tarea a ser eliminada.
     * @throws NonexistentEntityException Si la Tarea no existe en el repositorio.
     */
    void destroy(Integer id) throws NonexistentEntityException;

    /**
     * Obtiene una lista de todas las Tareas existentes en el repositorio.
     *
     * @return Una lista de objetos Tarea.
     */
    List<Tarea> findTareaEntities();

    /**
     * Busca una Tarea específica por su identificador.
     *
     * @param id El identificador de la Tarea a buscar.
     * @return La Tarea encontrada o null si no existe.
     */
    Tarea findTarea(Integer id);
}