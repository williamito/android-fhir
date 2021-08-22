import com.google.fhir.common.InvalidFhirException;
import com.google.fhir.r4.core.Date;
import com.google.fhir.r4.core.DeviceMetric;
import com.google.fhir.r4.core.Patient;
import com.google.fhir.shaded.common.io.Files;
import com.google.fhir.shaded.protobuf.Message;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.google.fhir.shaded.protobuf.GeneratedMessageV3;
import com.google.fhir.r4.core.MessageDefinition;
import com.google.fhir.r4.core.MessageHeader;

public class JsonFormatGenerate extends JsonFormatBase {

  /**
   * Generates a file with the prototxt version of the resource
   * for each of the json files passed into this function, and saves them
   * in the working directory
   * @param fileNames An array containing the names of the json files to be converted into prototxt
   * @param type A builder of the type of resource contained in the files
   * @throws InvalidFhirException If the FHIR resource in any json file is invalid and cannot be
   * parsed to prototxt, or if the builder is invalid for a particular file
   * @throws IOException If the file cannot be found
   */
  public void generateProtoTxt(String[] fileNames,
      Message.Builder type)
      throws InvalidFhirException, IOException {
    for (String filename : fileNames) {
      Message.Builder builder = type.clone();
      try {
        parseToProto(filename, builder);
      } catch (Exception e) {
        throw new InvalidFhirException("Failed parsing" + filename, e);
      }
      File file = new File("/android-fhir/" + filename + ".prototxt");
      Files.asCharSink(file, StandardCharsets.UTF_8).write(builder.toString());
      parseToJson(file, builder);
    }
  }



}
