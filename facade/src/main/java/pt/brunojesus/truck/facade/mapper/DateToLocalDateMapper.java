package pt.brunojesus.truck.facade.mapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component("dateToLocalDateMapper")
public class DateToLocalDateMapper implements Function<Date, LocalDate> {

	@Override
	public LocalDate apply(Date t) {
		if (t == null) {
			return null;
		}

		return t.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
