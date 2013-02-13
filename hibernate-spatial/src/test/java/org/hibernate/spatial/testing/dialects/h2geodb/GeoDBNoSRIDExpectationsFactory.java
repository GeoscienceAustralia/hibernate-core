/*
 * This file is part of Hibernate Spatial, an extension to the
 *  hibernate ORM solution for spatial (geographic) data.
 *
 *  Copyright © 2007-2012 Geovise BVBA
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/**
 *
 */
package org.hibernate.spatial.testing.dialects.h2geodb;

import org.geolatte.geom.ByteBuffer;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkb;

import org.hibernate.spatial.testing.AbstractExpectationsFactory;
import org.hibernate.spatial.testing.NativeSQLStatement;

/**
 * A Factory class that generates expected {@link org.hibernate.spatial.testing.NativeSQLStatement}s for GeoDB
 * version < 0.4. These versions don't support storage of the SRID value with
 * the geometry.
 *
 * @author Jan Boonen, Geodan IT b.v.
 */
public class GeoDBNoSRIDExpectationsFactory extends AbstractExpectationsFactory {

	public GeoDBNoSRIDExpectationsFactory(GeoDBDataSourceUtils dataSourceUtils) {
		super( dataSourceUtils );
	}

	@Override
	protected NativeSQLStatement createNativeAsBinaryStatement() {
		return createNativeSQLStatement( "select id, ST_AsEWKB(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeAsTextStatement() {
		return createNativeSQLStatement( "select id, ST_AsText(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeBoundaryStatement() {
		throw new UnsupportedOperationException(
				"Method ST_Bounday() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeBufferStatement(Double distance) {
		return createNativeSQLStatement(
				"select t.id, ST_Buffer(t.geom,?) from GEOMTEST t where ST_SRID(t.geom) = 4326",
				new Object[] { distance }
		);
	}

	@Override
	protected NativeSQLStatement createNativeContainsStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Contains(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Contains(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeConvexHullStatement(Geometry geom) {
		throw new UnsupportedOperationException(
				"Method ST_ConvexHull() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeCrossesStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Crosses(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Crosses(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeDifferenceStatement(Geometry geom) {
		throw new UnsupportedOperationException(
				"Method ST_Difference() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeDimensionSQL() {
		throw new UnsupportedOperationException(
				"Method ST_Dimension() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeDisjointStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Disjoint(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Disjoint(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeTransformStatement(int epsg) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected NativeSQLStatement createNativeHavingSRIDStatement(int srid) {
		return createNativeSQLStatement( "select t.id, (st_srid(t.geom) = " + srid + ") from GeomTest t where ST_SRID(t.geom) =  " + srid );
	}

	@Override
	protected NativeSQLStatement createNativeDistanceStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, st_distance(t.geom, ST_GeomFromText(?, 4326)) from GeomTest t where ST_SRID(t.geom) = 4326",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeEnvelopeStatement() {
		return createNativeSQLStatement( "select id, ST_Envelope(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeEqualsStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Equals(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Equals(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeFilterStatement(Geometry geom) {
		throw new UnsupportedOperationException(
				"Method ST_MBRIntersects() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeGeomUnionStatement(Geometry geom) {
		throw new UnsupportedOperationException(
				"Method ST_GeomUnion() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeGeometryTypeStatement() {
		return createNativeSQLStatement( "select id, GeometryType(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeIntersectionStatement(Geometry geom) {
		throw new UnsupportedOperationException(
				"Method ST_Intersection() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeIntersectsStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Intersects(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Intersects(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeIsEmptyStatement() {
		return createNativeSQLStatement( "select id, ST_IsEmpty(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeIsNotEmptyStatement() {
		return createNativeSQLStatement( "select id, not ST_IsEmpty(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeIsSimpleStatement() {
		return createNativeSQLStatement( "select id, ST_IsSimple(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeOverlapsStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Overlaps(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Overlaps(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeRelateStatement(Geometry geom,
															 String matrix) {
		throw new UnsupportedOperationException(
				"Method ST_Relate() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeDwithinStatement(Point geom, double distance) {
		String sql = "select t.id, st_dwithin(t.geom, ST_GeomFromText(?, 4326), " + distance + " ) from GeomTest t where st_dwithin(t.geom, ST_GeomFromText(?, 4326), " + distance + ") = 'true' and ST_SRID(t.geom) = 4326";
		return createNativeSQLStatementAllWKTParams( sql, geom.asText() );
	}

	/*
		  * (non-Javadoc)
		  *
		  * @seeorg.hibernatespatial.test.AbstractExpectationsFactory#
		  * createNativeSridStatement()
		  */

	@Override
	protected NativeSQLStatement createNativeSridStatement() {
		return createNativeSQLStatement( "select id, ST_SRID(geom) from GEOMTEST" );
	}

	@Override
	protected NativeSQLStatement createNativeSymDifferenceStatement(
			Geometry geom) {
		throw new UnsupportedOperationException(
				"Method ST_SymDifference() is not implemented in the current version of GeoDB."
		);
	}

	@Override
	protected NativeSQLStatement createNativeTouchesStatement(Geometry geom) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Touches(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Touches(t.geom, ST_GeomFromText(?, 4326)) = 1",
				geom.asText()
		);
	}

	@Override
	protected NativeSQLStatement createNativeWithinStatement(
			Geometry testPolygon) {
		return createNativeSQLStatementAllWKTParams(
				"select t.id, ST_Within(t.geom, ST_GeomFromText(?, 4326)) from GEOMTEST t where ST_Within(t.geom, ST_GeomFromText(?, 4326)) = 1 and ST_SRID(t.geom) = 4326",
				testPolygon.asText()
		);
	}

	@Override
	protected Geometry decode(Object object) {
		return Wkb.fromWkb( ByteBuffer.from( (byte[]) object ) );
	}
}
