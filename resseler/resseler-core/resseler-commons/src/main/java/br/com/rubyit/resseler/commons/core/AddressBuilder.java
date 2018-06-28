package br.com.rubyit.resseler.commons.core;

public class AddressBuilder implements Builder<AddressBuilder.Address>{
	private String country = null;
	private String state = null;
	private String city = null;

	public AddressBuilder ofCountry(final String country) {
		this.country = country;
		
		return this;
	}
	
	public AddressBuilder ofState(final String state) {
		this.state = state;
		
		return this;
	}
	
	public AddressBuilder ofCity(final String city) {
		this.city = city;
		
		return this;
	}
	
	@Override
	public Address build() {
		Address address = new Address();
		address.city = city;
		address.state = state;
		address.country = country;
		
		return address;
	}
	
	public class Address extends Entity {

		private String country = null;
		private String state = null;
		private String city = null;

		public String getCountry() {
			return country;
		}

		public String getState() {
			return state;
		}

		public String getCity() {
			return city;
		}

		@Override
		public String toString() {
			return "Address [country=" + country + ", state=" + state + ", city=" + city + "]";
		}
	}
}