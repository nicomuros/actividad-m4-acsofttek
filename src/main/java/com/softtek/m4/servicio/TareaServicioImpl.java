package com.softtek.m4.servicio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import com.softtek.m4.modelo.mapper.TareaMapper;
import com.softtek.m4.modelo.entidades.Tarea;
import com.softtek.m4.repositorio.TareaDao;
import java.util.Date;
import java.util.List;

public class TareaServicioImpl implements TareaServicio{
    
    private TareaDao tareaDao = null;
    private TareaMapper mapper = null;
    
    public TareaServicioImpl(TareaDao tareaDao, TareaMapper mapper){
        this.tareaDao = tareaDao;
        this.mapper = mapper;
    }

    @Override
    // TODO: Validar los datos. Excepciones personalizadas
    public void altaTarea(TareaRequestDTO request) {
        // Se convierte la solicitud en una entidad
        Tarea tarea = mapper.convertirATarea(request);
        
        // Se incluye la fecha en la que se creó la tarea
        
        tarea.setFechaCreacion(new Date());
        // Solicitud al repositorio para dar de alta a la entidad
        tareaDao.create(tarea);
    }

    @Override
    public List<TareaResponseDTO> listarTareas() {
        // Se solicita al repositorio la lista de tareas
        List<Tarea> listaDeTareas = tareaDao.findTareaEntities();
        
        // Se convierte la lista de entidades a una lista de objetos DTO
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
        
        // Recuperar la tarea original desde la base de datos
        Tarea tarea = tareaDao.findTarea(id);
        
        // Actualizar solo los campos que sean no-nulos y que sean diferentes a los originales.
        // Esta comprobación es necesaria para no sobreescribir datos antiguos con valores nulos
        
        // Variable para control de cambios
        boolean cambio = false;
        
        
        if (request.getTitulo() != null 
                && !request.getTitulo().equals(tarea.getTitulo())){
            
            // Si los valores nuevos son no-nulos y diferentes, actualizo el campo
            tarea.setTitulo(request.getTitulo());
            cambio = true;
        }
        
        if (request.getDescripcion() != null
                && !request.getDescripcion().equals(tarea.getDescripcion())){
            
            // Si los valores nuevos son no-nulos y diferentes, actualizo el campo
            tarea.setDescripcion(request.getTitulo());
            cambio = true;
        }
        
        if (cambio == true){
            // Se actualiza la fecha de modificación
            tarea.setFechaUltimaModificacion(new Date());

            // Se solicita al repositorio modificar la tarea
            tareaDao.edit(tarea);
        }
        
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
