import com.google.fhir.r4.core.Patient;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ProtoTxtFHIRPathTests {
  
  @Test
  public void TestPatientNameCount() throws IOException {

    File file = new File("/android-fhir/testbinary.proto");

    System.out.println(
        new ProtoFHIRPathFiles().
            evaluateBinaryResource(file, "Patient.name.count()", Patient.newBuilder().build()));
    
    
  }

}
