package com.jobtracker.jobtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobtracker.jobtracker.Enum.JobStatus;
import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;
import com.jobtracker.jobtracker.repository.JobRepo;

import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;

@Controller
public class JobController {

    @Autowired
    private JobRepo jobRepo;

    @GetMapping("/add-job")
    public String showForm(Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/index"; 
        }

        model.addAttribute("job", new Job());
        model.addAttribute("statuses", JobStatus.values());
        return "add-job";
    }

    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute Job job, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        job.setUser(user);

        jobRepo.save(job);

        return "redirect:/jobs";
    }

    @GetMapping("/jobs")
    public String viewJobs(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/index"; 
        }

        model.addAttribute("jobs", jobRepo.findByUser(user));
        return "jobs";
    }

    @GetMapping("/edit-job/{id}")
    public String editJob(@PathVariable Long id, Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/index"; //
        }

        Job job = jobRepo.findById(id).orElse(null);
        model.addAttribute("job", job);

        model.addAttribute("statuses", JobStatus.values());

        return "add-job";
    }

    @GetMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable Long id, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/index"; 
        }

        jobRepo.deleteById(id);
        return "redirect:/jobs";
    }
}