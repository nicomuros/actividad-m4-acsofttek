package com.softtek.m4.servicio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import com.softtek.m4.modelo.entidades.Tarea;
import java.util.List;

public interface TareaServicio {
    void altaTarea(TareaRequestDTO request);
    List<TareaResponseDTO> listarTareas();
    Tarea listarTareaPorId(Integer id);
    void modificarTarea(Integer id, TareaRequestDTO request) throws NonexistentEntityException, Exception;
    void bajaTarea(Integer id);
}
