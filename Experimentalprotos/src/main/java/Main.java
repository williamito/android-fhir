import com.google.fhir.common.InvalidFhirException;
import com.google.fhir.r4.core.Patient;
import java.io.File;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, InvalidFhirException {
//    new JsonFormatGenerate()
//        .generateProtoTxt(new String[] {"label"}, null);

    File filename = new File("/android-fhir/filename.prototxt").getAbsoluteFile();

    new JsonFormatBase().parseToJson(filename, Patient.newBuilder());
  }

}
