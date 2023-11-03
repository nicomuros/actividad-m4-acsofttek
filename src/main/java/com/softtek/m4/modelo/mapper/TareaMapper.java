package com.softtek.m4.modelo.mapper;
import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import com.softtek.m4.modelo.entidades.Tarea;
import java.util.List;

// TODO: documentar
public class TareaMapper {
    
    public Tarea convertirATarea(TareaRequestDTO request){
        Tarea tarea = new Tarea();
        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        return tarea;
    }
    
    public TareaResponseDTO convertirADto(Tarea tarea){
        TareaResponseDTO tareaResponseDTO = new TareaResponseDTO();
        tareaResponseDTO.setId(tarea.getId());
        tareaResponseDTO.setTitulo(tarea.getTitulo());
        tareaResponseDTO.setDescripcion(tarea.getDescripcion());
        return tareaResponseDTO;
    }
    
    public List<TareaResponseDTO> convertirADtoList(List<Tarea> listaDeTareas){
        List<TareaResponseDTO> listaDeTareasDTO = listaDeTareas.stream()
                .map(tarea -> convertirADto(tarea))
                .toList();
        return listaDeTareasDTO;
    }
}
