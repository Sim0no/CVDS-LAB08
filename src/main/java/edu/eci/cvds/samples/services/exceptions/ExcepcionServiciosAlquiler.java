/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.services.exceptions;

/**
 *
 * @author 2152805
 */
class ExcepcionServiciosAlquiler extends Exception {
    public ExcepcionServiciosAlquiler(String message) {
        super(message);
    }
    public ExcepcionServiciosAlquiler(String message,Throwable w) {
        super(message);
    }
    
}
