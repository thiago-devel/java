package br.com.rubyit.resseler.commons.core;

public class DesireTypeBuilder implements Builder<DesireTypeBuilder.DesireType> {

	private Type type = null;
	
	public class DesireType {
		private Type type = null;
		
		public Type getType() {
			return type;
		}
	}
	
	public enum Type {
		UNDEFINED, COMPANION, SEXUAL, FUN
	}
	
	public DesireTypeBuilder withType(Type type) {
		this.type = type;
		
		return this;
	}

	@Override
	public DesireType build() {
		DesireType result = new DesireType();
		result.type = type;
		
		return result;
	}
}
