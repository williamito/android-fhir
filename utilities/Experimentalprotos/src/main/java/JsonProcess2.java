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

public class JsonProcess2 {

  private static FHIRPathEngine fp;
  private final Map<String, Resource> resources = new HashMap<>();


  public List<Base> processJSON(String json, String expression)
      throws IOException, UcumException {

    fp = new FHIRPathEngine(new SimpleWorkerContext());

    String input = json;


    Resource res = null;

//    String expression = JSONUtil.parse(input)

    List<Base> outcome = new ArrayList<Base>();

    ExpressionNode node = null;

//    JsonObject jsonObject = JSONUtil.parse(json);
//
//    String propertyValue;
//
//
//    propertyValue = JSONUtil.str(jsonObject, expression);
//    String gender = JSONUtil.str(jsonObject, "gender");
//    String telecomValue = JSONUtil.str(jsonObject, "telecom", "value");
//    String name = JSONUtil.str(jsonObject, "name");



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

  public static void main(String[] args) throws IOException {
    System.out.println(new JsonProcess2().processJSON("{\n"
        + "  \"resourceType\": \"Patient\",\n"
        + "  \"id\": \"example\",\n"
        + "  \"address\": [\n"
        + "    {\n"
        + "      \"use\": \"home\",\n"
        + "      \"city\": \"PleasantVille\",\n"
        + "      \"type\": \"both\",\n"
        + "      \"state\": \"Vic\",\n"
        + "      \"line\": [\n"
        + "        \"534 Erewhon St\"\n"
        + "      ],\n"
        + "      \"postalCode\": \"3999\",\n"
        + "      \"period\": {\n"
        + "        \"start\": \"1974-12-25\"\n"
        + "      },\n"
        + "      \"district\": \"Rainbow\",\n"
        + "      \"text\": \"534 Erewhon St PeasantVille, Rainbow, Vic  3999\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"managingOrganization\": {\n"
        + "    \"reference\": \"Organization/1\"\n"
        + "  },\n"
        + "  \"name\": [\n"
        + "    {\n"
        + "      \"use\": \"official\",\n"
        + "      \"given\": [\n"
        + "        \"Peter\",\n"
        + "        \"James\"\n"
        + "      ],\n"
        + "      \"family\": \"Chalmers\"\n"
        + "    },\n"
        + "    {\n"
        + "      \"use\": \"usual\",\n"
        + "      \"given\": [\n"
        + "        \"Jim\"\n"
        + "      ]\n"
        + "    },\n"
        + "    {\n"
        + "      \"use\": \"maiden\",\n"
        + "      \"given\": [\n"
        + "        \"Peter\",\n"
        + "        \"James\"\n"
        + "      ],\n"
        + "      \"family\": \"Windsor\",\n"
        + "      \"period\": {\n"
        + "        \"end\": \"2002\"\n"
        + "      }\n"
        + "    }\n"
        + "  ],\n"
        + "  \"birthDate\": \"1974-12-25\",\n"
        + "  \"deceased\": {\n"
        + "    \"boolean\": false\n"
        + "  },\n"
        + "  \"active\": true,\n"
        + "  \"identifier\": [\n"
        + "    {\n"
        + "      \"use\": \"usual\",\n"
        + "      \"type\": {\n"
        + "        \"coding\": [\n"
        + "          {\n"
        + "            \"code\": \"MR\",\n"
        + "            \"system\": \"http://hl7.org/fhir/v2/0203\"\n"
        + "          }\n"
        + "        ]\n"
        + "      },\n"
        + "      \"value\": \"12345\",\n"
        + "      \"period\": {\n"
        + "        \"start\": \"2001-05-06\"\n"
        + "      },\n"
        + "      \"system\": \"urn:oid:1.2.36.146.595.217.0.1\",\n"
        + "      \"assigner\": {\n"
        + "        \"display\": \"Acme Healthcare\"\n"
        + "      }\n"
        + "    }\n"
        + "  ],\n"
        + "  \"telecom\": [\n"
        + "    {\n"
        + "      \"use\": \"home\"\n"
        + "    },\n"
        + "    {\n"
        + "      \"use\": \"work\",\n"
        + "      \"rank\": 1,\n"
        + "      \"value\": \"(03) 5555 6473\",\n"
        + "      \"system\": \"phone\"\n"
        + "    },\n"
        + "    {\n"
        + "      \"use\": \"mobile\",\n"
        + "      \"rank\": 2,\n"
        + "      \"value\": \"(03) 3410 5613\",\n"
        + "      \"system\": \"phone\"\n"
        + "    },\n"
        + "    {\n"
        + "      \"use\": \"old\",\n"
        + "      \"value\": \"(03) 5555 8834\",\n"
        + "      \"period\": {\n"
        + "        \"end\": \"2014\"\n"
        + "      },\n"
        + "      \"system\": \"phone\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"gender\": \"male\",\n"
        + "  \"contact\": [\n"
        + "    {\n"
        + "      \"name\": {\n"
        + "        \"given\": [\n"
        + "          \"Bénédicte\"\n"
        + "        ],\n"
        + "        \"family\": \"du Marché\",\n"
        + "        \"_family\": {\n"
        + "          \"extension\": [\n"
        + "            {\n"
        + "              \"url\": \"http://hl7.org/fhir/StructureDefinition/humanname-own-prefix\",\n"
        + "              \"valueString\": \"VV\"\n"
        + "            }\n"
        + "          ]\n"
        + "        }\n"
        + "      },\n"
        + "      \"gender\": \"female\",\n"
        + "      \"period\": {\n"
        + "        \"start\": \"2012\"\n"
        + "      },\n"
        + "      \"address\": {\n"
        + "        \"use\": \"home\",\n"
        + "        \"city\": \"PleasantVille\",\n"
        + "        \"line\": [\n"
        + "          \"534 Erewhon St\"\n"
        + "        ],\n"
        + "        \"type\": \"both\",\n"
        + "        \"state\": \"Vic\",\n"
        + "        \"period\": {\n"
        + "          \"start\": \"1974-12-25\"\n"
        + "        },\n"
        + "        \"district\": \"Rainbow\",\n"
        + "        \"postalCode\": \"3999\"\n"
        + "      },\n"
        + "      \"telecom\": [\n"
        + "        {\n"
        + "          \"value\": \"+33 (237) 998327\",\n"
        + "          \"system\": \"phone\"\n"
        + "        }\n"
        + "      ],\n"
        + "      \"relationship\": [\n"
        + "        {\n"
        + "          \"coding\": [\n"
        + "            {\n"
        + "              \"code\": \"N\",\n"
        + "              \"system\": \"http://hl7.org/fhir/v2/0131\"\n"
        + "            }\n"
        + "          ]\n"
        + "        }\n"
        + "      ]\n"
        + "    }\n"
        + "  ]\n"
        + "}", "Patient.name.children()")
    );

  }

}
