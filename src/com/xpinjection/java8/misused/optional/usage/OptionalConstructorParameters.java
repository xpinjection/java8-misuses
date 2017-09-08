package com.xpinjection.java8.misused.optional.usage;

import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalConstructorParameters {
    @Ugly
    class OptionalLeaksOutsideClass {
        public List<Email> create() {
            Email noAttachment = new Email("First!", "No attachment", Optional.empty());
            Attachment attachment = new Attachment("/mnt/files/image.png", 370);
            Email withAttachment = new Email("Second!", "With attachment", Optional.of(attachment));
            return Arrays.asList(noAttachment, withAttachment);
        }

        class Email implements Serializable {
            private final String subject;
            private final String body;
            private final Optional<Attachment> attachment;

            Email(String subject, String body, Optional<Attachment> attachment) {
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
            }

            String getSubject() {
                return subject;
            }

            String getBody() {
                return body;
            }

            Optional<Attachment> getAttachment() {
                return attachment;
            }
        }
    }

    @Good
    class OverloadedConstructors {
        public List<Email> create() {
            Email noAttachment = new Email("First!", "No attachment");
            Attachment attachment = new Attachment("/mnt/files/image.png", 370);
            Email withAttachment = new Email("Second!", "With attachment", attachment);
            return Arrays.asList(noAttachment, withAttachment);
        }

        class Email implements Serializable {
            private final String subject;
            private final String body;
            private final Attachment attachment;

            Email(String subject, String body, Attachment attachment) {
                this.subject = subject;
                this.body = body;
                this.attachment = attachment;
            }

            Email(String subject, String body) {
                this(subject, body, null);
            }

            String getSubject() {
                return subject;
            }

            String getBody() {
                return body;
            }

            boolean hasAttachment() {
                return attachment != null;
            }

            Attachment getAttachment() {
                return attachment;
            }
        }
    }

    class Attachment {
        private final String path;
        private final int size;

        Attachment(String path, int size) {
            this.path = path;
            this.size = size;
        }

        String getPath() {
            return path;
        }

        int getSize() {
            return size;
        }
    }
}
