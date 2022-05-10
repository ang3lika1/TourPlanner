package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.models.Tour;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import com.semesterproject.tourplanner.models.TourLog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/*public class ReportHelper {


    public ReportHelper() throws FileNotFoundException {
    }

    public static void sumReport() throws FileNotFoundException {
        ArrayList<Tour> tours = TourServiceImpl.getAllTours();
        String file = ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "report", "path") + "summary.pdf";
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document, new Tour("Summary", "String dauer", "String mapJson", 1, "String start", "String ziel"));
            addSumRepo(document, tours);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            LogHelper.error(e.getMessage(), e.getClass().getName());
        }
        if(ConfigHelper.getIniInt(ConfigHelper.getStandartConfig(), "settings", "openpdf") == 1) {
            FileHelper.openDefault(file);
        }
    }
    private static void addSumRepo(Document document, ArrayList<Tour> touren) throws DocumentException {
        double time = 0;
        double distance = 0;
        double calculatedTime = 0;
        double calculatedDistance = 0;
        for (Tour tour:touren) {
            calculatedDistance += tour.getDistance();
            calculatedTime += tour.getTime();
            ArrayList<TourLog> logs = tour.getLogs();
            for (TourLog log:logs) {
                dauer += log.getDauer();
                strecke += log.getStrecke();
            }
        }
        Anchor anchor = new Anchor(ConfigHelper.getLangIniString("rsummrepo"), catFont);
        anchor.setName(ConfigHelper.getLangIniString("rsummrepo"));
        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph(ConfigHelper.getLangIniString("rdaten"), subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(ConfigHelper.getLangIniString("reportvon") + " " + System.getProperty("user.name") + ", " + new Date()));
        subCatPart.add(new Paragraph(EinheitenAdder.addMinuten(ConfigHelper.getLangIniString("rzeitallerlogs") + " " + dauer)));
        subCatPart.add(new Paragraph(EinheitenAdder.addKm(ConfigHelper.getLangIniString("rstreckeallerlogs") + " " + strecke)));
        subCatPart.add(new Paragraph(EinheitenAdder.addMinuten(ConfigHelper.getLangIniString("rcalzeitallerlogs") + " " + calDauer)));
        subCatPart.add(new Paragraph(EinheitenAdder.addKm(ConfigHelper.getLangIniString("calstreckeallerloggs") + " " + calStecke)));
        document.add(catPart);
    }
    private static void addMetaData(Document document, Tour tour) {
        document.addTitle(ConfigHelper.getLangIniString("tourreportvon") + " " + tour.getName());
        document.addSubject(ConfigHelper.getLangIniString("tourplanervon"));
        document.addKeywords(ConfigHelper.getLangIniString("reportkeywords"));
        document.addAuthor(ConfigHelper.getLangIniString("tourplaner"));
        document.addCreator(ConfigHelper.getLangIniString("tourplaner"));
    }
}*/
