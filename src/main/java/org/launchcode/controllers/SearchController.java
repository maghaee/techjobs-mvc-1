package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs;
            if (searchTerm.equals(null))
            {
                //ArrayList<HashMap<String, String>> jobs = JobData.findAll();
                jobs = JobData.findAll();
            }
            else
            {
                //ArrayList<HashMap<String, String>> jobs = JobData.findAll(searchTerm);
                jobs = JobData.findByValue(searchTerm);
            }

            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobsSize", jobs.size() + " Result(s)");
            model.addAttribute("jobs", jobs);
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobsSize", jobs.size() + " Result(s)");
            model.addAttribute("jobs", jobs);
        }
        return "search";
    }
}