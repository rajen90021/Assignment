package in.smart.task.reader;

import in.smart.task.model.EmployeeShift;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVFileReader {

    private static final DateTimeFormatter DATETIME_FORMATTER_AMPM = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
    private static final DateTimeFormatter DATETIME_FORMATTER_NO_AMPM = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");

    public static void parseCSVFile(String filePath, Map<String, Set<LocalDate>> employeeWorkDates, 
                                    Map<String, List<LocalDateTime>> employeeShiftStarts, 
                                    Map<String, List<LocalDateTime>> employeeShiftEnds) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            int lineNumber = 0;

            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                if (lineNumber == 1) continue;

                String employeeName = nextLine[7];
                if (nextLine[2] != null && !nextLine[2].isEmpty() && nextLine[3] != null && !nextLine[3].isEmpty()) {
                    LocalDateTime shiftStart = parseDateTime(nextLine[2]);
                    LocalDateTime shiftEnd = parseDateTime(nextLine[3]);

                    employeeWorkDates.computeIfAbsent(employeeName, k -> new HashSet<>()).add(shiftStart.toLocalDate());
                    employeeShiftStarts.computeIfAbsent(employeeName, k -> new ArrayList<>()).add(shiftStart);
                    employeeShiftEnds.computeIfAbsent(employeeName, k -> new ArrayList<>()).add(shiftEnd);
                }
            }
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr.contains("AM") || dateTimeStr.contains("PM")) {
            return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER_AMPM);
        } else {
            return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER_NO_AMPM);
        }
    }
}
