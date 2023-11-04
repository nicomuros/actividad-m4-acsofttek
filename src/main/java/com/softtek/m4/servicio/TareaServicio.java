package com.softtek.m4.servicio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import java.util.List;

/**
 * Interfaz que define los servicios disponibles para gestionar Tareas.
 */
public interface TareaServicio {

    /**
     * Crea una nueva Tarea utilizando la información proporcionada en el objeto 
     * TareaRequestDTO.
     *
     * @param request El objeto TareaRequestDTO que contiene los detalles de la 
     * Tarea a crear.
     */
    void altaTarea(TareaRequestDTO request);

    /**
     * Obtiene una lista de todas las Tareas disponibles en el sistema.
     *
     * @return Una lista de objetos TareaResponseDTO que representan las Tareas 
     * existentes.
     */
    List<TareaResponseDTO> listarTareas();

    /**
     * Modifica una Tarea existente con la información proporcionada en el objeto
     * TareaRequestDTO.
     *
     * @param id El identificador de la Tarea a modificar.
     * @param request El objeto TareaRequestDTO que contiene los detalles actualizados
     * de la Tarea.
     * @throws NonexistentEntityException Si la Tarea no existe en el sistema.
     * @throws Exception Si ocurre algún error durante la modificación.
     */
    void modificarTarea(Integer id, TareaRequestDTO request) throws NonexistentEntityException, Exception;

    /**
     * Elimina una Tarea del sistema según su identificador.
     *
     * @param id El identificador de la Tarea a eliminar.
     * @throws NonexistentEntityException Si la Tarea no existe en el sistema.
     * @throws Exception Si ocurre algún error durante la eliminación.
     */
    void bajaTarea(Integer id) throws NonexistentEntityException, Exception;
}
