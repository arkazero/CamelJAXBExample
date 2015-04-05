package tudor.camel.example.util;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

/**
 * 
 * @author tudor
 *	Multiple constants that are needed for this application
 */
public class RouteConstants {

	// camel endpoints
	public static final String CREATE_XML_FROM_OBJECT = "direct:object.to-xml";
	public static final String CREATE_OBJECT_FROM_XML = "direct:xml.to.object";
	public static final String STORING_XML_FILE = "file:target?fileName=book.xml&noop=true";
	
	// jaxb data format
	public static final DataFormat JAXB_DATA_FORMAT = new JaxbDataFormat("tudor.camel.example.domain");
}
