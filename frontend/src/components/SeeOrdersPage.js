import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import Order from "./Order"
import logo from '../Logo3.png'

const SeeOrdersPage = () => {

    const {state} = useLocation()
    const customer = state.customer
    const navigate = useNavigate()

    const [activeOrders, setActiveOrders] = useState([])
    const [nonactiveOrders, setNonactiveOrders] = useState([])

    const fetchActiveOrders = async () => {
        const username = customer.username
        const res = await fetch(`http://localhost:8080/food-panda/filtered-orders/active-orders/${username}`)
        const data = await res.json()
    
        return data
    }
    
    useEffect(() => {
        const getActiveOrders = async () => {
            const ordersFromLocal = await fetchActiveOrders()
            setActiveOrders(ordersFromLocal)
        }
    
        getActiveOrders()
    }, [])

    const fetchNonactiveOrders = async () => {
        const username = customer.username
        const res = await fetch(`http://localhost:8080/food-panda/filtered-orders/nonactive-orders/${username}`)
        const data = await res.json()
    
        return data
    }
    
    useEffect(() => {
        const getNonactiveOrders = async () => {
            const ordersFromLocal = await fetchNonactiveOrders()
            setNonactiveOrders(ordersFromLocal)
        }
    
        getNonactiveOrders()
    }, [])

    const onBackSubmit = (e) => {
        e.preventDefault()

        navigate('/customer-page', {
            state: {
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
                <button className = "btn" style = {{marginLeft : 350}} type="submit" id='btn-back' onClick={(e) => onBackSubmit(e)}>
                    Back
                </button>
                <button className = "btn" style = {{marginLeft : 10}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                </button>
            </div>
            <div>
                <div className = "container-2">
                <div>
                    <h2 style={{marginLeft : 10}} className="details"> Active orders </h2>
                    <br></br>
                    <div className = "container-3">
                        {activeOrders.map((order) => (
                            <Order order={order} />
                        ))}
                    </div>
                    <h2 style={{marginLeft : 10}} className="details"> Order history </h2>
                    <br></br>
                    <div className = "container-3">
                        {nonactiveOrders.map((order) => (
                            <Order order={order} />
                        ))}
                    </div>
                </div>
                </div>
            </div>
        </div>
    )
}

export default SeeOrdersPage