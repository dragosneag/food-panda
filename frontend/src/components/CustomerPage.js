import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import Restaurant from "./Restaurant"
import logo from '../Logo3.png'

const CustomerPage = () => {

  const {state} = useLocation()
  const customer = state.customer
  const navigate = useNavigate()

  const [restaurants, setRestaurants] = useState([])

  const fetchRestaurants = async () => {
    const res = await fetch(`http://localhost:8080/food-panda/restaurants`)
    const data = await res.json()

    return data
  }

  useEffect(() => {
    const getRestaurants = async () => {
        const restaurantsFromLocal = await fetchRestaurants()
        console.log(restaurantsFromLocal)
        setRestaurants(restaurantsFromLocal)
    }

    getRestaurants()
  }, [])

  const onViewMenuSubmit = (e, restaurant) => {
    e.preventDefault()
    console.log(restaurant)
    navigate('/customer-page/menu-page', {
      state: {
        customer: customer,
        restaurant: restaurant
      }
    })
  }

  const onSeeOrdersSubmit = (e) => {
    e.preventDefault()

    navigate('/customer-page/see-orders-page', {
      state : {
        customer: customer
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
        <button className = "btn" style = {{marginLeft : 450}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
            Log out
        </button>
      </div>
      <div>
        <div className = "container-2">
          <div>
            <h1 style={{marginBottom : 10, marginLeft: 10 }}>Welcome, {customer.name} !</h1>
            <h2 style={{marginLeft : 10}} className="details"> Restaurants </h2>
            <br></br>
            <div className = "container-3">
              {restaurants.map((restaurant) => (
                <Restaurant restaurant = {restaurant} onClick = {(e) => onViewMenuSubmit(e, restaurant)} />
              ))}
            </div>
            <h2 style={{marginLeft : 10, cursor : "pointer"}} className="details" onClick={(e) => onSeeOrdersSubmit(e)}> See orders </h2>
          </div>
        </div>
      </div>
  </div>
  )
}

export default CustomerPage