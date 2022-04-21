import React from 'react'

const CustomerFood = ({food, restaurant, onClick}) => {
    if (food.restaurant === restaurant.name) {  
        return (
            <div style={{color: "#ffffff"}}>
                <br></br>
                <h2>
                    {food.name}
                    <div style={{fontSize : 15}}>
                        Description:&emsp;&emsp;
                        {food.description}
                    </div>
                </h2>
                <h3>
                    Price:
                    {food.price}
                    <button className = "btn" type="submit" id='btn-add-cart' onClick={(e) => onClick(e, food)}>
                        Add to cart
                    </button>
                </h3>
            </div>
        )
    }
}

export default CustomerFood