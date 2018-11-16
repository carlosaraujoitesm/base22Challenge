
/*If all we need is to send a GET request and the session management 
is not relevant, then Jsoup can effortlessly do the job.*/
package com.base22.challenge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.IOException;

public class contentCrawler{

    Document document; 
    String url;
    String title;
    StringBuilder foundLinks;
    Elements links; 
    Boolean cleanHTML; 

    public contentCrawler(String url,Boolean cleanHTML)
    {
        this.url = url;
        this.cleanHTML = cleanHTML;
    }

    public void extractHTML() throws IOException {
        /*The connect(String url) method creates a new Connection, and get() fetches and parses a HTML file. */
        this.document = Jsoup.connect(this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36").timeout(10*1000).get();
    }

   /*Jsoup elements support a CSS (or jquery) like selector syntax to find matching elements within the document*/
    public void extractTitle(){
        this.title = this.document.title();
    }

    public void extractLinks(){
        
        this.links = this.document.select("a"); 
        StringBuilder stringBuilder = new StringBuilder();
        for(Element link : this.links){
            stringBuilder.append("[");
            stringBuilder.append(link.attr("href"));
            stringBuilder.append("]"); 
            stringBuilder.append("(");
            stringBuilder.append(link.text());
            stringBuilder.append(")");
        }
        this.foundLinks = stringBuilder;
    }

    public void cleanHTML(){

        if(cleanHTML){
            Elements elements = this.document.select("*");
            /*Iterate elements using enhanced for loop*/
            for(Element e : elements){
                UtilityJsoup.removeAttrs(e, UtilityJsoup.Options.DeleteInLineStyle);
            }
        }
    }

    public void extractAndexport(){

        /*extractHTML method uses the connect(String url) method creates a new Connection, and get() fetches and parses a HTML file. 
        If an error occurs whilst fetching the URL, it will throw an IOException, which you should handle 
        appropriately.*/
        /*This method only suports web URLs (http and https protocols); if you need to load from a file,
        use the parse(File in, String charsetName) method instead*/

        try{

            extractHTML();
            cleanHTML();
            extractTitle();
            extractLinks();
            CsvFileWriter.writeCsvFile( this.url,
                                        this.title,
                                        this.document.toString(),
                                        this.foundLinks.toString());

        }catch(IOException e){
            System.out.println (e.toString());
        }
     
   }
}


