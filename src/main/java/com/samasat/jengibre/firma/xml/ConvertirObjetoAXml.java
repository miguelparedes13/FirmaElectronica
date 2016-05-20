/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samasat.jengibre.firma.xml;

import org.w3c.dom.Document;

/**
 *
 * @author anthoserv
 */
public interface ConvertirObjetoAXml {

    Document convertirObjetoXML(DocElectronico resultaod, String rutaArchivo, String nombreArchivo) throws Exception;
}
