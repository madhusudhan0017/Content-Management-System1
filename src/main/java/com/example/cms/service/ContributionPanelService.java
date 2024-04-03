package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.responsedto.PanelResponse;
import com.example.cms.utility.ResponseStructure;

public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<PanelResponse>> addContribution(int userId, int panelId);

	ResponseEntity<ResponseStructure<PanelResponse>> removeUserFromContribution(int userId, int panelId);

}
