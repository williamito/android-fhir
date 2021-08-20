import com.google.fhir.r4.core.DeviceMetric;
import com.google.fhir.r4.core.ImplementationGuide.Definition.Resource;
import com.google.fhir.r4.core.Patient;
import com.google.fhir.r4.core.SubstanceReferenceInformation.Gene;
import com.google.fhir.shaded.protobuf.GeneratedMessageV3;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.hl7.fhir.r5.model.Base;

public class ProtoBinaryFHIRPath {

  public List<Base> evaluate(File protoBinary, String expressionString) throws IOException {

    InputStream binaryInputStream = new FileInputStream(protoBinary);

    Patient binaryPatient = Patient.parseFrom(binaryInputStream);

    Resource resource = Resource.parseFrom(binaryInputStream);

    JsonFormatBase jsonFormatBase = new JsonFormatBase();

    String json = jsonFormatBase.parseToJson(binaryPatient);

    List<Base> result = new ProtoFHIRPathFiles().processJSON(json, expressionString);

    return result;
  }



}
