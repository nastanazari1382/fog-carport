package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import persistence.ConnectionPool;

public class MainController {

    private final ConnectionPool connectionPool;

    public MainController(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    // Registers the route for the front page
    public void addRoutes(Javalin app) {
        app.get("/", ctx -> index(ctx));
    }

    private void index(Context ctx) {
        ctx.render("index.html");
    }
}