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

    String realFileName = "/android-fhir/" + name + ".json";

    jsonParser.merge(loadJson(realFileName), builder);


  }

  protected String parseStringToProto(String json, Message.Builder builder) {
    jsonParser.merge(json, builder);
    return builder.toString();
  }

  protected void createProtoBinaryFile(String filename, Message.Builder builder)
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
