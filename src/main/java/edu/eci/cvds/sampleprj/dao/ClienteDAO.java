/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2152805
 */
public interface ClienteDAO {
     
    public void save(Cliente cliente) throws PersistenceException;
    public void saveVetado(long id, boolean estado) throws PersistenceException;
    public Cliente load(long documento) throws PersistenceException;
    public Cliente load(int documento) throws PersistenceException;
    public List<Cliente> load() throws PersistenceException;
    public void saveAlquiler(Date fechaInicio,int clienteId, Item itemAlquilado, int numeroDias);
    public void saveCliente(Cliente cliente);
}
