import React from 'react'

const Restaurant = ({ restaurant, onClick }) => {

    return (
        <div className = "speciality" style={{cursor : 'default'}}>
            <h3 style={{cursor : 'default'}}>
                {restaurant.name}
                <button className = "btn" type="submit" id='btn-back' onClick={onClick}>
                    Menu
                </button>
            </h3>
        </div>
    )
}

export default Restaurant