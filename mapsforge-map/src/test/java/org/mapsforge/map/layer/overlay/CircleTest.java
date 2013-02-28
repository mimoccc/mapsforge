/*
 * Copyright 2010, 2011, 2012 mapsforge.org
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.mapsforge.map.layer.overlay;

import org.junit.Assert;
import org.junit.Test;
import org.mapsforge.core.graphics.AwtGraphicFactory;
import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.GeoPoint;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Tile;

public class CircleTest {
	private static final GraphicFactory GRAPHIC_FACTORY = AwtGraphicFactory.INSTANCE;

	private static Circle createCircle(GeoPoint geoPoint, float radius, Paint paintFill, Paint paintStroke) {
		return new Circle(geoPoint, radius, paintFill, paintStroke);
	}

	private static void verifyInvalidRadius(GeoPoint geoPoint, float radius, Paint paintFill, Paint paintStroke) {
		try {
			createCircle(geoPoint, radius, paintFill, paintStroke);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void constructorTest() {
		GeoPoint geoPoint = new GeoPoint(0, 0);
		int radius = 3;
		Paint paintFill = GRAPHIC_FACTORY.createPaint();
		Paint paintStroke = GRAPHIC_FACTORY.createPaint();

		Circle circle = new Circle(geoPoint, radius, paintFill, paintStroke);
		Assert.assertEquals(geoPoint, circle.getGeoPoint());
		Assert.assertEquals(radius, circle.getRadius(), 0);
		Assert.assertEquals(paintFill, circle.getPaintFill());
		Assert.assertEquals(paintStroke, circle.getPaintStroke());

		verifyInvalidRadius(geoPoint, -1, paintFill, paintStroke);
		verifyInvalidRadius(geoPoint, Float.NaN, paintFill, paintStroke);
	}

	@Test
	public void drawTest() {
		Circle circle = new Circle(null, 0, null, null);

		BoundingBox boundingBox = new BoundingBox(-1, -1, 1, 1);
		Canvas canvas = GRAPHIC_FACTORY.createCanvas();
		canvas.setBitmap(GRAPHIC_FACTORY.createBitmap(Tile.TILE_SIZE, Tile.TILE_SIZE));
		Point point = new Point(0, 0);
		circle.draw(boundingBox, (byte) 0, canvas, point);

		circle.setGeoPoint(new GeoPoint(0, 0));
		circle.draw(boundingBox, (byte) 0, canvas, point);

		circle.setRadius(1);
		circle.draw(boundingBox, (byte) 0, canvas, point);

		circle.setPaintFill(GRAPHIC_FACTORY.createPaint());
		circle.draw(boundingBox, (byte) 0, canvas, point);

		circle.setPaintStroke(GRAPHIC_FACTORY.createPaint());
		circle.draw(boundingBox, (byte) 0, canvas, point);
	}

	@Test
	public void setterTest() {
		GeoPoint geoPoint = new GeoPoint(1, 2);
		Paint paintFill = GRAPHIC_FACTORY.createPaint();
		Paint paintStroke = GRAPHIC_FACTORY.createPaint();

		Circle circle = new Circle(null, 0, null, null);
		Assert.assertNull(circle.getGeoPoint());
		Assert.assertEquals(0, circle.getRadius(), 0);
		Assert.assertNull(circle.getPaintFill());
		Assert.assertNull(circle.getPaintStroke());

		circle.setGeoPoint(geoPoint);
		Assert.assertEquals(geoPoint, circle.getGeoPoint());

		circle.setRadius(1);
		Assert.assertEquals(1, circle.getRadius(), 0);

		circle.setPaintFill(paintFill);
		Assert.assertEquals(paintFill, circle.getPaintFill());

		circle.setPaintStroke(paintStroke);
		Assert.assertEquals(paintStroke, circle.getPaintStroke());
	}
}