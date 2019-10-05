/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.services;

/**
 *
 * @author 2152805
 */
public class ExcepcionServiciosAlquiler extends Exception{
    public ExcepcionServiciosAlquiler(String message) {
        super(message);
    }
    public ExcepcionServiciosAlquiler(String message,Throwable w) {
        super(message);
    }
}