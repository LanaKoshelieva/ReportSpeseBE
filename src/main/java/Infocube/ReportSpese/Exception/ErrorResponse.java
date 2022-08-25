package Infocube.ReportSpese.Exception;

import Infocube.ReportSpese.Utility.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "error")
public class ErrorResponse extends Response
{
    public ErrorResponse(String message, Integer code)
    {
        super(message, code, null);
    }


}