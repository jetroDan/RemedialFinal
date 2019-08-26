package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDao;
import Modelo.EntradaSalidaModelo;
import Vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    EmpleadoDao dao = new EmpleadoDao();
    Empleado p = new Empleado();
    EntradaSalidaModelo EnSal = new EntradaSalidaModelo();
    vista vista = new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
        this.vista.btnEntrada.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista.btnEntrada) {
             addEntrada();
             listarAsistencias(vista.tablaAsistencias);
        }
        
        if (e.getSource() == vista.btnListar) {
            // JOptionPane.showMessageDialog(vista, "Listar.");
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnAgregar) {
            add();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debee Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabla.getValueAt(fila, 1);
                String correo = (String) vista.tabla.getValueAt(fila, 2);
                String tel = String.valueOf(vista.tabla.getValueAt(fila, 3));
                String rfc = (String) vista.tabla.getValueAt(fila, 4);
                String turno = (String) vista.tabla.getValueAt(fila, 5);
                
                
                vista.txtId.setText("" + id);
                vista.txtNom.setText(nom);
                vista.txtCorreo.setText(correo);
                vista.txtTel.setText(tel);
                vista.txtRfc.setText(rfc);
                vista.cbTurno.setSelectedItem(turno);
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            Actualizar();
            listar(vista.tabla);
            nuevo();

        }
        if (e.getSource() == vista.btnDelete) {
            delete();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnNuevo) {
            nuevo();
        }

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNom.setText("");
        vista.txtTel.setText("");
        vista.txtCorreo.setText("");
        vista.txtRfc.setText("");
        vista.cbTurno.setSelectedItem("");
        vista.txtNom.requestFocus();
    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
            dao.Delete(id);
            System.out.println("El Reusltaod es" + id);
            JOptionPane.showMessageDialog(vista, "Usuario Eliminado...!!!");
        }
        limpiarTabla();
    }

    public void add() {
        String nom = vista.txtNom.getText();
        String correo = vista.txtCorreo.getText();
       int tel = Integer.parseInt( vista.txtTel.getText());
        String rfc = vista.txtRfc.getText();
        String turno = String.valueOf(vista.cbTurno.getSelectedItem());
        
        
        p.setNom(nom);
        p.setCorreo(correo);
        p.setTelefono(tel);
        p.setRfc(rfc);
        p.setTurno(turno);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nom = vista.txtNom.getText();
            String correo = vista.txtCorreo.getText();
            int tel = Integer.parseInt(vista.txtTel.getText());
            String rfc = vista.txtRfc.getText();
            String turno = String.valueOf(vista.cbTurno.getSelectedItem());
            
            p.setId(id);
            p.setNom(nom);
            p.setCorreo(correo);
            p.setTelefono(tel);
            p.setRfc(rfc);
            p.setTurno(turno);
            int r = dao.Actualizar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Usuario Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
        limpiarTabla();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Empleado> lista = dao.listar();
        Object[] objeto = new Object[6];
        
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNom();
            objeto[2] = lista.get(i).getCorreo();
            objeto[3] = lista.get(i).getTelefono();
            objeto[4] = lista.get(i).getRfc();
            objeto[5] = lista.get(i).getTurno();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    
     public void listarAsistencias(JTable tablaAsistencias) {
        //try {
             centrarCeldasAsistencias(tablaAsistencias);
        modelo = (DefaultTableModel) tablaAsistencias.getModel();
        tablaAsistencias.setModel(modelo);
        List<EntradaSalidaModelo> lista = dao.listarAsistencias();
        Object[] objeto = new Object[4];
        System.out.print(lista.get(2).getTipo());
        
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getTipo();
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getPerson_id();
            modelo.addRow(objeto);
        }
        tablaAsistencias.setRowHeight(35);
        tablaAsistencias.setRowMargin(10);
        //} catch (Exception e) {
            //System.out.print(e);
        //}
    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    
    void centrarCeldasAsistencias(JTable tablaAsistencias) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tablaAsistencias.getColumnCount(); i++) {
            tablaAsistencias.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    public void addEntrada() {        
      int empleadoId = Integer.parseInt(vista.personaId.getText());
      java.util.Date utilDate = new java.util.Date();
      java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
      // Date datetime = sqlDate

      EnSal.setTipo(String.valueOf("entrada"));
      EnSal.setPerson_id(empleadoId);
      EnSal.setFecha(sqlDate);

      int r = dao.registrarEntradaSalida(EnSal);
      if (r == 1) {
          JOptionPane.showMessageDialog(vista, "Entrada Agregada con Exito.");
      } else {
          JOptionPane.showMessageDialog(vista, "Error");
      }
    //  limpiarTabla();
  }
}
