package com.angelo.apiwatercount.resources;

import com.angelo.apiwatercount.models.Person;
import com.angelo.apiwatercount.repositories.PersonRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/person")
public class PersonResource {
    private PersonRepository repository = new PersonRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> get() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getById(@PathParam("id") int id) {
        return repository.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Person person) {
        try {
            repository.add(person);
            return Response.status(Response.Status.CREATED).entity(person).build();
        }
        catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, Person person) {
        Person p = repository.get(id);

        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            person.setId(id);
            repository.edit(person);
            return Response.status(Response.Status.OK).entity(person).build();
        }
        catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Person p = repository.get(id);

        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
