
import configuration.ThymeleafConfig;
import controllers.MainController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import persistence.ConnectionPool;

public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "carport";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        new MainController(connectionPool).addRoutes(app);
    }
}