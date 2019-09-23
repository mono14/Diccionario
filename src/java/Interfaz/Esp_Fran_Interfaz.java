/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.util.List;
import modelos.EspañolFrances;

/**
 *
 * @author front
 */
public interface Esp_Fran_Interfaz {
    public List<EspañolFrances> lista();
    public boolean insertar(EspañolFrances esp_fra);
    public int personaEliminada(EspañolFrances esp_fra);
    public boolean actualizacion(EspañolFrances esp_fra);
}
