
package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Electricista;
import Vista.Agregar_informacion;



public class ctrlAgregarInfo implements MouseListener{
    
    private Electricista Modelo;
    private Agregar_informacion Vista;
    
    public ctrlAgregarInfo(Electricista Modelo, Agregar_informacion Vista){
        this.Modelo = Modelo;
        this.Vista = Vista;
        
        Vista.btnAgregar.addMouseListener(this);
        Modelo.Mostrar(Vista.jtbElectricista);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.jtbElectricista.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        if(e.getSource() == Vista.btnAgregar){
            Modelo.setNombre(Vista.txtNombre.getText());
            Modelo.setEdad(Integer.parseInt(Vista.txtEdad.getText()));
            Modelo.setPeso(Double.parseDouble(Vista.txtPeso.getText()));
            Modelo.setCorreo(Vista.txtCorreoElectronico.getText());
            
            Modelo.Guardar();
            Modelo.Mostrar(Vista.jtbElectricista);
        }
        
        if (e.getSource() == Vista.btnEliminar) {
            Modelo.Eliminar(Vista.jtbElectricista);
            Modelo.Mostrar(Vista.jtbElectricista);
            
        }
        
        if (e.getSource() == Vista.jtbElectricista) {
            Modelo.cargarDatosTabla(Vista);
            
        }
        
        if (e.getSource() == Vista.btnActualizar) {
            Modelo.setNombre(Vista.txtNombre.getText());
            Modelo.setEdad(Integer.parseInt(Vista.txtEdad.getText()));
            Modelo.setPeso(Double.parseDouble(Vista.txtPeso.getText()));
            Modelo.setCorreo(Vista.txtCorreoElectronico.getText());
            
            Modelo.Actualizar(Vista.jtbElectricista);
            Modelo.Mostrar(Vista.jtbElectricista);
            
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
