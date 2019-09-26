/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import HibernateUtil.HibernateUtil;
import java.util.List;
import modelos.EspañolFrances;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author front
 */
public class Esp_Fran_Implementacion implements Interfaz.Esp_Fran_Interfaz {

    @Override
    public List<EspañolFrances> lista() {
        Session session = null;
        List<EspañolFrances> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from EspañolFrances");
            lista = (List<EspañolFrances>) query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }

    @Override
    public boolean insertar(EspañolFrances esp_fra) {
        Session session = null;
        boolean aux = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(esp_fra);
            session.getTransaction().commit();
            aux = true;

        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }

        return aux;
    }

    @Override
    public int personaEliminada(EspañolFrances esp_fra) {
        Session session = null;
        int aux = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(esp_fra);
            session.getTransaction().commit();
            aux = 1;
        } catch (Exception e) {
            aux = 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aux;
    }

    @Override
    public boolean actualizacion(EspañolFrances esp_fra) {
        Session session = null;
        boolean aux = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(esp_fra);
            aux = true;
        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
        return aux;
    }

    @Override
    public List<EspañolFrances> listaFiltrado(String busqueda) {
        Session session = null;
        List<EspañolFrances> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from EspañolFrances where esp='" + busqueda + "'");
            lista = (List<EspañolFrances>) query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }

}
