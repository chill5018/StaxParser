import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinhill on 10/29/15.
 */
public class Main {

    //StaxParser - Parse and read the document
    public static void StaxParser(String filename) throws Exception{
        List</*Insert Object*/> points = null;
        // Declare new Object
        Coordinate currPoint = new Coordinate();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(
                        ClassLoader.getSystemResourceAsStream(filename));

        //Scanning / Reading the document
        while(reader.hasNext()){
            int event = reader.next();

            switch(event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("trkpt".equals(reader.getLocalName())) {
                        currPoint = new Coordinate(0.0, 0.0);
                        currPoint.setLat(Double.parseDouble(reader.getAttributeValue(null, "lat")));
                        currPoint.setLon(Double.parseDouble(reader.getAttributeValue(null, "lon")));
                    }
                    if ("trkseg".equals(reader.getLocalName())) {
                        points = new ArrayList<>();

                    }
                    break;

                //Add current Point to the Points ArrayList
                case XMLStreamConstants.END_ELEMENT:
                    switch(reader.getLocalName()){
                        case "trkpt":
                            points.add(currPoint);
                            break;
                    }
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    points = new ArrayList<>();
                    break;
            }
        }

        //  Prints the Point list populated from XML
        //   I know it works so that is why it is now commented out
        for ( Coordinate c : points){
            System.out.println(c);
        }


        //Convert the Array List in to an Array
        Coordinate[] pArray = new Coordinate[points.size()];
        pArray = points.toArray(pArray);

    }
    //end of STaxParser
}
