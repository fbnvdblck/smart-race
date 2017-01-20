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

package be.bulck.smartrace.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * A test class for the measure unit used for the distance.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceDistanceUnitTest {

    private float distance;

    @Before
    public void initialize() {
        Random random = new Random();
        distance = Math.round(random.nextFloat());
    }

    @Test
    public void testComputeKm() {
        float computedDistance = RaceDistanceUnit.convert(RaceDistanceUnit.KM, distance);
        Assert.assertEquals(distance, computedDistance, 0f);
    }

    @Test
    public void testComputeMi() {
        float computedDistance = RaceDistanceUnit.convert(RaceDistanceUnit.MI, distance);
        float ingestedDistance = RaceDistanceUnit.ingest(RaceDistanceUnit.MI, computedDistance);
        Assert.assertEquals(distance, ingestedDistance, 0f);
    }
}
