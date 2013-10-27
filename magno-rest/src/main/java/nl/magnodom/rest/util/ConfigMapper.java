package nl.magnodom.rest.util;

import nl.magnodom.rest.config.MagnoliaConfig;

public interface ConfigMapper<T> {

	T map(MagnoliaConfig config);
}
