# Plan: Mobile App Complete Implementation

## Information Gathered:

### API Endpoints (hosted on Render):
- **Base URL**: Need to identify the Render URL
- **User Registration**: POST `/usuarios/insertar`
- **User Login**: GET `/usuarios/obtener/{email}/{password}` - Returns Usuario object
- **Get All Posts**: GET `/publicaciones/todas`
- **Create Post**: POST `/publicaciones/insertar`

### Current Issues in Mobile App:
1. API URL uses `http://10.0.2.2:8080/` (local) - needs Render URL
2. Login returns only boolean, not user object
3. Inicio fragment uses mock data instead of real API
4. Publicar fragment doesn't send data to API
5. No session management
6. Usuario model lacks setters

## Plan:

### Step 1: Update API URL and Add Methods
- **File**: `app/src/main/java/com/example/appmovil/API/ApiRest.java`
- Add Render base URL
- Add method to get full user object from login
- Add methods to fetch publications from API
- Add method to create publication with image (Base64)

### Step 2: Update Usuario Model
- **File**: `app/src/main/java/com/example/appmovil/Models/Usuario.java`
- Add setters for all fields
- Add empty constructor

### Step 3: Update Publicacion Model
- **File**: `app/src/main/java/com/example/appmovil/Models/Publicacion.java`
- Add imageUrl field (String for Base64 or URL)
- Add setters

### Step 4: Update Login View
- **File**: `app/src/main/java/com/example/appmovil/Views/InicioSesion.java`
- Save user data to SharedPreferences after login
- Pass user to next activity

### Step 5: Update Inicio Fragment
- **File**: `app/src/main/java/com/example/appmovil/Views/Inicio.java`
- Load real publications from API
- Remove mock data

### Step 6: Update Publicar Fragment
- **File**: `app/src/main/java/com/example/appmovil/Views/Publicar.java`
- Send publication to API with image
- Convert image to Base64

### Step 7: Update Registro View
- **File**: `app/src/main/java/com/example/appmovil/Views/RegistroCuenta.java`
- Add proper validation
- Return registered user data

## Followup Steps:
1. Test the application with emulator/device
2. Verify API connectivity
3. Handle offline scenarios if needed

