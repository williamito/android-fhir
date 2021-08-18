import com.google.fhir.common.InvalidFhirException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, InvalidFhirException {
    new JsonFormatGenerate()
        .generateProtoTxt(new String[] {"label"}, null);
  }

}
