import React, { useRef, useEffect } from 'react'
import { format, intervalToDuration } from 'date-fns'
import './Canvas.css'
import { useWeatherContext } from '../../Hooks/useWeatherContext';


const Canvas = ( { width, height } ) => {
    const { weather } = useWeatherContext()
    const canvasRef = useRef(null)

    useEffect(() => {
        // sun angle on a semicircle
        const dayLightDuration = weather.sys.sunset - weather.sys.sunrise
        const elapsedSeconds = weather.dt - weather.sys.sunrise
        const percentageElapsed = (elapsedSeconds / dayLightDuration) * 100
        const clampedPercentage = Math.min(100, Math.max(0, percentageElapsed))
        const angleDegrees = (clampedPercentage / 100) * 180
        const angleRadians = ((angleDegrees * Math.PI) / 180) + Math.PI

        // canvas setup
        const canvas = canvasRef.current
        const ctx = canvas.getContext('2d')
        ctx.clearRect(0, 0, canvas.width, canvas.height)

        const arcRadius = 60
        const centerX = canvas.width / 2
        const centerY = canvas.height - (arcRadius / 6)
        
        
        // semicircle path
        ctx.beginPath()
        ctx.strokeStyle = 'black'
        ctx.lineWidth = 1
        ctx.arc(centerX, centerY, arcRadius, 0, Math.PI, true)
        ctx.stroke()
        ctx.closePath()
        
        // sun path
        ctx.beginPath()
        ctx.lineWidth = 5
        ctx.strokeStyle = 'yellow'
        ctx.arc(centerX, centerY, arcRadius, Math.PI, angleRadians, false)
        ctx.stroke()
        ctx.closePath()

        // sun
        const x = centerX + arcRadius * Math.cos(angleRadians)
        const y = centerY + arcRadius * Math.sin(angleRadians)
        ctx.beginPath()
        ctx.fillStyle = 'white'
        ctx.arc(x, y, arcRadius / 6, 0, Math.PI * 2, true)
        ctx.fill()
        ctx.closePath()

    }, [weather])

    const currentOffsetSeconds = new Date().getTimezoneOffset() * 60

    const sunriseDate = new Date((weather.sys.sunrise + currentOffsetSeconds + weather.timezone) * 1000)
    const sunrise = format(sunriseDate, 'HH:mm')
    const sunsetDate = new Date((weather.sys.sunset + currentOffsetSeconds + weather.timezone) * 1000)
    const sunset = format(sunsetDate, 'HH:mm')
    const daylightTimeInterval = intervalToDuration({
        start: sunriseDate,
        end: sunsetDate})

    return (
        <div className="sun">
            <p className='sun-items'>Sunrise<br />
                {sunrise}
            </p>
            <canvas className='sun-items' width={width} height={height} ref={canvasRef}/>
            <p className='sun-items'>Sunset<br />
                {sunset}
            </p>
            <p className='sun-items'>{`${daylightTimeInterval.hours} hr ${daylightTimeInterval.minutes} min`}</p>
        </div>
  )
}

export default Canvas