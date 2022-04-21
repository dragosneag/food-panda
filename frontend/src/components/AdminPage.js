import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import Category from "./Category"
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
import logo from '../Logo3.png'

const AdminPage = () => {

  const {state} = useLocation()
  const admin = state.admin
  const navigate = useNavigate()

  const [categories, setCategories] = useState([])
  const [selectedCategory, setSelectedCategory] = useState('')
  const defaultOption = 'Add category'
  const [restaurantCategories, setRestaurantCategories] = useState([])

  const fetchCategories = async () => {
    const res = await fetch(`http://localhost:8080/food-panda/categories`)
    const data = await res.json()

    return data
  }

  useEffect(() => {
    const getCategories = async () => {
        const categoriesFromLocal = await fetchCategories()
        console.log(categoriesFromLocal)
        setCategories(categoriesFromLocal)
    }

    getCategories()
  }, [])

  const fetchRestaurantCategories = async () => {
    const name = admin.restaurant
    console.log(admin)
    const res = await fetch(`http://localhost:8080/food-panda/restaurants/${name}/categories`)
    const data = await res.json()

    return data
  }

  useEffect(() => {
    const getRestaurantCategories = async () => {
        const categoriesFromLocal = await fetchRestaurantCategories()
        console.log(categoriesFromLocal)
        setRestaurantCategories(categoriesFromLocal)
    }

    getRestaurantCategories()
  }, [])

  const onLogOutSubmit = (e) => {
    e.preventDefault()

    navigate('/', {
    })
  }

  const addCategory = async (category) => {
    const name = admin.restaurant
    const newCategory = {
      name: category,
      foodList: []
    }
    const res = await fetch(`http://localhost:8080/food-panda/restaurants/addcategory/${name}`, {
        method: "PUT",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(newCategory)
    }).then(response => response.json())
    .catch(error => error)

    if (res.error) {
        alert(res.error);
    } 
  }

  const onAddSubmit = (e) => {
    e.preventDefault();

    addCategory(selectedCategory);
  }

  const handleSelect = (e)=>{
    console.log(admin.restaurant.categories)
    setSelectedCategory(e.value);
  }

  const onAddFoodSubmit = (e, category) => {
    e.preventDefault();

    console.log(admin, admin.restaurant, category)

    navigate('/admin-page/add-food-page', {
      state: {
        admin: admin,
        restaurant: admin.restaurant,
        category: category
      }
    })
  }

  const onViewOrdersSubmit = (e) => {
    e.preventDefault()

    navigate('/admin-page/view-orders-page', {
      state : {
        admin : admin
      }
    })
  }

  return (
    <div>
      <div className = "row">
        <img src= {logo} alt = "Logo" width={80} height = {80} style={{ marginLeft : 50, marginTop: 20}} />
        <div style={{color: '#d1497f', fontFamily : 'less', marginLeft : 460, fontSize : 50}}>foodpanda</div>
        <button className = "btn" style = {{marginLeft : 310}} type="submit" id='btn-log-out' onClick={(e) => onViewOrdersSubmit(e)}>
            View orders
        </button>
        <button className = "btn" style = {{marginLeft : 10}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
            Log out
        </button>
      </div>
      <div>
        <div className = "container-2">
          <div>
            <div className = "row">
              <div style={{marginLeft : 30, fontSize : 40}}> {admin.restaurant} </div>
              <div style={{marginLeft : 580}}>
                <Dropdown options={categories.map((category) => (category.name))} onChange = {handleSelect}  value={defaultOption} placeholder="Select an option"></Dropdown>
              </div>      
              <button className = "btn" style = {{marginLeft : 50}} type="submit" id='btn-add-categ' onClick={(e) => onAddSubmit(e)}>
                  Add
              </button>        
            </div>
            <br></br>
            <br></br>
              {restaurantCategories.map((category) => (
                <Category category = {category} restaurant = {admin.restaurant} viewButton = {true} onClick = {(e) => onAddFoodSubmit(e, category)}/>
              ))}
          </div>
        </div>
      </div>
    </div>
  )
}

export default AdminPage