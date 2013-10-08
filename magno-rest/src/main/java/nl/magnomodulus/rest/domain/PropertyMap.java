package nl.magnomodulus.rest.domain;

import info.magnolia.link.LinkUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyMap implements Map<String, Object> {

	private final static Logger log = LoggerFactory.getLogger(PropertyMap.class);
	private final Map<String, Object> props = new HashMap<String, Object>();
	
	public PropertyMap(PropertyIterator properties) {
		
		try {
			while (properties.hasNext()) {
				Property prop = properties.nextProperty();
				props.put(convertKey(prop.getName()),getPropertyValue(prop));
			}
		} catch (RepositoryException e) {
			log.debug(e.getMessage());
		}
	}
	
	public int size() {
		return props.size();
	}

	public boolean isEmpty() {
		return props.isEmpty();
	}

	public boolean containsKey(Object key) {
		return props.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return props.containsValue(value);
	}

	public Object get(Object key) {
		return props.get(key);
	}

	public Set<String> keySet() {
		return props.keySet();
	}

	public Collection<Object> values() {
		return props.values();
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return props.entrySet();
	}
	
	public Object put(String key, Object value) {
		// ignore, read only
        return null;
	}

	public Object remove(Object key) {
		// ignore, read only
        return null;
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private String convertKey(Object key) {
        if (key == null) {
            return null;
        }
        try {
            return (String) key;
        } catch (ClassCastException e) {
            log.debug("Invalid key. Expected String, but got {}.", key.getClass().getName());
        }
        return null;
    }
	
	private Object getPropertyValue(Property prop) {
        try {
            int type = prop.getType();
            if (type == PropertyType.DATE) {
                return prop.getDate();
            } else if (type == PropertyType.BOOLEAN) {
                return prop.getBoolean();
            } else if (type == PropertyType.LONG) {
                return prop.getLong();
            } else if (type == PropertyType.DOUBLE) {
                return prop.getDouble();
            } else if (prop.isMultiple()) {

                Value[] values = prop.getValues();

                String[] valueStrings = new String[values.length];

                for (int j = 0; j < values.length; j++) {
                    try {
                        valueStrings[j] = values[j].getString();
                    } catch (RepositoryException e) {
                    	log.warn(e.getMessage());
                    }
                }

                return valueStrings;
            } else if (type == PropertyType.BINARY) {
                // TODO: Investigate how to decode binary inputstream (eg. images) to an JSON output. Is this possible? Base64 encoded string maybe?
            	//       What about large binaries? Maybe support for Types like PropertyType.REFERENCE and PropertyType.WEAKREFERENCE are better 
            	//       suited for this purpose, so a link to the node can be returned!
            } else if (type == PropertyType.NAME) {
            	// Already provided as main node property
            } else if (type == PropertyType.PATH) {
            	// Already provided as main node property
            } else if (LinkUtil.UUID_PATTERN.matcher(prop.getString()).find()) {
            	
            } else if (prop.isNode()) {
            	// Node value: Are we able to create a link to this?
            	//prop.getNode().getSession().getWorkspace().getName();
            } 
            // Finally provide the String value
            return prop.getString();
            

        } catch (PathNotFoundException e) {
            // ignore, property doesn't exist
        } catch (RepositoryException e) {
        	log.warn("Failed to retrieve property value with {}", new Object[] {e.getMessage() });
        }

        return null;
    }

}
