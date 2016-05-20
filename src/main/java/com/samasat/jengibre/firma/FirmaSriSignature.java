/*
 * Copyright (C) 2016 anthoserv
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.samasat.jengibre.firma;

import org.w3c.dom.Document;

/**
 *
 * @author anthoserv
 */
public interface FirmaSriSignature {
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
 void firmarDocumento(Document docum, String tagDoc, String urlOutArchivo, String name, String urlPkc12Resource, String password) throws  Exception;
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
 void firmarDocumento(String docum, String tagDoc, String urlOutArchivo, String name, String urlPkc12Resource, String password) throws  Exception;
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
 String generarClaveAcceso(String codComprobante, String codEstab, String codPtoEmi,
            String fecha, String ruc,
            String secuencial, String codAmbiente, String codEmision);
}
