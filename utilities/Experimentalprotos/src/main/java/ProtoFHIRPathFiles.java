import com.google.fhir.r4.core.Patient;
import java.io.File;
import java.io.IOException;
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

public class ProtoFHIRPathFiles {

  private static FHIRPathEngine fp;
  private final Map<String, Resource> resources = new HashMap<>();

  public List<Base> evaluate(File protoTxt, String expressionString) throws IOException {
    String json = new JsonFormatBase().parseToJson(protoTxt, Patient.newBuilder());
    return processJSON(json, expressionString);
  }

  public List<Base> evaluate(String protoTxt, String expressionString) throws IOException {
    String json = new JsonFormatBase().parseToJson(protoTxt, Patient.newBuilder());
    return processJSON(json, expressionString);
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
