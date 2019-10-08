package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import java.util.Date;
import java.util.List;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;
   public List<Item> load() throws PersistenceException;
   public List<Item> loadDisponibles() throws PersistenceException;
   public long loadMulta(int iditem,Date fechaDevolucion) throws PersistenceException;
   public long loadCostoA(int itemId,int numDias) throws PersistenceException;
   public void saveTarifaItem(int itemId,long nuevaTarifa) throws PersistenceException;
   public int loadValorMultaXDia(int itemId) throws PersistenceException;
}