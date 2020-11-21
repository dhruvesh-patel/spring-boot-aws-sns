This repo demonstrates how to send email notification using AWS SNS and java spring boot application. Notifications are important aspect of software and easy / quick notification mechanism with AWS SNS offers ability to quickly develop nofification service. 

Pre-requisite:
1) JDK 11
2) Eclipse / IntelliJ IDE 
3) Maven (if not part of IDE already)
4) Postman (Or swagger or browser - I prefer Postman)

Steps to Setup :

1. Create AWS Free-tier Account using https://portal.aws.amazon.com/billing/signup. 
```
- Create SNS Topic using AWS Console - https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/US_SetupSNS.html (Go to section - Create an SNS Topic)
- Note down SNS topic ARN (you will need to update this in application.properties)
- Also Get Access key and secret access key from AWS control using https://aws.amazon.com/blogs/security/how-to-find-update-access-keys-password-mfa-aws-management-console/. (you will need to update this in application.properties)
```

2. Clone the application
```
https://github.com/dhruvesh-patel/spring-boot-aws-sns.git
```
3. Update application.properties with your own values (without this application will not build or start).
```
cloud.aws.credentials.accessKey=xxxxxx
cloud.aws.credentials.secretKey=xxxxxx
cloud.aws.topic.arn:xxxxxx
```

4. Build and run the app using IDE / maven
```
mvn clean install 
mvn spring-boot:run
The app will start running - check app health using http://localhost:8721/health.
```
4. Use REST API to add subscription to existing SNS Topic using your email. 
```
GET http://localhost:8721/addSubscription/{email}

For example, http://localhost:8721/addSubscription/abc@gmail.com
```

5. Use below REST API to send notification to all emails that have subscribed to the topic. 
```
GET http://localhost:8721/sendNotification
```

When you do this, SNS Topic will receive email subject and email body to all email thats have subscribed to topic. 

