package org.example;

import jakarta.xml.ws.Endpoint;

public class ServeurJAXWS {
    public static void main(String[] args) {
        String url = "http://localhost:9090/GestionNoteService";
        Endpoint.publish(url, new GestionNoteServiceImpl());
        System.out.println("Service web publié à l'adresse : " + url);
        System.out.println("WSDL disponible à : " + url + "?wsdl");
    }
}
