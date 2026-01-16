package org.example.ws;

import jakarta.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        String url = "http://localhost:8090/absenceService";
        Endpoint.publish(url, new AbsenceServiceImpl());
        System.out.println("Service web publié à l'adresse : " + url);
    }
}
