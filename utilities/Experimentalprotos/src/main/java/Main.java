import com.google.fhir.common.InvalidFhirException;
import com.google.fhir.r4.core.Patient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, InvalidFhirException {
//    new JsonFormatGenerate()
//        .generateProtoTxt(new String[] {"label"}, null);

    File filename = new File("/android-fhir/filename.prototxt").getAbsoluteFile();

//    String parsedFilename = new JsonFormatBase().parseToJson(filename, Patient.newBuilder());
//
//    System.out.println(new FHIRPathProtoEvaluator().processJSON(parsedFilename, "name.count()"));
    File newFile2 = new File("/android-fhir/testbinary.proto");

    Patient newPatient = Patient.parseFrom(new FileInputStream(newFile2));

    String firstPatient = new JsonFormatBase().parseToJson(newPatient);

    System.out.println(new FHIRPathProtoEvaluator().processJSON(firstPatient,
        "name.given"));
    
    
    String JsonPractitioner = "{\n"
        + "  \"resourceType\": \"Practitioner\",\n"
        + "  \"id\": \"example\",\n"
        + "  \"text\": {\n"
        + "    \"status\": \"generated\",\n"
        + "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\">\\n      <p>Dr Adam Careful is a Referring Practitioner for Acme Hospital from 1-Jan 2012 to 31-Mar\\n        2012</p>\\n    </div>\"\n"
        + "  },\n"
        + "  \"identifier\": [\n"
        + "    {\n"
        + "      \"system\": \"http://www.acme.org/practitioners\",\n"
        + "      \"value\": \"23\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"active\": true,\n"
        + "  \"name\": [\n"
        + "    {\n"
        + "      \"family\": \"Careful\",\n"
        + "      \"given\": [\n"
        + "        \"Adam\"\n"
        + "      ],\n"
        + "      \"prefix\": [\n"
        + "        \"Dr\"\n"
        + "      ]\n"
        + "    }\n"
        + "  ],\n"
        + "  \"address\": [\n"
        + "    {\n"
        + "      \"use\": \"home\",\n"
        + "      \"line\": [\n"
        + "        \"534 Erewhon St\"\n"
        + "      ],\n"
        + "      \"city\": \"PleasantVille\",\n"
        + "      \"state\": \"Vic\",\n"
        + "      \"postalCode\": \"3999\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"qualification\": [\n"
        + "    {\n"
        + "      \"identifier\": [\n"
        + "        {\n"
        + "          \"system\": \"http://example.org/UniversityIdentifier\",\n"
        + "          \"value\": \"12345\"\n"
        + "        }\n"
        + "      ],\n"
        + "      \"code\": {\n"
        + "        \"coding\": [\n"
        + "          {\n"
        + "            \"system\": \"http://terminology.hl7.org/CodeSystem/v2-0360/2.7\",\n"
        + "            \"code\": \"BS\",\n"
        + "            \"display\": \"Bachelor of Science\"\n"
        + "          }\n"
        + "        ],\n"
        + "        \"text\": \"Bachelor of Science\"\n"
        + "      },\n"
        + "      \"period\": {\n"
        + "        \"start\": \"1995\"\n"
        + "      },\n"
        + "      \"issuer\": {\n"
        + "        \"display\": \"Example University\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}";

    System.out.println(new FHIRPathProtoEvaluator().processJSON(JsonPractitioner, "address.use"));
//    System.out.println(new FHIRPathProtoEvaluator().evaluate(filename,
//        "name.where(use='official')"));

//    System.out.println(((HumanName) (new FHIRPathProtoEvaluator().evaluate(filename,
//        "name.where(use='official')")).get(0)).getFamily());
//
//    System.out.println(new FHIRPathProtoEvaluator().evaluate("id {\n"
//        + "  value: \"examle\"\n"
//        + "}\n"
//        + "active {\n"
//        + "  value: true\n"
//        + "}\n"
//        + "name {\n"
//        + "  use {\n"
//        + "    value: OFFICIAL\n"
//        + "  }\n"
//        + "  family {\n"
//        + "    value: \"Chalmers\"\n"
//        + "  }\n"
//        + "  given {\n"
//        + "    value: \"Peter\"\n"
//        + "  }\n"
//        + "  given {\n"
//        + "    value: \"James\"\n"
//        + "  }\n"
//        + "}\n"
//        + "name {\n"
//        + "  use {\n"
//        + "    value: USUAL\n"
//        + "  }\n"
//        + "  given {\n"
//        + "    value: \"Jim\"\n"
//        + "  }\n"
//        + "}\n"
//        + "telecom {\n"
//        + "  system {\n"
//        + "    value: PHONE\n"
//        + "  }\n"
//        + "  value {\n"
//        + "    value: \"(03) 3410 5613\"\n"
//        + "  }\n"
//        + "  use {\n"
//        + "    value: MOBILE\n"
//        + "  }\n"
//        + "  rank {\n"
//        + "    value: 2\n"
//        + "  }\n"
//        + "}\n"
//        + "gender {\n"
//        + "  value: MALE\n"
//        + "}\n"
//        + "birth_date {\n"
//        + "  value_us: 157161600000000\n"
//        + "  timezone: \"Europe/London\"\n"
//        + "  precision: DAY\n"
//        + "  extension {\n"
//        + "    url {\n"
//        + "      value: \"http://hl7.org/fhir/StructureDefinition/patient-birthTime\"\n"
//        + "    }\n"
//        + "    value {\n"
//        + "      date_time {\n"
//        + "        value_us: 157232145000000\n"
//        + "        timezone: \"-05:00\"\n"
//        + "        precision: SECOND\n"
//        + "      }\n"
//        + "    }\n"
//        + "  }\n"
//        + "}\n"
//        + "deceased {\n"
//        + "  boolean {\n"
//        + "  }\n"
//        + "}\n"
//        + "address {\n"
//        + "  use {\n"
//        + "    value: HOME\n"
//        + "  }\n"
//        + "  type {\n"
//        + "    value: BOTH\n"
//        + "  }\n"
//        + "  text {\n"
//        + "    value: \"534 Erewhon St PeasantVille, Rainbow, Vic  3999\"\n"
//        + "  }\n"
//        + "  line {\n"
//        + "    value: \"534 Erewhon St\"\n"
//        + "  }\n"
//        + "  city {\n"
//        + "    value: \"PleasantVille\"\n"
//        + "  }\n"
//        + "  district {\n"
//        + "    value: \"Rainbow\"\n"
//        + "  }\n"
//        + "  state {\n"
//        + "    value: \"Vic\"\n"
//        + "  }\n"
//        + "  postal_code {\n"
//        + "    value: \"3999\"\n"
//        + "  }\n"
//        + "  period {\n"
//        + "    start {\n"
//        + "      value_us: 157161600000000\n"
//        + "      timezone: \"Europe/London\"\n"
//        + "      precision: DAY\n"
//        + "    }\n"
//        + "  }\n"
//        + "}\n", "name.count()"));
  }

}
