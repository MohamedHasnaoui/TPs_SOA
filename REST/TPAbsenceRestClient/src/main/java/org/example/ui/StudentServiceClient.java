package org.example.ui;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Student;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.util.List;

public class StudentServiceClient {
    private static final String BASE_URI = System.getenv().getOrDefault("API_BASE_URI", "http://localhost:8085/api");
    private final Client client;
    private final WebTarget target;

    public StudentServiceClient() {
        this.client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build();
        this.target = client.target(BASE_URI).path("students");
    }

    public List<Student> getAllStudents() {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Student>>() {
        });
    }

    public Student getStudent(String cne) {
        Response response = target.path(cne).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return null;
        }
        return response.readEntity(Student.class);
    }

    public Response addStudent(Student student) {
        return target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(student, MediaType.APPLICATION_JSON));
    }

    public Response updateStudent(String cne, Student student) {
        return target.path(cne).request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(student, MediaType.APPLICATION_JSON));
    }

    public Response deleteStudent(String cne) {
        return target.path(cne).request().delete();
    }

    public List<Student> getBlacklist(double threshold) {
        Response response = target.path("blacklist").queryParam("threshold", threshold)
                .request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Student>>() {
        });
    }

    public void close() {
        client.close();
    }
}
