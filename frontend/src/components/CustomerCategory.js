import CustomerFood from "./CustomerFood"
import { useState, useEffect } from "react"

const CustomerCategory = ({ category, restaurant, onClick }) => {

    const [categoryFoods, setCategoryFoods] = useState([])

    const fetchCategoryFoods = async () => {
        const name = category
        const res = await fetch(`http://localhost:8080/food-panda/foods/category/${name}`)
        const data = await res.json()
    
        return data
    }
    
    useEffect(() => {
        const getCategoryFoods = async () => {
            const foodsFromLocal = await fetchCategoryFoods()
            console.log(foodsFromLocal)
            setCategoryFoods(foodsFromLocal)
        }
    
        getCategoryFoods()
    }, [])

    return (
        <div className = "speciality" style={{cursor : 'default' }}>
            <h3 style={{cursor : 'default' , fontSize : 30, color: '#fb93bc'}}>
                {category}
            </h3>
            <div>
                {categoryFoods != null && categoryFoods.map((food) => (
                    <CustomerFood food = {food} restaurant = {restaurant} onClick = {onClick}/>
                ))}
            </div>
        </div>
    )
}

export default CustomerCategory