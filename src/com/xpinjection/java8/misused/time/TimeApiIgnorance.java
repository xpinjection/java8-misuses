package com.xpinjection.java8.misused.time;

public class TimeApiIgnorance {
    @Ugly
    class AddDayInPreJava8Style {

        public Date tomorrow() {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DAY_OF_MONTH, 1);
            return now.getTime();
        }
    }

    @Bad
    class AddDayInefficient {

        public LocalDate tomorrow() {
            return LocalDate.now().plus(1, TemporalUnit.DAYS);
        }
    }

    @Good
    class AddDayInJava8Style {

        public LocalDate tomorrow() {
            return LocalDate.now().plusDays(1);
        }
    }
}