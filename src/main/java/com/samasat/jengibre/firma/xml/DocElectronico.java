/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samasat.jengibre.firma.xml;

/**
 *
 * @author usuario03
 * @param <T>
 */
public class DocElectronico<T> {

    private T t;

    public DocElectronico() {

    }

    public DocElectronico(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
    }

}
