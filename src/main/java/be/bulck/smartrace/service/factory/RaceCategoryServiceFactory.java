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

package be.bulck.smartrace.service.factory;

import be.bulck.smartrace.service.RaceCategoryService;
import be.bulck.smartrace.service.ServiceManager;

/**
 * An abstract class representing a factory to retrieve the race category service.
 *
 * @author Fabien Vanden Bulck
 */
public abstract class RaceCategoryServiceFactory {

    /**
     * Gets the race category service.
     *
     * @return the race category service
     */
    public abstract RaceCategoryService getRaceCategoryService();

    /**
     * Gets the instance of this factory to retrieve the race category service.
     *
     * @return the instance of this factory to retrieve the race category service
     */
    public static RaceCategoryServiceFactory getInstance () {
        return (RaceCategoryServiceFactory) ServiceManager.getServiceByName("raceCategoryServiceFactory");
    }
}
