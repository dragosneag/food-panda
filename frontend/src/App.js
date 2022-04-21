import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import CreateRestaurant from "./components/CreateRestaurant";
import CustomerPage from "./components/CustomerPage";
import LoginPage from "./components/LoginPage";
import SignUpPage from "./components/SignUpPage";
import AdminPage from "./components/AdminPage";
import AddFoodPage from "./components/AddFoodPage";
import MenuPage from "./components/MenuPage";
import CartPage from "./components/CartPage";
import SeeOrdersPage from "./components/SeeOrdersPage";
import ManageOrdersPage from "./components/ManageOrdersPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<LoginPage />}/>
        <Route exact path="/customer-page" element={<CustomerPage />}/>
        <Route exact path="/register-page" element={<SignUpPage />}/>
        <Route exact path="/create-restaurant-page" element={<CreateRestaurant />}/>
        <Route exact path="/admin-page" element={<AdminPage />}/>
        <Route exact path="/admin-page/add-food-page" element={<AddFoodPage />}/>
        <Route exact path="/admin-page/view-orders-page" element={<ManageOrdersPage />}/>
        <Route exact path="/customer-page/menu-page" element={<MenuPage />}/>
        <Route exact path="/customer-page/menu-page/cart-page" element={<CartPage />}/>
        <Route exact path="/customer-page/see-orders-page" element={<SeeOrdersPage />}/>
      </Routes>
    </Router>
  );
}

export default App;
