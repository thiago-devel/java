package br.com.rubyit.resseler.persistence.entity.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * credits to:
 * http://stackoverflow.com/questions/8517323/how-to-convert-map-to-bytes-and-save-to-internal-storage
 * http://www.thoughts-on-java.org/jpa-21-how-to-implement-type-converter/
 * @author Thiago
 *
 */
@Converter(autoApply = true)
public class ProductPriceMapConverter implements AttributeConverter<Map<String, BigDecimal>, byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(final Map<String, BigDecimal> price) {
		byte[] result = null;
		// Convert Map to byte array
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(price);
			result = byteOut.toByteArray();
		} catch (IOException ex) {
			System.err.println("ERRORRRRRRRRRRR");
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public Map<String, BigDecimal> convertToEntityAttribute(final byte[] dbData) {
		Map<String, BigDecimal> result = null;
		ByteArrayInputStream byteIn = new ByteArrayInputStream(dbData);
		try {
			final ObjectInputStream in = new ObjectInputStream(byteIn);
			result = (Map<String, BigDecimal>) in.readObject();
		} catch (IOException | ClassNotFoundException ex) {
			System.err.println("ERRORRRRRRRRRRR");
			ex.printStackTrace();
		}

		return result;
	}

}
