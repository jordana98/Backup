/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udem.edu.co.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import udem.edu.co.entity.Insumos;

/**
 *
 * @author Maritza
 */
@Stateless
public class InsumosFacade extends AbstractFacade<Insumos> {

    @PersistenceContext(unitName = "InventarioTiendaUdemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsumosFacade() {
        super(Insumos.class);
    }
    
}
