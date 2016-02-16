package es.upm.oeg.FixedMultipleSerializationsOnGeosparqlGeometries;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import es.upm.oeg.FixedMultipleSerializationsOnGeosparqlGeometries.FixMultipleSerializationSimpleGeo;;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	List<String> wktGeometries = new ArrayList<String>();

		// TEST 1 Test with Polygons
		String toTest = "  sss  POLYGON((2        3)    ,  (   4 5      ),   (6 7))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "POLYGON((2 3,5 6))";
		wktGeometries.add(toTest);
		toTest = "  sss  GeometryCollection(Polygon((2 4,   3 4   )),Polygon((9 10,   7 8   )))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		Assert.assertEquals("Test number 1 fail",FixMultipleSerializationSimpleGeo.getSerializationOfWKTGeometries(wktGeometries),"MultiPolygon(((2 3),(4 5),(6 7)),((2 3,5 6)),((2 4,3 4)),((9 10,7 8)))");
		wktGeometries.clear();

		// TEST 2 Test with LineString
		toTest = "  sss  LiNeStRinG(2        3    ,     4 5      ,   6 7)()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "LineString(2 3,5 6)";
		wktGeometries.add(toTest);
		toTest = "  sss  GeometryCollection(LineSTRING(2 4,   3 4   ),LineSTRING(10 11,   76 8   ))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		Assert.assertEquals("Test number 2 fail",FixMultipleSerializationSimpleGeo.getSerializationOfWKTGeometries(wktGeometries),"MultiLineString((2 3,4 5,6 7),(2 3,5 6),(2 4,3 4),(10 11,76 8))");
		wktGeometries.clear();

		// TEST 3 Test with Points
		toTest = "  sss  POinT(2        3    ,     4 5      ,   6 7)()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "Point(2 20)";
		wktGeometries.add(toTest);
		toTest = "  sss  GeometryCollection(Point(2 4),POINT(10 9))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		Assert.assertEquals("Test number 3 fail",FixMultipleSerializationSimpleGeo.getSerializationOfWKTGeometries(wktGeometries),"MultiPoint((2 3,4 5,6 7),(2 20),(2 4),(10 9))");
		wktGeometries.clear();

		// TEST 4 Test with collection of different geometries
		toTest = "  sss  POLYGON((2        3)    ,  (   4 5      ),   (6 7))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "  sss  LiNeStRinG(2        3    ,     4 5      ,   6 7)()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "  sss  POinT(2        3    ,     4 5      ,   6 7)()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "  sss MultiPolygon( ( (   2    3  ),   (4 5),   (6 7)),((2 3,5 6)),((2 4,3 4)),((9 10,7 8)))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "  sss  MultiPoint((  2    3   ,4 5   ,   6 7),(2 20),(2 4),(10 9))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "  sss  MultiLineString(  (2 3  ,4   5 ,6   7)  ,(2 3,5 6),(2 4,3 4),(10 11,76 8))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		toTest = "  sss  GeometryCollection(Polygon((2 4,   3 4   )),LineString(8989 848, 77 65), POint(54 4))()(( ^^WKTLiteral";
		wktGeometries.add(toTest);
		Assert.assertEquals("Test number 2 fail",FixMultipleSerializationSimpleGeo.getSerializationOfWKTGeometries(wktGeometries),"GeometryCollection(POLYGON((2 3),(4 5),(6 7)),LiNeStRinG(2 3,4 5,6 7),POinT(2 3,4 5,6 7),MultiPolygon(((2 3),(4 5),(6 7)),((2 3,5 6)),((2 4,3 4)),((9 10,7 8))),MultiPoint((2 3,4 5,6 7),(2 20),(2 4),(10 9)),MultiLineString((2 3,4 5,6 7),(2 3,5 6),(2 4,3 4),(10 11,76 8)),Polygon((2 4,3 4)),LineString(8989 848,77 65),POint(54 4))");
		wktGeometries.clear();
    }
}
