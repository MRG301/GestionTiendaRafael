package vista;

import dao.implementacion.ClienteDAOImplementacion;
import dao.implementacion.ProductoDAOImplementacion;
import dao.implementacion.VentaDAOImplementacion;
import dao.interfaces.ClienteDAO;
import dao.interfaces.ProductoDAO;
import dao.interfaces.VentaDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.entidades.Cliente;
import modelo.entidades.Producto;
import modelo.entidades.ProductoVendido;
import modelo.entidades.Usuario;
import modelo.entidades.Venta;

/*
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
 */
public class VendedorVista extends javax.swing.JFrame {

    private Usuario vendedorActual;
    private Cliente clienteActual;
    private List<ProductoVendido> listaProductos; // Clase que representa cada línea de la venta
    private double totalVenta;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO; // Necesario para manejar productos
    private VentaDAO ventaDAO;
    

    public VendedorVista(Usuario vendedor) {
        this.vendedorActual = vendedor;
        this.listaProductos = new ArrayList<>();
        this.totalVenta = 0.0;
        this.clienteDAO = new ClienteDAOImplementacion();
        this.productoDAO = new ProductoDAOImplementacion();
        this.ventaDAO = new VentaDAOImplementacion();

        initComponents();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Mostrar el nombre del vendedor y la fecha actual
        lblVendedor.setText(vendedorActual.getUsername());
        lblFecha.setText(LocalDate.now().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        btnIniciarCliente = new javax.swing.JButton();
        btnRegistrarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnAgregarProducto = new javax.swing.JButton();
        btnModificarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnImprimirTiket = new javax.swing.JButton();
        btnTerminarVenta = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        jLabel1.setText("Venta");

        jLabel2.setText("Vendedor:");

        btnIniciarCliente.setText("Iniciar Cliente");
        btnIniciarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarClienteActionPerformed(evt);
            }
        });

        btnRegistrarCliente.setText("Registrar Cliente");
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha:");

        jLabel4.setText("Cliente:");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre de Producto", "Cantidad", "Precio Unitario", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnModificarProducto.setText("Modificar Producto");
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setText("Eliminar Producto");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnImprimirTiket.setText("Imprimir Tiket");
        btnImprimirTiket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirTiketActionPerformed(evt);
            }
        });

        btnTerminarVenta.setText("Terminar Venta");
        btnTerminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarVentaActionPerformed(evt);
            }
        });

        jLabel7.setText("Total:");

        btnCerrarSesion.setText("Cerrar Sesión");
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
                        .addGap(283, 283, 283)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblVendedor)
                        .addGap(345, 345, 345)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnIniciarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnRegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnImprimirTiket, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTerminarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblVendedor)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarCliente)
                    .addComponent(btnRegistrarCliente)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblCliente))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarProducto)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificarProducto)
                            .addComponent(btnEliminarProducto)
                            .addComponent(jLabel7)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirTiket)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTerminarVenta)
                            .addComponent(btnCerrarSesion))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void actualizarTablaProductos() {
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
        model.setRowCount(0);

        for (ProductoVendido detalle : listaProductos) {
            Object[] fila = {
                detalle.getProducto().getIdProducto(), // Cambiado de getId() a getIdProducto()
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
            };
            model.addRow(fila);
        }
    }

    private void actualizarTotalVenta() {
        totalVenta = listaProductos.stream().mapToDouble(ProductoVendido::getSubtotal).sum();
        lblTotal.setText("$" + String.format("%.2f", totalVenta));
    }
    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
        int filaSeleccionada = tblProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            ProductoVendido detalle = listaProductos.get(filaSeleccionada);
            int nuevaCantidad = obtenerCantidadDelUsuario();
            if (nuevaCantidad > 0) {
                detalle.setCantidad(nuevaCantidad);
                actualizarTablaProductos();
                actualizarTotalVenta();
            } else {
                JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int filaSeleccionada = tblProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            listaProductos.remove(filaSeleccionada);
            actualizarTablaProductos();
            actualizarTotalVenta();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnImprimirTiketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirTiketActionPerformed
        /*   if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en la venta para imprimir.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("TicketVenta.pdf"));
            documento.open();

            documento.add(new Paragraph("Ticket de Venta"));
            documento.add(new Paragraph("Fecha: " + LocalDate.now()));
            documento.add(new Paragraph("Vendedor: " + vendedorActual.getUsername()));
            documento.add(new Paragraph("Cliente: " + clienteActual.getDatosPersonales().getNombre() + " " + clienteActual.getDatosPersonales().getApellido()));
            documento.add(new Paragraph("\nProductos:"));

            for (ProductoVendido pv : listaProductos) {
                String linea = "Producto: " + pv.getProducto().getNombre()
                        + ", Cantidad: " + pv.getCantidad()
                        + ", Precio Unitario: $" + pv.getPrecioUnitario()
                        + ", Subtotal: $" + pv.getSubtotal();
                documento.add(new Paragraph(linea));
            }

            documento.add(new Paragraph("\nTotal: $" + String.format("%.2f", totalVenta)));

            documento.close();

            JOptionPane.showMessageDialog(this, "Ticket impreso exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }*/
    }//GEN-LAST:event_btnImprimirTiketActionPerformed

    private void btnTerminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarVentaActionPerformed

        try {
            // Crear objeto Venta
            Venta venta = new Venta(LocalDate.now(), totalVenta, vendedorActual.getId(), clienteActual.getId(), listaProductos);

            // Guardar la venta en la base de datos
            boolean exito = ventaDAO.guardarVenta(venta);
            if (exito) {
                // Actualizar el stock de los productos
                for (ProductoVendido detalle : listaProductos) {
                    productoDAO.actualizarStock(detalle.getIdProducto(), -detalle.getCantidad());
                }

                // Limpiar la lista y la tabla
                listaProductos.clear();
                actualizarTablaProductos();
                actualizarTotalVenta();

                JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnTerminarVentaActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginVista().setVisible(true);
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnIniciarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarClienteActionPerformed
        try {
            List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();
            Cliente seleccionado = mostrarDialogoSeleccionCliente(clientes);
            if (seleccionado != null) {
                clienteActual = seleccionado;
                lblCliente.setText(clienteActual.getDatosPersonales().getNombre() + " " + clienteActual.getDatosPersonales().getApellido());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener los clientes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIniciarClienteActionPerformed
    private Cliente mostrarDialogoSeleccionCliente(List<Cliente> clientes) {
        // Implementa un diálogo personalizado o utiliza JOptionPane para seleccionar un cliente
        // Por simplicidad, usaré JOptionPane con una lista de nombres

        String[] nombresClientes = clientes.stream()
                .map(c -> c.getDatosPersonales().getNombre() + " " + c.getDatosPersonales().getApellido())
                .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona un cliente:",
                "Iniciar Cliente",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombresClientes,
                nombresClientes.length > 0 ? nombresClientes[0] : null
        );

        if (seleccion != null) {
            for (Cliente c : clientes) {
                String nombreCompleto = c.getDatosPersonales().getNombre() + " " + c.getDatosPersonales().getApellido();
                if (nombreCompleto.equals(seleccion)) {
                    return c;
                }
            }
        }
        return null;
    }

    private Producto mostrarDialogoSeleccionProducto() {
        try {
            List<Producto> productos = productoDAO.obtenerTodosLosProductos();
            String[] nombresProductos = productos.stream()
                    .map(p -> p.getIdProducto() + " - " + p.getNombre())
                    .toArray(String[]::new);

            String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona un producto:",
                    "Agregar Producto",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nombresProductos,
                    nombresProductos.length > 0 ? nombresProductos[0] : null
            );

            if (seleccion != null) {
                long idProducto = Long.parseLong(seleccion.split(" - ")[0]);
                for (Producto p : productos) {
                    if (p.getIdProducto() == idProducto) {
                        return p;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private int obtenerCantidadDelUsuario() {
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Ingresa la cantidad:", "Cantidad", JOptionPane.PLAIN_MESSAGE);
            if (input == null) {
                return 0; // Cancelado
            }
            try {
                int cantidad = Integer.parseInt(input);
                if (cantidad > 0) {
                    return cantidad;
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
        FormularioClienteVista formularioCliente = new FormularioClienteVista(this, null); // 'this' es VendedorVista que extiende JFrame
        formularioCliente.setVisible(true);

        // Después de cerrar el formulario, verificar si se registró un cliente
        Cliente nuevoCliente = formularioCliente.getCliente();
        if (nuevoCliente != null) {
            clienteActual = nuevoCliente;
            lblCliente.setText("Cliente: " + clienteActual.getDatosPersonales().getNombre() + " " + clienteActual.getDatosPersonales().getApellido());
        }
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        Producto productoSeleccionado = mostrarDialogoSeleccionProducto();
        if (productoSeleccionado != null) {
            int cantidad = obtenerCantidadDelUsuario();
            if (cantidad > 0) {
                // Verificar si el producto ya está en la lista
                boolean existe = false;
                for (ProductoVendido pv : listaProductos) {
                    if (pv.getIdProducto() == productoSeleccionado.getIdProducto()) {
                        pv.setCantidad(pv.getCantidad() + cantidad);
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    ProductoVendido detalle = new ProductoVendido(0, productoSeleccionado, cantidad); // IDVenta=0 por ahora
                    listaProductos.add(detalle);
                }
                actualizarTablaProductos();
                actualizarTotalVenta();
            } else {
                JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    public static void main(String args[]) {
        /* Configuración del Look and Feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormularioClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Crear y mostrar la interfaz */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                FormularioClienteVista formulario = new FormularioClienteVista(parent, null);
                formulario.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnImprimirTiket;
    private javax.swing.JButton btnIniciarCliente;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnTerminarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
