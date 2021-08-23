package com.google.fhirpathproto;

import com.google.fhir.r4.core.MessageHeader;
import com.google.fhir.shaded.protobuf.Message;
import com.google.fhir.shaded.protobuf.MessageOrBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hl7.fhir.exceptions.UcumException;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.r4.formats.JsonParser;

import org.hl7.fhir.r4.model.ExpressionNode;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.utils.FHIRPathEngine;
import org.hl7.fhir.utilities.Utilities;

public class FHIRPathProtoEvaluator {

  private static FHIRPathEngine fhirPathEngine;
  private final Map<String, Resource> resources = new HashMap<>();

  public <T extends Message.Builder> List<Base> 
  evaluate(File protoTxt, String expressionString, T builder) throws IOException {
    String json = new JsonFormatBase().parseToJson(protoTxt, builder);
    return processJSON(json, expressionString);
  }
  
  public <T extends Message.Builder> List<Base> 
  evaluateProtoTxtFileName(String fileName, String expressionString, T builder) throws IOException {
    File file = new File("android-fhir/" + fileName + ".prototxt");
    return evaluate(file, expressionString, builder);
  }

  public <T extends Message.Builder> List<Base> 
  evaluate(String protoTxt, String expressionString, T builder) throws IOException {
    String json = new JsonFormatBase().parseToJson(protoTxt, builder);
    return processJSON(json, expressionString);
  }

  public List<Base> evaluateBinary(File protoBinary, String expressionString) throws IOException {

    InputStream binaryInputStream = new FileInputStream(protoBinary);

    MessageHeader resource = MessageHeader.parseFrom(binaryInputStream);
    
    JsonFormatBase jsonFormatBase = new JsonFormatBase();

    String json = jsonFormatBase.parseToJson(resource);
    
    List<Base> result = processJSON(json, expressionString);

    return result;
  }
  
  public <T extends MessageOrBuilder> List<Base> evaluateBinaryResource(File protoBinary, String expressionString, 
      T messageOrBuilder) throws IOException {
    
    InputStream binaryInputStream = new FileInputStream(protoBinary);
    
    var resource = messageOrBuilder.
        getDefaultInstanceForType().getParserForType().parseFrom(binaryInputStream);
    
    String json = new JsonFormatBase().parseToJson(resource);
    
    List<Base> result = processJSON(json, expressionString);
    
    return result;
  }
  


  public List<Base> processJSON(String json, String expression)
      throws IOException, UcumException {
    
    SimpleWorkerContext simpleWorkerContext = new SimpleWorkerContext();

    fhirPathEngine = new FHIRPathEngine(simpleWorkerContext);

    String input = json;


    Resource res = null;


    List<Base> outcome = new ArrayList<Base>();

    ExpressionNode node = null;



    try {
      node = fhirPathEngine.parse(expression);
    } catch (Exception e) {
      System.out.println("Parsing Error");
    }

    if (node != null) {
      try {
        if (Utilities.noString(input)) {
          fhirPathEngine.check(null, null, node);
        } else {
          res = resources.get(input);
          if (res == null) {
            res = new JsonParser().parse(input);
            resources.put(input, res);
          }
        }
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }

    if (node != null) {
      try {
        outcome = fhirPathEngine.evaluate(res, node);
      } catch (Exception e) {
        System.out.println("Execution Error" + e.getMessage());
      }

    }

    System.out.println(outcome);

    return outcome;


  }

}
