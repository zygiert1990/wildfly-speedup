package org.zygiert.wildflyspeedup.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.zygiert.wildflyspeedup.service.UserService;

import java.util.logging.Logger;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());

    @Inject
    private UserService userService;

    @GET
    public Response getAllUsers() {
        return Response.ok(userService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        return userService.findById(id)
                .map(Response::ok)
                .orElse(Response.noContent())
                .build();
    }

    @POST
    public Response save(UserDTO user) {
        LOGGER.info("Saving user: " + user);
        return Response.ok(userService.save(user)).build();
    }

}
