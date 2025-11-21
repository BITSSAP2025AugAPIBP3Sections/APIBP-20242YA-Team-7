# 🚀 Project Setup Guide
 
A comprehensive guide to setting up the complete microservices architecture with Java backends, Python backend, and Next.js frontends.
 
---
 
## 📋 Table of Contents
 
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
  - [Step 1: Clone the Repository](#step-1-clone-the-repository)
  - [Step 2: Open Projects in IDEs](#step-2-open-projects-in-ides)
  - [Step 3: Setup Docker Containers](#step-3-setup-docker-containers)
  - [Step 4: Database Configuration](#step-4-database-configuration)
  - [Step 5: Python Backend Setup](#step-5-python-backend-setup)
  - [Step 6: Frontend Setup](#step-6-frontend-setup)
  - [Step 7: API Gateway Routing (Kong)](#step-7-api-gateway-routing-kong)
- [Reference](#reference)
  - [Microservices Port Reference](#microservices-port-reference)
  - [Troubleshooting](#troubleshooting)
 
---
 
## ✅ Prerequisites
 
Before starting the setup, ensure you have the following installed on your system:
 
| Tool                | Version    | Purpose                  | Download Link                                                      |
| ------------------- | ---------- | ------------------------ | ------------------------------------------------------------------ |
| **Docker Desktop**  | Latest     | Container management     | [Download Docker](https://www.docker.com/products/docker-desktop)  |
| **DBeaver**         | Latest     | Database management      | [Download DBeaver](https://dbeaver.io/download/)                   |
| **JDK**             | 11+        | Java backend runtime     | [Download JDK](https://www.oracle.com/java/technologies/downloads/) |
| **Node.js**         | 16+        | Next.js frontend runtime | [Download Node.js](https://nodejs.org/)                            |
| **Python**          | 3.8+       | Python backend runtime   | [Download Python](https://www.python.org/downloads/)               |
| **IntelliJ IDEA**   | Latest     | Java IDE (recommended)   | [Download IntelliJ](https://www.jetbrains.com/idea/download/)      |
| **VS Code**         | Latest     | Python/Next.js IDE       | [Download VS Code](https://code.visualstudio.com/)                 |
 
---
 
## 📁 Project Structure
 
After cloning, your project will contain the following structure:
 
```
project-root/
├── analyser-app-backend/          # Java backend (Spring Boot)
├── analyser-app-frontend/         # Next.js frontend
├── code-contribution-analyser/    # Python backend (Django)
├── version-control-data-fetcher/  # Java backend (Spring Boot)
├── email-sender/
│   ├── email-backend/             # Java backend (Spring Boot)
│   └── email-frontend/            # Next.js frontend
└── devtools/                      # Docker setup scripts
```
 
### 🛠️ Technology Stack
 
| Component              | Technology  | Count        |
| ---------------------- | ----------- | ------------ |
| **Backend (Java)**     | Spring Boot | 3 services   |
| **Backend (Python)**   | Django      | 1 service    |
| **Frontend**           | Next.js     | 2 apps       |
 
---
 
## 🔧 Setup Instructions
 
### Step 1: Clone the Repository
 
Clone the repository to your preferred location:
 
```bash
git clone <repository-url>
cd <project-folder>
```
 
---
 
## Step 2: Open Projects in IDEs
 
### 💻 Recommended IDE Setup
 
| Project Type          | Recommended IDE    | Projects                                                                      |
| --------------------- | ------------------ | ----------------------------------------------------------------------------- |
| **Java Projects**     | IntelliJ IDEA      | `analyser-app-backend`, `version-control-data-fetcher`, `email-sender/email-backend` |
| **Python Project**    | VS Code            | `code-contribution-analyser`                                                  |
| **Next.js Projects**  | VS Code            | `analyser-app-frontend`, `email-sender/email-frontend`                        |
 
### 📝 Steps
 
1. **Open IntelliJ IDEA**
   - Go to `File` → `Open`
   - Import each Java project (`analyser-app-backend`, `version-control-data-fetcher`, `email-sender/email-backend`)
   - Wait for Maven/Gradle dependencies to download
 
2. **Open VS Code**
   - Go to `File` → `Open Folder`
   - Open the Python project (`code-contribution-analyser`)
   - Open each Next.js project (`analyser-app-frontend`, `email-sender/email-frontend`) in separate windows
 
---
 
## Step 3: Setup Docker Containers
 
### ⚠️ Prerequisites
 
- Ensure **Docker Desktop** is running before executing the script
- Verify Docker is running: `docker --version`
 
### 🐳 Steps
 
1. **Navigate to the devtools folder:**
   ```bash
   cd devtools
   ```
 
2. **Give execute permissions to the Docker script:**
   ```bash
   chmod +x dockerScript.sh
   ```
 
3. **Execute the Docker script with command:**
   
   **To start containers:**
   ```bash
   ./dockerScript.sh start
   ```
 
   **To stop containers:**
   ```bash
   ./dockerScript.sh stop
   ```
 
   **To remove containers:**
   ```bash
   ./dockerScript.sh remove
   ```
 
4. **Wait** for all containers to be created and started (this may take a few minutes)
 
5. **Verify containers are running:**
   ```bash
   docker ps
   ```
 
### 📦 Expected Containers
 
The script will create and start containers for:
 
| Container          | Purpose                      | Port(s)       |
| ------------------ | ---------------------------- | ------------- |
| **PostgreSQL**     | Main application database    | 5432          |
| **PostgreSQL**     | Analyser database            | 5433          |
| **PostgreSQL**     | Version control database     | 5434          |
| **Redis**          | Caching layer                | 6379          |
| **Kong Gateway**   | API Gateway                  | 8000, 8001    |
 
---
 
## Step 4: Database Configuration
 
Now we'll configure the databases using **DBeaver**.
 
### 🔌 Connection 1: Main Application Database
 
1. Open **DBeaver**
2. Click on **New Database Connection** (or press `Ctrl+Shift+N` / `Cmd+Shift+N`)
3. Select **PostgreSQL**
4. Configure with the following settings:
 
   ![Database Configuration 1](./assets/db-config-1.png)
 
   **Connection Details:**
 
   | Field          | Value                          |
   | -------------- | ------------------------------ |
   | **Host**       | `localhost`                    |
   | **Port**       | `5432`                         |
   | **Database**   | `main_app_db`                  |
   | **Username**   | `user`                         |
   | **Password**   | `pass`                         |
 
5. Click **Test Connection** (bottom left)
6. You should see a success popup:
 
   ![Connection Success](./assets/db-connection-success.png)
 
7. Click **Finish**
 
---
 
### 🔌 Connection 2: Analyser Database
 
Repeat the same process with these credentials:
 
![Database Configuration 2](./assets/db-config-2.png)
 
**Connection Details:**
 
| Field          | Value                          |
| -------------- | ------------------------------ |
| **Host**       | `localhost`                    |
| **Port**       | `5433`                         |
| **Database**   | `analyser_db`                  |
| **Username**   | `user`                         |
| **Password**   | `pass`                         |
 
---
 
### 🔌 Connection 3: Version Control Database
 
![Database Configuration 3](./assets/db-config-3.png)
 
**Connection Details:**
 
| Field          | Value                          |
| -------------- | ------------------------------ |
| **Host**       | `localhost`                    |
| **Port**       | `5434`                         |
| **Database**   | `version_control_db`           |
| **Username**   | `version_control_user`         |
| **Password**   | `version_control_pass`         |
 
---
 
### ✅ Verification
 
- All three database connections should appear in DBeaver's Database Navigator
- You should be able to expand each connection and view the database schemas
- **Java backends are now configured!**
 
---
 
## Step 5: Python Backend Setup
 
### 🐍 Navigate to Python Project
 
```bash
cd code-contribution-analyser
```
 
---
 
### 🔧 Create Virtual Environment
 
Create a Python virtual environment to isolate project dependencies:
 
```bash
python -m venv venv
```
 
> **Note:** Use `python3` instead of `python` if you have multiple Python versions installed.
 
---
 
### ⚡ Activate Virtual Environment
 
**macOS/Linux:**
```bash
source venv/bin/activate
```
 
**Windows (Command Prompt):**
```bash
venv\Scripts\activate
```
 
**Windows (PowerShell):**
```bash
venv\Scripts\Activate.ps1
```
 
> **Tip:** You should see `(venv)` prefix in your terminal after successful activation.
 
---
 
### 📦 Install Required Packages
 
#### Option 1: Install from Requirements File (Recommended)
 
```bash
pip install -r requirements.txt
```
 
#### Option 2: Install Packages Individually
 
```bash
pip install django==4.2
pip install djangorestframework
pip install psycopg2-binary==2.9.3
pip install requests==2.28.1
pip install drf-spectacular==0.27.3
```
 
### 📚 Package List
 
| Package                  | Version | Purpose                              |
| ------------------------ | ------- | ------------------------------------ |
| `django`                 | 4.2     | Web framework                        |
| `djangorestframework`    | Latest  | REST API framework                   |
| `psycopg2-binary`        | 2.9.3   | PostgreSQL database adapter          |
| `requests`               | 2.28.1  | HTTP library for API calls           |
| `drf-spectacular`        | 0.27.3  | OpenAPI schema generation            |
 
---
 
### 🗄️ Run Migrations and Start Server
 
1. **Navigate to the inner project folder:**
   ```bash
   cd codeContributionAnalyser
   ```
 
2. **Apply database migrations:**
   ```bash
   python manage.py migrate
   ```
 
   Expected output:
   ```
   Operations to perform:
     Apply all migrations: admin, auth, contenttypes, sessions
   Running migrations:
     Applying contenttypes.0001_initial... OK
     Applying auth.0001_initial... OK
     ...
   ```
 
3. **Create a superuser (Optional but recommended):**
   ```bash
   python manage.py createsuperuser
   ```
 
4. **Start the development server:**
   ```bash
   python manage.py runserver 8085
   ```
 
   Expected output:
   ```
   Django version 4.2, using settings 'codeContributionAnalyser.settings'
   Starting development server at http://127.0.0.1:8085/
   Quit the server with CONTROL-C.
   ```
 
---
 
### ✅ Verification
 
- Python backend should be running on: **http://localhost:8085**
- Admin panel accessible at: **http://localhost:8085/admin**
- API documentation at: **http://localhost:8085/api/schema/swagger-ui/**
 
---
 
## Step 6: Frontend Setup
 
### 🌐 Main App Frontend
 
#### 1. Navigate to the frontend folder:
 
```bash
cd analyser-app-frontend
```
 
#### 2. Install dependencies:
 
```bash
npm install
```
 
> **Note:** This may take a few minutes. If you encounter errors, try `npm install --legacy-peer-deps`
 
#### 3. Create environment configuration:
 
Create `.env.local` file in the root folder:
 
```bash
touch .env.local
```
 
#### 4. Add environment variables:
 
Open `.env.local` and add the following content:
 
```env
# API Gateway Configuration
# Use port 8000 if routing through Kong API Gateway
# Use port 8083 to connect directly to the backend service
NEXT_PUBLIC_API_BASE=http://localhost:8083
 
# Optional: Additional environment variables
# NEXT_PUBLIC_API_TIMEOUT=5000
# NEXT_PUBLIC_ENV=development
```
 
> **Tip:** Change port to `8000` when using Kong API Gateway in production.
 
#### 5. Start the development server:
 
```bash
npm run dev
```
 
Expected output:
```
> analyser-app-frontend@0.1.0 dev
> next dev
 
- ready started server on 0.0.0.0:3000, url: http://localhost:3000
- info Loaded env from /path/to/analyser-app-frontend/.env.local
```
 
---
 
### 📧 Email Frontend
 
Repeat the same process for the email frontend:
 
#### 1. Navigate to email frontend:
 
```bash
cd email-sender/email-frontend
```
 
#### 2. Install dependencies:
 
```bash
npm install
```
 
#### 3. Create `.env.local`:
 
```bash
touch .env.local
```
 
Add configuration:
```env
NEXT_PUBLIC_API_BASE=http://localhost:8084
```
 
#### 4. Start development server:
 
```bash
npm run dev
```
 
---
 
### ✅ Verification
 
| Application           | URL                          | Status          |
| --------------------- | ---------------------------- | --------------- |
| **Main Frontend**     | http://localhost:3000        | Should be ✅    |
| **Email Frontend**    | http://localhost:3001        | Should be ✅    |
 
> **Note:** Email frontend may run on port `3001` if `3000` is already in use.
 
---
 
### 🔍 Troubleshooting Frontend Issues
 
**Issue: Port already in use**
```bash
# Find and kill process using the port (Mac/Linux)
lsof -ti:3000 | xargs kill -9
 
# Or use a different port
npm run dev -- -p 3001
```
 
**Issue: Module not found errors**
```bash
# Clear npm cache and reinstall
rm -rf node_modules package-lock.json
npm cache clean --force
npm install
```
 
---
 
## Step 7: API Gateway Routing (Kong)
 
### 🌐 Access Kong Admin GUI
 
Open your browser and navigate to:
```
http://localhost:8002
```
 
> **Note:** Ensure the Kong container is running. Verify with `docker ps | grep kong`
 
---
