# 🐳 Docker Setup for Event Booking Frontend

## What was added

### 1. Docker files
- **`Dockerfile`** - Multi-stage build with Node.js and Nginx
- **`docker-compose.yml`** - Configuration with profiles for different modes
- **`nginx.conf`** - Optimized Nginx configuration for React SPA
- **`backend-stub.conf`** - Stub for demonstrating backend integration
- **`.dockerignore`** - Exclusions to optimize the build

### 2. Launch scripts

- **Unix (macOS/Linux):**
  - `scripts/start-docker-mocks.sh` - Start with mocks
  - `scripts/start-docker-backend.sh` - Start with backend
  - `scripts/stop-docker.sh` - Stop containers

- **Windows:**
  - `scripts/start-docker-mocks.bat` - Start with mocks
  - `scripts/start-docker-backend.bat` - Start with backend
  - `scripts/stop-docker.bat` - Stop containers

### 3. NPM scripts
Added to `package.json`:
```json
{
  "docker:mocks": "./scripts/start-docker-mocks.sh",
  "docker:backend": "./scripts/start-docker-backend.sh",
  "docker:stop": "./scripts/stop-docker.sh",
  "docker:build": "docker-compose build",
  "docker:logs": "docker-compose logs -f"
}
```

### 4. Configuration files
- **`env.example`** - Example environment variables
- **`QUICKSTART.md`** - Quick start guide

## Modes of operation

### Profile `mocks`
- Runs only the frontend with mocks
- Ideal for demos and development
- Port: 3000

### Profile `backend`
- Runs frontend + backend stub
- For testing API integration
- Ports: 3000 (frontend), 8080 (backend)

## Launch commands

### Quick start
```bash
# With mocks
npm run docker:mocks

# With backend
npm run docker:backend

# Stop
npm run docker:stop
```

### Advanced commands
```bash
# Build images
npm run docker:build

# View logs
npm run docker:logs

# Direct Docker Compose commands
docker-compose --profile mocks up --build
docker-compose --profile backend up --build
docker-compose down
```

## Implementation details

### Multi-stage build
1. **Builder stage** - Node.js for building the React application
2. **Production stage** - Nginx for serving static files

### Optimizations
- Gzip compression
- Static file caching
- Security headers
- Health check endpoints

### Environment variables
- `REACT_APP_USE_MOCKS` - Toggle between mocks and real API
- `REACT_APP_API_URL` - Backend URL

## Testing

All components tested:
- ✅ Docker image build
- ✅ Start with mocks
- ✅ NPM scripts
- ✅ Stop containers
- ✅ Application accessible on port 3000

## Compatibility

- **macOS**: ✅ Tested
- **Linux**: ✅ Expected compatibility
- **Windows**: ✅ .bat scripts created

## Next steps

1. Integration with real backend
2. CI/CD setup
3. Image size optimization
4. Add monitoring
