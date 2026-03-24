package org.example;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import java.io.IOException;
import java.net.URI;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

public class Application {

    public static final String BASE_URI = "http://localhost:9090/api/";

    public static HttpServer startServer() {
    	final ResourceConfig config = new ResourceConfig()
    	        .packages("org.example.resource")
    	        .register(ApiListingResource.class)
    	        .register(SwaggerSerializers.class)
    	        .property(ServerProperties.WADL_FEATURE_DISABLE, false);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("ElectroShop Sales API");
        beanConfig.setDescription("Documentation Swagger de l'API REST des ventes");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:9090");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("org.example.resource");
        beanConfig.setScan(true);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Serveur REST démarré avec succès !");
        System.out.println("URL de base: " + BASE_URI);
        System.out.println("Appuyez sur Entrée pour arrêter le serveur...");
        System.in.read();
        server.shutdownNow();
    }
}