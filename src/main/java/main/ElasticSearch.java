/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import methods.QueryMethods;
import org.elasticsearch.action.search.SearchResponse;
import timer.Timer;

/**
 *
 * @author aysenurdeniz
 */
public class ElasticSearch {

    /*
     * @param args the command line arguments
     * @throws org.apache.solr.client.solrj.SolrServerException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        //QueryMethods sınıfındaki metotları çağırmak için oluşturulan methods nesnesi
        QueryMethods Methods = new QueryMethods();
        // QUERY1 Denemesi
        for(int i = 0; i < 10; i++) {
            System.out.println("SORGU 1 BAŞLANGICI ::::: " + i);
            
            Timer.start();
            Methods.translate_review_nan();
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            System.out.println("SORGU 2 BAŞLANGICI ::::: " + i);
            
            Timer.start();
            Methods.query2();
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            Timer.start();
            
            Methods.query3();
            
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            Timer.start();
            
            Methods.query4();
            
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            Timer.start();
            
            Methods.query5();
            
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            Timer.start();
            
            Methods.query6();
            
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            Timer.start();
            
            Methods.query7();
            
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            Timer.start();
            
            Methods.query8();
            
            Timer.stop();
            
            System.out.println("TIMER ::::: " + Timer.getElapsedMilliseconds() + " ");
            
            
            System.out.println("SORGU BİTİŞİ ::::: " + i);
        }
        //QUERY2 Denemesi
        //Methods.query2();
        //QUERY3 Denemesi
        //Methods.query3();
        //QUERY4 Denemesi
        //Methods.query4();
        //QUERY5 Denemesi
        //Methods.query5();
        //QUERY6 Denemesi
        //Methods.query6();
        //QUERY7 Denemesis
        //Methods.query7();
        //QUERY8 Denemesi
        //Methods.query8(); // 5 tane veriyor :/


        //Methods._between("Sentiment_Subjectivity", "Sentiment_Subjectivity", "0.2", "0.3");
        //Methods._between("Sentiment_Subjectivity", "0.2", "0.3");
        //Methods._search_fuzzy("Sentiment_Polarity", "0.511111111");
        //System.out.println(response2.toString());
        /*QueryMethods methods = new QueryMethods();
        
        Timer.start();
        methods.querySix(); //Çalışma süresi hesaplanmak istenen kod
        Timer.stop();
        
        methods.print();
        System.out.println("-----------------------");
        methods.printSize();
        System.out.println( "Sorgu çalisirken geçen süre: " + Timer.getElapsedMilliseconds());
         */
    }

}
