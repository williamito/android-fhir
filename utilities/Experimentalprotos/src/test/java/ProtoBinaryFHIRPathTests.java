import com.google.fhir.r4.core.Patient;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.hl7.fhir.r5.model.Base;
import org.hl7.fhir.r5.model.HumanName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProtoBinaryFHIRPathTests {

  @BeforeAll
  static void createPatientProtoBinary() throws IOException {
    Patient.Builder patientBuilder = Patient.newBuilder();
    new JsonFormatBase().createProtoFile("filename", patientBuilder);
  }
  
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
        ((HumanName) result.get(0)).getGivenAsSingleString() );
  }

}
