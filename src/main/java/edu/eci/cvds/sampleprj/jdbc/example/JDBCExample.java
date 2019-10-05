/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2152805;
            registrarNuevoProducto(con, suCodigoECI, "Simono", 99999999);            
            con.commit();
                        
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los par�metros dados
     * @param con la conexi�n JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
            //Crear preparedStatement
            PreparedStatement insertarProducto;       
            insertarProducto = con.prepareStatement("INSERT INTO ORD_PRODUCTOS(codigo,nombre,precio) VALUES(?,?,?)");       
            //Asignar par�metros
            insertarProducto.setInt(1, codigo);
            insertarProducto.setString(2, nombre);
            insertarProducto.setInt(3, precio);
            //usar 'execute'
            insertarProducto.execute();
            con.commit();     
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexi�n JDBC
     * @param codigoPedido el c�digo del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        List<String> np=new LinkedList<>();        
        //Crear prepared statement
        PreparedStatement consultaPedido = con.prepareStatement("SELECT nombre FROM ORD_PEDIDOS a,ORD_DETALLE_PEDIDO b,ORD_PRODUCTOS c WHERE a.codigo = b.pedido_fk and b.producto_fk = c.codigo and a.codigo = ?");        
        //asignar par�metros
        consultaPedido.setInt(1, codigoPedido);
        //usar executeQuery y Sacar resultados del ResultSet
        ResultSet resultadosConsulta = consultaPedido.executeQuery();
         //Llenar la lista y retornarla
        while(resultadosConsulta.next()){
            String nombreProducto = resultadosConsulta.getString("nombre");
            np.add(nombreProducto);           
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido c�digo del pedido cuyo total se calcular�
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException{        
        //Crear prepared statement
        PreparedStatement consultaValorPedido = con.prepareStatement("SELECT SUM(precio*cantidad) as total "
                                                                    + " FROM ORD_PEDIDOS a,ORD_DETALLE_PEDIDO b,ORD_PRODUCTOS c"
                                                                    +" WHERE a.codigo = b.pedido_fk and b.producto_fk = c.codigo and a.codigo = ?"
                                                                    +" GROUP BY(a.codigo)");
        //asignar par�metros
        consultaValorPedido.setInt(1,codigoPedido);
        //usar executeQuery
        ResultSet resultadoConsultaPrecio = consultaValorPedido.executeQuery();
        //Sacar resultado del ResultSet
        int costoTotal =0;
        while(resultadoConsultaPrecio.next()){
            costoTotal = resultadoConsultaPrecio.getInt("total");         
        }
        
        return costoTotal;
    }
    

    
    
    
}