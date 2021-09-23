package jersey.app;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.core.Application;

public class JakartaRestfulApp extends Application {
	private final Set<Class<?>> classes;

	public JakartaRestfulApp() {
		HashSet<Class<?>> c = new HashSet<Class<?>>();
		c.add(MenuResource.class);
		classes = Collections.unmodifiableSet(c);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}