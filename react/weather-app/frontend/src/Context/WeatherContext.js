import { createContext, useReducer } from 'react'

export const WeatherContext = createContext()

export const weatherReducer = (state, action) => {
  switch (action.type) {
    case 'SET_WEATHER': 
      return {
        ...state,
        weather: action.payload
      }
    case 'TOGGLE_DEGREE':
      return {
        ...state,
        degreeType: state.degreeType === 'Celcius' ? 'Fahrenheit' : 'Celcius'
      }
    default:
      throw new Error()
  }
}

export const WeatherContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(weatherReducer, {
    weather: null,
    degreeType: 'Celcius'
  })

  return (
    <WeatherContext.Provider value={{...state, dispatch}}>
      { children }
    </WeatherContext.Provider>
  )
}

/* const initWeather = {
  "coord": {
    "lon": 10.99,
    "lat": 44.34
  },
  "weather": [
    {
      "id": 501,
      "main": "Rain",
      "description": "moderate rain",
      "icon": "10d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 298.48,
    "feels_like": 298.74,
    "temp_min": 297.56,
    "temp_max": 300.05,
    "pressure": 1015,
    "humidity": 64,
    "sea_level": 1015,
    "grnd_level": 933
  },
  "visibility": 10000,
  "wind": {
    "speed": 0.62,
    "deg": 349,
    "gust": 1.18
  },
  "rain": {
    "1h": 3.16
  },
  "clouds": {
    "all": 100
  },
  "dt": 1661870592,
  "sys": {
    "type": 2,
    "id": 2075663,
    "country": "IT",
    "sunrise": 1661834187,
    "sunset": 1661882248
  },
  "timezone": 7200,
  "id": 3163858,
  "name": "Search for a town or city",
  "cod": 200
  } */