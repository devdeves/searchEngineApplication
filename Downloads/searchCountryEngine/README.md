# Counties Suggest API

Spring Boot REST API implementation for suggesting US counties based on
partial county name and/or state input.

------------------------------------------------------------------------

## 🚀 Project Overview

This project implements the `/suggest` endpoint as defined in the
provided OpenAPI specification.

The API allows users to search US counties using:

-   Partial county name
-   State abbreviation
-   Combination of county + state

The API returns: - Maximum 5 results - Case-insensitive matching - JSON
array response


**Key Features:**
-   **Rate Limiting**: Protected by [Bucket4j](https://bucket4j.com) to handle high traffic.
-   **Search**: Supports partial names, state abbreviations, or combined "county, state" queries.
-   **Performance**: Results limited to 5 and case-insensitive.
------------------------------------------------------------------------

## 🏗️ Tech Stack

-   Java 17+
-   Spring Boot
-   Spring Data JPA
-   H2 / Configurable Database
-   Maven
-   Jackson
-   JUnit (Testing)

------------------------------------------------------------------------

## 📂 Project Structure

com.search.counties ├── controller │ └── CountyController.java ├── dto │
└── CountyResponseDto.java ├── model │ └── County.java ├── repositories
│ └── CountyRepository.java ├── services │ └── CountyService.java └──
SearchEngineApplication.java

------------------------------------------------------------------------

## ⚙️ Setup Instructions

### 1. Build Project

mvn clean install

### 2. Run Application

mvn spring-boot:run

Application runs on:

http://localhost:3000

------------------------------------------------------------------------

## 🔎 API Endpoint

GET /suggest?q=`<query>`{=html}

Example:

GET /suggest?q=cowl GET /suggest?q=cowlitz, wa GET /suggest?q=wa

------------------------------------------------------------------------

## 📌 Search Logic

-   Case-insensitive matching
-   Matches county name OR state
-   If comma present (e.g., "cowlitz, wa"):
    -   First part matches county name
    -   Second part matches state
-   Results limited to 5

------------------------------------------------------------------------

## 🗄 Data Loading

-   Data is loaded from data.json at startup
-   Stored in database before API becomes available

------------------------------------------------------------------------

## 🧪 Run Tests

mvn test

------------------------------------------------------------------------

## 🎥 Submission Includes

-   Source code (ZIP)
-   README file
-   Postman collection
-   Screen recording demo

------------------------------------------------------------------------


