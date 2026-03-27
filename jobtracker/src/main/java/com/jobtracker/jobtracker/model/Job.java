package com.jobtracker.jobtracker.model;

import java.time.LocalDate;

import com.jobtracker.jobtracker.Enum.JobStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "job")

@AllArgsConstructor
@NoArgsConstructor
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="company name required")
	private String companyname;

	@NotBlank(message="role is required")
	private String role;

	@NotNull(message="status is required")
	@Enumerated(EnumType.STRING)
	private JobStatus status;

	@NotNull(message="Applied date is required")
	private LocalDate applieddate;

	@Min(value=0,message="ATS score must be >=0")
	@Max(value=100,message="ATS score must be <=100")
	private Integer atsScore;

	@NotNull(message = "Follow-up date is required")
	private LocalDate followupdate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public LocalDate getApplieddate() {
		return applieddate;
	}

	public void setApplieddate(LocalDate applieddate) {
		this.applieddate = applieddate;
	}

	public Integer getAtsScore() {
		return atsScore;
	}

	public void setAtsScore(Integer atsScore) {
		this.atsScore = atsScore;
	}

	public LocalDate getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(LocalDate followupdate) {
		this.followupdate = followupdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}

