package demo.util;

import com.vaadin.data.Result;
import com.vaadin.data.util.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hdavid on 10/14/16.
 */
public class LocalDateToDateConverter implements Converter<LocalDate, Date> {

    private final String error;
    public LocalDateToDateConverter(String error) {
        this.error = error;
    }

    @Override
    public Result<Date> convertToModel(LocalDate value, Locale locale) {
        try {
            return Result.ok(value == null ? null : Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(error);
    }

    @Override
    public LocalDate convertToPresentation(Date value, Locale locale) {

        // TODO, this is not a good implemetnation
        return value == null ? null : LocalDate.of(value.getYear(), value.getMonth(), value.getDay());
    }
}
