package br.com.rubyit.resseler.commons.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.rubyit.resseler.commons.core.DesireBuilder.Desire;

public class Maker extends User {

	private Logger LOG = LogManager.getLogger(Maker.class);
	
	public void comeTrue(Desire desirousDesire) {
		LOG.debug("Realizando " + desirousDesire + " para : " + desirousDesire.getDesirous());
	}
}
