package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MyBatisItemDAO implements ItemDAO{

  @Inject
  private ItemMapper itemMapper;    

  @Override
  public void save(Item it) throws PersistenceException{
    try{
        itemMapper.insertarItem(it);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al registrar el item "+it.toString(),e);
    }        

  }

  @Override
  public Item load(int id) throws PersistenceException {
    try{
        return itemMapper.consultarItem(id);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al consultar el item "+id,e);
    }


    }

    @Override
    public List<Item> load() throws PersistenceException {
        return itemMapper.consultarItems();
    }

    @Override
    public List<Item> loadDisponibles() throws PersistenceException {
        return itemMapper.consultarItemsD();
    }

    @Override
    public long loadMulta(int iditem, Date fechaDevolucion) throws PersistenceException {
        
        try{
            return (long)itemMapper.consultarMulta(iditem,fechaDevolucion);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+iditem);
        }
    }

    @Override
    public long loadCostoA(int itemId, int numDias) {
        return itemMapper.consultarCostoTarifa(itemId,numDias);
    }

    @Override
    public void saveTarifaItem(int itemId, long nuevaTarifa) throws PersistenceException {
        itemMapper.actualizarTarifa(itemId,nuevaTarifa);
    }

    @Override
    public int loadValorMultaXDia(int itemId) {
        return itemMapper.consultarValorMulta(itemId);
    }
  }