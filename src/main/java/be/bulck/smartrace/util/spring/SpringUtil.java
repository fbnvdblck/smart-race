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

package be.bulck.smartrace.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A class representing a Spring utility.
 *
 * @author Fabien Vanden bulck
 */
public class SpringUtil {

    /** The Spring context. */
    private static final ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    }

    /**
     * Gets the Spring bean bases on the name.
     *
     * @param beanName the name of bean
     *
     * @return the bean corresponding to the name provided
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
