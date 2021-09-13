/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author PC
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            HibernateConfig.class,
            TilesConfig.class,
//            SpringSecurityConfig.class
        };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
            return new Class[] {
                WebApplicationContextConfig.class
            };
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
