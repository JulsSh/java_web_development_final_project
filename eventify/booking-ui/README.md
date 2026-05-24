# Event Booking Frontend

Frontend for the event booking system, built with React and TypeScript.

## Project Description

The system allows you to:
- Register and log in
- Browse the event list with pagination
- Book tickets for events
- Manage your bookings
- Configure notifications
- Admins: manage events and bookings

## Tech Stack

- **React 18** with TypeScript
- **React Router** for navigation
- **React Hook Form** with Yup for form validation
- **Axios** for HTTP requests
- **Tailwind CSS** for styling
- **Lucide React** for icons
- **React Hot Toast** for notifications

## Quick Start

You have 4 options to run the application:

### 🐳 Option 1: Docker with mocks (demo)

**Prerequisites:**
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

**Run:**
```bash
# macOS/Linux
npm run docker:mocks

# Windows
npm run docker:mocks
# or run scripts/start-docker-mocks.bat
```

**What happens:**
- Frontend starts in a Docker container
- Uses built-in mocks (demo data)
- Application available at http://localhost:3000

### 🐳 Option 2: Docker with real backend

**Prerequisites:**
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- **Your backend must be running on port 8080**

**Run:**
```bash
# macOS/Linux
npm run docker:backend

# Windows
npm run docker:backend
# or run scripts/start-docker-backend.bat
```

**What happens:**
- Frontend starts in a Docker container
- Connects to your backend at http://localhost:8080
- Application available at http://localhost:3000
- **Port 8080 must be free for your backend**

#### Managing Docker containers

**Using npm scripts:**
```bash
# Stop containers
npm run docker:stop

# View logs
npm run docker:logs

# Rebuild images
npm run docker:build
```

**Using Docker Compose commands:**
```bash
# Stop containers
docker-compose down

# View logs
docker-compose logs -f

# Rebuild images
docker-compose build --no-cache
```

#### Direct Docker Compose commands

```bash
# Option 1: Docker with mocks
docker-compose --profile docker-mocks up --build

# Option 2: Docker with real backend
docker-compose --profile docker-backend up --build

# Stop
docker-compose down
```

### 💻 Option 3: Local run with mocks

**Prerequisites:**
- Node.js 16+
- npm or yarn

**Install dependencies:**
```bash
npm install
```

**Run:**
```bash
npm run start:mocks
```

**What happens:**
- Frontend starts locally via npm
- Uses built-in mocks (demo data)
- Application available at http://localhost:3000

### 💻 Option 4: Local run with real backend

**Prerequisites:**
- Node.js 16+
- npm or yarn
- **Your backend must be running on port 8080**

**Install dependencies:**
```bash
npm install
```

**Run:**
```bash
npm run start:backend
```

**What happens:**
- Frontend starts locally via npm
- Connects to your backend at http://localhost:8080
- Application available at http://localhost:3000
- **Port 8080 must be free for your backend**

### 🔧 Alternative method (via .env file)

For options 3 and 4 you can also use a `.env` file:

```bash
# Copy example configuration
cp env.example .env
```

Then edit `.env` based on your needs:

```env
# For mocks (Option 3)
REACT_APP_USE_MOCKS=true

# For real backend (Option 4)
REACT_APP_API_URL=http://localhost:8080
REACT_APP_USE_MOCKS=false
```

And run:
```bash
npm start
```

**Note:** Using a `.env` file is less convenient than npm scripts, as it requires manual editing when switching between modes.

### 🔑 Test accounts for demo

**Regular user:**
- Email: `user@example.com`
- Password: `password123`

**Admin:**
- Email: `admin@example.com`
- Password: `password123`

## OS-specific instructions

### Windows

1. **Install Node.js:**
   - Download Node.js from the official site: https://nodejs.org/
   - Install following the installer instructions
   - Verify installation: `node --version` and `npm --version`

2. **Clone and run:**
   ```cmd
   git clone <repository-url>
   cd event-booking-frontend
   npm install
   npm start
   ```

3. **Possible issues:**
   - If you get permission errors, run the command prompt as Administrator
   - For npm cache issues: `npm cache clean --force`

### macOS

1. **Install Node.js:**
   - Recommended via Homebrew:
     ```bash
     brew install node
     ```
   - Or download from the official site: https://nodejs.org/

2. **Clone and run:**
   ```bash
   git clone <repository-url>
   cd event-booking-frontend
   npm install
   npm start
   ```

3. **Possible issues:**
   - For permission issues: `sudo npm install`
   - If port 3000 is busy, npm will automatically suggest another port

## Project Structure

```
├── src/                    # Application source code
│   ├── components/         # React components
│   │   ├── AuthForm.tsx    # Authentication form
│   │   ├── Navigation.tsx  # Navigation
│   │   ├── EventList.tsx   # Event list
│   │   ├── EventDetail.tsx # Event details
│   │   ├── BookingList.tsx # Booking list
│   │   ├── AdminBookingList.tsx # Admin: manage bookings
│   │   └── NotificationSettings.tsx # Notification settings
│   ├── contexts/           # React contexts
│   │   └── AuthContext.tsx # Auth context
│   ├── services/           # API services
│   │   ├── api.ts         # Main API service
│   │   └── mockApi.ts     # Mocks for demo
│   ├── types/              # TypeScript types
│   │   └── index.ts       # Type definitions
│   ├── App.tsx            # Root component
│   └── index.tsx          # Entry point
├── scripts/               # Launch scripts
│   ├── start-docker-mocks.sh    # Start Docker with mocks (Unix)
│   ├── start-docker-backend.sh  # Start Docker with backend (Unix)
│   ├── stop-docker.sh           # Stop Docker (Unix)
│   ├── start-docker-mocks.bat   # Start Docker with mocks (Windows)
│   ├── start-docker-backend.bat # Start Docker with backend (Windows)
│   └── stop-docker.bat          # Stop Docker (Windows)
├── Dockerfile             # Docker image configuration
├── docker-compose.yml     # Docker Compose configuration
├── nginx.conf             # Nginx configuration
├── backend-stub.conf      # Backend stub
├── .dockerignore          # Docker exclusions
├── env.example            # Example environment variables
├── QUICKSTART.md          # Quick start guide
└── README.md              # Project documentation
```

## API Endpoints

### Authentication
- `POST /auth/login` - Log in
- `POST /auth/register` - Register

### Events
- `GET /events` - List events
- `GET /events/{id}` - Event details
- `POST /admin/events` - Create event (Admin)
- `PUT /admin/events/{id}` - Update event (Admin)
- `DELETE /admin/events/{id}` - Delete event (Admin)

### Bookings
- `GET /bookings` - My bookings
- `POST /bookings` - Create booking
- `DELETE /bookings/{id}` - Cancel booking
- `GET /admin/bookings` - All bookings (Admin)
- `PUT /admin/bookings/{id}/confirm` - Confirm booking (Admin)
- `DELETE /admin/bookings/{id}` - Delete booking (Admin)

### Notifications
- `GET /user/notifications` - Notification settings
- `PUT /user/notifications` - Update settings
- `DELETE /user/notifications` - Reset settings
- `POST /user/telegram/link` - Link Telegram

## Configuration

### Environment variables

| Variable | Description | Default | Note |
|----------|-------------|---------|------|
| `REACT_APP_API_URL` | Your backend URL | `http://localhost:8080` | For Docker use `http://host.docker.internal:8080` |
| `REACT_APP_USE_MOCKS` | Use built-in mocks | `true` | `true` = demo data, `false` = real API |

### Docker configuration

#### Docker Compose profiles

- **`docker-mocks`** - Option 1: Frontend in Docker with mocks
- **`docker-backend`** - Option 2: Frontend in Docker with real backend

#### Production setup

For production, create a `.env.production` file:

```env
REACT_APP_API_URL=https://your-backend-domain.com
REACT_APP_USE_MOCKS=false
```

And build the image:

```bash
docker build -t event-booking-frontend:latest .
```

#### Port customization

To change ports, edit `docker-compose.yml`:

```yaml
services:
  frontend-mocks:
    ports:
      - "YOUR_PORT:80"  # Change YOUR_PORT to the desired port
```

#### Docker specifics

When running inside a Docker container, use `host.docker.internal` to access the backend on the host machine:

```env
REACT_APP_API_URL=http://host.docker.internal:8080
```

## Production Build

```bash
npm run build
```

Built files will be in the `build/` folder.

## Testing

```bash
npm test
```

## NPM Scripts

### Main commands
- `npm start` - Start in development mode (with .env settings)
- `npm run build` - Build for production
- `npm run test` - Run tests
- `npm run eject` - Eject configuration (irreversible)

### Docker commands
- `npm run docker:mocks` - Option 1: Docker with mocks
- `npm run docker:backend` - Option 2: Docker with real backend
- `npm run docker:stop` - Stop Docker containers
- `npm run docker:build` - Build Docker images
- `npm run docker:logs` - View container logs

### Local commands
- `npm run start:mocks` - Option 3: Local run with mocks
- `npm run start:backend` - Option 4: Local run with real backend

### Additional commands
- `npm run test:coverage` - Tests with coverage

## Demo mode features

When running with mocks:
- All data is stored in browser memory
- Data resets on page reload
- API delays are simulated for realism
- Pre-configured test accounts are available

## Support

### Troubleshooting

#### For local run:
1. Check Node.js version (must be 16+)
2. Delete `node_modules` and `package-lock.json`, then run `npm install`
3. Clear npm cache: `npm cache clean --force`
4. Make sure port 3000 is free

#### For Docker:
1. **Port busy**: Change the port in `docker-compose.yml` or stop the process on port 3000
2. **Build errors**: Clear Docker cache: `docker system prune -a`
3. **Permission issues**: Run Docker as administrator
4. **Container won't start**: Check logs: `docker-compose logs frontend-mocks`

#### Common issues:

**Docker not installed:**
- Windows/macOS: Download Docker Desktop from the [official site](https://www.docker.com/products/docker-desktop)
- Linux: Follow the [installation guide](https://docs.docker.com/engine/install/)

**Port 3000 busy:**
```bash
# Find the process on port 3000
lsof -i :3000  # macOS/Linux
netstat -ano | findstr :3000  # Windows

# Stop the process
kill -9 <PID>  # macOS/Linux
taskkill /PID <PID> /F  # Windows
```

**Docker build errors:**
```bash
# Clear all Docker resources
docker system prune -a --volumes

# Rebuild image
docker-compose build --no-cache
```

## License

MIT
