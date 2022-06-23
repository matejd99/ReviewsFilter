package com.example.rewiesfilter.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReviewsModel {
    public Long id;
    public String reviewId;
    public String reviewFullText;
    public String reviewText;
    public int numLikes;
    public int numComments;
    public int numShares;
    public int rating;
    public String reviewCreatedOn;
    public String reviewCreatedOnDate;
    public int reviewCreatedOnTime;
    public String reviewerId;
    public String reviewerUrl;
    public String reviewerName;
    public String reviewerEmail;
    public String sourceType;
    public boolean isVerified;
    public String source;
    public String sourceName;
    public String sourceId;
    public List<String> tags;
    public String href;
    public String logoHref;
    public List<String> photos;

}