package com.example.cms.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.responsedto.PanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;

@Service
public class ContributionPanelServiceImpl implements ContributionPanelService{

	private ContributionPanelRepository contributionpanelRepository;

	private BlogRepository blogRepository;

	private UserRepository userRepository;

	private ResponseStructure<PanelResponse>responseStructure;


	public ContributionPanelServiceImpl(ContributionPanelRepository contributionpanelRepository) {
		this.contributionpanelRepository = contributionpanelRepository;
	}

	@Override
	public ResponseEntity<ResponseStructure<PanelResponse>> addContribution(int userId, int panelId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(owner -> {
			return contributionpanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add contribution");
				return userRepository.findById(userId).map(contribution -> {
					if(!panel.getUsers().contains(contribution) && panel.getUsers().contains(owner))
						panel.getUsers().add(contribution);

					contributionpanelRepository.save(panel);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("Contribution added successfully")
							.setBody(mapToContributionPanelResponse(panel)));
				}).orElseThrow(() -> new UserNotFoundByIdException("User not found by Id"));
			}).orElseThrow(() -> new PanelNotFoundByIdException("panel not found by id"));
		}).get();
	}
	private PanelResponse mapToContributionPanelResponse(ContributionPanel panel) {
		return new PanelResponse(panel.getPanelId());
	}

	@Override
	public ResponseEntity<ResponseStructure<PanelResponse>> removeUserFromContribution(int userId, int panelId) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(owner -> {
			return contributionpanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add contribution");
				return userRepository.findById(userId).map(contribution -> {
					if(!panel.getUsers().contains(contribution) && panel.getUsers().contains(owner))
						panel.getUsers().remove(contribution);

					contributionpanelRepository.save(panel);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("removed user from contribution")
							.setBody(mapToContributionPanelResponse(panel)));
				}).orElseThrow(() -> new UserNotFoundByIdException("User not found by Id"));
			}).orElseThrow(() -> new PanelNotFoundByIdException("panel not found by id"));
		}).get();
	}

}
