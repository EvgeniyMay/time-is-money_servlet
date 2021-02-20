package com.mylearning.timeismoney.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class DangerError extends SimpleTagSupport {


    StringWriter sw = new StringWriter();
    public void doTag() throws IOException, JspException {
        getJspBody().invoke(sw);

        getJspContext().getOut()
                .print(new StringBuilder()
                        .append("<div class=\"alert alert-danger\" role=\"alert\">")
                        .append(sw.toString())
                        .append("</div>")
                        .toString());
    }
}
