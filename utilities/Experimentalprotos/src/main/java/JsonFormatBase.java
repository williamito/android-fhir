import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.fhir.r4.core.Patient;
import com.google.fhir.shaded.common.io.Files;
import com.google.fhir.shaded.protobuf.CodedInputStream;
import com.google.fhir.shaded.protobuf.GeneratedMessageV3;
import com.google.fhir.shaded.protobuf.Message;
import com.google.fhir.shaded.protobuf.MessageOrBuilder;
import com.google.fhir.shaded.protobuf.TextFormat.ParseException;
import com.google.gson.JsonParser;
import com.google.fhir.shaded.protobuf.Message.Builder;
import com.google.fhir.common.JsonFormat;
import com.google.fhir.shaded.protobuf.TextFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.ZoneId;


public class JsonFormatBase {

  protected JsonFormat.Parser jsonParser
      = JsonFormat.Parser.withDefaultTimeZone(ZoneId.systemDefault());

  protected TextFormat.Parser textParser = TextFormat.getParser();

  protected JsonFormat.Printer jsonPrinter = JsonFormat.getPrinter();

  protected String loadJson(String filename) throws IOException {
    File file = new File("D:/android-fhir/" + filename);
    return Files.asCharSource(file, UTF_8).read();
  }

  protected void parseToProto(String name, com.google.fhir.shaded.protobuf.Message.Builder builder)
      throws IOException {

    String filename = "/d/android-fhir/example.json";

    String realFileName = name + ".json";

//    jsonParser.merge(loadJson(realFileName), builder);


    jsonParser.merge("{\n"
        + "  \"active\": true,\n"
        + "  \"deceasedBoolean\": false,\n"
        + "  \"gender\": \"male\",\n"
        + "  \"address\": [\n"
        + "    {\n"
        + "      \"use\": \"home\",\n"
        + "      \"period\": {\n"
        + "        \"start\": \"1974-12-25\"\n"
        + "      },\n"
        + "      \"postalCode\": \"3999\",\n"
        + "      \"type\": \"both\",\n"
        + "      \"district\": \"Rainbow\",\n"
        + "      \"line\": [\n"
        + "        \"534 Erewhon St\"\n"
        + "      ],\n"
        + "      \"text\": \"534 Erewhon St PeasantVille, Rainbow, Vic  3999\",\n"
        + "      \"state\": \"Vic\",\n"
        + "      \"city\": \"PleasantVille\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"id\": \"examle\",\n"
        + "  \"name\": [\n"
        + "    {\n"
        + "      \"use\": \"official\",\n"
        + "      \"family\": \"Chalmers\",\n"
        + "      \"given\": [\n"
        + "        \"Peter\",\n"
        + "        \"James\"\n"
        + "      ]\n"
        + "    },\n"
        + "    {\n"
        + "      \"given\": [\n"
        + "        \"Jim\"\n"
        + "      ],\n"
        + "      \"use\": \"usual\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"_birthDate\": {\n"
        + "    \"extension\": [\n"
        + "      {\n"
        + "        \"url\": \"http://hl7.org/fhir/StructureDefinition/patient-birthTime\",\n"
        + "        \"valueDateTime\": \"1974-12-25T14:35:45-05:00\"\n"
        + "      }\n"
        + "    ]\n"
        + "  },\n"
        + "  \"resourceType\": \"Patient\",\n"
        + "  \"telecom\": [\n"
        + "    {\n"
        + "      \"rank\": 2,\n"
        + "      \"value\": \"(03) 3410 5613\",\n"
        + "      \"system\": \"phone\",\n"
        + "      \"use\": \"mobile\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"birthDate\": \"1974-12-25\"\n"
        + "}", builder);


  }

  protected void createProtoFile(String filename, Message.Builder builder)
      throws IOException {
    File file = new File("/android-fhir/" + filename + ".prototxt");

    textParser.merge(Files.asCharSource(file, UTF_8).read(), builder);

    File newFile = new File("/android-fhir/" + filename + ".proto");

    builder.build()
        .writeTo(new FileOutputStream(newFile));

    Message newMessage = builder.getDefaultInstanceForType().getParserForType()
        .parseFrom(new FileInputStream(newFile));

    System.out.println(newMessage);
  }

  protected String parseToJson(File file, Message.Builder builder) throws IOException {
    
    textParser.merge(Files.asCharSource(file, UTF_8).read(), builder);


    File newFile = new File("/android-fhir/testbinary.proto");

    builder.build()
        .writeTo(new FileOutputStream(newFile));

    File newFile2 = new File("/android-fhir/testbinary.proto");

    Patient newPatient = Patient.parseFrom(new FileInputStream(newFile2));

    System.out.println(newPatient);

    return jsonPrinter.print(builder);
  }

  protected String parseToJson(String json, Message.Builder builder) throws IOException {
    textParser.merge(json, builder);

    return jsonPrinter.print(builder);
  }

  public String parseToJson(Message message) throws IOException {
    
    return jsonPrinter.print(message);
  }

}
