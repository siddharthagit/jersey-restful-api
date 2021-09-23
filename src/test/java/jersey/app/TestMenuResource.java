package jersey.app;

import static org.junit.Assert.assertEquals;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.jdkhttp.JdkHttpServerTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for {@link MenuResource }
 * @author siddharthagit
 */
public class TestMenuResource extends JerseyTest {

	@BeforeEach
	void init() throws Exception {
		super.setUp();
	}

	@Override
	protected Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(MenuResource.class);
	}

	@Test
	public void get_one() throws Exception {
		Response response = target("/rest/menus/1").request().get();
		System.out.println("sd " + response.getStatus());
		assertEquals(response.getStatus(), 200);
		assertEquals(response.readEntity(Menu.class).getName(), "Menu One");
	}

	protected TestContainerFactory getTestContainerFactory() {
		return new JdkHttpServerTestContainerFactory();
	}

}
