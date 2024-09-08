# Java OCR CLI Tool using Tess4J

This project is a **Command-Line Interface (CLI)** application for extracting text from images and PDFs using the **Tess4J** library, which provides Tesseract-based Optical Character Recognition (OCR) functionality. The extracted text can be copied to the clipboard for easy use.

## Features

- Extract text from images (PNG, JPG, etc.)
- Extract text from PDFs
- Supports multiple languages (e.g., English, Sinhala)
- Automatically copies extracted text to the clipboard

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **Maven** (for building the project)
- **Tesseract-OCR** trained data files (`.traineddata` files) for the required languages. Place these files in the `tessdata/` directory.

## Installation

### Clone the Repository

To get started, clone the repository from GitHub:

```bash
git clone https://github.com/yourusername/ocr-cli-tool.git
cd ocr-cli-tool
```

### Download and Setup Tess4J

Tess4J requires the Tesseract-OCR engine. You can download the necessary Tesseract language data files (`.traineddata` files) for the languages you want to use. These files should be placed in the `tessdata/` directory in your project.

For example, download `eng.traineddata` (English) or `sin.traineddata` (Sinhala), and place them in the `tessdata/` folder.

### Build the Project

Use Maven to build the project and create a JAR file:

```bash
mvn clean package
```

This will generate a JAR file in the `target/` directory.

### Running the Application

To run the application, use the following command:

```bash
java -jar target/java-ocr-tess4j-1.0-SNAPSHOT-shaded.jar <image_or_pdf_path> <language>
```

- `<image_or_pdf_path>`: Path to the image or PDF file you want to process.
- `<language>`: Language for OCR (e.g., `eng` for English, `sin` for Sinhala).

Example:

```bash
java -jar target/java-ocr-tess4j-1.0-SNAPSHOT-shaded.jar ./src/images/testocr.png eng
```

This will extract the text from the image and copy it to the clipboard.

## Project Structure

The project structure is as follows:

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── ocr
│   │   │       ├── ReadImagesCLI.java
│   │   │       ├── OCRProcessor.java
│   |   |       └── OCRTestConcept.java
│   │   └── resources
│   ├── test
│   │   └── java
├── tessdata
│   └── eng.traineddata
│   └── sin.traineddata
├── target
│   └── java-ocr-tess4j-1.0-SNAPSHOT-shaded.jar
├── pom.xml
└── README.md
```

- **`ReadImagesCLI.java`**: The main class for running the CLI application.
- **`OCRProcessor.java`**: Handles OCR logic for both images and PDFs.
- **`OCRTestConcept.java`**: Testing concept class for Tesseract OCR.
- **`tessdata/`**: Contains Tesseract language data files.
- **`pom.xml`**: Maven configuration file.

## Dependencies

This project uses the following dependencies:

- **Tess4J**: Java wrapper for Tesseract-OCR
- **PDFBox**: For rendering PDF files into images (used when processing PDFs)
- **JAI Image I/O**: For reading various image formats

These dependencies are managed via Maven. You can find the complete list of dependencies in the `pom.xml` file.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests if you would like to improve the project.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact
For any questions or suggestions, feel free to open an issue or contact me at [email](neophytetoskill@gmail.com).

---
