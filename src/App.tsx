import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./pages/auth/Login";
import Signup from "./pages/auth/Signup";
import Home from "./pages/main/Home";
import Cart from "./pages/cart/Cart";
import NotFound from "./pages/main/NotFound";
import Product from "./pages/product/Product";
import { isLoggedIn } from "./hooks/useAuth";
import Layout from "./pages/main/Layout";
import OrderDetails from "./pages/order/OrderDetails";
import OrderList from "./pages/order/OrderList";
import SearchProducts from "./pages/product/SearchProducts";
import Settings from "./pages/main/Settings";
import CreateProduct from "./pages/product/CreateProduct";
import SellerProducts from "./pages/product/SellerProducts";
import Inventory from "./pages/inventory/Inventory";
import CreateOrder from "./pages/order/CreateOrder";
import PaymentFailed from "./components/payment/PaymentFailed";

const App = () => {
  const loggedIn = isLoggedIn();

  return (
    <>
      <Routes>
        <Route path="/login" element={loggedIn ? <Navigate to={"/"} /> : <Login />} />
        <Route path="/signup" element={loggedIn ? <Navigate to={"/"} /> : <Signup />} />

        <Route element={<Layout />}>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/products/:productId" element={<Product />} />
          <Route path="/products/search" element={<SearchProducts />} />
          <Route path="/my-products" element={<SellerProducts />} />
          <Route path="/inventories/:productId" element={<Inventory />} />
          <Route path="/create-product" element={<CreateProduct />} />
          <Route path="/create-order" element={<CreateOrder />} />
          <Route path="/orders" element={<OrderList />} />
          <Route path="/orders/:orderId" element={<OrderDetails />} />
          <Route path="/settings" element={<Settings />} />
          <Route path="/payment-failed" element={<PaymentFailed />} />
          <Route path="*" element={<NotFound />} />
        </Route>

      </Routes>
    </>
  );
}

export default App;