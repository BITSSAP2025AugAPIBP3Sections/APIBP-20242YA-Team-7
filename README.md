#  Dev Impact - GitHub Contribution Analyser
 
Dev Impact is a powerful microservice-based application designed to **analyze and quantify the impact and contribution of individual developers** within a given GitHub repository. By leveraging a microservices architecture, secure authentication, and AI-powered analysis, Dev Impact provides users with deep insights into their team's collective development effort.
 
##  Features
 
* **Secure Authentication:** Standard **Login/Register** flow with **JWT Authentication**.
* **GitHub OAuth Integration:** Securely obtain necessary tokens to access and analyze the user's private/public repositories.
* **Repository Analysis:** Users can input a GitHub repository URL for in-depth analysis.
* **Microservices Architecture:** A distributed system ensuring scalability, resilience, and maintainability.
* **AI-Powered Insights:** Uses **AI to analyze, summarize, and assess the overall impact** and contribution of each contributor.
* **Email Notifications:** Notifies the user via email once the complex analysis is complete.
* **Centralized Logging:** Utilizes **Kibana** for effective, centralized monitoring and debugging.
* **API Gateway:** Uses **Kong** as an API Router for unified access to the backend services.
 
---
 
##  Architecture and Microservices
 
Dev Impact is built upon a modular microservices architecture. The system is designed to decouple major functionalities, improving development agility and operational scalability.
 
###  Context Diagram
 
To understand how **Dev Impact** interacts with external entities (like the User and GitHub), the **Context Diagram** provides a high-level view of the system boundary.
 
**![Context Diagram for Dev Impact](assets/context_diagram.jpeg)**
 
---
 
### Deployment Diagram
 
The **Deployment Diagram** illustrates the physical arrangement of the services, showing where and how each microservice, the API Gateway, and the centralized logging are hosted.
 
**![Deployment Diagram for Dev Impact](assets/deployment_diagram.png)**
 
---
 
