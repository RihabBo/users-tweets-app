package wishow.rihab.tweeter.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ApiError {

    private String message;
    private int code;

    private ApiError(ApiErrorBuilder builder) {
        this.message = builder.message;
        this.code = builder.code;
    }

    public static ApiErrorBuilder builder() {
        return new ApiErrorBuilder();
    }

    @Getter
    @NoArgsConstructor
    public static class ApiErrorBuilder {

        private String message;
        private int code;

        public ApiErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorBuilder code(int code) {
            this.code = code;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
