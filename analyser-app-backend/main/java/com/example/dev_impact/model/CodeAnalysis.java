package com.example.dev_impact.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "code_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeAnalysis {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "repo_url")
    private String repoUrl;

    @Column(name = "status")
    private AnalysisStatus status;

    @Column(name = "result")
    private String result;

}
