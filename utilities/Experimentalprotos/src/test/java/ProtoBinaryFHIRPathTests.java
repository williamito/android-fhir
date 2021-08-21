import com.google.fhir.r4.core.Patient;
import com.google.fhir.r4.core.Practitioner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.hl7.fhir.r5.model.Base;
import org.hl7.fhir.r5.model.HumanName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProtoBinaryFHIRPathTests {

//  @BeforeAll
//  static void createPatientProtoBinary() throws IOException {
//    Patient.Builder patientBuilder = Patient.newBuilder();
//    new JsonFormatBase().createProtoFile("filename", patientBuilder);
//  }
  
  @Test
  public void TestPatientNameCount() throws IOException {

    File file = new File("/android-fhir/testbinary.proto");

    System.out.println(new ProtoFHIRPathFiles().
        evaluateBinaryResource(file, "Patient.name.count()", Patient.newBuilder()));

    System.out.println(new ProtoFHIRPathFiles().
        evaluateBinaryResource(file, "name.given", Patient.newBuilder()));

    
    
  }

  @Test
  public void TestPatientInvariantName() throws IOException {

    new JsonFormatBase().createProtoFile("filename", Patient.newBuilder());

    File file = new File("/android-fhir/filename.proto");

    List<Base> result = new ProtoFHIRPathFiles().evaluateBinaryResource(file,
        "name.where(use = 'official')", Patient.newBuilder());

    Assertions.assertEquals("Peter James",
        ((HumanName) result.get(0)).getGivenAsSingleString());
  }

  @Test
  public void TestPractitionerTxt() throws IOException {

    String string = new JsonFormatBase().parseStringToProto("{\n"
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
        + "}", Practitioner.newBuilder());

    System.out.println(string);

    String practitioner = new JsonFormatBase().parseToJson(string, Practitioner.newBuilder());

    System.out.println(new ProtoFHIRPathFiles().processJSON(practitioner, "qualification.issuer"));
  }

}
