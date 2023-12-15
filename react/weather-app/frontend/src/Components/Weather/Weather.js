import React, { useState, useEffect } from 'react'
import './Weather.css';
import { SlRefresh } from "react-icons/sl";
import Canvas from '../Canvas/Canvas';
import { WiStrongWind, WiHumidity, WiThermometer, WiCloud } from "react-icons/wi";
import { IoIosStar, IoIosStarOutline } from "react-icons/io";
import { formatDistanceToNow } from 'date-fns';
import weatherIcons from './weatherIcons';
import { useWeatherContext } from '../../Hooks/useWeatherContext';
import { useWeather } from '../../Hooks/useWeather';
import { useFavorites } from '../../Hooks/useFavorites';
import { useAuthContext } from '../../Hooks/useAuthContext';
import { useDropdownContext } from '../../Hooks/useDropdownContext';

const Weather = () => {
  const { search } = useWeather()
  const { weather, degreeType } = useWeatherContext()
  const [timeAgo, setTimeAgo] = useState(getTimeAgo(weather && weather.dt) || 0);
  const { addFavorite, removeFavorite, getFavorites } = useFavorites()
  const [isFavorite, setIsFavorite] = useState(false)
  const { user } = useAuthContext()
  const { open, dispatch } = useDropdownContext()

  const handleFavorite = async () => {
    if (!user) return
    if (isFavorite) {
      const favorites = await getFavorites()
      const favorite = favorites.locations.find(
        favorite => favorite.locationName === weather.name)
      if (!favorite) return
      const id = favorite._id
      await removeFavorite(id)
      setIsFavorite(false)
    } else {
      await addFavorite(weather.name, weather.coord.lat, weather.coord.lon)
      setIsFavorite(true)
    }
  }

  const convertDegree = (degree) => {
    if (degreeType === 'Celcius') {
      return (degree - 273.15).toFixed(0) + '°C';
    } else {
      return ((degree - 273.15) * 1.8 + 32).toFixed(0) + '°F';
    }
  }

  const convertWindSpeed = (speed) => {
    if (degreeType === 'Celcius') {
      return (speed * 3.6).toFixed(0) + ' km/h'
    } else {
      return (speed * 2.23694).toFixed(0) + ' mph'
    }
  }


  function getTimeAgo(unixTimestamp) {
    const date = new Date(unixTimestamp * 1000);
    return formatDistanceToNow(date, { addSuffix: true });
  }

  useEffect(() => {
    setTimeAgo(getTimeAgo(weather && weather.dt))

    const intervalId = setInterval(() => {
      setTimeAgo(getTimeAgo(weather && weather.dt));
    }, 60000); // 1 minute

    return () => {
      clearInterval(intervalId);
    };
  }, [weather]);

  useEffect(() => {
    const checkFavorite = async () => {
      if (!user) return
      const favorites = await getFavorites()
      if (favorites && weather) {
        const favorite = favorites.locations.find(
          favorite => favorite.locationName === weather.name)
        if (favorite) {
          setIsFavorite(true)
        } else {
          setIsFavorite(false)
        }
      }
    }
    checkFavorite()
  }, [weather, user, getFavorites])

  if (!weather) return (
      <div className='cloudyr' id='WeatherBackground' onClick={() => {if (open) dispatch({ type: 'TOGGLE_DROPDOWN' });}}>
      <div id='AppContainer' >
        <h1 className='no-location'>Search for a location</h1>
      </div>
    </div>
  )

  let name = weather.name
  if (weather.state) {
    name += ', ' + weather.state
  }
  if (weather.country) {
    name += ', ' + weather.country
  }
  const weatherMain = weather.weather[0].main
  const description = weather.weather[0].description.charAt(0).toUpperCase() + weather.weather[0].description.slice(1)
  const icon = weatherIcons[weather.weather[0].icon] || weatherIcons['01d']
  const temp = convertDegree(weather.main.temp)
  const feelsLike = convertDegree(weather.main.feels_like)
  const windSpeed = convertWindSpeed(weather.wind.speed)
  const humidity = weather.main.humidity + '%'
  const cloudiness = weather.clouds.all + '%'

  return (
    <div className='cloudyr' id='WeatherBackground' onClick={() => {if (open) dispatch({ type: 'TOGGLE_DROPDOWN' });}}>
      <div id='AppContainer' >
        <div id='UpperWeatherInfo'>
          <h3 id='Star' onClick={handleFavorite}>{isFavorite ? <IoIosStar /> : <IoIosStarOutline />}</h3> {/* todo: favoriting system */}
          <h3 id='Location'>{name}</h3>
          <div id='Refresh'>
            <p className='weather-p' id='RefreshTime'>{timeAgo}</p>
            <button
              id='RefreshButton'
              aria-label="Refresh"
              onClick={async () => (search(weather.name))}
            >
              <SlRefresh size={40} />
            </button>
          </div>
        </div>
        <div className="cloudy" id="LowerWeatherInfo">
          <div id='TempContainer' className='infoBG'>
            <div id='tempL'>
              <p className='weather-p' id='Temp'>{temp}</p>
              <p className='weather-p' id='WeatherMain'>{weatherMain}</p>
            </div>
            <div id='tempR'>
              <p className='weather-p' id='WeatherDescription'>{description}</p>
            </div>
          </div>
          <p id='Icon' className='weather-p'>{React.createElement(icon)}</p>
          <div id="Wind" className='infoBG'>
            <div id='windL'>
              <p className='weather-p'>Wind</p>
              <p className='weather-p' id='WindSpeed'>{windSpeed}</p>
            </div>
            <div id='windR'>
              <p className='weather-p info-icon' id='WindIcon'><WiStrongWind /></p>
            </div>
          </div>
          <div id='Humidity' className='infoBG'>
            <div id='humidityL'>
              <p className='weather-p'>Humidity</p>
              <p className='weather-p'>{humidity}</p>
            </div>
            <div id='humidityR'>
              <p className='weather-p info-icon' id='HumidityIcon'><WiHumidity /></p>
            </div>
          </div>
          <div id='FeelsLike' className='infoBG'>
            <div id='feelslikeL'>
              <p className='weather-p'>Feels like</p>
              <p className='weather-p'>{feelsLike}</p>
            </div>
            <div id='feelslikeR'>
              <p className='weather-p info-icon' id='FeelsLikeIcon'><WiThermometer /></p>
            </div>
          </div>
          <div id='Cloudiness' className='infoBG'>
            <div id='cloudinessL'>
              <p className='weather-p'>Cloudiness</p>
              <p className='weather-p'>{cloudiness}</p>
            </div>
            <div id='cloudinessR'>
              <p className='weather-p info-icon' id='CloudinessIcon'><WiCloud /></p>
            </div>
          </div>
          <div id='SunContainer'>
            <Canvas weather={weather} width={150} height={80} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default Weather