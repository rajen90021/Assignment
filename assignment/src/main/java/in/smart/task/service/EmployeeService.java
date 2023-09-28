package in.smart.task.service;

import in.smart.task.model.EmployeeShift;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EmployeeService {
    
    public void checkSevenConsecutiveDays(Map<String, Set<LocalDate>> employeeWorkDates) {
        for (String employee : employeeWorkDates.keySet()) {
            if (hasConsecutiveDays(new ArrayList<>(employeeWorkDates.get(employee)), 7)) {
                System.out.println(employee + " has worked for 7 consecutive days.");
            }
        }
    }

    public void checkShiftDuration(Map<String, List<LocalDateTime>> employeeShiftStarts, 
                                   Map<String, List<LocalDateTime>> employeeShiftEnds) {
        for (String employee : employeeShiftStarts.keySet()) {
            List<LocalDateTime> starts = employeeShiftStarts.get(employee);
            List<LocalDateTime> ends = employeeShiftEnds.get(employee);

            for (int i = 1; i < starts.size(); i++) {
                Duration duration = Duration.between(ends.get(i - 1), starts.get(i));
                if (duration.toHours() > 1 && duration.toHours() < 10) {
                    System.out.println(employee + " had less than 10 hours but more than 1 hour between shifts.");
                    break;
                }
            }
        }
    }

    public void checkLongShifts(Map<String, List<LocalDateTime>> employeeShiftStarts, 
                                Map<String, List<LocalDateTime>> employeeShiftEnds) {
        for (String employee : employeeShiftStarts.keySet()) {
            List<LocalDateTime> starts = employeeShiftStarts.get(employee);
            List<LocalDateTime> ends = employeeShiftEnds.get(employee);

            for (int i = 0; i < starts.size(); i++) {
                Duration duration = Duration.between(starts.get(i), ends.get(i));
                if (duration.toHours() > 14) {
                    System.out.println(employee + " worked more than 14 hours in a single shift.");
                    break;
                }
            }
        }
    }

    private boolean hasConsecutiveDays(List<LocalDate> dates, int consecutiveDays) {
        Collections.sort(dates);
        int count = 1;

        for (int i = 1; i < dates.size(); i++) {
            if (dates.get(i).minusDays(1).equals(dates.get(i - 1))) {
                count++;
                if (count >= consecutiveDays) return true;
            } else {
                count = 1;
            }
        }
        return false;
    }
}
