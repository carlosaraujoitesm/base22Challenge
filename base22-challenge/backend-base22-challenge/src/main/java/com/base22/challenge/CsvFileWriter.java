package com.base22.challenge;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;


public class CsvFileWriter {
	//Delimiter used in CSV file

    static final String COMMA_DELIMITER = ",";
	static final String NEW_LINE_SEPARATOR = "\n";
	static FileWriter fileWriter;

	//CSV file header
	static final String FILE_HEADER = "URL,Title,Body,LinksFound";

    public CsvFileWriter(String output_fileName){

        try {
                
                fileWriter = new FileWriter(output_fileName);
                System.out.println("CSV file was created successfully !!! ... ");
        }catch (IOException e) {

	            System.out.println("Error in CsvFileWriter !!!");
	            e.printStackTrace();
        }   
    }

	public static void writeCsvFile(String url,String title,String html, String links) {
		
            try{
			/*Write the CSV file header*/
			fileWriter.append(FILE_HEADER.toString());
			/*Add a new line separator after the header*/
			fileWriter.append(NEW_LINE_SEPARATOR);
            /*Write url*/
            String escapedURL = StringEscapeUtils.escapeCsv(url);
            fileWriter.append(escapedURL);
            fileWriter.append(COMMA_DELIMITER);
            /*Write title*/
            String escapedTitle = StringEscapeUtils.escapeCsv(title);
            fileWriter.append(escapedTitle);
            fileWriter.append(COMMA_DELIMITER);
            /*Write cleaned html*/
            String escapedHTML = StringEscapeUtils.escapeCsv(html);
            fileWriter.append(escapedHTML);
            fileWriter.append(COMMA_DELIMITER);
            /*Write links*/
            String escapedLinks = StringEscapeUtils.escapeCsv(links);
            fileWriter.append(escapedLinks);
            fileWriter.append(COMMA_DELIMITER);

            //Write newline
            fileWriter.append(NEW_LINE_SEPARATOR);    
  
            }catch(IOException e) {

                    System.out.println("Error in CsvFileWriter !!!");
                    e.printStackTrace();
            } 
    }
    public void closeFile(){

        try {
            fileWriter.flush();
            fileWriter.close();
            System.out.println("CSV file was closed successfully !!!");
        } catch (IOException e) {
            System.out.println("Error while flushing/closing fileWriter !!!");
            e.printStackTrace();
        }
			
	}
}
