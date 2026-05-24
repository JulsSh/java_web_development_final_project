# 🎟️ Eventify — Event Booking System

A full-stack web application for browsing and booking event tickets, with an admin panel for event and booking management.

> **Tech stack:** Java · Spring Boot · React 18 · TypeScript · Docker

---

## ✨ Features

### For users
- Register and log in
- Browse events with pagination and search
- Book tickets for events
- Manage your bookings (view, cancel)
- Configure email and Telegram notifications

### For admins
- Create, edit and delete events
- View and manage all bookings
- Confirm or cancel bookings

---

## 🏗️ Architecture

```
eventify/
├── booking-service/     # Java Spring Boot backend (REST API, port 8080)
└── booking-ui/          # React + TypeScript frontend (port 3000)
```

**Backend:** Java · Spring Boot · REST API · JWT Authentication
**Frontend:** React 18 · TypeScript · Tailwind CSS · React Hook Form · Axios
**Infrastructure:** Docker · Docker Compose · Nginx

---

## 🚀 Quick Start

### Option 1 — Docker demo (no backend needed)

```bash
cd eventify/booking-ui
npm run docker:mocks
```

App available at **http://localhost:3000**

**Test accounts:**
| Role  | Email               | Password    |
|-------|---------------------|-------------|
| User  | user@example.com    | password123 |
| Admin | admin@example.com   | password123 |

### Option 2 — Full stack with real backend

```bash
# 1. Start the Java backend (port 8080)
cd eventify/booking-service
./mvnw spring-boot:run

# 2. Start the frontend connected to backend
cd eventify/booking-ui
npm run docker:backend
```

---

## 📡 API Overview

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Login |
| POST | `/auth/register` | Register |
| GET | `/events` | List events |
| GET | `/events/{id}` | Event details |
| POST | `/bookings` | Create booking |
| DELETE | `/bookings/{id}` | Cancel booking |
| POST | `/admin/events` | Create event (Admin) |
| PUT | `/admin/bookings/{id}/confirm` | Confirm booking (Admin) |

---

## 📚 Detailed Documentation

- [Frontend README](eventify/booking-ui/README.md) — full setup, Docker options, environment variables, project structure

---

## 👩‍💻 About

Built as a final project after 1 year of Java backend development training.
Background: 12 years in QA Engineering & Test Automation, including 3 years of automation with AI/agentic tools.

The QA background shaped this project: thorough API design, mock support for demo mode, and attention to edge cases throughout.

---

## 📄 License

MIT
