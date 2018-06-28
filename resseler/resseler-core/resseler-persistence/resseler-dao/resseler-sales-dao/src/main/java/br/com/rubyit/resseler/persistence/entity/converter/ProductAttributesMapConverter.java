package br.com.rubyit.resseler.persistence.entity.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
@Converter
public class ProductAttributesMapConverter implements AttributeConverter<Map<String, Object>, byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(final Map<String, Object> attribute) {
		byte[] result = null;
		// Convert Map to byte array
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(attribute);
			result = byteOut.toByteArray();
		} catch (IOException ex) {
			System.err.println("ERRORRRRRRRRRRR");
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(final byte[] dbData) {
		Map<String, Object> result = null;
		ByteArrayInputStream byteIn = new ByteArrayInputStream(dbData);
		try {
			final ObjectInputStream in = new ObjectInputStream(byteIn);
			result = (Map<String, Object>) in.readObject();
		} catch (IOException | ClassNotFoundException ex) {
			System.err.println("ERRORRRRRRRRRRR");
			ex.printStackTrace();
		}

		return result;
	}

}
