package com.softtek.m4;

import com.softtek.m4.modelo.mapper.TareaMapper;
import com.softtek.m4.repositorio.TareaDao;
import com.softtek.m4.repositorio.TareaJpaController;
import com.softtek.m4.servicio.TareaServicio;
import com.softtek.m4.servicio.TareaServicioImpl;
import com.softtek.m4.vista.TareaForm;

public class ActividadM4 {

    public static void main(String[] args) {
        TareaDao tareaDao = new TareaJpaController();
        TareaMapper mapper = new TareaMapper();
        TareaServicio tareaServicio = new TareaServicioImpl(tareaDao, mapper);
        new TareaForm(tareaServicio).setVisible(true);
    }
}
