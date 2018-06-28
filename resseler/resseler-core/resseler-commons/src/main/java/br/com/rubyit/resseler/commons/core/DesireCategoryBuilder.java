package br.com.rubyit.resseler.commons.core;

public class DesireCategoryBuilder implements Builder<DesireCategoryBuilder.DesireCategory>{
	
	private Category category;
	
	public class DesireCategory {
		private Category category = null;
		
		public Category getCategory() {
			return category;
		}
	}

	public enum Category {
		UNDEFINED, PERSONA, OBJECT
	}

	public DesireCategoryBuilder withCategory(Category category) {
		this.category = category;
		
		return this;
	}
	
	@Override
	public DesireCategory build() {
		DesireCategory result = new DesireCategory();
		result.category = this.category;
		
		return result;
	}
}
