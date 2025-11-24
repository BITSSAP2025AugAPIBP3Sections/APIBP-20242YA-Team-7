# Contributing Guide

Welcome to **Dev Impact**!

We're excited that you're interested in contributing to this open-source project. Dev Impact is a microservices-based application that analyzes and quantifies developer contributions within GitHub repositories. This guide will help you get started with contributing to the project.

---

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Project Architecture](#project-architecture)
- [Development Workflow](#development-workflow)
- [Branching Strategy](#branching-strategy)
- [Commit Message Guidelines](#commit-message-guidelines)
- [Coding Standards](#coding-standards)
- [Testing Requirements](#testing-requirements)
- [Submitting Changes](#submitting-changes)
- [Adding New Features](#adding-new-features)
- [Documentation](#documentation)
- [Communication](#communication)

---

## Code of Conduct

We are committed to providing a welcoming and inclusive environment for all contributors. Please:

- Be respectful and considerate in all interactions
- Provide constructive feedback
- Focus on what is best for the community
- Show empathy towards other community members

---

## Getting Started

### Prerequisites

Before you begin contributing, ensure you have completed the setup process outlined in **[SUPPORT.md](./SUPPORT.md)**. You should have:

- All required tools installed (Docker, JDK, Node.js, Python, etc.)
- Docker containers running
- Databases configured in DBeaver
- All services running locally

### First-Time Contributors

1. **Fork the repository** to your GitHub account
2. **Clone your fork** locally:
```bash
   git clone https://github.com/YOUR-USERNAME/REPOSITORY-NAME.git
   cd REPOSITORY-NAME
```
3. **Set up the upstream remote:**
```bash
   git remote add upstream https://github.com/ORIGINAL-OWNER/REPOSITORY-NAME.git
```
4. **Complete the setup** following [SUPPORT.md](./SUPPORT.md)
5. **Verify all services** are running correctly

---

## Project Architecture

Dev Impact follows a **microservices architecture**. Understanding the service boundaries is crucial for effective contributions.

### Service Structure
```
project-root/
├── analyser-app-backend/          # Main App Backend (Java/Spring Boot) - Port 8083
├── analyser-app-frontend/         # Main App Frontend (Next.js) - Port 3000
├── code-contribution-analyser/    # Code Analyser (Python/Django) - Port 8085
├── version-control-data-fetcher/  # Version Control Fetcher (Java/Spring Boot) - Port 8081
├── email-sender/
│   ├── email-backend/             # Email Service (Java/Spring Boot) - Port 8082
│   └── email-frontend/            # Email Frontend (Next.js)
├── devtools/                      # Docker setup scripts
├── load-testing-scripts/          # JMeter load testing scripts
└── assets/                        # Project assets and diagrams
```

### Core Principles

1. **Respect Service Boundaries** – No cross-service runtime code imports. Services communicate via REST APIs only.
2. **Consistent Port Usage** – Use the designated ports listed in the [README.md](./README.md).
3. **Documentation First** – Update relevant documentation when changing service behavior.

---

## Development Workflow

### Setting Up Your Development Environment

1. **Sync with upstream** before starting new work:
```bash
   git checkout main
   git fetch upstream
   git merge upstream/main
```

2. **Start required services:**
```bash
   cd devtools
   ./dockerScript.sh start
```

3. **Start the service(s) you're working on:**
   - **Java services:** Open in IntelliJ IDEA and run Spring Boot application
   - **Python service:** Activate venv and run `python manage.py runserver 8085`
   - **Frontend services:** Run `npm run dev` in the respective frontend directory

### Making Changes

1. Create a new branch (see [Branching Strategy](#branching-strategy))
2. Make your changes following the [Coding Standards](#coding-standards)
3. Test your changes thoroughly
4. Update documentation if needed
5. Commit your changes with clear messages
6. Push to your fork and create a Pull Request

---

## Branching Strategy

We follow a simplified Git workflow to keep `main` stable and deployable.

### Branch Naming Convention

| Branch Type | Purpose | Example |
|-------------|---------|---------|
| `feat/<description>` | New features or enhancements | `feat/ai-model-integration` |
| `fix/<description>` | Bug fixes | `fix/auth-token-expiry` |
| `docs/<description>` | Documentation updates | `docs/update-api-reference` |
| `chore/<description>` | Tooling, dependencies, housekeeping | `chore/update-spring-boot` |
| `refactor/<description>` | Code refactoring without feature changes | `refactor/analyser-service` |
| `test/<description>` | Adding or updating tests | `test/email-service-unit-tests` |

### Branch Guidelines

- Keep branches **focused and small** – one logical change per branch
- **Delete merged branches** promptly to keep the repository clean
- Always branch from the latest `main`
- Regularly sync your branch with `main` to avoid merge conflicts

---

## Commit Message Guidelines

We follow **Conventional Commits** specification for clear and semantic commit history.

### Format
```
<type>(<scope>): <short summary>

[optional body]

[optional footer]
```

### Types

- `feat`: New feature for the user
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, missing semicolons, etc.)
- `refactor`: Code refactoring without changing functionality
- `perf`: Performance improvements
- `test`: Adding or updating tests
- `build`: Changes to build system or dependencies
- `ci`: CI/CD configuration changes
- `chore`: Maintenance tasks

### Scopes

Use the service name as scope:

- `main-backend`
- `analyser-backend`
- `code-analyser`
- `version-control`
- `email-backend`
- `frontend`
- `gateway`
- `docs`
- `devtools`

### Examples
```bash
feat(code-analyser): add AI-powered contribution scoring algorithm

fix(main-backend): resolve JWT token refresh issue on expired sessions

docs(readme): update architecture diagram with Kong integration

chore(analyser-backend): upgrade Spring Boot to version 3.2.0

test(email-backend): add unit tests for email template rendering
```

---

## Coding Standards

### General Principles

- **Write clean, readable code** – Code is read more often than written
- **Follow the DRY principle** – Don't Repeat Yourself
- **Keep functions small** – Each function should do one thing well
- **Use meaningful names** – Variables, functions, and classes should be self-documenting
- **Comment wisely** – Explain *why*, not *what*
- **Handle errors properly** – Never swallow exceptions without logging

### Java/Spring Boot Services

| Aspect | Standard |
|--------|----------|
| **Formatting** | Use IntelliJ IDEA default formatter |
| **Naming** | PascalCase for classes, camelCase for methods/variables |
| **Package Structure** | `controller`, `service`, `repository`, `model`, `dto`, `config` |
| **Logging** | Use SLF4J with structured logging (JSON format preferred) |
| **Dependency Injection** | Constructor injection preferred over field injection |
| **API Documentation** | Use Swagger/OpenAPI annotations |

### Python/Django Service

| Aspect | Standard |
|--------|----------|
| **Formatting** | Black (line length: 100) |
| **Naming** | snake_case for functions/variables, PascalCase for classes |
| **Type Hints** | Use type hints where applicable (Python 3.8+) |
| **Logging** | Use Django logging framework with structured format |

### Next.js/TypeScript Frontends

| Aspect | Standard |
|--------|----------|
| **Formatting** | Prettier (use project's `.prettierrc` if exists) |
| **TypeScript** | Strict mode enabled |
| **Components** | Functional components with hooks |
| **File Naming** | PascalCase for components, camelCase for utilities |
| **State Management** | React hooks (useState, useContext, useReducer) |
| **Styling** | CSS Modules or Tailwind CSS (as per project standard) |

### Environment Variables

- **Never commit secrets** or API keys
- Use `.env.example` files with placeholder values
- Document all environment variables in [SUPPORT.md](./SUPPORT.md) or [README.md](./README.md)
- Use descriptive variable names (e.g., `GITHUB_CLIENT_ID`, not `GH_ID`)

### Error Handling

- **Always log errors** with appropriate context and correlation IDs
- Return meaningful error messages to the client
- Use proper HTTP status codes
- Include error handling in all external API calls

---

## Testing Requirements

### Minimum Requirements

All services must have:

1. **Critical Path Tests** – Test main functionality of the service
2. **Edge Case Handling** – Test error scenarios and boundary conditions

---

## Submitting Changes

### Pull Request Process

1. **Ensure your branch is up to date:**
```bash
   git fetch upstream
   git rebase upstream/main
```

2. **Push to your fork:**
```bash
   git push origin feat/your-feature-name
```

3. **Create a Pull Request** from your fork to the main repository

4. **Fill out the PR template** with:
   - Clear description of changes
   - Related issue numbers (use `Closes #123` or `Fixes #456`)
   - Screenshots (for UI changes)
   - Testing performed
   - Checklist completion

### PR Checklist

Before submitting, ensure:

- [ ] Code follows the project's coding standards
- [ ] All tests pass locally
- [ ] New tests added for new functionality
- [ ] Documentation updated (README, SETUP, API docs)
- [ ] No console logs or debug code left in
- [ ] Environment variables documented in `.env.example`
- [ ] No secrets or API keys committed
- [ ] Commit messages follow Conventional Commits format
- [ ] Branch is up to date with `main`

### Review Process

- **At least one approval** required before merging
- Address all review comments or discuss concerns
- Maintain a respectful and constructive dialogue
- Be patient – reviewers are volunteers too!

### Review Guidelines for Reviewers

When reviewing PRs, check for:

- **Scope** – Is the PR focused on a single concern?
- **Code Quality** – Does it follow coding standards?
- **Testing** – Are tests adequate and passing?
- **Documentation** – Is it updated where necessary?
- **Service Boundaries** – Are they respected?
- **Security** – No secrets, SQL injection risks, XSS vulnerabilities?
- **Performance** – No obvious performance issues?
- **Breaking Changes** – Are they documented and necessary?

---

## Adding New Features

### Before Starting

1. **Check existing issues** – Someone might already be working on it
2. **Open a discussion issue** for significant features
3. **Get feedback** from maintainers on your approach
4. **Break down large features** into smaller, incremental PRs

### Feature Development Workflow

1. **Document the feature** – Write or update relevant documentation
2. **Design the API** – Define endpoints, request/response schemas
3. **Implement in stages:**
   - Database schema changes (if needed)
   - Backend logic
   - API endpoints
   - Frontend integration
   - Tests
4. **Update OpenAPI/Swagger** documentation
5. **Add to changelog** or release notes

### Adding a New Microservice

If you need to add a new microservice:

1. **Open a proposal issue** describing:
   - Service purpose and responsibilities
   - Technology stack (Java/Python/Node.js)
   - API contracts
   - Database requirements
   - Integration points with existing services

2. **Get approval** from maintainers

3. **Implementation steps:**
   - Create service directory following project structure
   - Add Dockerfile and update `docker-compose.yml`
   - Configure database (if needed) in `devtools/dockerScript.sh`
   - Document service in [README.md](./README.md) and [SUPPORT.md](./SUPPORT.md)
   - Update port configuration table
   - Add Kong routing configuration
   - Implement core functionality
   - Add tests
   - Update architecture diagrams in `assets/`

4. **Submit PR** with comprehensive documentation

---

## Documentation

Good documentation is as important as good code!

### What to Document

- **New features** – How to use them
- **API changes** – Update OpenAPI specs
- **Configuration changes** – Environment variables, ports, etc.
- **Breaking changes** – Migration guides
- **Architecture decisions** – Consider adding ADRs in `docs/adr/`
- **Setup changes** – Update [SUPPORT.md](./SUPPORT.md)

### Documentation Style

- Write in clear, concise English
- Use examples and code snippets
- Include screenshots for UI features
- Keep formatting consistent with existing docs
- Use tables for structured information
- Link related documentation

### Where to Document

| Type | Location |
|------|----------|
| **Project overview** | [README.md](./README.md) |
| **Contribution guidelines** | [CONTRIBUTING.md](./CONTRIBUTING.md) (this file) |
| **API documentation** | Swagger/OpenAPI specs in each service |
| **Architecture decisions** | `docs/adr/` (if created) |
| **Support and Setup information** | [SUPPORT.md](./SUPPORT.md) |
| **Code documentation** | Inline comments and docstrings |

---

## Communication

### How to Get Help

- **Questions about setup?** Check [SUPPORT.md](./SUPPORT.md)
- **Feature discussions?** Open a GitHub issue with `[Discussion]` prefix
- **Bug reports?** Open an issue with detailed reproduction steps
- **General questions?** Use GitHub Discussions (if enabled) or comment on relevant issues

### Opening Issues

When opening an issue, include:

**For Bugs:**
- Clear, descriptive title
- Steps to reproduce
- Expected vs actual behavior
- Environment details (OS, versions, etc.)
- Error logs or screenshots
- Relevant code snippets

**For Features:**
- Clear description of the proposed feature
- Use case and motivation
- Proposed implementation approach (if you have one)
- Potential impact on existing functionality

### Response Times

- We aim to respond to issues within **3-5 business days**
- Be patient – maintainers may have other commitments
- Provide additional information if requested
- Feel free to politely bump if no response after a week

---

## Community and Recognition

### Contributors

All contributors will be recognized in our:
- GitHub contributors page
- Release notes (for significant contributions)
- Project documentation

### Building a Positive Community

- Welcome newcomers warmly
- Offer help to those struggling
- Share knowledge generously
- Celebrate wins together
- Learn from mistakes constructively

---

## License

By contributing to Dev Impact, you agree that your contributions will be licensed under the same license as the project.

---

## Thank You!

Thank you for taking the time to contribute to Dev Impact! Your efforts help make this project better for everyone. Whether you're fixing a typo, reporting a bug, or adding a major feature – every contribution matters.

Happy coding! ❤️

---

## Quick Links

- [README.md](./README.md) – Project overview
- [SUPPORT.md](./SUPPORT.md) – Support resources

---

**Questions?** Feel free to reach out by opening an issue or discussion!