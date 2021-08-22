import com.google.fhir.r4.core.CompartmentDefinition;
import com.google.fhir.r4.core.ImplementationGuide.Definition;
import com.google.fhir.r4.core.MessageHeader;
import com.google.fhir.r4.core.Patient;
import com.google.fhir.shaded.protobuf.InvalidProtocolBufferException;
import com.google.fhir.shaded.protobuf.Message;
import com.google.fhir.shaded.protobuf.MessageOrBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hl7.fhir.exceptions.UcumException;
import org.hl7.fhir.r5.context.SimpleWorkerContext;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.model.Base;
import org.hl7.fhir.r5.model.ExpressionNode;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.utils.FHIRPathEngine;
import org.hl7.fhir.utilities.Utilities;

public class FHIRPathProtoEvaluator {

  private static FHIRPathEngine fp;
  private final Map<String, Resource> resources = new HashMap<>();

  public List<Base> evaluate(File protoTxt, String expressionString) throws IOException {
    String json = new JsonFormatBase().parseToJson(protoTxt, Patient.newBuilder());
    return processJSON(json, expressionString);
  }
  
  public List<Base> evaluateProtoTxtFileName(String fileName, String expressionString)
      throws IOException {
    File file = new File("android-fhir/" + fileName + ".prototxt");
    return evaluate(file, expressionString);
  }

  public List<Base> evaluate(String protoTxt, String expressionString) throws IOException {
    String json = new JsonFormatBase().parseToJson(protoTxt, Patient.newBuilder());
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

    fp = new FHIRPathEngine(new SimpleWorkerContext());

    String input = json;


    Resource res = null;


    List<Base> outcome = new ArrayList<Base>();

    ExpressionNode node = null;



    try {
      node = fp.parse(expression);
    } catch (Exception e) {
      System.out.println("Parsing Error");
    }

    if (node != null) {
      try {
        if (Utilities.noString(input)) {
          fp.check(null, null, node);
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
        outcome = fp.evaluate(res, node);
      } catch (Exception e) {
        System.out.println("Execution Error" + e.getMessage());
      }

    }

    System.out.println(outcome);

    return outcome;


  }

}
