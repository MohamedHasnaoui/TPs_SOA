package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Path("/students")
public class StudentResource {

    private static final Map<String, Student> students = new ConcurrentHashMap<>();

    static {
        // Sample data
        students.put("G123456", new Student("G123456", "Dupont", "Jean", "M1", 10));
        students.put("G789012", new Student("G789012", "Durand", "Marie", "L3", 60));
        students.put("G345678", new Student("G345678", "Martin", "Paul", "M1", 50));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        if (student == null || student.getCne() == null || students.containsKey(student.getCne())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        students.put(student.getCne(), student);
        URI location = URI.create("/students/" + student.getCne());
        return Response.created(location).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {
        return Response.ok(students.values()).build();
    }

    @GET
    @Path("/{cne}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("cne") String cne) {
        Student student = students.get(cne);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student).build();
    }

    @PUT
    @Path("/{cne}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("cne") String cne, Student updatedStudent) {
        if (!students.containsKey(cne)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        students.put(cne, updatedStudent);
        return Response.ok(updatedStudent).build();
    }

    @DELETE
    @Path("/{cne}")
    public Response deleteStudent(@PathParam("cne") String cne) {
        if (students.remove(cne) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/blacklist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlacklist(@QueryParam("threshold") @DefaultValue("50") double threshold) {
        List<Student> blacklist = students.values().stream()
                .filter(s -> s.getAbsenceHours() >= threshold)
                .sorted(Comparator.comparing(Student::getAbsenceHours).reversed()
                        .thenComparing(Student::getNom))
                .collect(Collectors.toList());
        return Response.ok(blacklist).build();
    }

}
