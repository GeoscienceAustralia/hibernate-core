/*
 * This file is part of Hibernate Spatial, an extension to the
 * hibernate ORM solution for spatial (geographic) data.
 *
 * Copyright © 2007-2013 Geovise BVBA
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.hibernate.spatial;

/**
 * The spatial analysis functions defined in the OGC SFS specification.
 *
 * @author Karel Maesen
 */
public interface SpatialAnalysis {

	/**
	 * The distance function
	 */
	public static int DISTANCE = 1;

	/**
	 * The buffer function
	 */
	public static int BUFFER = 2;

	/**
	 * The convexhull function
	 */
	public static int CONVEXHULL = 3;

	/**
	 * The intersection function
	 */
	public static int INTERSECTION = 4;

	/**
	 * The union function
	 */
	public static int UNION = 5;

	/**
	 * The difference function
	 */
	public static int DIFFERENCE = 6;

	/**
	 * The symmetric difference function
	 */
	public static int SYMDIFFERENCE = 7;

}
