const Birthdays = ({ item, onDelete }) => {

  const handleDelete = () => {
    onDelete(item.id);
  };

  return (
    <article className="person">
      <img src={item.image} alt={item.name} className="img"></img>
      <div>
        <h4>{item.name}</h4>
        <p>{item.age} years</p>
        <button onClick={handleDelete} className="btn">Hide</button>
      </div>
    </article>
  );
};

export default Birthdays;