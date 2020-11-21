package com.dpins.springbootawssns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@RestController
public class AwsSnsController {

	@Autowired
    private AmazonSNSClient snsClient;

	@Value("${cloud.aws.topic.arn}")
    private String topicArn;
	
	@GetMapping("/health")
	public ResponseEntity<String> getHealth() {
		return new ResponseEntity<String>("Successful health check - AWS SNS Service", HttpStatus.OK);
	}
	
	@GetMapping("/addSubscription/{email}")
	public String addSubscription(@PathVariable String email) {
		SubscribeRequest request = new SubscribeRequest(topicArn, "email", email);
		snsClient.subscribe(request);
		return "Thank you for subsribing. Your subscription request is pending. To confirm, please check your email : " + email;
	}

	@GetMapping("/sendNotification")
	public String publishMessageToTopic(){
		 PublishRequest publishRequest = new PublishRequest(topicArn, buildEmailBody(), "Alert - Data Center Outage");
		 snsClient.publish(publishRequest);
		 return "Notification email sent successfully.";
	}

	private String buildEmailBody(){
		return "Dear User ,\n" +
				"\n" +
				"\n" +
				"There is sudden connection outage in data center."+ "\n" +
				"All the VMs are not accessiible. Our team is working on to fix this services as soon as possible. \n" +
				"We will notify you once the services are restoredNotification will be sent out as soon as the issue is resolved. \n" + 
				"Please feel free to contact supprot team for more details."; }
	
}
