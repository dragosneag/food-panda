import React from 'react'

const Food = ({food, restaurant}) => {

    if (food.restaurant === restaurant) {
        return (
            <div style={{color: "#ffffff"}}>
                <h3>
                    {food.name}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                    Price:&nbsp;&nbsp;
                    {food.price}
                </h3>
            </div>
        )
    }
}

export default Food