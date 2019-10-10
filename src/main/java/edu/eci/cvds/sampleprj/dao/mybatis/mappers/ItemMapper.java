package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("idit") int id);
     
    public void insertarItem(@Param("item") Item it);
    
    public List<Item> consultarItemsD();
    public long consultarMulta(@Param("itemid")int itemid,@Param("fecha")Date fechaDevolucion);
    public long consultarCostoTarifa(@Param("itemid")int itemId,@Param("numdias")int numDias);   

    public void actualizarTarifa(@Param("itemid")int itemId,@Param("tarifa") long nuevaTarifa);

    public int consultarValorMulta(@Param("itemid")int itemId);
}
