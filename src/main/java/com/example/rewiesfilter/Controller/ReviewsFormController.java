package com.example.rewiesfilter.Controller;

import com.example.rewiesfilter.Models.ReviewsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.example.rewiesfilter.Data.JsonParser.filterByRating;

@Controller
public class ReviewsFormController {



    @GetMapping("ReviewForm")
    public String GetReviewForm(){
        return "ReviewForm";
    }

    @GetMapping("/FilteredReviews")
    public String ShowFilteredReviews(HttpServletRequest request, Model model) throws IOException {
        boolean prioritizeByText, highestRatingFirst,newestFirst;

        String prioritize = request.getParameter("TextPriority");
        if(prioritize == "true"){
            prioritizeByText = true;
        } else prioritizeByText = false;

        String RatingFirst = request.getParameter("RatingFilter");
        if(RatingFirst.equals("HighestFirst")){
            highestRatingFirst = false;
        } else highestRatingFirst = true;

        String newest = request.getParameter("DateFilter");
        if(newest == "Newest"){
            newestFirst = true;
        } else  newestFirst = false;

        String min = request.getParameter("MinRating");
        int minimumRating = Integer.parseInt(min);

        List<ReviewsModel> reviews = filterByRating(prioritizeByText,highestRatingFirst,newestFirst,minimumRating);

        model.addAttribute("reviews", reviews);

        return "FilteredReviews";
    }

}