package Models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BeraArge
 */
public class Model {
    private String id;
    private String App;
    private String Translated_Review;
    private String Sentiment;
    private String Sentiment_Polarity;
    private String Sentiment_Subjectivity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getApp() {
        return App;
    }

    public void setApp(String App) {
        this.App = App;
    }

    public String getTranslated_Review() {
        return Translated_Review;
    }

    public void setTranslated_Review(String Translated_Review) {
        this.Translated_Review = Translated_Review;
    }

    public String getSentiment() {
        return Sentiment;
    }

    public void setSentiment(String Sentiment) {
        this.Sentiment = Sentiment;
    }

    public String getSentiment_Polarity() {
        return Sentiment_Polarity;
    }

    public void setSentiment_Polarity(String Sentiment_Polarity) {
        this.Sentiment_Polarity = Sentiment_Polarity;
    }

    public String getSentiment_Subjectivity() {
        return Sentiment_Subjectivity;
    }

    public void setSentiment_Subjectivity(String Sentiment_Subjectivity) {
        this.Sentiment_Subjectivity = Sentiment_Subjectivity;
    }

}
