package com.softtek.m4.modelo.mapper;
import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import com.softtek.m4.modelo.entidades.Tarea;
import java.util.List;

/**
 * Clase TareaMapper: Utilizada para convertir entre objetos Tarea, TareaRequestDTO y TareaResponseDTO.
 */
public class TareaMapper {
    
    /**
     * Convierte un objeto TareaRequestDTO en un objeto Tarea.
     * @param request El objeto TareaRequestDTO a convertir.
     * @return El objeto Tarea resultante.
     */
    public Tarea convertirATarea(TareaRequestDTO request){
        Tarea tarea = new Tarea();
        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        return tarea;
    }
    
    /**
     * Convierte un objeto Tarea en un objeto TareaResponseDTO.
     * @param tarea El objeto Tarea a convertir.
     * @return El objeto TareaResponseDTO resultante.
     */
    public TareaResponseDTO convertirADto(Tarea tarea){
        TareaResponseDTO tareaResponseDTO = new TareaResponseDTO();
        tareaResponseDTO.setId(tarea.getId());
        tareaResponseDTO.setTitulo(tarea.getTitulo());
        tareaResponseDTO.setDescripcion(tarea.getDescripcion());
        return tareaResponseDTO;
    }
    
    /**
     * Convierte una lista de objetos Tarea en una lista de objetos TareaResponseDTO.
     * @param listaDeTareas La lista de objetos Tarea a convertir.
     * @return La lista de objetos TareaResponseDTO resultante.
     */
    public List<TareaResponseDTO> convertirADtoList(List<Tarea> listaDeTareas){
        return listaDeTareas.stream()
                .map(this::convertirADto)
                .toList();
    }
}