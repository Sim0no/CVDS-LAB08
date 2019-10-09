/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.util.List;

/**
 *
 * @author 2146516
 */
public class MyBatisTipoItemDAO implements TipoItemDAO{
    @Inject
    private TipoItemMapper tipoItemMapper;
    
    @Override
    public void save(TipoItem it) throws PersistenceException {
        tipoItemMapper.insertarTipoItem(it);
    }

    @Override
    public TipoItem load(int id) throws PersistenceException {
        return tipoItemMapper.consultarTipoItem(id);
    }

    @Override
    public List<TipoItem> load() throws PersistenceException {
        return tipoItemMapper.consultarTiposItems();
    }
    
    
}
