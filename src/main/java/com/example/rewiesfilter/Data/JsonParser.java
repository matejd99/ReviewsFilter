package com.example.rewiesfilter.Data;

import com.example.rewiesfilter.Models.ReviewsModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {

    public static List<ReviewsModel> GetReviews() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:reviews.json");
        ReviewsModel[] reviews = mapper.readValue(file, ReviewsModel[].class);
        return List.of(reviews);
    }

    public static List<ReviewsModel> filterByRating(boolean prioritizeByText, boolean highestRatingFirst, boolean newestFirst, int minimumRating) throws IOException {
        List<ReviewsModel> Reviews = GetReviews();

        Comparator<ReviewsModel> comparator = (o1, o2) -> {
            if(prioritizeByText && o1.getReviewText().isBlank() != o2.getReviewText().isBlank()){
                if(o1.getReviewText().isBlank() && !o2.getReviewText().isBlank()){
                    return 1;
                } else {return  -1;}
            }

            if(o1.getRating() != o2.getRating()){
                if(highestRatingFirst){
                    return o1.getRating() - o2.getRating();
                } else {return o2.getRating() - o1.getRating();}
            }

            if(o1.getReviewCreatedOnTime() != o2.getReviewCreatedOnTime()){
                if(newestFirst){
                    return o1.getReviewCreatedOnTime() - o2.getReviewCreatedOnTime();
                }else {return o2.getReviewCreatedOnTime() - o1.getReviewCreatedOnTime();}
            }

            return  0;
        };

        return Reviews.stream()
                .filter(r -> r.getRating() >= minimumRating)
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}