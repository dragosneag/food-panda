import React from 'react'

const AdminOrder = ( {order, onClick} ) => {
    return (
        <div className = "speciality">
            <div className='row'>
                <h3 style={{marginLeft : 10}}>
                    Order ID:&nbsp;&nbsp;
                    {order.id}&emsp;&emsp;&emsp;&emsp;
                    Client username:&nbsp;&nbsp;
                    {order.customer}
                    {order.status === "PENDING" && <button className = "btn" type="submit" id='btn-accept' onClick={(e) => {onClick(e, order.id, "ACCEPTED")}}>
                        Accept
                    </button>}
                    {order.status === "ACCEPTED" && <button className = "btn" type="submit" id='btn-deliver' onClick={(e) => onClick(e, order.id, "IN_DELIVERY")}>
                        Deliver
                    </button>}
                    {order.status === "IN_DELIVERY" && <button className = "btn" type="submit" id='btn-mark-delivered' onClick={(e) => onClick(e, order.id, "DELIVERED")}>
                        Mark as delivered
                    </button>}
                </h3>
                <h3 style={{marginLeft : 10}}>
                    Price:&nbsp;&nbsp;
                    {order.price}
                    {order.status === "PENDING" && <button className = "btn" type="submit" id='btn-decline' onClick={(e) => onClick(e, order.id, "DECLINED")}>
                        Decline
                    </button>}
                </h3>
                <h3 style={{marginLeft : 10, color : '#d8d6d6'}}>
                    Status:&nbsp;&nbsp;
                    {order.status}
                </h3>
            </div>
        </div>
    )
}

export default AdminOrder