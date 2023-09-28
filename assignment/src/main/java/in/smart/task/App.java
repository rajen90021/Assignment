package in.smart.task;




import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.opencsv.exceptions.CsvValidationException;

import in.smart.task.reader.CSVFileReader;
import in.smart.task.service.EmployeeService;

public class App {
	public static void main(String[] args) throws IOException, CsvValidationException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to your CSV file:");
        String filePath = scanner.nextLine();

        Map<String, Set<LocalDate>> employeeWorkDates = new HashMap<>();
        Map<String, List<LocalDateTime>> employeeShiftStarts = new HashMap<>();
        Map<String, List<LocalDateTime>> employeeShiftEnds = new HashMap<>();

        // Parse the CSV file
        CSVFileReader.parseCSVFile(filePath, employeeWorkDates, employeeShiftStarts, employeeShiftEnds);

        EmployeeService employeeService = new EmployeeService();

        // Check for conditions
        employeeService.checkSevenConsecutiveDays(employeeWorkDates);
        employeeService.checkShiftDuration(employeeShiftStarts, employeeShiftEnds);
        employeeService.checkLongShifts(employeeShiftStarts, employeeShiftEnds);

        scanner.close();
    }
}
