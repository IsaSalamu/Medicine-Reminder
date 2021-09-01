//package com.example.medicineremindertwo;
//
//public class FetchDrugNames {
//
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://iterar-mapi-us.p.rapidapi.com/api/autocomplete?query=res"))
//            .header("x-rapidapi-host", "iterar-mapi-us.p.rapidapi.com")
//            .header("x-rapidapi-key", "97580ed195msh8bd39d9171d7890p17568bjsn9eb16b43259d")
//            .method("GET", HttpRequest.BodyPublishers.noBody())
//            .build();
//    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//System.out.println(response.body());
//}
