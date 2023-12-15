import { useEffect, useState } from "react"
import { TbTemperatureCelsius, TbTemperatureFahrenheit } from "react-icons/tb"
import { AiFillStar } from "react-icons/ai"
import { FaBars } from 'react-icons/fa';
import { useAuthContext } from '../../Hooks/useAuthContext'
import { useFavorites } from '../../Hooks/useFavorites'
import { useDropdownContext } from '../../Hooks/useDropdownContext'
import { useWeather } from '../../Hooks/useWeather'
import './Dropdown.css'
import { useWeatherContext } from "../../Hooks/useWeatherContext";
import NavItems from '../Navbar/NavItems'; 
import { Link } from 'react-router-dom';
import { useLogout } from '../../Hooks/useLogout';


function DropdownMenu() {
    const [locations, setLocations] = useState([])
    const { user } = useAuthContext()
    const { open, dispatch: dropdownDispatch } = useDropdownContext()
    const { getFavorites } = useFavorites()
    const { search } = useWeather()
    const { degreeType, dispatch: degreeDispatch } = useWeatherContext()
    const { logout } = useLogout()

    useEffect(() => {
        const mapFavorites = async () => {
            const favorites = await getFavorites()
            if (!favorites) return []
            return favorites.locations
        };
        if (user) {
            mapFavorites().then(res => setLocations(res))
        } else {
            setLocations([])
        }
        // eslint-disable-next-line
    }, [user, open]);

    const toggleDropdown = () => {
        dropdownDispatch({ type: 'TOGGLE_DROPDOWN' });
    };

    const toggleDegree = () => {
        degreeDispatch({ type: 'TOGGLE_DEGREE' })
    }


    const handleLocationClick = (location) => {
        search(`${location.latitude}, ${location.longitude}`, true)
    }

    return (
        <div className="dropdown">
            <button onClick={toggleDropdown} className="dropdown-button">
                <FaBars />
            </button>

            {open && (
                <div className="dropdown-content">
                    {user && (
                        <div onClick={() => {if (open) toggleDropdown()}}>
                            <p className='email' title='Logout' onClick={() => {logout()}}>{user.email}</p>
                        </div>
                    )}
                    {!user && (
                        <div className='navLinks'>
                            <ul>
                            {NavItems.map((item, index) => (
                                <li key={index} onClick={toggleDropdown}>
                                <Link to={item.url} className={` ${item.cName} login-signup-link`}>
                                    {item.title}
                                </Link>
                                </li>
                            ))}
                            </ul>
                        </div>
                    )}
                    <div className="slider-switch">
                        <label className={`slider-toggle`}>
                            <input className=""
                                type="checkbox"
                                checked={degreeType === 'Fahrenheit'}
                                onChange={toggleDegree}
                                />
                            <span className="slider"></span>
                            <div className="icons">
                                <TbTemperatureCelsius size={60} className="icon" />
                                <TbTemperatureFahrenheit size={60} className="icon" />
                            </div>
                        </label>
                    </div>
                    <ul className="location-list">
                        {locations.map((location) => (
                            <li
                                key={location._id}
                                onClick={() => handleLocationClick(location)}
                            >
                                <AiFillStar /> {location.locationName}
                            </li>
                        ))}

                    </ul>
                </div>
            )}
        </div>
    );
}

export default DropdownMenu;