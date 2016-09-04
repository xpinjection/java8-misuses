package org.javaday.java8.misused.lambda;

import org.javaday.java8.misused.User;
import org.javaday.java8.misused.UserDto;
import org.javaday.java8.misused.Annotations.Good;
import org.javaday.java8.misused.Annotations.Ugly;

import java.util.List;
import java.util.function.Function;

import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;

public class AvoidLongLambdas {

    List<User> users = EMPTY_LIST;

    @Ugly
    class LongLambdaInPlace {

        public List<UserDto> convertToDto(List<User> users){
            return users.stream()
                    .map(user -> {
                        UserDto dto = new UserDto();
                        dto.setId(user.getId());
                        dto.setName(user.getName());
                        //It happens to be much more fields and much more logic in terms of remapping these fields
                        return dto;
                    })
                    .collect(toList());
        }
    }

    @Good
    class MethodReferenceInsteadOfLambda {

        //Particular converter could be implemented as a separate class or as a lambda function
        Function<User, UserDto> converter = this::convertToDto;

        public List<UserDto> convertToDto(List<User> users){
            return users.stream()
                    .map(converter)
                    .collect(toList());
        }

        private UserDto convertToDto(User user){
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            return dto;
        }
    }

}
