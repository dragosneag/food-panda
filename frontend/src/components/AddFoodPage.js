import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useLocation } from "react-router-dom"
import logo from '../Logo3.png'

const AddFoodPage = () => {

    const {state} = useLocation()
    const admin = state.admin
    const restaurant = state.restaurant
    const category = state.category
    const navigate = useNavigate()

    const [name, setName] = useState('')
    const [price, setPrice] = useState('')
    const [description, setDescription] = useState('')

    const onCancelSubmit = (e) => {
        e.preventDefault()

        navigate('/admin-page', {
            state: {
                admin: admin
            }
        })
    }

    const addFood = async () => {
        const newRestaurant = {
            name : restaurant
        }
        const newCategory = {
            name : category.name
        }
        const newFood = {
            name : name,
            price : price,
            description : description,
            category : newCategory,
            restaurant : newRestaurant
        }
        console.log(JSON.stringify(newFood))
        const res = await fetch(`http://localhost:8080/food-panda/addfood`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(newFood)
        }).then(response => response.json())
        .catch(error => error)
    
        if (res.error) {
            alert(res.error);
        } else {
            navigate('/admin-page', {
              state: {
                admin: admin
              }
            })
        }
    }

    const onAddSubmit = (e) => {
        e.preventDefault()
    
        if(!name) {
            alert('Please enter food name')
            return
        }
    
        if(!price) {
            alert('Please enter food price')
            return
        }
    
        addFood();
    }

    return (
        <div>
            <div className = "row">
                <img src= {logo} alt = "Logo" width={80} height = {80} style={{ marginLeft : 50, marginTop: 20}} />
                <div style={{color: '#d1497f', fontFamily : 'less', marginLeft : 460, fontSize : 50}}>foodpanda</div>
            </div>
            <div className = "container-2">
                <div className = "row">
                    <div style={{marginLeft : 570, fontSize : 30}}>Add meal</div>
                </div>
                <form className="add-form">
                    <div className="form-control" style={{marginLeft : 400}}>
                        <label> Name </label>
                        <input  type="text"
                        placeholder="Enter name" value={name} onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className="form-control" style={{marginLeft : 400}}>
                        <label> Price </label>
                        <input  type="text"
                        placeholder="Enter price" value={price} onChange={(e) => setPrice(e.target.value)} />
                    </div>
                    <div className="form-control" style={{marginLeft : 400}}>
                        <label> Description </label>
                        <input  type="text"
                        placeholder="Enter description" value={description} onChange={(e) => setDescription(e.target.value)} />
                    </div>
                    <button type="submit" id='btn-create' className="btn btn-block" style={{marginLeft : 370}} onClick={(e) => onAddSubmit(e)}>
                        Add
                    </button>
                    <button type="submit" id='btn-cancel' className="btn btn-block" style={{marginLeft : 370}} onClick={(e) => onCancelSubmit(e)}>
                        Cancel
                    </button>
                </form>
            </div>
        </div>
    )
}

export default AddFoodPage