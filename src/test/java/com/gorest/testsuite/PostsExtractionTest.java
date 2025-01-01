package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2/posts";

        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", 25)
                .get()
                .then().statusCode(200);
    }


    // 1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("[].title");
        System.out.println("The value of limit is : " + title);
        System.out.println("-------------------------------------");
    }


    // 2. Extract the total number of record
    @Test
    public void test002() {
        int total = response.extract().path("[].size()");
        System.out.println("The total is : " + total);
        System.out.println("-------------------------------------");
    }

    // 3. Extract the body of 15th record
    @Test
    public void test003() {
        String nameOfFifthStore = response.extract().path("[14]");
        System.out.println("The name of 5th store is : " + nameOfFifthStore);
        System.out.println("-------------------------------------");
    }


    // 4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<String> namesOfAllStore = response.extract().path("[].id");
        System.out.println("The name of all stores are : " + namesOfAllStore);
        System.out.println("-------------------------------------");
    }


    // 5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIdOfAllStore = response.extract().path("data.services.storeservices['storeId']");
        System.out.println("The store IDs of all stores are : " + storeIdOfAllStore);
        System.out.println("-------------------------------------");
    }
    // 6. Extract the title of all records whose user_id = 7609175
    @Test
    public void test006() {

        List<String> itlesWithSpecificUserId = response.extract().path("findAll { it.user_id == 7609175 }.title");
        System.out.println("Titles of records where user_id = 7609175 " + itlesWithSpecificUserId);
        System.out.println("-------------------------------------");
    }

    // 7. Extract the body of all records whose id = 93957
    @Test
    public void test007() {
        int targetId = 184654;
        List<String>  bodiesWithSpecificId = response.extract().path("findAll { it.id == 184654 }.body");
        System.out.println("Bodies of records with id : " + bodiesWithSpecificId);
        System.out.println("-------------------------------------");
    }





}




