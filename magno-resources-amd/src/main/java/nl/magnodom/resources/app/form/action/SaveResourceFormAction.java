package nl.magnodom.resources.app.form.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Item;

import info.magnolia.cms.core.Path;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.ui.form.EditorCallback;
import info.magnolia.ui.form.EditorValidator;
import info.magnolia.ui.form.action.SaveFormAction;
import info.magnolia.ui.form.action.SaveFormActionDefinition;
import info.magnolia.ui.vaadin.integration.jcr.JcrNewNodeAdapter;
import info.magnolia.ui.vaadin.integration.jcr.JcrNodeAdapter;

public class SaveResourceFormAction extends SaveFormAction {

	private static final Logger log = LoggerFactory.getLogger(SaveResourceFormAction.class);
	
	public SaveResourceFormAction(SaveFormActionDefinition definition, Item item, EditorCallback callback, EditorValidator validator) {
		super(definition, (JcrNodeAdapter) item, callback, validator);
	}

	protected void setNodeName(Node node, JcrNodeAdapter item) throws RepositoryException {
		JcrNodeAdapter itemChanged = item;
		if (itemChanged instanceof JcrNewNodeAdapter || !node.getName().startsWith(defineNodeName(node))) {
			final String newNodeName = generateUniqueNodeNameForResource(node);
			itemChanged.setNodeName(newNodeName);
			NodeUtil.renameNode(node, newNodeName);
		}
	}
	
	private String generateUniqueNodeNameForResource(final Node node) throws RepositoryException {
		String newNodeName = defineNodeName(node);
		return Path.getUniqueLabel(node.getSession(), node.getParent().getPath(), newNodeName);
	}
	
	private String defineNodeName(final Node node) throws RepositoryException {
		return node.getProperty("title").getString().trim();
	}
}
