package cn.yurihentai.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class Test {

//    @lombok.SneakyThrows
    public static void main(String[] args) {

        //1、创建es客户端
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("localhost", 9200));
        RestHighLevelClient esClient = new RestHighLevelClient(clientBuilder);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2、执行操作
        creatIndex(esClient);

        // 3、关闭es客户端链接
        try {
            esClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建索引
     */
    public static void creatIndex(RestHighLevelClient esCLient) {
        CreateIndexRequest indexRequest = new CreateIndexRequest("indexName");
        try {
            CreateIndexResponse response = esCLient.indices().create(indexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = response.isAcknowledged();
            if(acknowledged) {
                System.out.println("索引创建成功");
            } else {
                System.out.println("索引创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}