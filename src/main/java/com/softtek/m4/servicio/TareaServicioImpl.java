package com.softtek.m4.servicio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import com.softtek.m4.modelo.mapper.TareaMapper;
import com.softtek.m4.modelo.entidades.Tarea;
import com.softtek.m4.repositorio.TareaDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TareaServicioImpl implements TareaServicio{
    
    private TareaDao tareaDao = null;
    private TareaMapper mapper = null;
    
    public TareaServicioImpl(TareaDao tareaDao, TareaMapper mapper){
        this.tareaDao = tareaDao;
        this.mapper = mapper;
    }

    @Override
    public void altaTarea(TareaRequestDTO request) {
        // Se convierte la solicitud en una entidad
        Tarea tarea = mapper.convertirATarea(request);
        
        // Solicitud al repositorio para dar de alta a la entidad
        tareaDao.create(tarea);
    }

    @Override
    public List<TareaResponseDTO> listarTareas() {
        // Se solicita al repositorio la lista de tareas
        List<Tarea> listaDeTareas = tareaDao.findTareaEntities();
        
        // Se convierte la entidad a una lista de objetos DTO
        List<TareaResponseDTO> listaDeTareasDTO = 
                mapper.convertirADtoList(listaDeTareas);
        
        // Se retorna la lista de DTO
        return listaDeTareasDTO;
    }

    @Override
    public Tarea listarTareaPorId(Integer id) {
        return tareaDao.findTarea(id);
    }

    @Override
    public void modificarTarea(Integer id, TareaRequestDTO request) 
            throws NonexistentEntityException, Exception{
        
        // Se convierte la solicitud en una entidad
        Tarea tarea = mapper.convertirATarea(request);
        
        // Se añade el ID a la tarea
        tarea.setId(id);
        
        // Se solicita al repositorio modificar la tarea
        tareaDao.edit(tarea);
        
    }

    @Override
    public void bajaTarea(Integer id) {
        try {
        tareaDao.destroy(id);
        } catch (NonexistentEntityException e){
            System.out.println("e = " + e);
        }
    }
    
}
