package vista;

import dao.implementacion.EmpleadoDAOImplementacion;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.entidades.Direccion;
import modelo.entidades.Empleado;
import modelo.enums.RolEmpleado;

public class FormularioEmp extends javax.swing.JPanel {

    private GestionEmpleados gestionEmpleados;
    private Empleado empleado; // Puede ser null para agregar
    private EmpleadoDAOImplementacion empleadoDAO;

    public FormularioEmp(GestionEmpleados gestionEmpleados, Empleado empleado) {
        this.gestionEmpleados = gestionEmpleados;
        this.empleado = empleado;
        this.empleadoDAO = new EmpleadoDAOImplementacion();
        initComponents();
        cargarPuestos(); // Llama al método para cargar los puestos

        if (empleado != null) {
            cargarDatosEmpleado();
            lblTitulo.setText("Editar Empleado");
        } else {
            lblTitulo.setText("Agregar Empleado");
        }
    }

    private void cargarPuestos() {
        cmbPuesto.removeAllItems();
        for (RolEmpleado rol : RolEmpleado.values()) {
            cmbPuesto.addItem(rol); // Agregar el enum directamente sin convertir a String
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        txtSalario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbPuesto = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEstadoEmp = new javax.swing.JTextField();
        txtEmailEmp = new javax.swing.JTextField();
        txtNumeroEmp = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtCalleEmp = new javax.swing.JTextField();
        txtNombreEmp = new javax.swing.JTextField();
        txtTelefonoEmp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoEmp = new javax.swing.JTextField();
        txtCiudadEmp = new javax.swing.JTextField();
        txtApellidoEmp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        jLabel11.setText("Estado");

        jLabel13.setText("Puesto:");

        jLabel3.setText("Nombre:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });

        jLabel12.setText("Salario");

        jLabel9.setText("Ciudad");

        cmbPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuestoActionPerformed(evt);
            }
        });

        jLabel8.setText("Numero");

        jLabel7.setText("Calle");

        jLabel10.setText("C.P:");

        jLabel5.setText("Email:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtNombreEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEmpActionPerformed(evt);
            }
        });

        jLabel6.setText("Telefono");

        jLabel4.setText("Apellido:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtApellidoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(31, 31, 31)
                                        .addComponent(txtNombreEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCalleEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(cmbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNumeroEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(txtCiudadEmp)
                            .addComponent(txtCodigoEmp)
                            .addComponent(txtEstadoEmp)
                            .addComponent(txtSalario)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(lblTitulo)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtNombreEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(txtApellidoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudadEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(txtEmailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstadoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCalleEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            parentWindow.dispose(); // Cierra la ventana contenedora
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioActionPerformed

    private void txtNombreEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEmpActionPerformed
    private void cargarDatosEmpleado() {
        txtNombreEmp.setText(empleado.getNombre());
        txtApellidoEmp.setText(empleado.getApellido());
        txtEmailEmp.setText(empleado.getEmail());
        txtTelefonoEmp.setText(empleado.getTelefono());
        txtCalleEmp.setText(empleado.getDireccion().getCalle());
        txtNumeroEmp.setText(empleado.getDireccion().getNumero());
        txtCiudadEmp.setText(empleado.getDireccion().getCiudad());
        txtCodigoEmp.setText(empleado.getDireccion().getCodigoPostal());
        txtEstadoEmp.setText(empleado.getDireccion().getEstado());
        txtSalario.setText(String.valueOf(empleado.getSalario()));
        cmbPuesto.setSelectedItem(empleado.getRol());
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        RolEmpleado rolSeleccionado = (RolEmpleado) cmbPuesto.getSelectedItem();

        if (txtNombreEmp.getText().trim().isEmpty()
                || txtApellidoEmp.getText().trim().isEmpty()
                || txtEmailEmp.getText().trim().isEmpty()
                || txtTelefonoEmp.getText().trim().isEmpty()
                || txtCalleEmp.getText().trim().isEmpty()
                || txtNumeroEmp.getText().trim().isEmpty()
                || txtCiudadEmp.getText().trim().isEmpty()
                || txtCodigoEmp.getText().trim().isEmpty()
                || txtEstadoEmp.getText().trim().isEmpty()
                || txtSalario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar formato de salario
        double salario;
        try {
            salario = Double.parseDouble(txtSalario.getText().trim());
            if (salario < 0) {
                throw new NumberFormatException("El salario no puede ser negativo.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un salario válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (empleado == null) { // Agregar
            Direccion direccion = new Direccion();
            direccion.setCalle(txtCalleEmp.getText().trim());
            direccion.setNumero(txtNumeroEmp.getText().trim());
            direccion.setCiudad(txtCiudadEmp.getText().trim());
            direccion.setCodigoPostal(txtCodigoEmp.getText().trim());
            direccion.setEstado(txtEstadoEmp.getText().trim());

            Empleado nuevoEmpleado = new Empleado(
                    0, // ID será asignado automáticamente
                    txtNombreEmp.getText().trim(),
                    txtApellidoEmp.getText().trim(),
                    txtEmailEmp.getText().trim(),
                    txtTelefonoEmp.getText().trim(),
                    direccion,
                    rolSeleccionado, // Asignación del rol
                    salario
            );

            boolean creado = empleadoDAO.agregarEmpleado(nuevoEmpleado);
            if (creado) {
                JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                gestionEmpleados.cargarDatosTabla(); // Refrescar la tabla en la vista principal
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else { // Editar
            Direccion direccion = empleado.getDireccion();
            direccion.setCalle(txtCalleEmp.getText().trim());
            direccion.setNumero(txtNumeroEmp.getText().trim());
            direccion.setCiudad(txtCiudadEmp.getText().trim());
            direccion.setCodigoPostal(txtCodigoEmp.getText().trim());
            direccion.setEstado(txtEstadoEmp.getText().trim());

            empleado.setNombre(txtNombreEmp.getText().trim());
            empleado.setApellido(txtApellidoEmp.getText().trim());
            empleado.setEmail(txtEmailEmp.getText().trim());
            empleado.setTelefono(txtTelefonoEmp.getText().trim());
            empleado.setRol(rolSeleccionado); // Asignación del rol
            empleado.setSalario(salario);

            boolean actualizado = empleadoDAO.actualizarEmpleado(empleado);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Empleado actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                gestionEmpleados.cargarDatosTabla(); // Refrescar la tabla en la vista principal
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuestoActionPerformed

    }//GEN-LAST:event_cmbPuestoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<RolEmpleado> cmbPuesto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtApellidoEmp;
    private javax.swing.JTextField txtCalleEmp;
    private javax.swing.JTextField txtCiudadEmp;
    private javax.swing.JTextField txtCodigoEmp;
    private javax.swing.JTextField txtEmailEmp;
    private javax.swing.JTextField txtEstadoEmp;
    private javax.swing.JTextField txtNombreEmp;
    private javax.swing.JTextField txtNumeroEmp;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTelefonoEmp;
    // End of variables declaration//GEN-END:variables
}
