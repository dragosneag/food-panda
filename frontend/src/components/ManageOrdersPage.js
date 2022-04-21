import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import AdminOrder from "./AdminOrder"
import Dropdown from 'react-dropdown'
import logo from '../Logo3.png'

const ManageOrdersPage = () => {

    const {state} = useLocation()
    const admin = state.admin
    const navigate = useNavigate()

    const [activeOrders, setActiveOrders] = useState([])
    const [nonactiveOrders, setNonactiveOrders] = useState([])
    const [filteredOrders, setFilteredOrders] = useState([])
    const [selectedStatus, setSelectedStatus] = useState('')
    const [isFiltered, setIsFiltered] = useState(false)
    const defaultOption = 'Select status'

    const fetchActiveOrders = async () => {
        const name = admin.restaurant
        const res = await fetch(`http://localhost:8080/food-panda/filtered-orders/restaurant-active-orders/${name}`)
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
        const name = admin.restaurant
        const res = await fetch(`http://localhost:8080/food-panda/filtered-orders/restaurant-nonactive-orders/${name}`)
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

    const changeStatus = async (id, status) => {

        const res = await fetch(`http://localhost:8080/food-panda/change-order/${id}/${status}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            }
        }).then(response => response.json())
        .catch(error => error)

        if (res.error) {
            alert(res.error);
        } 
    }

    const onChangeOrderSubmit = (e, id, status) => {
        e.preventDefault()
        
        changeStatus(id, status)
    }

    const onBackSubmit = (e) => {
        e.preventDefault()

        navigate('/admin-page', {
            state: {
              admin: admin
            }
          })
    }

    const handleSelect = (e)=>{
        setSelectedStatus(e.value);
    }

    const fetchFilteredOrders = async () => {
        const restaurant = admin.restaurant
        const status = selectedStatus
        const res = await fetch(`http://localhost:8080/food-panda/filtered-orders/${restaurant}/${status}`)
        const data = await res.json()
    
        return data
    }

    const onFilterSubmit = (e) => {
        e.preventDefault()

        if (selectedStatus !== "No filter") {
            setIsFiltered(true)
        } else {
            setIsFiltered(false)
        }
        
        const getFilteredOrders = async () => {
            const ordersFromLocal = await fetchFilteredOrders()
            console.log(ordersFromLocal)
            setFilteredOrders(ordersFromLocal)
        }

        getFilteredOrders()
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
                <button className = "btn" style = {{marginLeft : 900}} type="submit" id='btn-back' onClick={(e) => onBackSubmit(e)}>
                    Back
                </button>
                <button className = "btn" style = {{marginLeft : 10}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                </button>
            </div>
            <div>
                <div className = "container-2">
                    <div>
                        <div className="row">
                            <div style={{marginLeft : 10, fontSize: 30}} className="details"> Orders </div>
                            <div style={{marginLeft : 580}}>
                                <Dropdown options={["No filter", "PENDING", "ACCEPTED", "IN_DELIVERY", "DELIVERED", "DECLINED"]} onChange = {handleSelect}  value={defaultOption} placeholder="Select an option" ></Dropdown>
                            </div>
                            <button className = "btn" style = {{marginLeft : 10}} type="submit" id='btn-log-out' onClick={(e) => onFilterSubmit(e)}>
                                Filter
                            </button>
                        </div>
                        <div className = "container-5">
                            {!isFiltered && activeOrders.map((order) => (
                                <AdminOrder order={order} onClick = {(e, id, status) => onChangeOrderSubmit(e, id, status)}/>
                            ))}
                            {!isFiltered && nonactiveOrders.map((order) => (
                                <AdminOrder order={order} />
                            ))}
                            {isFiltered && filteredOrders.map((order) => (
                                <AdminOrder order={order} onClick = {(e, id, status) => onChangeOrderSubmit(e, id, status)}/>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ManageOrdersPage