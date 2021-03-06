package com.aspose.pdf.examples.AsposePdfExamples.DocumentConversion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlSaveOptions;
import com.aspose.pdf.LettersPositioningMethods;

public class PDFToHTMLAllResourceEmbeddedInSingleResultantStream {

	public static void main(String[] args) {
		Document doc = new Document("Input.pdf");
		// tune conversion parameters
		HtmlSaveOptions newOptions = new HtmlSaveOptions();
		newOptions.RasterImagesSavingMode = HtmlSaveOptions.RasterImagesSavingModes.AsEmbeddedPartsOfPngPageBackground;
		newOptions.FontSavingMode = HtmlSaveOptions.FontSavingModes.SaveInAllFormats;
		newOptions.PartsEmbeddingMode = HtmlSaveOptions.PartsEmbeddingModes.EmbedAllIntoHtml;
		newOptions.LettersPositioningMethod = LettersPositioningMethods.UseEmUnitsAndCompensationOfRoundingErrorsInCss;
		newOptions.setSplitIntoPages(false);// force write HTMLs of all pages into one output document
		newOptions.CustomHtmlSavingStrategy = new HtmlSaveOptions.HtmlPageMarkupSavingStrategy() {
			public void invoke(HtmlSaveOptions.HtmlPageMarkupSavingInfo htmlSavingInfo) {
				// TODO Auto-generated method stub
				byte[] resultHtmlAsBytes = new byte[(int) htmlSavingInfo.ContentStream.getLength()];
				htmlSavingInfo.ContentStream.read(resultHtmlAsBytes, 0, resultHtmlAsBytes.length);
				// here You can use any writable stream, file stream is taken just as example
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("PDFtoHTML.html");
					fos.write(resultHtmlAsBytes);
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		// we can use some non-existing file name all real saving will be done in CustomerHtmlSavingStrategy
		String outHtmlFile = "SomeUnexistingFile.html";
		doc.save(outHtmlFile, newOptions);
	}
}
