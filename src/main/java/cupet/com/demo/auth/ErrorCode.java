package cupet.com.demo.auth;

public enum ErrorCode {
    AUTH_TOKEN_NOT_MATCH("Authentication token does not match")
    ,NOT_FIND_CUPETLOFINUSERTOKEN("NOT_FIND_CUPETLOFINUSERTOKEN");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
