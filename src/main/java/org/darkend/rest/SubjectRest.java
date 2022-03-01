package org.darkend.rest;

import org.darkend.entity.Subject;
import org.darkend.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {


    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response addSubject(Subject subject) {
        subjectService.add(subject);

        return Response.created(URI.create("/labb2/subjects/" + subject.getId()))
                .entity(subject)
                .build();
    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.get(id);

        return Response.ok(foundSubject)
                .build();
    }

    @Path("{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject) {
        subjectService.update(id, subject);

        return Response.ok(subject)
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response removeSubject(@PathParam("id") Long id) {
        Subject subjectToRemove = subjectService.remove(id);

        return Response.ok(subjectToRemove)
                .build();
    }

    @Path("")
    @GET
    public Response getAllSubjects() {
        return Response.ok(subjectService.getAll())
                .build();
    }

    @Path("{id}/students")
    @GET
    public Response getAllStudentsInSubject(@PathParam("id") Long id) {
        return Response.ok(subjectService.getAllStudents(id))
                .build();
    }
}
