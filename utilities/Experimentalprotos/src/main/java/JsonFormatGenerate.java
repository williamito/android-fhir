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

  public void generateProtoTxt(String[] fileNames,
      Message.Builder type)
      throws InvalidFhirException, IOException {
    for (String filename : fileNames) {
      Message.Builder builder = Patient.newBuilder();
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
