CREATE TABLE solar_system_planets (
    planet_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,-- Unique ID
    name VARCHAR(50) NOT NULL,                        -- Name of the planet
    diameter_km INT NOT NULL,                         -- Diameter in kilometers
    mass_kg VARCHAR(30) NOT NULL,                     -- Mass in kilograms 
    gravity_ms2 DECIMAL(5,2) NOT NULL,                -- Surface gravity (m/s^2)
    orbital_period_days DECIMAL(8,2) NOT NULL,        -- Orbital period in days 
    rotation_period_hours DECIMAL(6,2) NOT NULL,      -- Rotation period in hours
    moons INT NOT NULL,                               -- Number of moons
    ring_system BOOLEAN NOT NULL,                     -- Presence of ring system (true/false)
    discovery_year INT NOT NULL,                      -- Year of discovery
    discovery_method VARCHAR(50) NOT NULL,            -- Method of discovery
    atmosphere_composition VARCHAR(255) NOT NULL,     -- Primary atmospheric components
    mean_temperature_celsius INT NOT NULL,            -- Mean surface temperature in Celsius
    distance_from_sun_au DECIMAL(10,4) NOT NULL,      -- Average distance from the Sun in AU
    planet_type VARCHAR(20) NOT NULL                  -- Type (terrestrial, gas giant, etc.) 
); 
