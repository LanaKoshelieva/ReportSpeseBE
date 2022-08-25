package Infocube.ReportSpese.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Response
{
    private String message;
    private Integer code;
    private Object data;

    public Response(Exception e)
    {
        message = e.getMessage();
        code = 500;
        data = null;
    }

    public Response(Object o)
    {
        message = "OK";
        code = 200;
        data = o;
    }

    public Response(String message, Integer code, Object data)
    {
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
