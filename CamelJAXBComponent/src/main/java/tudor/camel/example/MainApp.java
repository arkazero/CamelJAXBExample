package tudor.camel.example;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tudor.camel.example.Routes.ObjectToXMLRoute;
import tudor.camel.example.Routes.XMLToObjectRoute;
import tudor.camel.example.domain.Book;
import tudor.camel.example.util.RouteConstants;

/**
 * 
 * @author tudor 
 * 		   simple application for demonstrating the power of apache camel
 *         and JAXB
 */
public class MainApp {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

	/**
	 * 
	 * @param args
	 * @throws Exception
	 *             this mian method starts a camelContext and writes a book
	 *             Object to an XML file and after it reads it back from the xml
	 *             file
	 */
	public static void main(String... args) throws Exception {
		DefaultCamelContext defaultCamelContext = new DefaultCamelContext();
		// adding the routes to the camel context is done one by one
		defaultCamelContext.addRoutes(new ObjectToXMLRoute());
		defaultCamelContext.addRoutes(new XMLToObjectRoute());
		defaultCamelContext.start();

		Book book = new Book();
		book.setName("testName");
		book.setAuthor("testAuthor");
		book.setPublishYear("2000");
		LOGGER.info(book.toString());

		// this endpoint is the beginning for writting the xml file
		ProducerTemplate producerTemplate = defaultCamelContext.createProducerTemplate();
		producerTemplate.sendBody(RouteConstants.CREATE_XML_FROM_OBJECT, book);

		// this endpoint is the beginning for reading from the xml file
		Book returnedBook = (Book) producerTemplate.requestBody(RouteConstants.CREATE_OBJECT_FROM_XML, "");
		LOGGER.info(returnedBook.toString());

		defaultCamelContext.shutdown();
	}

}
