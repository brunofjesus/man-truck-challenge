package pt.brunojesus.truck.facade.mapper;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

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
