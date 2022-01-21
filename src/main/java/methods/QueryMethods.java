/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import Models.Model;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class QueryMethods {

    private static final String HOST = "localhost";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";
    private static RestHighLevelClient restHighLevelClient;

    private static final String INDEX = "yavuz_2";
    private static final String TYPE = "_doc";

    public QueryMethods() {
        makeConnection();
    }

    private static synchronized RestHighLevelClient makeConnection() {

        if (restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(HOST, PORT_ONE, SCHEME),
                            new HttpHost(HOST, PORT_TWO, SCHEME)));
        }

        return restHighLevelClient;
    }

    private static synchronized void closeConnection() throws IOException {
        restHighLevelClient.close();
        restHighLevelClient = null;
    }

    public void _search_fuzzy(String key, String value) throws IOException {

        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.fuzzyQuery(key, value)).from(0).size(1);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getTook() + " süre tuttu.");
        System.out.println(response.getHits().getTotalHits() + " adet veri geldi.");
    }

    public void _search_in_query(String key, String value) throws IOException {

        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.termsQuery(key, value)).from(0).size(1);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getTook() + " süre tuttu.");
        System.out.println(response.getHits().getTotalHits() + " adet veri geldi.");
    }

    public void _searchwithexist(String value) throws IOException {

        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.existsQuery(value));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getTook() + " süre tuttu.");
        System.out.println(response.getTotalShards() + " adet veri geldi.");
    }

    public void _searchwithbetween(String value, int from, int to) throws IOException {

        //return getResponse.getFields();
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.rangeQuery(value).from(from).to(100000));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getTook() + " süre tuttu.");
        System.out.println(response.getHits().getTotalHits()+ " adet veri geldi.");
    }
    public void _between(String value, String value2, String from, String to) throws IOException {

        //return getResponse.getFields();
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.rangeQuery(value).from(from).to(to));
        builder.trackTotalHits(true);
        builder.sort(value2, SortOrder.DESC);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getTook() + " süre tuttu.");
        System.out.println(response.getHits().getTotalHits()+ " adet veri geldi.");
    }
    
    public void query18() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("Sentiment", "Positive"))
                .query(QueryBuilders.fuzzyQuery("App", "*Food*"))
                .query(QueryBuilders.fuzzyQuery("Translated_Review", "*Full*"));
        builder.trackTotalHits(true);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getTook() + " ms süre tuttu.");
        System.out.println(Arrays.toString(response.getHits().getHits()));
        System.out.println(response.getHits().getTotalHits());
    }
    
    
    
    
    public void query8() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("Sentiment", "Positive"))
                        .must(QueryBuilders.prefixQuery("App", "food"))
                        .must(QueryBuilders.prefixQuery("Translated_Review", "full"))
                ).size(0);//.query(QueryBuilders.termQuery("Translate_Review", "nan"));
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

                System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
        
    }
    
    public void query7() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("Sentiment", "Negative"))
                .sort("Sentiment_Polarity",SortOrder.DESC)
                .size(5);
        builder.trackTotalHits(true);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
    }
    
    public void query6() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(
                AggregationBuilders.filter("filter", QueryBuilders.termQuery("App", "*"))
        ).size(0);
        builder.query(QueryBuilders.matchQuery("Sentiment", "Negative"));
        
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
    }
    
    public void query5() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.boolQuery()
                        .must(QueryBuilders.rangeQuery("Sentiment_Subjectivity").gte("0.2"))
                        .must(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.7"))
                );//.query(QueryBuilders.termQuery("Translate_Review", "nan"));
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
    }
    
    
    public void query4() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.boolQuery()
                        .must(QueryBuilders.rangeQuery("Sentiment_Subjectivity").gte("0.8"))
                        .must(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("1.0"))
                );//.query(QueryBuilders.termQuery("Translate_Review", "nan"));
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
    }
    
    public void query3() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.boolQuery()
                        .must(QueryBuilders.rangeQuery("Sentiment_Polarity").gte("0.5"))
                        .must(QueryBuilders.rangeQuery("Sentiment_Polarity").lt("0.6"))
                );//.query(QueryBuilders.termQuery("Translate_Review", "nan"));
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        
        System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
    }
    
    public void query2() throws IOException { //.rangeQuery("Sentiment_Polarity").gte("0.5")).query(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lte("0.5")
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.boolQuery()
                        .must(QueryBuilders.rangeQuery("Sentiment_Polarity").gt("0.5"))
                        .must(QueryBuilders.rangeQuery("Sentiment_Subjectivity").lt("0.5"))
                );//.query(QueryBuilders.termQuery("Translate_Review", "nan"));
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        searchRequest.source(builder);
        
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getHits().getHits()));
        //System.out.println(response.getHits().getTotalHits());
    }
    
    public void translate_review_nan() throws IOException{
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchQuery("Translated_Review","nan"));//q=Translated_Review:nan&track_total_hits=true
        builder.trackTotalHits(true);
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.searchType(SearchType.DEFAULT);
        searchRequest.source(builder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(response.getHits().getHits()));
    }

    public int size() {

        return 0;
    }
}
