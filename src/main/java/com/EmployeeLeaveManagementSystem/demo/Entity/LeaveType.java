package com.EmployeeLeaveManagementSystem.demo.Entity;

public enum LeaveType {
        FULLDAY(-1),
        HALFDAY(-0.5),
        SHORTLEAVE(-0.25);

        private final double value;

        LeaveType(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

