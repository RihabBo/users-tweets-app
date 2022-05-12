package wishow.rihab.tweeter.controller.request;

import lombok.Getter;
import wishow.rihab.tweeter.controller.input.PatchUserInput;

@Getter
public class PatchUserRequest {

    private final String name;
    private final Integer age;

    public PatchUserRequest(PatchUserInput patchUserInput) {
        if (patchUserInput == null) {
            throw new IllegalArgumentException("The user is not valid");
        }
        if (patchUserInput.getName() != null && patchUserInput.getName().isEmpty()) {
            throw new IllegalArgumentException("The name of the useris not valid");
        }
        if (patchUserInput.getAge() != null && patchUserInput.getAge() < 0) {
            throw new IllegalArgumentException("The age of the user is not valid");
        }
        name = patchUserInput.getName();
        age = patchUserInput.getAge();

    }
}
