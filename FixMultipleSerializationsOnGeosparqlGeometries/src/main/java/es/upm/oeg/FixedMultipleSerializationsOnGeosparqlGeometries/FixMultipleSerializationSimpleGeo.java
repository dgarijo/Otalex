package es.upm.oeg.FixedMultipleSerializationsOnGeosparqlGeometries;

import java.util.ArrayList;
import java.util.List;

public class FixMultipleSerializationSimpleGeo {
	private static enum WKTTypes {
		GEOMETRYCOLLECTION, MULTIPOLYGON, MULTIPOINT, MULTILINESTRING, LINESTRING, POLYGON, POINT
		// The program need this order if you change program don't work
	}

	private static enum SimpleWKTTypes {
		LINESTRING, POLYGON, POINT
	}

	public static String getSerializationOfWKTGeometries(
			List<String> wktGeometries) {
		List<String> cleanGeometries = cleanWKTGeometries(wktGeometries);
		SimpleWKTTypes type = detectSameSimpleWKTType(cleanGeometries);
		StringBuffer toReturn = new StringBuffer();
		if (type != null) {
			toReturn.append(getCollectionStringOfType(type) + "(");
			for (String wkt : cleanGeometries) {
				BalancedResponse aux = getBalancedWKT(wkt);
				if (aux.isBalanced) {
					toReturn.append("(");
					toReturn.append(aux.internResult);
					toReturn.append("),");
				}
			}
			if (toReturn.charAt(toReturn.length() - 1) == ',') {
				toReturn.deleteCharAt(toReturn.length()-1);
			}
			toReturn.append(")");
		} else {
			toReturn.append("GeometryCollection(");
			for (String wkt : cleanGeometries) {
				toReturn.append(wkt);
				toReturn.append(",");
			}
			if (toReturn.charAt(toReturn.length() - 1) == ',') {
				toReturn.deleteCharAt(toReturn.length()-1);
			}
			toReturn.append(")");
		}
		return toReturn.toString();
	}
	
	public static String getRdfTypeOfWKT(String wkt){
		switch (detectWKTType(wkt)) {
		case GEOMETRYCOLLECTION:
			return "http://www.opengis.net/ont/sf#GeometryCollection";
		case LINESTRING:
			return "http://www.opengis.net/ont/sf#LineString";
		case POLYGON:
			return "http://www.opengis.net/ont/sf#Polygon";
		case POINT:
			return "http://www.opengis.net/ont/sf#Point";
		case MULTILINESTRING:
			return "http://www.opengis.net/ont/sf#MultiLineString";
		case MULTIPOLYGON:
			return "http://www.opengis.net/ont/sf#MultiPolygon";
		case MULTIPOINT:
			return "http://www.opengis.net/ont/sf#MultiPoint";
		default:
			return null;
		}
	}

	private static String getCollectionStringOfType(SimpleWKTTypes type) {
		switch (type) {
		case POLYGON:
			return "MultiPolygon";
		case LINESTRING:
			return "MultiLineString";
		case POINT:
			return "MultiPoint";
		}
		// Make compiler happy
		return null;
	}

	private static List<String> cleanWKTGeometries(List<String> wktGeometries) {
		List<String> cleanGeometries = new ArrayList<String>();
		for (String toClean : wktGeometries) {
			BalancedResponse cleanResponse = getBalancedWKT(toClean);
			if (cleanResponse.isBalanced) {
				String cleanWKT = cleanResponse.result;
				if (isGeometryCollection(cleanWKT)) {
					for (String toAdd : getGeometriesOfGeometryCollection(cleanResponse.internResult)) {
						cleanGeometries.add(toAdd);
					}
				} else {
					cleanGeometries.add(cleanWKT);
				}
			}
		}
		return cleanGeometries;
	}

	private static boolean isGeometryCollection(String wkt) {
		int firtsIndex = -1;
		WKTTypes type = null;
		for (WKTTypes i : WKTTypes.values()) {
			int index = wkt.toLowerCase().indexOf(i.toString().toLowerCase());
			if (index >= 0 && (index < firtsIndex || firtsIndex == -1)) {
				firtsIndex = index;
				type = i;
			}
		}
		return type == WKTTypes.GEOMETRYCOLLECTION;
	}

	private static SimpleWKTTypes detectSameSimpleWKTType(
			List<String> wktGeometries) {
		SimpleWKTTypes globalType = null;
		for (String toDetect : wktGeometries) {
			int firtsIndex = -1;
			SimpleWKTTypes type = null;
			for (SimpleWKTTypes i : SimpleWKTTypes.values()) {
				int index = toDetect.toLowerCase().indexOf(
						i.toString().toLowerCase());
				if (index >= 0 && (index < firtsIndex || firtsIndex == -1)) {
					firtsIndex = index;
					type = i;
				}
			}
			if (globalType != null && globalType != type) {
				return null;
			}
			globalType = type;
		}
		return globalType;
	}
	
	private static WKTTypes detectWKTType(String wkt) {
		WKTTypes type = null;
		String wktLowercase = wkt.toLowerCase();
		for (WKTTypes toDetect : WKTTypes.values()) {
			if(wktLowercase.contains(toDetect.name().toLowerCase())){
				type = toDetect;
				break;
			}
		}
		return type;
	}

	private static List<String> getGeometriesOfGeometryCollection(
			String geometry) {
		String[] separateGeometries = geometry.split("\\),");
		List<String> toReturn = new ArrayList<String>();
		for (int i = 0; i < separateGeometries.length; i++) {
			String sepGeometry = separateGeometries[i];
			if (i < separateGeometries.length - 1) {
				sepGeometry += ")";
			}
			toReturn.add(sepGeometry);
		}
		return toReturn;
	}

	private static class BalancedResponse {
		public String result;
		public String internResult;
		public boolean isBalanced;
		public int firtsParenthesis;

		public BalancedResponse(String result) {
			this.result = result;
			this.isBalanced = true;
			if (result == null || result.isEmpty()) {
				this.isBalanced = false;
				this.result = "";
			}
			this.firtsParenthesis = result.indexOf('(');
			this.internResult = result.substring(firtsParenthesis + 1,
					result.length() - 1);
		}
	}

	private static String removeWhiteSpaces(String in) {
		String toReturn = "";
		toReturn = in.replaceAll("( )+", " ").trim();
		toReturn = toReturn.replaceAll(" \\(", "(");
		toReturn = toReturn.replaceAll("\\( ", "(");
		toReturn = toReturn.replaceAll(" \\)", ")");
		toReturn = toReturn.replaceAll("\\) ", ")");
		toReturn = toReturn.replaceAll(" ,", ",");
		toReturn = toReturn.replaceAll(", ", ",");
		return toReturn;
	}

	private static BalancedResponse getBalancedWKT(String wktIN) {
		try {
			String realWKTText = "";
			String wkt = removeWhiteSpaces(wktIN);
			int firtsIndex = -1;
			for (WKTTypes i : WKTTypes.values()) {
				int index = wkt.toLowerCase().indexOf(
						i.toString().toLowerCase());
				if (index >= 0 && (index < firtsIndex || firtsIndex == -1)) {
					firtsIndex = index;
				}
			}
			if (firtsIndex == -1) {
				return new BalancedResponse(null);
			}
			realWKTText = wkt.substring(firtsIndex, wkt.length());
			int count = 1;
			int lastIndex = -1;
			int searchIndex = realWKTText.indexOf("(");
			if (searchIndex == -1 || searchIndex == realWKTText.length() - 1) {
				return new BalancedResponse(null);
			}
			for (int i = searchIndex + 1; i < realWKTText.length(); i++) {
				if (realWKTText.charAt(i) == '(') {
					count++;
				}
				if (realWKTText.charAt(i) == ')') {
					count--;
				}
				if (count == 0) {
					lastIndex = i + 1;
					break;
				}
			}
			if (lastIndex == -1) {
				return new BalancedResponse("");
			}
			return new BalancedResponse(realWKTText.substring(0, lastIndex));
		} catch (Exception e) {
			return new BalancedResponse("");
		}
	}
}
