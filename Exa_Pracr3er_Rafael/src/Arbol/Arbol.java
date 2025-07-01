package Arbol;

import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

public class Arbol extends javax.swing.JFrame {

    private Nodo raiz;

    public Arbol() {
        initComponents();
        construirArbol();
    }

    @SuppressWarnings("unchecked")

    private void construirArbol() {
        // Nivel 1
        raiz = new Nodo("¿Cuál es tu área de interés?\n1 Ciencias Humanistas\n2 Tecnologías");
        // Nivel 2 
        Nodo cienciasHumanistas = new Nodo("¿Qué te gusta más?\n1 Tecnología\n2 Sociales");
        raiz.setIzquierda(cienciasHumanistas);
        // Nivel 3
        Nodo techLab = new Nodo("¿Prefieres trabajar en laboratorio?\n1 Sí\n2 No");
        cienciasHumanistas.setIzquierda(techLab);
        // Hoja para Ciencias
        Nodo ingenieriaSoftware = new Nodo("Carrera de Ingeniería en Software.");
        techLab.setIzquierda(ingenieriaSoftware);
        // Hoja para Ciencias Humanistas
        Nodo diseñoGrafico = new Nodo("Carrera de Diseño Gráfico.");
        techLab.setDerecha(diseñoGrafico);
        // Nivel 3 para Ciencias Humanistas
        Nodo socialesDetalle = new Nodo("¿Prefieres trabajar en campo o en oficina?\n1 Campo\n2 Oficina");
        cienciasHumanistas.setDerecha(socialesDetalle);
        // Hoja para Ciencias Humanistas - Sociales
        Nodo sociologia = new Nodo("Carrera de Sociología.");
        socialesDetalle.setIzquierda(sociologia);
        // Hoja para Ciencias Humanistas
        Nodo psicologia = new Nodo("Carrera en Psicología.");
        socialesDetalle.setDerecha(psicologia);
        // Nivel 2 para Tecnologías
        Nodo tecnologias = new Nodo("¿Te interesa más el desarrollo de software o el hardware?\n1 Software\n2 Hardware");
        raiz.setDerecha(tecnologias);
        // Nivel 3 para Tecnologías - Software
        Nodo desarrolloWeb = new Nodo("Carrera en Desarrollo Web.");
        tecnologias.setIzquierda(desarrolloWeb);
        // Nivel 3 para Tecnologías
        Nodo redes = new Nodo("Ingenieria en Telecomunicaciones.");
        tecnologias.setDerecha(redes);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("UACM Rafael");

        jButton1.setText("Recomendaciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Chatbot");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void recomendarCarrera() {
        Nodo current = raiz;
        while (!current.hijos()) {
            String respuesta = JOptionPane.showInputDialog(current.getMensaje());
            if (respuesta == null) { // Manejar si el usuario cierra el diálogo
                return;
            }
            respuesta = respuesta.trim();
            if (respuesta.equals("1")) {
                current = current.getIzquierda();
            } else if (respuesta.equals("2")) {
                current = current.getDerecha();
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige 1 o 2.");
            }
        }
        // Mostrar la recomendación
        JOptionPane.showMessageDialog(null, current.getMensaje());
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        recomendarCarrera();
    }//GEN-LAST:event_jButton1ActionPerformed
    private void iniciarChatbot() {
        Queue<String> preguntas = new LinkedList<>();
        preguntas.add("¿Qué es el ciclo de vida del software?");
        preguntas.add("¿Para que sirve el MVC?");
        preguntas.add("¿Qué es una estructura llamada Cola?");

        while (!preguntas.isEmpty()) {
            String preguntaActual = preguntas.poll();
            String respuesta = JOptionPane.showInputDialog(preguntaActual);
            if (respuesta == null) {
                return;
            }
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iniciarChatbot();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arbol().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
