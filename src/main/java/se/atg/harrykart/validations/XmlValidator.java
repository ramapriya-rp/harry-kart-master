package se.atg.harrykart.validations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.io.StringWriter;


@Slf4j
@Component
public class XmlValidator implements Validator {

    private Schema schema;
    
    @Value("${input.xsdFile}")
    private String xsdFile;
    
    @Value("${schema.language}")
    private String schemaLang;

	
    @PostConstruct
    public void initialize() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLang);
        schema = schemaFactory.newSchema(getClass().getResource(xsdFile));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(se.atg.harrykart.model.HarryKartDO.class);
    }

    @Override
    public void validate(Object target, Errors errors)  {
    	try {
    		log.info("playHarryKart :: Validating the input xml against the xsd");
            JAXBContext jaxbContext = JAXBContext.newInstance(se.atg.harrykart.model.HarryKartDO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);

         // Cast the target object to the appropriate class
            se.atg.harrykart.model.HarryKartDO harryKartDO = (se.atg.harrykart.model.HarryKartDO) target;

            // Marshal the object to XML and validate
            StringWriter sw = new StringWriter();
            jaxbContext.createMarshaller().marshal(harryKartDO, sw);
            String xmlString = sw.toString();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            unmarshaller.unmarshal(inputStream);
    		
    	}catch(Exception e) {
    		log.error("playHarryKart :: validating input xml against xsd FAILS!!!");
            throw new IllegalStateException("xml Validation against xsd failed", e);
        }
       
        }
    
}
