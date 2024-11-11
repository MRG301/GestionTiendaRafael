package vista;

import dao.implementacion.EmpleadoDAOImplementacion;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.entidades.Empleado;

public class GestionEmpleados extends javax.swing.JFrame {

    private EmpleadoDAOImplementacion empleadoDAO;

    public GestionEmpleados() {

        empleadoDAO = new EmpleadoDAOImplementacion();
        initComponents();
        cargarDatosTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAgregarEmp = new javax.swing.JButton();
        btnEditarEmp = new javax.swing.JButton();
        btnEliminarEmp = new javax.swing.JButton();
        btnActualizarEmp = new javax.swing.JButton();
        btnCancelarEmp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablaEmp = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gestion de Empleados");

        btnAgregarEmp.setText("Agregar");
        btnAgregarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpActionPerformed(evt);
            }
        });

        btnEditarEmp.setText("Editar");
        btnEditarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEmpActionPerformed(evt);
            }
        });

        btnEliminarEmp.setText("Eliminar");
        btnEliminarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpActionPerformed(evt);
            }
        });

        btnActualizarEmp.setText("Actualizar");
        btnActualizarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpActionPerformed(evt);
            }
        });

        btnCancelarEmp.setText("Cancelar");
        btnCancelarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEmpActionPerformed(evt);
            }
        });

        tblTablaEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Puesto", "Salario"
            }
        ));
        jScrollPane1.setViewportView(tblTablaEmp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarEmp)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarEmp)
                        .addGap(33, 33, 33)
                        .addComponent(btnCancelarEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarEmp)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarEmp)))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEmp)
                    .addComponent(btnEditarEmp)
                    .addComponent(btnEliminarEmp)
                    .addComponent(btnActualizarEmp)
                    .addComponent(btnCancelarEmp))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEmpActionPerformed
        int filaSeleccionada = tblTablaEmp.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) tblTablaEmp.getValueAt(filaSeleccionada, 0);
            Empleado empleado = empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
            if (empleado != null) {
                // Crear un JDialog para mostrar el FormularioEmp
                JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Editar Empleado", true);
                FormularioEmp formularioEmp = new FormularioEmp(this, empleado);
                dialog.add(formularioEmp);
                dialog.pack();
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar el empleado seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un empleado para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarEmpActionPerformed

    private void btnCancelarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEmpActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarEmpActionPerformed

    private void btnActualizarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpActionPerformed
        cargarDatosTabla();
    }//GEN-LAST:event_btnActualizarEmpActionPerformed

    private void btnEliminarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpActionPerformed
        int filaSeleccionada = tblTablaEmp.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) tblTablaEmp.getValueAt(filaSeleccionada, 0);
            int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este empleado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean eliminado = empleadoDAO.eliminarEmpleado(idEmpleado);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarDatosTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarEmpActionPerformed

    private void btnAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpActionPerformed

        JFrame frame = new JFrame("Agregar Empleado");
        FormularioEmp formularioEmpleado = new FormularioEmp(GestionEmpleados.this, null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(formularioEmpleado); // Agregar FormularioEmp al contenido del JFrame
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }//GEN-LAST:event_btnAgregarEmpActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionEmpleados gestionEmpleados = new GestionEmpleados();
                new GestionEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarEmp;
    private javax.swing.JButton btnAgregarEmp;
    private javax.swing.JButton btnCancelarEmp;
    private javax.swing.JButton btnEditarEmp;
    private javax.swing.JButton btnEliminarEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTablaEmp;
    // End of variables declaration//GEN-END:variables

    public void cargarDatosTabla() {
        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tblTablaEmp.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista de empleados desde el DAO
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();

        // Agregar cada empleado al modelo de la tabla
        for (Empleado empleado : empleados) {
            Object[] fila = new Object[8];
            fila[0] = empleado.getId();
            fila[1] = empleado.getNombre();
            fila[2] = empleado.getApellido();
            fila[3] = empleado.getEmail();
            fila[4] = empleado.getTelefono();
            fila[5] = empleado.getDireccion().getCalle() + " " + empleado.getDireccion().getNumero() + ", "
                    + empleado.getDireccion().getCiudad() + ", " + empleado.getDireccion().getCodigoPostal() + ", "
                    + empleado.getDireccion().getEstado();
            fila[6] = empleado.getRol();
            fila[7] = empleado.getSalario();
            model.addRow(fila);
        }
    }
}
