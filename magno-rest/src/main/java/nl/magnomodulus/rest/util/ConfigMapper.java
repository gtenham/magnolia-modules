package nl.magnomodulus.rest.util;

import nl.magnomodulus.rest.config.MagnoliaConfig;

public interface ConfigMapper<T> {

	T map(MagnoliaConfig config);
}
