package vista;

import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.entidades.Usuario;
import modelo.enums.Rol;
import util.Autenticacion;
import util.Sesion;

public class LoginVista extends javax.swing.JFrame {

    public LoginVista() {

        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Inicio de Sesión");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        btnLogin.setText("Iniciar Sesión");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txtPassword))
                        .addGap(83, 83, 83))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(btnLogin)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            String username = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword()).trim(); // Obtener contraseña de JPasswordField

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese usuario y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Autenticacion autenticacion = new Autenticacion();
            Usuario usuario = autenticacion.iniciarSesion(username, password);

            if (usuario != null) {
                // Iniciar la sesión
                Sesion.iniciarSesion(usuario);

                // Redirigir según el rol
                redirigirPorRol(usuario);

                this.dispose(); // Cerrar la ventana de login
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed
    private void redirigirPorRol(Usuario usuario) {
        Set<Rol> roles = usuario.getRoles();

        if (roles.contains(Rol.SUPERADMIN)) {
            // Redirigir a AdministracionVista
            SwingUtilities.invokeLater(() -> {
                new AdministracionVista().setVisible(true);
            });
        } else if (roles.contains(Rol.GESTIONEMPLEADOS)) {
            // Redirigir a GestionEmpleadosVista
            SwingUtilities.invokeLater(() -> {
                new GestionEmpleados().setVisible(true);
            });
        } else if (roles.contains(Rol.GESTIONPRODUCTOS)) {
            // Redirigir a GestionProductosVista
            SwingUtilities.invokeLater(() -> {
                new GestionProducto().setVisible(true);
            });
        } else if (roles.contains(Rol.GESTIONVENTAS)) {
            // Redirigir a GestionVentasVista
            SwingUtilities.invokeLater(() -> {
                new GestionVentas().setVisible(true);
            });
        } else if (roles.contains(Rol.GESTIONCLIENTES)) {
            // Redirigir a GestionClientesVista
            SwingUtilities.invokeLater(() -> {
                new GestionCliente().setVisible(true);
            });
        } else if (roles.contains(Rol.VENDEDOR)) {
            // Redirigir a VendedorVista
            Usuario usuarioActual = Sesion.getUsuarioActual();
            if (usuarioActual != null) {
                SwingUtilities.invokeLater(() -> {
                    new VendedorVista(usuarioActual).setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(this, "No hay un usuario en sesión. Por favor, inicia sesión.", "Error", JOptionPane.ERROR_MESSAGE);
                new LoginVista().setVisible(true); // Redirigir al login
            }
        } else {
            JOptionPane.showMessageDialog(this, "No tiene asignado un rol válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
