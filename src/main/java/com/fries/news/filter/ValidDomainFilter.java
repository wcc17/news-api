package com.fries.news.filter;

import com.fries.news.controller.ArticleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidDomainFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        int port = req.getRemotePort();

        if(isValidDomain(ipAddress,port)){
            filterChain.doFilter(req, res);
        } else {
            //TODO: need to give error to the front-end
//            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,res);
        }
    }

    private boolean isValidDomain(String ipAddress, int port){

        //TODO: TO LIMIT WHO CAN MAKE REQUESTS TO THE API
//        if(!ipAddress.equals("104.236.231.207")) {
//            LOGGER.error("This is an invalid or unrecognized IP address");
//            LOGGER.error("IP: {}", ipAddress);
//            LOGGER.error("PORT: {}", port);
//            return false;
//        }

        return true;
    }

}
