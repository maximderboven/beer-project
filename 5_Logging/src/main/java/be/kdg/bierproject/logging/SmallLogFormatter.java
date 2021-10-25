package be.kdg.bierproject.logging;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.LogRecord;

/**
 * @author Maxim Derboven
 * @version 1.0 25/10/2021 13:30
 */
public class SmallLogFormatter extends java.util.logging.Formatter{
    @Override
    public String format(LogRecord record) {
        LocalDateTime ldt = LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        String message = MessageFormat.format(record.getMessage(), record.getParameters());
        return ldt + " Level: " +record.getLevel() + " melding: " + message + "\n";
    }
}
