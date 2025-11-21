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
 
### 🔧 Create Gateway Service
 
#### 1. Navigate to Gateway Services
 
- Click on **Gateway Services** in the left side navigation panel
- Click on **+ New Gateway Service** button (top right)
 
#### 2. Configure Service Settings
 
Fill in the service configuration as shown below:
 
![Kong Service Configuration 1](./assets/kong-service-1.png)
 
**Service Configuration Details:**
 
| Field                | Value                                    |
| -------------------- | ---------------------------------------- |
| **Name**             | `analyser-backend-service`               |
| **Protocol**         | `http`                                   |
| **Host**             | `host.docker.internal` (Mac/Windows) or `172.17.0.1` (Linux) |
| **Port**             | `8083`                                   |
| **Path**             | `/` (leave empty or root)                |
 
![Kong Service Configuration 2](./assets/kong-service-2.png)
 
#### 3. Save the Service
 
- Click **Create** or **Save** button at the bottom
- You should see a success message
 
---
 
### 🛣️ Create Route for the Service
 
#### 1. Navigate to Routes
 
- From the service details page, click on the **Routes** tab
- Alternatively, click **Routes** in the left navigation and then **+ New Route**
 
#### 2. Configure Route Settings
 
![Kong Route Configuration](./assets/kong-route-config.png)
 
**Route Configuration Details:**
 
| Field                | Value                                    |
| -------------------- | ---------------------------------------- |
| **Name**             | `analyser-backend-route`                 |
| **Protocols**        | `http`, `https`                          |
| **Hosts**            | `localhost`                              |
| **Paths**            | `/api/analyser` or `/`                   |
| **Methods**          | *(Configure in next step)*               |
| **Strip Path**       | ❌ **UNCHECKED** (Important!)            |
 
#### 3. Click **Create** to save the route
 
---
 
### ✅ Enable HTTP Methods
 
#### 1. Configure Methods
 
From the route configuration page, locate the **Methods** section:
 
![Kong Methods Configuration](./assets/kong-methods.png)
 
**Enable the following HTTP methods:**
- ✅ GET
- ✅ POST
- ✅ PUT
- ✅ PATCH
- ✅ DELETE
- ✅ OPTIONS
- ✅ HEAD
 
> **Important:** Do **NOT** use custom function - select all standard HTTP methods manually
 
#### 2. Save the methods configuration
 
---
 
### ⚙️ Advanced Configuration
 
#### 1. Access Advanced Settings
 
- Scroll down to **Advanced Settings** or click **Advanced Filters**
- Locate the **Strip Path** option
 
![Kong Advanced Settings](./assets/kong-advanced.png)
 
#### 2. Configure Strip Path
 
**⚠️ CRITICAL:** Ensure **Strip Path** is **UNCHECKED** (disabled)
 
| Setting              | Value                                    |
| -------------------- | ---------------------------------------- |
| **Strip Path**       | ❌ **DISABLED/UNCHECKED**                |
| **Preserve Host**    | ✅ **ENABLED** (recommended)             |
 
> **Why?** Unchecking "Strip Path" ensures the full API path is forwarded to the backend service.
 
#### 3. Save all changes
 
---
 
### 🧪 Test Kong Gateway
 
Before updating frontend configuration, verify Kong is working:
 
```bash
# Test through Kong Gateway (port 8000)
curl http://localhost:8000/api/health
 
# Compare with direct backend access (port 8083)
curl http://localhost:8083/api/health
```
 
Both should return the same response if Kong is configured correctly.
 
---
 
### 🔄 Update Frontend Environment Variables
 
> **⚠️ IMPORTANT:** Only proceed with this step if Kong routing is successfully working!
 
#### ✅ If Kong Setup is Successful:
 
1. **Open `.env.local` in your frontend projects:**
   
   **Analyser App Frontend:**
   ```bash
   cd analyser-app-frontend
   nano .env.local  # or use your preferred editor
   ```
 
   **Email Frontend:**
   ```bash
   cd email-sender/email-frontend
   nano .env.local
   ```
 
2. **Update the API base URL:**
   ```env
   # Before (Direct Backend Connection)
   NEXT_PUBLIC_API_BASE=http://localhost:8083
 
   # After (Through Kong Gateway)
   NEXT_PUBLIC_API_BASE=http://localhost:8000
   ```
 
3. **Restart the development servers:**
   ```bash
   # Stop the running server (Ctrl+C)
   # Then restart
   npm run dev
   ```
 
#### ❌ If Kong Setup Has Issues:
 
- **Leave the configuration as `localhost:8083`** to connect directly to the backend
- Your application will work without the API Gateway
- Troubleshoot Kong issues separately (see troubleshooting section)
 
---
 
### 🔍 Verify Kong Gateway Integration
 
Open your browser and test:
 
| Test                 | Direct Backend URL            | Through Kong Gateway URL      |
| -------------------- | ----------------------------- | ----------------------------- |
| **Health Check**     | http://localhost:8083/health  | http://localhost:8000/health  |
| **API Endpoint**     | http://localhost:8083/api/... | http://localhost:8000/api/... |
 
Both should return identical responses.
 
---
 
## 📊 Microservices Port Reference
 
### Backend Services
 
| Service                              | Technology   | Port | URL                       | Purpose                          |
| ------------------------------------ | ------------ | ---- | ------------------------- | -------------------------------- |
| **Analyser App Backend**             | Spring Boot  | 8083 | http://localhost:8083     | Main analysis service            |
| **Version Control Data Fetcher**     | Spring Boot  | 8084 | http://localhost:8084     | Git data fetching service        |
| **Email Backend**                    | Spring Boot  | 8086 | http://localhost:8086     | Email notification service       |
| **Code Contribution Analyser**       | Django       | 8085 | http://localhost:8085     | Python analysis engine           |
 
### Frontend Applications
 
| Application                          | Technology   | Port | URL                       | Purpose                          |
| ------------------------------------ | ------------ | ---- | ------------------------- | -------------------------------- |
| **Analyser App Frontend**            | Next.js      | 3000 | http://localhost:3000     | Main web interface               |
| **Email Frontend**                   | Next.js      | 3001 | http://localhost:3001     | Email management interface       |
 
### Infrastructure Services
 
| Service                              | Technology   | Port(s)     | URL(s)                             | Purpose                          |
| ------------------------------------ | ------------ | ----------- | ---------------------------------- | -------------------------------- |
| **Kong API Gateway**                 | Kong         | 8000        | http://localhost:8000              | API Gateway (proxy)              |
| **Kong Admin API**                   | Kong         | 8001        | http://localhost:8001              | Kong Admin REST API              |
| **Kong Admin GUI (Konga)**           | Konga        | 8002        | http://localhost:8002              | Web-based admin interface        |
| **PostgreSQL (Main App)**            | PostgreSQL   | 5432        | localhost:5432                     | Main application database        |
| **PostgreSQL (Analyser)**            | PostgreSQL   | 5433        | localhost:5433                     | Analyser service database        |
| **PostgreSQL (Version Control)**     | PostgreSQL   | 5434        | localhost:5434                     | Version control database         |
| **Redis**                            | Redis        | 6379        | localhost:6379                     | Caching layer                    |
 
---
 
## 🔧 Troubleshooting
 
### Common Issues and Solutions
 
#### 🐳 Docker Issues
 
| Issue                                    | Solution                                                                                              |
| ---------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **Docker containers not starting**       | • Ensure Docker Desktop is running<br>• Check for port conflicts: `lsof -i :8000`<br>• Restart Docker Desktop |
| **Container exits immediately**          | • Check container logs: `docker logs <container-name>`<br>• Verify Docker Compose configuration      |
| **Out of memory errors**                 | • Increase Docker memory limit in Docker Desktop settings<br>• Close unnecessary containers          |
 
#### 🗄️ Database Issues
 
| Issue                                    | Solution                                                                                              |
| ---------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **Database connection failed**           | • Verify Docker containers are running: `docker ps`<br>• Check database credentials in DBeaver<br>• Restart database container |
| **Migrations fail**                      | • Ensure database is accessible<br>• Drop and recreate database if needed<br>• Check migration files for errors |
| **Port already in use**                  | • Find process: `lsof -i :5432`<br>• Kill process or change port in docker-compose.yml              |
 
#### 🐍 Python Backend Issues
 
| Issue                                    | Solution                                                                                              |
| ---------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **Package installation fails**           | • Ensure virtual environment is activated: `source venv/bin/activate`<br>• Upgrade pip: `pip install --upgrade pip`<br>• Use `pip install --no-cache-dir` |
| **Django server won't start**            | • Check for syntax errors<br>• Verify database connection<br>• Check port 8085 is not in use         |
| **Module not found error**               | • Reinstall dependencies: `pip install -r requirements.txt`<br>• Check Python version compatibility  |
 
#### ⚛️ Frontend Issues
 
| Issue                                    | Solution                                                                                              |
| ---------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **npm install fails**                    | • Delete `node_modules` and `package-lock.json`<br>• Run `npm cache clean --force`<br>• Try `npm install --legacy-peer-deps` |
| **Build errors**                         | • Check Node.js version: `node -v` (should be 16+)<br>• Clear Next.js cache: `rm -rf .next`         |
| **Environment variables not loading**    | • Ensure `.env.local` exists in project root<br>• Restart development server<br>• Verify variable names start with `NEXT_PUBLIC_` |
| **API calls failing**                    | • Check `NEXT_PUBLIC_API_BASE` URL<br>• Verify backend service is running<br>• Check browser console for CORS errors |
 
#### 🌐 Kong Gateway Issues
 
| Issue                                    | Solution                                                                                              |
| ---------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **Kong GUI not accessible**              | • Check Kong container is running: `docker ps | grep kong`<br>• Access admin API: `curl http://localhost:8001` |
| **Routing not working**                  | • Verify service and route configuration<br>• Check "Strip Path" is **unchecked**<br>• Use direct backend connection (port 8083) as fallback |
| **404 errors through Kong**              | • Check route paths match exactly<br>• Verify all HTTP methods are enabled<br>• Check backend service is accessible |
| **502 Bad Gateway**                      | • Verify backend service is running<br>• Check host configuration (`host.docker.internal` on Mac/Windows)<br>• Test direct backend connection |
 
---
 
### 🔍 Diagnostic Commands
 
#### Check Running Services
 
```bash
# View all running Docker containers
docker ps
 
# View all containers (including stopped)
docker ps -a
 
# Check specific container logs
docker logs <container-name>
 
# Follow container logs in real-time
docker logs -f <container-name>
```
 
#### Check Port Usage
 
**macOS/Linux:**
```bash
# Check what's using a specific port
lsof -i :8083
 
# Check all listening ports
lsof -i -P -n | grep LISTEN
 
# Kill process using a port
lsof -ti:8083 | xargs kill -9
```
 
**Windows (Command Prompt):**
```bash
# Check what's using a specific port
netstat -ano | findstr :8083
 
# Kill process by PID
taskkill /PID <process_id> /F
```
 
#### Test API Connectivity
 
```bash
# Test backend service directly
curl http://localhost:8083/api/health
 
# Test through Kong Gateway
curl http://localhost:8000/api/health
 
# Test with verbose output
curl -v http://localhost:8083/api/health
```
 
#### Verify Docker Network
 
```bash
# List Docker networks
docker network ls
 
# Inspect specific network
docker network inspect <network-name>
 
# Check container IP address
docker inspect <container-name> | grep IPAddress
```
 
---
 
### 🆘 Getting Help
 
If you encounter issues not covered here:
 
1. **Check Container Logs:**
   ```bash
   docker logs <container-name>
   ```
 
2. **Verify Service Health:**
   ```bash
   curl http://localhost:<port>/health
   ```
 
3. **Review Configuration Files:**
   - Docker Compose files
   - Environment variables (`.env`, `.env.local`)
   - Application configuration files
 
4. **Test Incrementally:**
   - Start with database → backend → frontend
   - Test each service independently before integration
 
---