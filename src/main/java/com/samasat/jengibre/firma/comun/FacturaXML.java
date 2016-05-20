/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samasat.jengibre.firma.comun;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anthoserv
 */
@XmlRootElement(name = "factura")
@XmlAccessorType(XmlAccessType.FIELD)
public class FacturaXML {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "infoTributaria")
    private InfoTributariaXML infoTributaria;
    @XmlElement(name = "infoFactura")
    private InfoFacturaXML infoFactura;
    @XmlElement(name = "detalles")
    private DetallesXML detalles;
    @XmlElement(name = "infoAdicional")
    private InfoAdicionalesXML infoAdicionales;

    public String getId() {
        return id;
    }

    public FacturaXML() {
    }

    public FacturaXML(String id, String version, InfoTributariaXML infoTributaria, InfoFacturaXML infoFactura, DetallesXML detalles, InfoAdicionalesXML infoAdicionales) {
        this.id = id;
        this.version = version;
        this.infoTributaria = infoTributaria;
        this.infoFactura = infoFactura;
        this.detalles = detalles;
        this.infoAdicionales = infoAdicionales;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public InfoTributariaXML getInfoTributaria() {
        return infoTributaria;
    }

    public void setInfoTributaria(InfoTributariaXML infoTributaria) {
        this.infoTributaria = infoTributaria;
    }

    public InfoFacturaXML getInfoFactura() {
        return infoFactura;
    }

    public void setInfoFactura(InfoFacturaXML infoFactura) {
        this.infoFactura = infoFactura;
    }

    public DetallesXML getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallesXML detalles) {
        this.detalles = detalles;
    }

    public InfoAdicionalesXML getInfoAdicionales() {
        return infoAdicionales;
    }

    public void setInfoAdicionales(InfoAdicionalesXML infoAdicionales) {
        this.infoAdicionales = infoAdicionales;
    }

   

}
