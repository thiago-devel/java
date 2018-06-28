package br.com.rubyit.resseler.commons.core;

import static br.com.rubyit.resseler.commons.core.Constants.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.rubyit.resseler.commons.core.AddressBuilder.Address;
import br.com.rubyit.resseler.commons.core.DesireCategoryBuilder.DesireCategory;
import br.com.rubyit.resseler.commons.core.DesireTypeBuilder.DesireType;
import br.com.rubyit.resseler.commons.core.exceptions.CreateDesireException;

public final class DesireBuilder implements Builder<DesireBuilder.Desire> {

	private Logger LOG = LogManager.getLogger(DesireBuilder.class);

	private User desirous = null;
	private String description = null;
	private DesireType type = null;
	private DesireCategory category = null;
	private Set<String> tags = null;
	private Address desireAddress = null;
	private Gender targetGender = null;
	private Gender desirousGender = null;

	public Desire build() {
		validate();

		Desire result = mountDesire();
		LOG.info("new Desire [" + result + "] created.");

		return result;
	}

	private Desire mountDesire() {
		final Desire desire = new Desire();
		desire.setDescription(description);
		desire.desirous = desirous;
		mountDesireType(desire);
		mountDesireCategory(desire);
		mountDesireTags(desire);
		desire.desireAddress = this.desireAddress;
		desire.targetGender = this.targetGender;
		desire.desirousGender = this.desirousGender;
		desire.desireDate = LocalDate.now();

		return desire;
	}

	private void mountDesireTags(final Desire desire) {
		if (tags == null) {
			tags.add("");
		} else {
			desire.tags = tags;
		}
	}

	private void mountDesireCategory(final Desire desire) {
		if (this.category == null) {
			DesireCategoryBuilder.Category withCategory = DesireCategoryBuilder.Category.UNDEFINED;
			DesireCategory category = new DesireCategoryBuilder().withCategory(withCategory).build();

			desire.category = category;
		} else {
			desire.category = category;
		}
	}

	private void mountDesireType(final Desire desire) {
		if (type == null) {
			DesireTypeBuilder.Type withType = DesireTypeBuilder.Type.UNDEFINED;
			DesireType type = new DesireTypeBuilder().withType(withType).build();

			desire.type = type;
		} else {
			desire.type = type;
		}
	}

	private void validate() {
		if (description == null && desirous == null && desireAddress == null && targetGender == null
				&& desirousGender == null) {
			String msg = ERROR_NOT_FILLED_DESIRE;
			LOG.error(msg);
			throw new CreateDesireException(msg);
		}
		if (description == null && desirous != null && desireAddress != null && targetGender != null
				&& desirousGender != null) {
			String msg = ERROR_DESIRE_WITHOUT_DESCRIPTION;
			LOG.error(msg);
			throw new CreateDesireException(msg);
		}
		validateDesirous();
	}

	private void validateDesirous() {
		if (description != null && desirous == null && desireAddress != null && targetGender != null
				&& desirousGender != null) {
			String msg = ERROR_DESIRE_WITHOUT_DESIREOUS;
			LOG.error(msg);
			throw new CreateDesireException(msg);
		}
	}

	public DesireBuilder withTheDescription(String description) {
		this.description = description;

		return this;
	}

	public DesireBuilder withType(DesireType type) {
		this.type = type;

		return this;
	}
	
	public DesireBuilder withCategory(DesireCategory category) {
		this.category = category;
		
		return this;
	}

	public DesireBuilder withDesirousAddress(Address desireAddress) {
		this.desireAddress = desireAddress;

		return this;
	}

	public DesireBuilder withTargetGender(Gender targetGender) {
		this.targetGender = targetGender;

		return this;
	}

	public DesireBuilder withDesirousGender(Gender desireGender) {
		this.desirousGender = desireGender;

		return this;
	}

	public DesireBuilder forDesirous(User desirous) {
		this.desirous = desirous;

		return this;
	}

	public DesireBuilder withTheTags(Set<String> tags) {
		this.tags = tags;

		return this;
	}

	@Deprecated
	public DesireBuilder withRatingBoard(RatingBoard classificacao) {
		return this;
	}

	public final class Desire extends Entity {

		private User desirous = null;
		private User maker = null;
		private DesireType type = null;
		private DesireCategory category = null;
		private Set<String> tags = null;
		private Address desireAddress = null;
		private Gender targetGender = null;
		private Gender desirousGender = null;
		private LocalDate desireDate = null;

		public User getDesirous() {
			return this.desirous;
		}

		public void setMaker(User maker) {
			this.maker = maker;
		}

		public User getMaker() {
			return maker;
		}

		public DesireType getType() {
			return type;
		}

		public DesireCategory getCategory() {
			return category;
		}

		public Set<String> getTags() {
			if (tags == null) {
				tags = new HashSet<>();
			}

			return tags;
		}

		public Address getDesireAddress() {
			return desireAddress;
		}

		public Gender getTargetGender() {
			return targetGender;
		}

		public Gender getDesirousGender() {
			return desirousGender;
		}

		public LocalDate getDesireDate() {
			return desireDate;
		}

		@Override
		public String toString() {
			return "Desire [desirous=" + desirous + ", maker=" + maker + ", type=" + type + ", category=" + category
					+ ", tags=" + tags + ", desireAddress=" + desireAddress + ", targetGender=" + targetGender
					+ ", desireGender=" + desirousGender + ", desireDate=" + desireDate + ", getID()=" + getID()
					+ ", getDescription()=" + getDescription() + "]";
		}
	}
}
