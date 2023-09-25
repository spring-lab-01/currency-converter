package com.hk.prj.currencyconverter.aws;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Handler {

    public static void putObject(String bucketName, String key, String message){
        s3Client().putObject(PutObjectRequest.builder().bucket(bucketName).key(key)
                        .build(),
                RequestBody.fromString(message));
        System.out.println("upload complete");

    }

    public static S3Client s3Client() {
        return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .build();
    }

}