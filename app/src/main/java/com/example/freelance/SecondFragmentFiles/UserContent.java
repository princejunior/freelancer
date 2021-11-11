package com.example.freelance.SecondFragmentFiles;

public class UserContent {
     String title, imageUrl, price, description, userId;
//     Long price;

     public UserContent() {
     }

     public String getDescription() {
          return description;
     }

     public String getUserId() {
          return userId;
     }

     public UserContent(String title, String imageUrl, String price, String description, String userId) {
          this.title = title;
          this.imageUrl= imageUrl;
          this.price = price;
          this.description = description;
          this.userId = userId;
     }

     public String getTitle() {
          return title;
     }

     public String getImageUrl() {
          return imageUrl;
     }

     public String getPrice() {
          return price;
     }
}
