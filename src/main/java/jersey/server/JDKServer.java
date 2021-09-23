package jersey.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.ext.RuntimeDelegate;
import jersey.app.JakartaRestfulApp;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class JDKServer {

	private static final Logger LOGGER = Logger.getLogger(JDKServer.class.getName());

	/**
	 * Starts the lightweight HTTP server serving the JAX-RS application.
	 */
	static HttpServer startServer() throws IOException {

		// create a new server listening at port 8080
		final HttpServer server = HttpServer.create(new InetSocketAddress(getBaseURI().getPort()), 0);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				server.stop(0);
			}
		}));

		// create a handler wrapping the JAX-RS application
		HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JakartaRestfulApp(), HttpHandler.class);

		// map JAX-RS handler to the server root
		server.createContext(getBaseURI().getPath(), handler);

		// start the server
		server.start();

		return server;
	}

	public static void main(String[] args) {

		try {

			final HttpServer httpServer = startServer();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try {
					LOGGER.info("Shutting down the application...");

					httpServer.stop(0);

					LOGGER.info("Done, exit.");
				} catch (Exception e) {
					Logger.getLogger(GrizzlyServer.class.getName()).log(Level.SEVERE, null, e);
				}
			}));

			LOGGER.info("Application started.%nStop the application using CTRL+C");

			Thread.currentThread().join();

		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Gets base {@link URI}.
	 */
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(8080).build();
	}
}