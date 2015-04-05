package tudor.camel.example.Routes;

import org.apache.camel.builder.RouteBuilder;

import tudor.camel.example.util.RouteConstants;

/**
 * 
 * @author tudor
 *	this route is used for storing Java POJO's into xml format in xml files
 *	the masrhalling is done with JAXB DataFormat  
 */
public class ObjectToXMLRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from(RouteConstants.CREATE_XML_FROM_OBJECT)
		.marshal(RouteConstants.JAXB_DATA_FORMAT)
		.to(RouteConstants.STORING_XML_FILE);
		
	}

}
