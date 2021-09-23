package jersey.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.core.UriBuilder;
import jersey.app.MenuResource;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrizzlyServer {

	private static final Logger LOGGER = Logger.getLogger(GrizzlyServer.class.getName());

	// Starts Grizzly HTTP server
	public static HttpServer startServer() {

		final ResourceConfig config = new ResourceConfig();
		config.registerClasses(MenuResource.class);
		LOGGER.info("Starting Server........Grizzly");
		final HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(getBaseURI(), config);
		return httpServer;
	}

	public static void main(String[] args) {

		try {

			final HttpServer httpServer = startServer();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try {
					LOGGER.info("Shutting down the application...");
					httpServer.shutdownNow();
					LOGGER.info("Done, exit.");
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, e.getMessage());
				}
			}));

			LOGGER.info("Application started.%nStop the application using CTRL+C");
			Thread.currentThread().join();
		} catch (InterruptedException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		}

	}

	/**
	 * Gets base {@link URI}.
	 */
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(8080).build();
	}
}
