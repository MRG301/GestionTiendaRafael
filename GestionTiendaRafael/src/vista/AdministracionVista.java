package vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.entidades.Usuario;
import util.Sesion;

public class AdministracionVista extends javax.swing.JFrame {

    private final Usuario usuario;

    public AdministracionVista() {
        initComponents();

        // Obtener el usuario actual de la sesión
        this.usuario = Sesion.getUsuarioActual();

        // Verificar que el usuario no sea nulo
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "No hay una sesión activa. Por favor, inicie sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            new LoginVista().setVisible(true);
            this.dispose();
            return;
        }

        // Configurar el título de la ventana con el nombre de usuario
        setTitle("Administración - " + usuario.getUsername());

        // Configurar el tamaño y posición de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGestionarEmpleados = new javax.swing.JButton();
        btnGestionarProductos = new javax.swing.JButton();
        btnGestionarVentas = new javax.swing.JButton();
        btnGestionarClientes = new javax.swing.JButton();
        txtTitulo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGestionarEmpleados.setText("Gestionar Empleados");
        btnGestionarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarEmpleadosActionPerformed(evt);
            }
        });

        btnGestionarProductos.setText("Gestionar Productos");
        btnGestionarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarProductosActionPerformed(evt);
            }
        });

        btnGestionarVentas.setText("Gestionar Ventas");
        btnGestionarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarVentasActionPerformed(evt);
            }
        });

        btnGestionarClientes.setText("Gestionar Clientes");
        btnGestionarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarClientesActionPerformed(evt);
            }
        });

        txtTitulo.setText("Gestion");

        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(btnCerrarSesion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGestionarEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGestionarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGestionarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGestionarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(txtTitulo)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(txtTitulo)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionarEmpleados)
                    .addComponent(btnGestionarProductos))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionarVentas)
                    .addComponent(btnGestionarClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Sesion.cerrarSesion();
            new LoginVista().setVisible(true);
            this.dispose();
        }
    }
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        cerrarSesion();
        SwingUtilities.invokeLater(() -> {
            new LoginVista().setVisible(true);
        });
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnGestionarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarEmpleadosActionPerformed
        SwingUtilities.invokeLater(() -> {
            new GestionEmpleados().setVisible(true);
        });
        dispose();
    }//GEN-LAST:event_btnGestionarEmpleadosActionPerformed

    private void btnGestionarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarProductosActionPerformed
        SwingUtilities.invokeLater(() -> {
            new GestionProducto().setVisible(true);
        });
    }//GEN-LAST:event_btnGestionarProductosActionPerformed

    private void btnGestionarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarVentasActionPerformed
        SwingUtilities.invokeLater(() -> {
            new GestionVentas().setVisible(true);
        });
    }//GEN-LAST:event_btnGestionarVentasActionPerformed

    private void btnGestionarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarClientesActionPerformed
        SwingUtilities.invokeLater(() -> {
            new GestionCliente().setVisible(true);
        });
    }//GEN-LAST:event_btnGestionarClientesActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnGestionarClientes;
    private javax.swing.JButton btnGestionarEmpleados;
    private javax.swing.JButton btnGestionarProductos;
    private javax.swing.JButton btnGestionarVentas;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
