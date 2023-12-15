import React, { useState } from 'react'
import './Navbar.css'
import { PiMagnifyingGlassLight } from 'react-icons/pi';
import DropdownMenu from '../Dropdownmenu/DropdownMenu';
import { Link } from 'react-router-dom';
import { useWeather } from '../../Hooks/useWeather';



const Navbar = () => {
  const [searchTerm, setSearchTerm] = useState('')
  const { search } = useWeather()
  const [searchError, setSearchError] = useState(undefined)

  const handleSubmit = async (e) => {
    e.preventDefault()
    const res = await search(searchTerm)
    setSearchError(res)
    if (!res) setSearchTerm('')
  };

  return (
    <nav className='navBar'>
      <div className='drop-menu'>
        <DropdownMenu />
      </div>
      
      <Link to='/' className='logo-link'>
        <p>Weather App</p>
      </Link>

      <div className='search-bar'>
        <form onSubmit={handleSubmit} className='search-form'>
          <label htmlFor='search'></label>
          <input 
            type='text' 
            placeholder='Search for location' 
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            autoComplete='off'
            className={searchError ? 'search-error' : ''}
          />
          <button type='submit' className='search-button'>
            <PiMagnifyingGlassLight size={25} className='search-icon' />
            </button>
        </form>
      </div>
    </nav>
  )
}

export default Navbar;