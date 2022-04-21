import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import logo from '../Logo3.png'

const CartPage = () => {

    const {state} = useLocation()
    const foodList = state.foodList
    const total = state.total
    const customer = state.customer
    const restaurant = state.restaurant
    const navigate = useNavigate()

    var currentTime = new Date();

    const onCancelSubmit = (e) => {
        e.preventDefault()

        navigate('/customer-page', {
            state: {
                customer: customer
            }
        })
    }

    const addOrder = async () => {

        const newOrder = {
            status: "PENDING",
            price : total,
            placingDate : currentTime.toLocaleDateString('en-CA'),
            placingTime : currentTime.toLocaleTimeString('it-IT'),
            foodList : foodList,
            customer : customer.username,
            restaurant : restaurant.name
        }
        console.log(JSON.stringify(newOrder))
        const res = await fetch(`http://localhost:8080/food-panda/addorder`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(newOrder)
        }).then(response => response.json())
        .catch(error => error)
    
        if (res.error) {
            alert(res.error);
        } else {
            navigate('/customer-page', {
                state: {
                    customer: customer
                }
            })
        }
    }

    const onPlaceOrderSubmit = (e) => {
        e.preventDefault()

        addOrder()
    }

    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
    }

    return (
        <div>
            <div className = "row">
                <img src= {logo} alt = "Logo" width={80} height = {80} style={{ marginLeft : 50, marginTop: 20}} />
                <div style={{color: '#d1497f', fontFamily : 'less', marginLeft : 460, fontSize : 50}}>foodpanda</div>
                <button className = "btn" style = {{marginLeft : 450}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                </button>
            </div>
            <div>
                <div className = "container-2">
                    <div>
                        <h1 style={{marginLeft : 10}} className="details"> Cart </h1>
                        <br></br>
                        {foodList.map((food) => (
                            <div className = "row">
                                <div style={{ marginLeft : 50, fontSize : 30}}> {food.name} </div>
                                <div style={{ marginLeft : 410, fontSize : 30}}> Price: </div>
                                <div style={{ fontSize : 30}}> {food.price} </div>
                            </div>
                        ))}
                        <br></br>
                        <div className = "row">
                            <div style={{ marginLeft : 760, fontSize : 30}}> Total: </div>
                            <div style={{ fontSize : 30}}> {total} </div>
                        </div>
                    </div>
                    <br></br>
                    <button className = "btn" style = {{marginLeft : 10}} type="submit" id='btn-cancel' onClick={(e) => onCancelSubmit(e)}>
                        Cancel
                    </button>
                    <button className = "btn" style = {{marginLeft : 860}} type="submit" id='btn-place-order' onClick={(e) => onPlaceOrderSubmit(e)}>
                        Place order
                    </button>
                </div>
            </div>
        </div>
    )
}

export default CartPage