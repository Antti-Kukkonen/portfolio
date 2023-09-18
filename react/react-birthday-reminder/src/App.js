import React, { useState } from 'react';
import './App.css';
import itemsData from './data';
import Birthdays from './Birthdays';

function App() {
  const [items, setItems] = useState(itemsData);

  const handleDeleteItem = (itemId) => {
    const updatedItems = items.filter((item) => item.id !== itemId);
    setItems(updatedItems);
  };

  return (
    <main>
      <section className="container">
        <div className="title">
          <h3>{items.length} birthdays today</h3>
        </div>
        <section>
          {items.map((item) => (
            <Birthdays
              key={item.id}
              item={item}
              onDelete={handleDeleteItem}
            />
          ))}
        </section>
        <button className="btn long" onClick={() => {
          setItems(itemsData)
        }}>Show all</button>
        <button className="btn long" onClick={() => {
          setItems([])
        }}>Hide all</button>
      </section>
    </main>
  );
}

export default App;