package com.fries.news.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidDomainFilter extends OncePerRequestFilter {

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
        //TODO: need to log this properly without System.out.println
//        System.out.println(ipAddress);
//        System.out.println(port);

        //TODO: will need to check for ip of digital ocean server with front-end on it
//        if(!ipAddress.equals("www.google.com")) {
//            System.out.println("This is not google.com");
//            return false;
//        }
//
        return true;
    }

}
