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
 * A test class for the measure unit used for elevation.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceElevationUnitTest {

    private float elevation;

    @Before
    public void initialize() {
        Random random = new Random();
        elevation = Math.round(random.nextFloat());
    }

    @Test
    public void testComputeM() {
        float computedElevation = RaceElevationUnit.convert(RaceElevationUnit.M, elevation);
        Assert.assertEquals(elevation, computedElevation, 0f);
    }

    @Test
    public void testComputeFt() {
        float computedElevation = RaceElevationUnit.convert(RaceElevationUnit.FT, elevation);
        float ingestedElevation = RaceElevationUnit.ingest(RaceElevationUnit.FT, computedElevation);
        Assert.assertEquals(elevation, ingestedElevation, 0f);
    }
}
