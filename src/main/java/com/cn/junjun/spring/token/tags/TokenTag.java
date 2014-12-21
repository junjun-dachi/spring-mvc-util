package com.cn.junjun.spring.token.tags;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.junjun.spring.token.TokenHandler;

/**
 * This class is the jstl for token.
 * 
 * @author junjun
 * @since 28 NOV 2014
 *
 */

public class TokenTag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {

        ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        TokenHandler handler = ctx.getBean(TokenHandler.class);

        JspWriter out = getJspContext().getOut();
        out.println("<input type=\"hidden\" id=\"token\" name=\"token\"  value=\"" + handler.generate() + "\""+ "/>");
    }

}
