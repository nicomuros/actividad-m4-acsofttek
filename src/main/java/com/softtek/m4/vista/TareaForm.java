package com.softtek.m4.vista;

import com.softtek.m4.modelo.dto.TareaRequestDTO;
import com.softtek.m4.modelo.dto.TareaResponseDTO;
import com.softtek.m4.modelo.mapper.TareaMapper;
import com.softtek.m4.repositorio.TareaDao;
import com.softtek.m4.repositorio.TareaJpaController;
import com.softtek.m4.servicio.TareaServicio;
import com.softtek.m4.servicio.TareaServicioImpl;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TareaForm extends javax.swing.JFrame {
    
    // Dependencia servicio. Se inyecta.
    TareaServicio tareaServicio = null;
    
    // Constructor
    public TareaForm(TareaServicio tareaServicio) {
        
        // Inicializar servicio
        this.tareaServicio = tareaServicio;
        
        // Inicializar componentes
        initComponents();
        
        // Ubicar en el centro de la pantalla
        setLocationRelativeTo(null);
        
        // Actualizar campos
        actualizarCampos();

    }
    // Método para actualizar tabla. Solicita la lista al servicio
    private void actualizarTabla(){
        
        // Instanciamos un modelo de tabla donde se almacenaran los datos
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"ID", "Título", "Descripción"}, 0);
        
        // Se limpian todas las filas de la tabla
        modeloTabla.setRowCount(0);
        

        try{
            // Solicitud al servicio de la lista completa de tareas
            List<TareaResponseDTO> listaDeTareas = tareaServicio.listarTareas();
            
            // Se recorre la lista para crear una fila de tabla por tarea
            listaDeTareas.forEach((tarea) -> {
                    Object[] renglon = {
                       tarea.getId(),
                       tarea.getTitulo(),
                       tarea.getDescripcion()
                    };
                    modeloTabla.addRow(renglon);
                }
            );
            
            tablaTareas.setModel(modeloTabla);
            if (tablaTareas.getColumnModel().getColumnCount() > 0) {
                tablaTareas.getColumnModel().getColumn(0).setMinWidth(40);
                tablaTareas.getColumnModel().getColumn(0).setPreferredWidth(40);
                tablaTareas.getColumnModel().getColumn(0).setMaxWidth(40);
                tablaTareas.getColumnModel().getColumn(1).setMinWidth(150);
                tablaTareas.getColumnModel().getColumn(1).setPreferredWidth(150);
                tablaTareas.getColumnModel().getColumn(1).setMaxWidth(150);
            }
        } catch(Exception e){
            mostrarError(e);
        }
    }
    
    // Actualziar pantalla. Desencadenado por una acción exitosa.
    private void actualizarCampos(){
        idLabel.setText("");
        tituloTextField.setText("");
        descripcionTextField.setText("");
        actualizarTabla();
    }
    
    // Mostrar mensajes de error
    private void mostrarError(Exception e){
        resultadoLabel.setText("Error: " + e.getMessage());
    }
    
    private void mostrarExito(String mensaje){
        resultadoLabel.setText("Exito: " + mensaje);

    }
    
    // Metodo para agregar tarea
    private void agregarTarea(){
        
        // Se obtienen los valores ingresados por el usuario
        String titulo = tituloTextField.getText();
        String descripcion = descripcionTextField.getText();
        
        TareaRequestDTO request = new TareaRequestDTO();
        
        try{
            request.setTitulo(titulo);
            request.setDescripcion(descripcion);
            tareaServicio.altaTarea(request);
            mostrarExito("Se agrego la tarea correctamente");
        } catch (Exception e){
            mostrarError(e);
        } finally{
            actualizarCampos();
        }
    }
    
    // Metodo para modificar tarea
    private void modificarTarea(){
        
        // Obtener el ID de la tarea a modificar
        Integer id = obtenerIdSeleccionado();
        
        // Obtener datos actualizados
        String titulo = tituloTextField.getText();
        String descripcion = descripcionTextField.getText();
        
        // Construcción de la request
        TareaRequestDTO request = new TareaRequestDTO();
        request.setTitulo(titulo);
        request.setDescripcion(descripcion);
        
        // Envío de la request al servicio
        try{
            tareaServicio.modificarTarea(id, request);
            mostrarExito("La tarea se modificó exitosamente");
            actualizarCampos();
        } catch (Exception e){
            mostrarError(e);
        }
    }
    
    private void eliminarTarea(){
        // Obtener id seleccionado
        Integer id = obtenerIdSeleccionado();
        
        // Se intenta eliminar la tarea
        try{
            tareaServicio.bajaTarea(id);
            mostrarExito("Se elimino correctamente la tarea");
            actualizarCampos();
        } catch(Exception e){
            mostrarError(e);
        }
    }
    
    private Integer obtenerIdSeleccionado(){
        String id = idLabel.getText();
        Integer idInt = Integer.valueOf(id);
        return idInt;
    }
    // Se maneja el click de la tabla. Se rellenan los textField con los datos
    private void manejarClickTabla(){
        int filaSeleccionada = tablaTareas.getSelectedRow();
        
        // Se verifica que el usuario haya seleccionado una fila activa
        if (filaSeleccionada != -1){
            String id = tablaTareas.getValueAt(filaSeleccionada, 0).toString();
            String titulo = tablaTareas.getValueAt(filaSeleccionada, 1).toString();
            String descripcion = tablaTareas.getValueAt(filaSeleccionada, 2).toString();
            
            idLabel.setText(id);
            tituloTextField.setText(titulo);
            descripcionTextField.setText(descripcion);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        agregarBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tituloTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        descripcionTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTareas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        resultadoLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));

        agregarBtn.setText("Agregar tarea");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        modificarBtn.setText("Modificar Tarea");
        modificarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarBtnMouseClicked(evt);
            }
        });

        eliminarBtn.setText("Eliminar Tarea");
        eliminarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarBtnMouseClicked(evt);
            }
        });

        jLabel1.setText("ID:");

        idLabel.setText(" ");

        jLabel3.setText("Título:");

        jLabel4.setText("Descripción:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modificarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agregarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloTextField)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(descripcionTextField))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarBtn)
                    .addComponent(jLabel1)
                    .addComponent(idLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modificarBtn)
                    .addComponent(jLabel3)
                    .addComponent(tituloTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarBtn)
                    .addComponent(jLabel4)
                    .addComponent(descripcionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tablaTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTareasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaTareas);
        if (tablaTareas.getColumnModel().getColumnCount() > 0) {
            tablaTareas.getColumnModel().getColumn(0).setMinWidth(40);
            tablaTareas.getColumnModel().getColumn(0).setPreferredWidth(40);
            tablaTareas.getColumnModel().getColumn(0).setMaxWidth(40);
            tablaTareas.getColumnModel().getColumn(1).setMinWidth(150);
            tablaTareas.getColumnModel().getColumn(1).setPreferredWidth(150);
            tablaTareas.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Resultado"));

        resultadoLabel.setText("Aguardando acción del usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultadoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resultadoLabel)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel6.setText("TODO LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        agregarTarea();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void tablaTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTareasMouseClicked
        manejarClickTabla();
    }//GEN-LAST:event_tablaTareasMouseClicked

    private void modificarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBtnMouseClicked
        modificarTarea();
    }//GEN-LAST:event_modificarBtnMouseClicked

    private void eliminarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBtnMouseClicked
        eliminarTarea();
    }//GEN-LAST:event_eliminarBtnMouseClicked


    // MAIN
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TareaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TareaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TareaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TareaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TareaDao tareaDao = new TareaJpaController();
                TareaMapper mapper = new TareaMapper();
                TareaServicio tareaServicio = new TareaServicioImpl(tareaDao, mapper);
                new TareaForm(tareaServicio).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JTextField descripcionTextField;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JLabel resultadoLabel;
    private javax.swing.JTable tablaTareas;
    private javax.swing.JTextField tituloTextField;
    // End of variables declaration//GEN-END:variables
}
