import React from 'react'
import Food from "./Food"

const Category = ({ category, restaurant, viewButton, onClick }) => {

    return (
        <div className = "speciality" style={{cursor : 'default' }}>
            <h3 style={{cursor : 'default' , fontSize : 30 , color: '#fb93bc'}}>
                {category.name}
                {viewButton && <button className = "btn" type="submit" id='btn-back' onClick={onClick}>
                    Add
                </button>}
            </h3>
            {viewButton && <div>
                {category.foodList.map((food) => (
                    <Food food = {food} restaurant = {restaurant}/>
                ))}
            </div>}
        </div>
    )
}

export default Category