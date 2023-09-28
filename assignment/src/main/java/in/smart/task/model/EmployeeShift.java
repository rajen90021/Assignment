package in.smart.task.model;

import java.time.LocalDateTime;

public class EmployeeShift {
	private String employeeName;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public LocalDateTime getShiftStart() {
		return shiftStart;
	}
	public void setShiftStart(LocalDateTime shiftStart) {
		this.shiftStart = shiftStart;
	}
	public LocalDateTime getShiftEnd() {
		return shiftEnd;
	}
	public void setShiftEnd(LocalDateTime shiftEnd) {
		this.shiftEnd = shiftEnd;
	}
	@Override
	public String toString() {
		return "EmployeeShift [employeeName=" + employeeName + ", shiftStart=" + shiftStart + ", shiftEnd=" + shiftEnd
				+ "]";
	}
    
}
