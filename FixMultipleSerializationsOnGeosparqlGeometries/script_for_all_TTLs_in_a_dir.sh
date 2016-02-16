#!/bin/bash
for dir in $(ls $1/*.ttl);
do
	echo $dir
	java -Xmx2048m -Xms1024m -jar FixMultipleSerializationsOnGeosparqlGeometries-0.0.1-jar-with-dependencies.jar $dir
done
