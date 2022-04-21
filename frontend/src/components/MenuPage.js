import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import CustomerCategory from "./CustomerCategory"
import logo from '../Logo3.png'

const MenuPage = () => {
    const {state} = useLocation()
    const restaurant = state.restaurant
    const customer = state.customer
    const navigate = useNavigate()

    const [amount, setAmount] = useState('0')
    const [cart, setCart] = useState([])

    const onAddCartSubmit = (e, food) => {
        e.preventDefault()

        console.log(food)
        
        setAmount(parseInt(amount) + parseInt(food.price))
        setCart([...cart, food])
    }

    const onSeeCartSubmit = (e) => {
        e.preventDefault()

        console.log(cart)

        navigate('/customer-page/menu-page/cart-page', {
            state : {
                foodList : cart,
                total : amount,
                customer : customer,
                restaurant : restaurant
            }
        })
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
                <div style={{color: '#d1497f', marginLeft : 190, fontSize : 30, width: 150}}>Cart: {amount}</div>
                {amount !== '0' && <button className = "btn" type="submit" id='btn-see-cart' onClick={(e) => onSeeCartSubmit(e)}>
                    See cart
                </button>}
                <button className = "btn" style = {{marginLeft : 10}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                </button>
            </div>
            <div>
                <div className = "container-2">
                    <div className = "row">
                        <div style={{marginLeft : 10, fontSize : 30}}>{restaurant.name}</div>
                    </div>
                    <br></br>
                    {restaurant.categories.map((category) => (
                        <CustomerCategory category = {category} restaurant = {restaurant} onClick = {(e, food) => onAddCartSubmit(e, food)}/>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default MenuPage