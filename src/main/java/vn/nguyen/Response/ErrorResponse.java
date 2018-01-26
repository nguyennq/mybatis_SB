package vn.nguyen.Response;


public class ErrorResponse extends BaseResponse<String> {

    private static final long serialVersionUID = -3076453132887653668L;

    public ErrorResponse() {
        setStatus("1");
    }
}
