import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import Zone from './Zone'
import logo from '../Logo3.png'

const CreateRestaurant = () => {
  const {state} = useLocation()
  const admin = state.admin
  const navigate = useNavigate()

  const [name, setName] = useState('')
  const [address, setAddress] = useState('')
  const [zones, setZones] = useState([])
  const [initialZones, setInitialZones] = useState([])

  const fetchZones = async () => {
    const res = await fetch(`http://localhost:8080/food-panda/zones`)
    const data = await res.json()

    return data
  }

  useEffect(() => {
    const getZones = async () => {
        const zonesFromLocal = await fetchZones()
        console.log(zonesFromLocal)
        setInitialZones(zonesFromLocal)
    }

    getZones()
  }, [])

  const addRestaurant = async (restaurant) => {
    console.log(JSON.stringify(restaurant));
    const res = await fetch(`http://localhost:8080/food-panda/addrestaurant`, {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(restaurant)
    }).then(response => response.json())
    .catch(error => error)

    const newAdmin = {
      name: admin.name,
      username: admin.username,
      password: admin.password,
      restaurant: restaurant.name
    }

    if (res.error) {
        alert(res.error);
    } else {
        navigate('/admin-page', {
          state: {
            admin: newAdmin
          }
        })
    }
  }

  const onCreateSubmit = (e) => {
    e.preventDefault()

    if(!name) {
        alert('Please enter your name')
        return
    }

    if(!address) {
        alert('Please enter your address')
        return
    }

    addRestaurant({name, address, zones, admin});
  }

  return (
    <div>
      <div className = "row">
        <img src= {logo} alt = "Logo" width={80} height = {80} style={{ marginLeft : 50, marginTop: 20}} />
        <div style={{color: '#d1497f', fontFamily : 'less', marginLeft : 460, fontSize : 50}}>foodpanda</div>
      </div>
      <div className = "container-2">
        <div className = "row">
          <div style={{marginLeft : 350, fontSize : 30}}>{admin.name}, create your restaurant!</div>
        </div>
        <form className="add-form">
          <div className="form-control" style={{marginLeft : 400}}>
            <label> Name </label>
            <input  type="text"
              placeholder="Enter name" value={name} onChange={(e) => setName(e.target.value)} />
          </div>
          <div className="form-control" style={{marginLeft : 400}}>
            <label> Address </label>
            <input  type="text"
              placeholder="Enter address" value={address} onChange={(e) => setAddress(e.target.value)} />
          </div>
          <h2 style={{marginLeft : 60}} className="details" > Delivery zones </h2>
          <br></br>
          <h3 style={{marginLeft : 60}} className="details"> Selected zones: {zones.map((zone) => (zone.name + "; "))} </h3>
          <br></br>
          <div className = "container-3">
            {initialZones.map((zone, index) => (
              <Zone key = {index} zone = {zone} onClick = {() => (setZones([...zones, zone]))} />
            ))}
          </div>
          <button type="submit" id='btn-create' className="btn btn-block" style={{marginLeft : 380}} onClick={(e) => onCreateSubmit(e)}>
            Create
          </button>
        </form>
      </div>
    </div>
  )
}

export default CreateRestaurant