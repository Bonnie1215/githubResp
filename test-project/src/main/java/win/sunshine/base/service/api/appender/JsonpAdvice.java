package win.sunshine.base.service.api.appender;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice(basePackages = "win.sunshine.base.service.api.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	
    public JsonpAdvice() {  
        super("callback","jsonp");  
    }
}