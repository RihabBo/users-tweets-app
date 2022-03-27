package wishow.rihab.tweeter.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewUserRequest {

    private String name;

    private int age;

}
