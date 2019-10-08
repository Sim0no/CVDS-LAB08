package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mybatis.guice.transactional.Transactional;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;
   @Override
   public int valorMultaRetrasoxDia(int itemId) {
       try {
           return itemDAO.loadValorMultaXDia(itemId);
       } catch (PersistenceException ex) {
           Logger.getLogger(ServiciosAlquilerImpl.class.getName()).log(Level.SEVERE, null, ex);
           return 0;
       }
   }

    /**
     *
     * @param docu
     * @return
     * @throws ExcepcionServiciosAlquiler
     */
    @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.load(docu);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente, id no encontrado "+docu,ex);
       }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idCliente) throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.load(idCliente).getRentados();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente, id no encontrado "+idCliente,ex);
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.load();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar clientes");
       }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItems() {
       try {
           return itemDAO.load();
       } catch (PersistenceException ex) {
           Logger.getLogger(ServiciosAlquilerImpl.class.getName()).log(Level.SEVERE, null, ex);
           return null;           
       }      
       
   }
   @Override
   public List<Item> consultarItemsDisponibles() {

       try {
           return itemDAO.loadDisponibles();
       } catch (PersistenceException ex) {
           Logger.getLogger(ServiciosAlquilerImpl.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
       

   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.loadMulta(iditem,fechaDevolucion);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+iditem,ex);
       }
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   @Transactional
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
       clienteDAO.saveAlquiler(date, (int) docu, item, numdias);
   }
   @Transactional
   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       clienteDAO.saveCliente(c);
       
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        try {
           return itemDAO.loadCostoA(iditem,numdias);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+iditem,ex);
       }
       
   }

   @Override
   @Transactional
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.saveTarifaItem(id,tarifa);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al actualizar tarifa item "+id,ex);
       }
   }
   @Override
   @Transactional
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.save(i);
       } catch (PersistenceException ex) {
           Logger.getLogger(ServiciosAlquilerImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

   @Override
   @Transactional
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try {
           clienteDAO.saveVetado(docu,estado);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al encontrar cliente"+docu,ex);
       }
   }
}