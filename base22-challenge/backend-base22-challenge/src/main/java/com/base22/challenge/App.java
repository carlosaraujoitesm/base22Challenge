package com.base22.challenge;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.cli.*;

public class App
{
    public static void main(String[] args)throws Exception { 

        /*Argument parser*/
        Options options = new Options();

        Option inputFileName   = Option.builder()
         .longOpt("inputFileName")
         .argName("inputfile" )
         .hasArg()
         .required()
         .desc("use given file for input" )
         .build();
        options.addOption(inputFileName);

        Option outputFileName   = Option.builder()
         .longOpt("outputFileName")
         .argName("outputfile" )
         .required()
         .hasArg()
         .desc("use given file for output" )
         .build();
        options.addOption(outputFileName);

        Option cleanHTML   = Option.builder()
         .longOpt("cleanHTML")
         .argName("cleanHTML option" )
         .desc("use given args for clean instyle" )
         .build();
        options.addOption(cleanHTML);

        Option outputFormat   = Option.builder()
         .longOpt("outputFormat")
         .argName("outputformat" )
         .required()
         .hasArg()
         .desc("use given arg for type of output" )
         .build();
        options.addOption(outputFormat);

       
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd =  null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("usage", options);
            System.exit(1);
        }

        /*Extract options*/
        String inputFilePath = cmd.getOptionValue("inputFileName");
        String outputFilePath = cmd.getOptionValue("outputFileName");
        Boolean cleanHTMLOP = cmd.hasOption("cleanHTML");
        String outputFormatName = cmd.getOptionValue("outputFormat");
    

        /*Validate*/
        if(outputFormatName.equals("csv")){
            outputFilePath = outputFilePath + "." + outputFormatName;
        }else{
            System.out.println("Error in outputFormat, this application support only csv extension.");
            System.exit(1);
        }

        /*Create outputFIle*/
        CsvFileWriter writer =  new CsvFileWriter(outputFilePath);
    
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String url;

            while ((url = reader.readLine()) != null)
            {


                System.out.println("Extracting information from\t " + url);
                /*Create content crawler object*/
                contentCrawler CC = new contentCrawler(url,cleanHTMLOP);
                /*Extract and export information of interest*/
                CC.extractAndexport();     
            }

            writer.closeFile();
            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", inputFilePath);
            e.printStackTrace();
            return;
        }
    }     
}

