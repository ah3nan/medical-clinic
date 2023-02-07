package com.ah3nan.medicalclinic.error;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.Map;
@Component
public class CustomErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        var errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("success",Boolean.FALSE);
        errorAttributes.put("status",errorAttributes.get("error"));
        errorAttributes.put("message",errorAttributes.get("message"));
        errorAttributes.put("messageDetails", Collections.singletonList(errorAttributes.get("message")));
        errorAttributes.remove("error");
        errorAttributes.remove("path");
        return errorAttributes;
    }
}
