# Introduction

An implementation of a HL7 R4 FHIR Path engine for the FHIR Proto java library

Author: [Deepro Choudhury](https://github.com/DeeproChoudhury)

Mentor: [Martin Ndegwa](https://github.com/ndegwamartin)

Documentation: https://docs.google.com/document/d/15aZA2okP8YaZEReUMIKdn5QHyAK-qih3CVZ7kenJJxQ/

---
FHIRPath is a path-based graph-traversal navigation and extraction language for FHIR resources, somewhat like XPath.
This project creates a library to make the FHIRPath language work with protocol buffers, in both .prototxt and .protobinary formats

---
The user may create a new instance of the `FHIRPathProtoEvaluator` class when they have the resource they want to evaluate ready. The user can input the proto resource in several ways - either as a `.prototxt` file, a `.proto` binary file, an array of multiple `.prototxt` or `.protobinary` files, or a `.prototxt` string. For each format the resource is in, there is an evaluation method inside the class which takes a proto resource in that format, along with the FHIRPath expression as a string. If the user wishes to put in one or more files, all they need to do is input the filename, and the methods will search for it in the working directory. The methods are also capable of creating new empty files if one does not exist.

The conversion between protos and JSON is implemented in 2 main classes, `JSONFormatBase` and `JSONFormatGenerate` (which extends `JSONFormatBase`). If the user wishes, they can create separate instances of those classes to convert their files manually between prototxt and proto binary, proto to JSON, etc. The `JSONFormatBase` class can be instantiated with no parameters in the constructor, in which case a default examples directory inside the repository will be used and the separate directories for the different types of files will keep their standard values. The user can also specify their own directories using an overloaded constructor which takes in string values for the folder of the JSON files, the folder of the ProtoTxt files and the folder of the binary files. 

You can find more in the [documentation](https://docs.google.com/document/d/15aZA2okP8YaZEReUMIKdn5QHyAK-qih3CVZ7kenJJxQ/)
