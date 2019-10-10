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
package edu.eci.cvds.samples.services.client;



import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {


    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    private static ServiciosAlquiler serviciosAlquiler = ServiciosAlquilerFactory.getServiciosAlquiler();
    
    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {
        
       probarServiciosAlquiler();
       System.out.println(serviciosAlquiler.consultarCliente(321817)); // Punto 9 del laboratorio
    }
    private static void probarServiciosAlquiler() throws ExcepcionServiciosAlquiler {
        probarServiciosCliente();
        probarServiciosItem();
       
        
    }
    public static void imprimirEspacios(){
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void probarServiciosItem() throws ExcepcionServiciosAlquiler {
        /*
        System.out.println("Consultado todos los items");
        System.out.println(serviciosAlquiler.consultarItems());
        imprimirEspacios();
        System.out.println("Consultado solo items disponibles");
        System.out.println(serviciosAlquiler.consultarItemsDisponibles());
        imprimirEspacios();
        TipoItem tipoitem = new TipoItem(2, "Accion");
        Item item1 = new Item(tipoitem, 980, "White widow", "Widow Blanco", new Date(20190715), 3500, "Mensual", "Fantasia");
        System.out.println("Item insertado");
        serviciosAlquiler.registrarItem(item1);
        System.out.println(serviciosAlquiler.consultarItem(980));
        
        serviciosAlquiler.actualizarTarifaItem(980, 400);
        serviciosAlquiler.consultarCostoAlquiler(980, 15);
        serviciosAlquiler.valorMultaRetrasoxDia(980);
        */
    }

    private static void probarServiciosCliente() throws ExcepcionServiciosAlquiler {
        
        System.out.println("Consultado todos los clientes");
        System.out.println(serviciosAlquiler.consultarClientes());
        imprimirEspacios();
        ArrayList<ItemRentado> rentadosDefault = new ArrayList<>(); 
        Cliente cliente1 = new Cliente("Saladoman",100100, "453322", "Cr1#22a43", "vlok@quek.com", false,rentadosDefault);
        System.out.println("Cliente insertado");
        serviciosAlquiler.registrarCliente(cliente1);
        System.out.println(serviciosAlquiler.consultarCliente(100100));
        imprimirEspacios();
        serviciosAlquiler.vetarCliente(100100, true);
        System.out.println("Cliente vetado");
        System.out.println(serviciosAlquiler.consultarCliente(100100));
        TipoItem tipoitem = new TipoItem(2, "Accion");
        Item item1 = new Item(tipoitem, 233, "la caida del halcon", "helicoptero", new Date(20190815), 3500, "Mensual", "Fantasia");
        serviciosAlquiler.registrarAlquilerCliente(new Date(2019109), 100100,item1, 30);
        serviciosAlquiler.consultarItemsCliente(100100);
        
    }

}
