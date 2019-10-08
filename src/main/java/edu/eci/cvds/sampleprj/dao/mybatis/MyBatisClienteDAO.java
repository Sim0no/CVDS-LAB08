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
import edu.eci.cvds.samples.entities.Item;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public Cliente load(int documento) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(documento);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente "+documento,e);
        }
    }

    @Override
    public void saveAlquiler(Date fechaInicio, int clienteId, Item itemAlquilado, int numeroDias) {
        Calendar calendario=Calendar.getInstance();
        calendario.setTime(fechaInicio);
        calendario.add(Calendar.DAY_OF_YEAR, numeroDias);
        Date fechaFin=calendario.getTime();
        clienteMapper.agregarItemRentadoACliente(clienteId,itemAlquilado.getId(),fechaInicio,fechaFin);
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteMapper.insertarCliente(cliente);
    }

    @Override
    public void saveVetado(long id, boolean estado) throws PersistenceException {
        if (estado){
           clienteMapper.actualizarBetado(id,1); 
        }else{
            clienteMapper.actualizarBetado(id,0);
        }
        
    }
  }