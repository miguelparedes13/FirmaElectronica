/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samasat.jengibre.firma.xml;

import com.samasat.jengibre.firma.Util;
import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.w3c.dom.Document;

/**
 *
 * @author anthoserv
 */
public class ConvertirObjetoXMLImp implements ConvertirObjetoAXml {

    /**
     *
     * @param resultado
     * @param rutaArchivo
     * @param nombreArchivo
     * @return 
     * @throws java.lang.Exception
     */
    @Override
    public Document convertirObjetoXML(DocElectronico resultado, String rutaArchivo, String nombreArchivo) throws Exception {
        JAXBContext jaxbContext = JAXBContext.
                newInstance(resultado.getT().getClass());
        Marshaller jaxbMarshaller = jaxbContext.
                createMarshaller();
        File file = new File(rutaArchivo + File.separatorChar + nombreArchivo + ".xml");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(resultado.getT(), file);
        Document convertStringToDocument = Util.convertFileToDocument(file);
        return convertStringToDocument;
    }

    /**
     *
     * @return
     */
    public static ConvertirObjetoAXml geInstance() {
        return new ConvertirObjetoXMLImp();
    }

}
