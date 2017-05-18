package com.xpinjection.java8.misused.optional;

public class OptionalOverEngineering {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalOverEngineering.class);

    @Ugly
    class ShiftOverEngineering {

        void justShiftField() {
            DomainData source = service.getData();
            DomainData target = new DomainData();   // target has just created empty

            Optional.ofNullable(source.getField())
                    .ifPresent(target::setField);
        }
    }

    @Good
    class SimpleShift {

        void justShiftField() {
            DomainData source = service.getData();
            DomainData target = new DomainData();

            target.setField(source.getField());
        }
    }

    @Ugly
    class OutputOverEngineering {

        void justPrintField() {
            DomainData source = service.getData();

            LOGGER.info("Field value {}", ofNullable(source.getField()).orElse(null));
        }
    }

    @Good
    class SimpleOutput {

        void justPrintField() {
            DomainData source = service.getData();

            LOGGER.info("Field value {}", source.getField());
        }
    }

    class DomainData {
        private Object field;

        public Object getField() {
            return field;
        }

        public void setField(Object value) {
            field = value;
        }
    }
}