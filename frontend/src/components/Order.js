import React from 'react'

const Order = ({order}) => {
    return (
        <div className = "speciality">
            <div className='row'>
                <h3 style={{marginLeft : 10}}>
                    Order ID:&nbsp;&nbsp;
                    {order.id}&emsp;&emsp;&emsp;&emsp;
                    Restaurant:&nbsp;&nbsp;
                    {order.restaurant}
                </h3>
                <h3 style={{marginLeft : 10}}>
                    Price:&nbsp;&nbsp;
                    {order.price}
                </h3>
                <h3 style={{marginLeft : 10, color : '#d8d6d6'}}>
                    Status:&nbsp;&nbsp;
                    {order.status}
                </h3>
            </div>
        </div>
    )
}

export default Order