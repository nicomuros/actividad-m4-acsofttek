package com.softtek.m4.servicio;

import com.softtek.m4.exceptions.DatosInvalidosException;
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
    public void altaTarea(TareaRequestDTO request) {
        
        // Validación de datos de request
        if (request.getTitulo().isBlank()) 
            throw new DatosInvalidosException("No se proporcionó un título.");
        
        if (request.getDescripcion().isBlank()) 
            throw new DatosInvalidosException("No se proporcionó una descripción");
        
        if (request.getTitulo().length() < 3 
                || request.getTitulo().length() > 15) {
            throw new DatosInvalidosException(
                    "El título debe contener entre 3 y 15 caracteres.");
        }
        if (request.getDescripcion().length() < 3 
                || request.getDescripcion().length() > 50) {
            throw new DatosInvalidosException(
                    "La descripción debe contener entre 3 y 50 caracteres.");
        }
        
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
        return mapper.convertirADtoList(listaDeTareas);
    }

    @Override
    public void modificarTarea(Integer id, TareaRequestDTO request)
            throws Exception {
        
        // Validación de datos de request
        if (request.getTitulo().isBlank()) 
            throw new DatosInvalidosException("No se proporcionó un título.");
        
        if (request.getDescripcion().isBlank()) 
            throw new DatosInvalidosException("No se proporcionó una descripción.");
        
        if (request.getTitulo().length() < 3 
                || request.getTitulo().length() > 15) {
            throw new DatosInvalidosException(
                    "El título debe contener entre 3 y 15 caracteres.");
        }
        if (request.getDescripcion().length() < 3 
                || request.getDescripcion().length() > 50) {
            throw new DatosInvalidosException(
                    "La descripción debe contener entre 3 y 50 caracteres.");
        }
        
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
            tarea.setDescripcion(request.getDescripcion());
            cambio = true;
        }
        
        // Si no se efectuaron cambios, se lanza una advertencia.
        
        if (!cambio)
            throw new DatosInvalidosException(
                    "Los datos nuevos son iguales a los antiguos.");
        
        // Se actualiza la fecha de modificación
        tarea.setFechaUltimaModificacion(new Date());

        // Se solicita al repositorio modificar la tarea
        tareaDao.edit(tarea);
        
        
    }

    @Override
    public void bajaTarea(Integer id) throws NonexistentEntityException{
        tareaDao.destroy(id);
    }
    
}
