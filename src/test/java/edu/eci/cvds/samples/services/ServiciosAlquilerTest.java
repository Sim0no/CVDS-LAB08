package edu.eci.cvds.samples.services;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {


    ServiciosAlquiler serviciosAlquiler;
    
    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }


    @Test
    public void deberiaConsultarCliente(){
        try {
            serviciosAlquiler = ServiciosAlquilerFactory.getServiciosAlquilerTesting();
            serviciosAlquiler.consultarCliente(3218171);
            Assert.assertTrue(true);
        } catch (ExcepcionServiciosAlquiler ex) {
            Assert.fail();
        }
    }

}