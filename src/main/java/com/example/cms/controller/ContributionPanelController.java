package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.responsedto.PanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;


@RestController
public class ContributionPanelController {

	private ContributionPanelService contributionpanelService;
	
	public ContributionPanelController(ContributionPanelService contributionpanelService) {
		
		this.contributionpanelService = contributionpanelService;
	}

	public ResponseEntity<ResponseStructure<PanelResponse>> addContribution(@RequestParam int userId, @RequestParam int panelId){
		return contributionpanelService.addContribution(userId, panelId);
		
	}
}
