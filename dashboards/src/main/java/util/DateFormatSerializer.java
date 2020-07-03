package util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author CSLC1181
 * @info Utility class for date format conversions.
 */
public class DateFormatSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator gen, SerializerProvider serializers) {
		String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern(Constants.DATE_DISPLAY_FORMAT));
		try {
			gen.writeString(formattedDateTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
