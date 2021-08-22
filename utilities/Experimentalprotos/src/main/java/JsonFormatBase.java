import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.fhir.r4.core.Patient;
import com.google.fhir.shaded.common.io.Files;
import com.google.fhir.shaded.protobuf.Message;
import com.google.fhir.common.JsonFormat;
import com.google.fhir.shaded.protobuf.TextFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;


public class JsonFormatBase {

  protected JsonFormat.Parser jsonParser
      = JsonFormat.Parser.withDefaultTimeZone(ZoneId.systemDefault());

  protected TextFormat.Parser textParser = TextFormat.getParser();

  protected JsonFormat.Printer jsonPrinter = JsonFormat.getPrinter();
  
  private final String examplesDir = "/android-fhir/utilities/examplefiles";
  
  public enum FileType {
      JSON,
      PROTOTXT,
      PROTOBINARY
  }

  protected String loadJson(String filename) throws IOException {
    File file = new File("D:/android-fhir/" + filename);
    return Files.asCharSource(file, UTF_8).read();
  }
  
  public String getProtoBinaryPath(String filename) {
    String pathName = examplesDir + "/protobinary/" + filename + ".proto";
    return pathName;
  }
  
  public String getProtoTxtPath(String filename) {
    String pathName = examplesDir + "/prototxt/" + filename + ".prototxt";
    return pathName;
  }
  
  public String getJsonPath(String filename) {
    String pathName = examplesDir + "/json/" + filename + ".json";
    return pathName;
  }
  
  public File getExampleFile(String filename, FileType fileType)  {
    String filePath;
    
    switch (fileType) {
      case JSON:
        filePath = getJsonPath(filename);
        break;
      case PROTOTXT:
        filePath = getProtoTxtPath(filename);
        break;
      case PROTOBINARY:
        filePath = getProtoBinaryPath(filename);
        break;
      default:
        throw new IllegalArgumentException("Invalid file type");
    }
    
    File resultFile = new File(filePath);
    return resultFile;
  }

  protected void parseToProto(String name, com.google.fhir.shaded.protobuf.Message.Builder builder)
      throws IOException {

    String realFileName = getJsonPath(name);

    jsonParser.merge(loadJson(realFileName), builder);


  }

  protected String parseStringToProto(String json, Message.Builder builder) {
    jsonParser.merge(json, builder);
    return builder.toString();
  }

  protected File createProtoBinaryFile(String filename, Message.Builder builder)
      throws IOException {
    
    File file = getExampleFile(filename, FileType.PROTOTXT);

    textParser.merge(Files.asCharSource(file, UTF_8).read(), builder);

    File protoFile = getExampleFile(filename, FileType.PROTOBINARY);

    builder.build()
        .writeTo(new FileOutputStream(protoFile));

    Message newMessage = builder.getDefaultInstanceForType().getParserForType()
        .parseFrom(new FileInputStream(protoFile));

    System.out.println(newMessage);
    
    return protoFile;
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
