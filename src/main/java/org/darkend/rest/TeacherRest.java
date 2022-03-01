package org.darkend.rest;

import org.darkend.entity.Teacher;
import org.darkend.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {


    @Inject
    private TeacherService teacherService;

    @Path("")
    @POST
    public Response addTeacher(Teacher teacher) {
        teacherService.add(teacher);

        return Response.created(URI.create("/labb2/teachers/" + teacher.getId()))
                .entity(teacher)
                .build();
    }

    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.get(id);

        return Response.ok(foundTeacher)
                .build();
    }

    @Path("{id}")
    @PATCH
    public Response patchTeacher(@PathParam("id") Long id, Teacher teacher) {
        Teacher patchedTeacher = teacherService.patch(id, teacher);

        return Response.ok(patchedTeacher)
                .build();
    }

    @Path("{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        teacherService.update(id, teacher);

        return Response.ok(teacher)
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response removeTeacher(@PathParam("id") Long id) {
        Teacher teacherToRemove = teacherService.remove(id);

        return Response.ok(teacherToRemove)
                .build();
    }

    @Path("")
    @GET
    public Response getAllTeachers() {
        return Response.ok(teacherService.getAll())
                .build();
    }
}
