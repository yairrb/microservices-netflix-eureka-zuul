package com.formacionbdi.sprinboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {


    private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //log.info(String.format("%s request enrutado a %", request.getMethod(), request.getRequestURL().toString()));

        Long tiempo = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempo);

        return null;
    }
}
