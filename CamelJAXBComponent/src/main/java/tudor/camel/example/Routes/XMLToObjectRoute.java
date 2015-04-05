package tudor.camel.example.Routes;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import tudor.camel.example.util.RouteConstants;

/**
 * 
 * @author tudor
 *	This route is used for reading xml files and changing them into Java POJO's 
 *	the unmarshalling is done with JAXB DataFormat
 */
public class XMLToObjectRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from(RouteConstants.CREATE_OBJECT_FROM_XML)
		.process(new Processor() {
			// this processor is used for reading the xml file and sotring that info on the exchange body
			@Override
			public void process(Exchange exchange) throws Exception {
				// a consumer is used for reading the file content
				ConsumerTemplate consumerTemplate = exchange.getContext().createConsumerTemplate();
				Object receiveBody = consumerTemplate.receiveBody(RouteConstants.STORING_XML_FILE);
				
				exchange.getIn().setBody(receiveBody);
				
			}
		})
		.unmarshal(RouteConstants.JAXB_DATA_FORMAT);
	}

}
