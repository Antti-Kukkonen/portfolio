import React, { useState } from 'react';
import people from './data';
import { FaChevronLeft, FaChevronRight, FaQuoteRight } from 'react-icons/fa';

const Review = () => {
    const maxId = Math.max(...people.map(person => person.id))
    const minId = Math.min(...people.map(person => person.id))
    const [person, setPerson] = useState(people.find(person => person.id === minId))


    const nextPerson = () => {
        const currentId = person.id
        if (currentId === maxId) {
            setPerson(people.find(person => person.id === minId))
        } else {
            setPerson(people.find(person => person.id === currentId + 1))
        }
    }

    const prevPerson = () => {
        const currentId = person.id
        if (currentId === minId) {
            setPerson(people.find(person => person.id === maxId))
        } else {
            setPerson(people.find(person => person.id === currentId - 1))
        }
    }

    const randomPerson = () => {
        let randomId = person.id
        while (randomId === person.id) {
            randomId = (Math.floor(Math.random() * (Math.floor(maxId) - Math.ceil(minId) + 1) + Math.ceil(minId)))
        }
        setPerson(people.find(person => person.id === randomId))
    }

    return (
        <article className='review'>
            <div className='img-container'>
                <img className='person-img' src={person.image} alt={person.name}></img>
                <span className='quote-icon'><FaQuoteRight /></span>
            </div>
            <h4 className='author'>{person.name}</h4>
            <p className='job'>{person.job}</p>
            <p className='info'>{person.text}</p>
            <div className='btn-container'>
                <button className='prev-btn' onClick={prevPerson}><FaChevronLeft /></button>
                <button className='next-btn' onClick={nextPerson}><FaChevronRight /></button>
            </div>
            <button className='random-btn' onClick={randomPerson}>Surprise Me</button>
        </article>
    );
};

export default Review;