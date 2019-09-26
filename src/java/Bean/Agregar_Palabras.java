/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Implementacion.Esp_Fran_Implementacion;
import Interfaz.Esp_Fran_Interfaz;
import static java.lang.String.valueOf;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelos.EspañolFrances;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author front
 */
@ManagedBean
@ViewScoped
public class Agregar_Palabras {

    private EspañolFrances e_f;
    public List<EspañolFrances> esp_fra;
    public int idUsuaidEspFranrio;
    public String esp;
    public String frances;
    private List<EspañolFrances> filteredPalabras;
    public String busqueda;

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<EspañolFrances> getFilteredPalabras() {
        Esp_Fran_Interfaz lista_Diccionario=new Esp_Fran_Implementacion();
        System.out.println("--->"+busqueda);
        filteredPalabras=lista_Diccionario.listaFiltrado(busqueda);
        return filteredPalabras;
    }

    public void setFilteredPalabras(List<EspañolFrances> filteredPalabras) {
        this.filteredPalabras = filteredPalabras;
    }
    public Agregar_Palabras() {
        e_f = new EspañolFrances();
    }

    public EspañolFrances getE_f() {
        return e_f;
    }

    public void setE_f(EspañolFrances e_f) {
        this.e_f = e_f;
    }

    public List<EspañolFrances> getEsp_fra() {
        Esp_Fran_Interfaz lista_Diccionario=new Esp_Fran_Implementacion();
        esp_fra=lista_Diccionario.lista();
        return esp_fra;
    }

    public void setEsp_fra(List<EspañolFrances> esp_fra) {
        this.esp_fra = esp_fra;
    }

    public int getIdUsuaidEspFranrio() {
        return idUsuaidEspFranrio;
    }

    public void setIdUsuaidEspFranrio(int idUsuaidEspFranrio) {
        this.idUsuaidEspFranrio = idUsuaidEspFranrio;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getFrances() {
        return frances;
    }

    public void setFrances(String frances) {
        this.frances = frances;
    }

    public void boton() {
        Esp_Fran_Interfaz Palabra_Agregada = new Esp_Fran_Implementacion();
        if (Palabra_Agregada.insertar(e_f)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Palabra insertada Exitosamente"));
            RequestContext.getCurrentInstance().update("panel2:msgs5");
        }
    }

    public void botonEliminar(String ide) {
        e_f.setIdEspFran(Integer.parseInt(ide));        
        Esp_Fran_Interfaz datoeliminado = new Esp_Fran_Implementacion();
        if (datoeliminado.personaEliminada(e_f) != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Palabras Eliminadas Exitosamente"));
            RequestContext.getCurrentInstance().update("myform:msgs");
        }
    }

    public void onRowEdit(RowEditEvent event) {
        EspañolFrances c = new EspañolFrances();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion!", "palabra Actualiza"));
        RequestContext.getCurrentInstance().update("myform:msgs");
        Esp_Fran_Interfaz actualizar = new Esp_Fran_Implementacion();
        idUsuaidEspFranrio = ((EspañolFrances) event.getObject()).getIdEspFran();
        c.setIdEspFran(idUsuaidEspFranrio);
        if (esp != null) {
            c.setEsp(esp);
        }
        if (frances != null) {
            c.setFrances(frances);
        }
        System.out.println("--->" + c.getEsp()+ "--->" + c.getFrances()+ " ID-->" + c.getIdEspFran());
        actualizar.actualizacion(c);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", valueOf(((EspañolFrances) event.getObject()).getIdEspFran()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("--->" + newValue);
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
   
}
