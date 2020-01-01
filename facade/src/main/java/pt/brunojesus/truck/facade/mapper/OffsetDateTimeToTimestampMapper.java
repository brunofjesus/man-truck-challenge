package pt.brunojesus.truck.facade.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component("offsetDateTimeToTimestampMapper")
public class OffsetDateTimeToTimestampMapper implements Function<OffsetDateTime, Timestamp> {

	@Override
	public Timestamp apply(OffsetDateTime t) {
		
		if (t == null) {
			return null;
		}
		
		return Timestamp.from(t.toInstant());
	}

}
