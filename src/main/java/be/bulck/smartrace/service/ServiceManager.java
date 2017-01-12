/*
 * Smart Race
 * Copyright (C) 2015-2017 Fabien Vanden Bulck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.bulck.smartrace.service;

import be.bulck.smartrace.util.spring.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A class to handle services.
 *
 * @author Fabien Vanden Bulck
 */
public class ServiceManager {

    /** The spring context. */
    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("/spring/services-configuration.xml", "/spring/dao-configuration.xml");
    }

    /**
     * Gets a service by name.
     *
     * @param serviceName the name of the service
     *
     * @return the service corresponding to the name provided
     */
    public static Object getServiceByName(String serviceName) {
        return SpringUtil.getBean(serviceName);
    }

}
