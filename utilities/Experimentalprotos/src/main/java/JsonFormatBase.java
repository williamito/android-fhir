import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.fhir.r4.core.Patient;
import com.google.fhir.shaded.common.io.Files;
import com.google.fhir.shaded.protobuf.Message;
import com.google.fhir.common.JsonFormat;
import com.google.fhir.shaded.protobuf.TextFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;

/**
* @author Deepro choudhury
*
*/
public class JsonFormatBase {

  protected JsonFormat.Parser jsonParser
      = JsonFormat.Parser.withDefaultTimeZone(ZoneId.systemDefault());

  protected TextFormat.Parser textParser = TextFormat.getParser();

  protected JsonFormat.Printer jsonPrinter = JsonFormat.getPrinter();
  
  private final String examplesDir;

  private String jsonDir = "/json/";

  private String protoTxtDir = "/prototxt/";

  private String protoBinaryDir = "/protobinary/";
  
  public enum FileType {
      JSON,
      PROTOTXT,
      PROTOBINARY
  }

  public JsonFormatBase() {
    examplesDir = "/android-fhir/utilities/examplefiles";
  }

  /**
   *
   * @param examplesDir The directory in which the methods of this class should be searching and
   *                    creating files
   */
  public JsonFormatBase(String examplesDir) {
    this.examplesDir = examplesDir;
  }

  public JsonFormatBase(String examplesDir, String jsonDir,
      String protoTxtDir, String protoBinaryDir) {
    this.examplesDir = examplesDir;
    this.jsonDir = jsonDir;
    this.protoTxtDir = protoTxtDir;
    this.protoBinaryDir = protoBinaryDir;
  }

  /**
   * Loads the json contents of a json file with the specified filename in the examplesDir
   * @param filename The name of the file to be searched for. The ".json" extension should
   *                 NOT be given
   * @return The contents of the JSON file as a string
   * @throws IOException If the file is not found
   */
  protected String loadJson(String filename) throws IOException {
    File file = new File(examplesDir + jsonDir + filename);
    return Files.asCharSource(file, UTF_8).read();
  }
  
  public String getProtoBinaryPath(String filename) {
    String pathName = examplesDir + protoBinaryDir + filename + ".proto";
    return pathName;
  }
  
  public String getProtoTxtPath(String filename) {
    String pathName = examplesDir + protoTxtDir + filename + ".prototxt";
    return pathName;
  }
  
  public String getJsonPath(String filename) {
    String pathName = examplesDir + jsonDir + filename + ".json";
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

    try {
      File resultFile = new File(filePath);
      return resultFile;
    } catch (Exception e) {
      System.out.println("There was no file with the filename you requested at " + examplesDir);
    }

    return null;
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
