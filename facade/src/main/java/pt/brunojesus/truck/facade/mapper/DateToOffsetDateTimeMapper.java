package pt.brunojesus.truck.facade.mapper;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

/**
 * The Class DateToOffsetDateTimeMapper.
 * 
 * Maps a {@link java.util.Date} to {@link java.time.OffsetDateTime}
 * 
 * @see java.util.Date
 * @see java.time.OffsetDateTime
 */
@Component("dateToOffsetDateTimeMapper")
public class DateToOffsetDateTimeMapper implements Function<Date, OffsetDateTime> {

	@Override
	public OffsetDateTime apply(Date t) {

		if (t == null) {
			return null;
		}

		return OffsetDateTime.ofInstant(t.toInstant(), ZoneId.systemDefault());
	}

}
