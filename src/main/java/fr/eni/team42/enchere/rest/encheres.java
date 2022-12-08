package fr.eni.team42.enchere.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/afficher")
public class encheres {
    @GET
    @Produces("text/plain")
    public String hello() {
        return null;
    }
}