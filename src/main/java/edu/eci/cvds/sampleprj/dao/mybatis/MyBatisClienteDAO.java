/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2152805
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import java.sql.SQLException;
import java.util.List;

public class MyBatisClienteDAO implements ClienteDAO{

  @Inject
  private ClienteMapper clienteMapper;    

  @Override
  public void save(Cliente cliente) throws PersistenceException{
    try{
        clienteMapper.insertarCliente(cliente);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al registrar el cliente "+cliente.toString(),e);
    }        

  }

  @Override
  public Cliente load(long documento) throws PersistenceException {
    try{
        return clienteMapper.consultarCliente((int) documento);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al consultar el cliente "+documento,e);
    }


    }
  @Override
  public List<Cliente> load() throws PersistenceException{
      
    return clienteMapper.consultarClientes();

  }

  }