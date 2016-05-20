/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samasat.jengibre.firma.comun;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anthoserv
 */
@XmlRootElement(name = "campoAdicional")
@XmlAccessorType (XmlAccessType.FIELD)

public class InfoAdicionalesXML {
    private List<InfoAdicionalXML> infoAdicionales;

    public List<InfoAdicionalXML> getInfoAdicionales() {
        return infoAdicionales;
    }

    public void setInfoAdicionales(List<InfoAdicionalXML> infoAdicionales) {
        this.infoAdicionales = infoAdicionales;
    }

    

    
    
}
