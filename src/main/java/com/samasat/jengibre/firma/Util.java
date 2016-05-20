/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samasat.jengibre.firma;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author anthoserv
 */
public class Util {
/**
 * 
 * @param fXmlFile
 * @return
 * @throws SAXException
 * @throws IOException
 * @throws ParserConfigurationException 
 */
    public static Document convertFileToDocument(File fXmlFile) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder dBuilder = dbf.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        dBuilder.setErrorHandler(null);
        return doc;
    }
/**
 * 
 * @param xmlStr
 * @return
 * @throws ParserConfigurationException
 * @throws SAXException
 * @throws IOException 
 */
    public static Document convertStringToDocument(String xmlStr) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(null);
        Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));

        return doc;

    }

    /**
     * Permite convertir un document a String
     *
     * @param doc
     * @return
     * @throws TransformerException
     */
    public static String convertDocumentToString(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        transformer = tf.newTransformer();
        // below code to remove XML declaration
        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString();
        return output;
    }

    /**
     * Permite guardar un document en un direcctorio
     *
     * @param urlOutArchivo
     * @param nombreArchivo
     * @param doc
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public static void saveDocument(String urlOutArchivo, String nombreArchivo, Document doc) throws TransformerConfigurationException, TransformerException {
        String ruta = urlOutArchivo + File.separatorChar + nombreArchivo + ".xml";
        Source source = new DOMSource(doc);
        Result result = new StreamResult(new java.io.File(ruta)); //nombre del archivo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    /**
     * metodo que permite comprobar una cadena mediante el modulo 11
     *
     * @param cadena
     * @return
     */
    public static int module11(final String cadena) {
        int pivote = 2;
        int longitudCadena = cadena.length();
        int cantidadTotal = 0;
        int b = 1;
        for (int i = 0; i < longitudCadena; i++) {
            if (pivote == 8) {
                pivote = 2;
            }
            int temporal = Integer.parseInt("" + cadena.substring(i, b));
            b++;
            temporal *= pivote;
            pivote++;
            cantidadTotal += temporal;
        }
        cantidadTotal = 11 - cantidadTotal % 11;
        if (cantidadTotal == 11) {
            cantidadTotal = 0;
        }
        if (cantidadTotal == 10) {
            cantidadTotal = 1;
        }
        return cantidadTotal;
    }

    /**
     * Metodo que permite invertir una cadena
     *
     * @param cadena
     * @return
     */
    public static String invertirCadena(final String cadena) {
        String cadenaInvertida = "";
        for (int x = cadena.length() - 1; x >= 0; x--) {
            cadenaInvertida = cadenaInvertida + cadena.charAt(x);
        }
        return cadenaInvertida;
    }

}
