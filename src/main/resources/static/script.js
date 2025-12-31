const API_BASE = `${window.location.origin}/weather`;

// 1. Get Current Weather
function getCurrentWeather() {
    const city = document.getElementById('weather_city').value;
    if (!city) {
        showError('Please enter a city name');
        return;
    }
    
    hideError();
    document.getElementById('result').classList.remove('show');
    
    fetch(`${API_BASE}/my/${city}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('resultTitle').textContent = '☀️ Current Weather - ' + city;
                document.getElementById('resultData').textContent = JSON.stringify(data.data, null, 2);
                document.getElementById('result').classList.add('show');
            } else {
                showError(data.message);
            }
        })
        .catch(error => showError('Error: ' + error.message));
}

// 2. Get Forecast
function getForecast() {
    const city = document.getElementById('forecast_city').value;
    const days = document.getElementById('forecast_days').value;
    
    if (!city) {
        showError('Please enter a city name');
        return;
    }
    
    hideError();
    document.getElementById('result').classList.remove('show');
    
    fetch(`${API_BASE}/forecast?city=${city}&days=${days}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('resultTitle').textContent = '📅 Forecast - ' + city + ' (' + days + ' days)';
                document.getElementById('resultData').textContent = JSON.stringify(data.data, null, 2);
                document.getElementById('result').classList.add('show');
            } else {
                showError(data.message);
            }
        })
        .catch(error => showError('Error: ' + error.message));
}

// 3. Get Weather Alert
function getWeatherAlert() {
    const city = document.getElementById('alert_city').value;
    if (!city) {
        showError('Please enter a city name');
        return;
    }
    
    hideError();
    document.getElementById('result').classList.remove('show');
    
    fetch(`${API_BASE}/alert/${city}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('resultTitle').textContent = '⚠️ Weather Alert - ' + city;
                document.getElementById('resultData').textContent = JSON.stringify(data.data, null, 2);
                document.getElementById('result').classList.add('show');
            } else {
                showError(data.message);
            }
        })
        .catch(error => showError('Error: ' + error.message));
}

// 4. Get Weather by Coordinates
function getWeatherByCoordinates() {
    const latitude = document.getElementById('coord_lat').value;
    const longitude = document.getElementById('coord_lon').value;
    
    if (!latitude || !longitude) {
        showError('Please enter latitude and longitude');
        return;
    }
    
    hideError();
    document.getElementById('result').classList.remove('show');
    
    fetch(`${API_BASE}/coordinates?latitude=${latitude}&longitude=${longitude}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('resultTitle').textContent = '📍 Weather at ' + latitude + ', ' + longitude;
                document.getElementById('resultData').textContent = JSON.stringify(data.data, null, 2);
                document.getElementById('result').classList.add('show');
            } else {
                showError(data.message);
            }
        })
        .catch(error => showError('Error: ' + error.message));
}

// 5. Get Extended Forecast
function getExtendedForecast() {
    const city = document.getElementById('extended_city').value;
    const days = document.getElementById('extended_days').value || 5;
    
    if (!city) {
        showError('Please enter a city name');
        return;
    }
    
    hideError();
    document.getElementById('result').classList.remove('show');
    
    let url = `${API_BASE}/extended-forecast?city=${city}`;
    if (days) {
        url += `&days=${days}`;
    }
    
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('resultTitle').textContent = '📊 Extended Forecast - ' + city;
                document.getElementById('resultData').textContent = JSON.stringify(data.data, null, 2);
                document.getElementById('result').classList.add('show');
            } else {
                showError(data.message);
            }
        })
        .catch(error => showError('Error: ' + error.message));
}

// 6. Compare Multiple Cities
function compareMultipleCities() {
    const citiesInput = document.getElementById('compare_cities').value;
    
    if (!citiesInput) {
        showError('Please enter city names');
        return;
    }
    
    const cities = citiesInput.split(',').map(city => city.trim());
    
    if (cities.length === 0) {
        showError('Please enter at least one city');
        return;
    }
    
    hideError();
    document.getElementById('result').classList.remove('show');
    
    fetch(`${API_BASE}/compare`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cities)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('resultTitle').textContent = '🏙️ Comparing Cities';
                document.getElementById('resultData').textContent = JSON.stringify(data.data, null, 2);
                document.getElementById('result').classList.add('show');
            } else {
                showError(data.message);
            }
        })
        .catch(error => showError('Error: ' + error.message));
}

// Helper Functions
function showError(message) {
    const errorDiv = document.getElementById('error');
    errorDiv.textContent = '❌ ' + message;
    errorDiv.classList.add('show');
}

function hideError() {
    document.getElementById('error').classList.remove('show');
}
