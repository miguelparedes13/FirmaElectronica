package com.samasat.jengibre.firma;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.pkstore.CertStoreException;
import es.mityc.javasign.pkstore.IPKStoreManager;
import es.mityc.javasign.pkstore.keystore.KSStore;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FirmaXMLSignatureImpl implements FirmaSriSignature {

    /**
     * <p>
     * Ejecución del ejemplo. La ejecución consistirá en la firma de los datos
     * creados por el método abstracto <code>createDataToSign</code> mediante el
     * certificado declarado en la constante <code>PKCS12_FILE</code>. El
     * resultado del proceso de firma será almacenado en un fichero XML en el
     * directorio correspondiente a la constante <code>OUTPUT_DIRECTORY</code>
     * del usuario bajo el nombre devuelto por el método abstracto
     * <code>getSignFileName</code>
     * </p>
     *
     * @return
     */
    private void execute(Document docum, String documento, String ruta, String nombreDoc, String urlPkc12Resource, String password) throws Exception {
        // Obtencion del gestor de claves
        IPKStoreManager storeManager = getPKStoreManager(urlPkc12Resource, password);
        if (storeManager == null) {
            throw new IllegalArgumentException("La clave o passord son incorrectos");
        }
        X509Certificate certificate = getFirstCertificate(storeManager);
        if (certificate == null) {
            throw new IllegalArgumentException("La clave o passord son incorrectos");
        }
        PrivateKey privateKey;
        privateKey = storeManager.getPrivateKey(certificate);
        Provider provider = storeManager.getProvider(certificate);
        DataToSign dataToSign = createDataToSign(docum, documento);
        FirmaXML firma = new FirmaXML();
        Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
        Document docSigned = (Document) res[0];
        Util.saveDocument(ruta,nombreDoc, docSigned);
    }
    private void execute(String resource, String documento, String ruta, String nombreDoc, String urlPkc12Resource, String password) throws Exception {
        // Obtencion del gestor de claves
        IPKStoreManager storeManager = getPKStoreManager(urlPkc12Resource, password);
        if (storeManager == null) {
            throw new IllegalArgumentException("La clave o passord son incorrectos");
        }
        X509Certificate certificate = getFirstCertificate(storeManager);
        if (certificate == null) {
            throw new IllegalArgumentException("La clave o passord son incorrectos");
        }
        PrivateKey privateKey;
        privateKey = storeManager.getPrivateKey(certificate);
        Provider provider = storeManager.getProvider(certificate);
        DataToSign dataToSign = createDataToSign(getDocument(resource), documento);
        FirmaXML firma = new FirmaXML();
        Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
        Document docSigned = (Document) res[0];
        Util.saveDocument(ruta,nombreDoc, docSigned);
    }
    private Document getDocument(String resource) throws SAXException, IOException, ParserConfigurationException {
        Document doc ;
        File fXmlFile;
        fXmlFile = new File(resource);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder dBuilder = dbf.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);
        dBuilder.setErrorHandler(null);
        return doc;
    }
    /**
     * <p>
     * Devuelve el gestor de claves que se va a utilizar
     * </p>
     *
     * @return El gestor de claves que se va a utilizar</p>
     */
    private IPKStoreManager getPKStoreManager(String urlPkc12Resource, String pasword) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new BufferedInputStream(
                new FileInputStream(urlPkc12Resource)), pasword.toCharArray());
        IPKStoreManager storeManager = new KSStore(ks, new PassStoreKS(pasword));

        return storeManager;
    }

    /**
     * <p>
     * Recupera el primero de los certificados del almacén.
     * </p>
     *
     * @param storeManager Interfaz de acceso al almacén
     * @return Primer certificado disponible en el almacén
     */
    private X509Certificate getFirstCertificate(
            final IPKStoreManager storeManager) throws CertStoreException {
        List<X509Certificate> certs = storeManager.getSignCertificates();
        if ((certs == null) || (certs.isEmpty())) {
            return null;
        }
        X509Certificate certificate = certs.get(0);
        return certificate;
    }

    private DataToSign createDataToSign(Document docum, String documento) throws SAXException, IOException, ParserConfigurationException {
        DataToSign dataToSign = new DataToSign();
        dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
        dataToSign.setEsquema(XAdESSchemas.XAdES_132);
        dataToSign.setXMLEncoding("UTF-8");
        dataToSign.setEnveloped(true);
        dataToSign.setDocument(docum);
        dataToSign.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "Documento Electronicos", null, "text/xml", null));
        System.err.println("documento"+documento);
        dataToSign.setParentSignNode(documento);
        return dataToSign;
    }

    /**
     *
     * @param docum
     * @param tagDoc
     * @param urlOutArchivo
     * @param name
     * @param urlPkc12Resource
     * @param password
     * @throws java.lang.Exception
     *
     */
    @Override
    public void firmarDocumento(Document docum, String tagDoc, String urlOutArchivo, String name, String urlPkc12Resource, String password) throws Exception {
        execute(docum, tagDoc, urlOutArchivo, name,urlPkc12Resource, password);

    }

    /**
     *
     * @param docum
     * @param tagDoc
     * @param urlOutArchivo
     * @param name
     * @param urlPkc12Resource
     * @param password
     * @throws Exception
     */
    @Override
    public void firmarDocumento(String docum, String tagDoc, String urlOutArchivo, String name, String urlPkc12Resource, String password) throws Exception {
        execute(docum, tagDoc, urlOutArchivo, name,urlPkc12Resource, password);

    }
    
    /**
     *
     * @param codComprobante
     * @param codEstab
     * @param codPtoEmi
     * @param fecha
     * @param ruc
     * @param secuencial
     * @param codAmbiente
     * @param codEmision
     * @return
     */
    @Override
    public String generarClaveAcceso(String codComprobante, String codEstab, String codPtoEmi,
            String fecha, String ruc,
            String secuencial, String codAmbiente, String codEmision) {
        String Serie = codEstab + codPtoEmi;
        String numeroF = secuencial.substring(1, secuencial.length());
        StringBuilder nombreBuilder = new StringBuilder();
        nombreBuilder.append(fecha
                .replace("/", "").concat(codComprobante));
        nombreBuilder.append(ruc);
        nombreBuilder.append(codAmbiente);
        nombreBuilder.append(Serie);
        nombreBuilder.append(secuencial);
        nombreBuilder.append(numeroF);
        nombreBuilder.append(codEmision);
        String keyAccess = nombreBuilder.toString();
       
        return keyAccess.concat(Util.module11(Util.invertirCadena(keyAccess)) + "");
    }
     /**
      * 
      * @return 
      */
    public static FirmaSriSignature geInstance()  {
        return new FirmaXMLSignatureImpl();
    }
}
