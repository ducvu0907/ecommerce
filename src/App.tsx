import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./pages/auth/Login";
import Signup from "./pages/auth/Signup";
import Home from "./pages/main/Home";
import Cart from "./pages/cart/Cart";
import NotFound from "./pages/main/NotFound";
import Product from "./pages/product/Product";
import { isLoggedIn } from "./hooks/useAuth";
import Layout from "./pages/main/Layout";

const App = () => {
  const loggedIn = isLoggedIn();

  return (
    <>
      <Routes>
        <Route path="/login" element={loggedIn ? <Navigate to={"/"} /> : <Login />} />
        <Route path="/signup" element={loggedIn ? <Navigate to={"/"} /> : <Signup />} />

        <Route element={<Layout />}>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={loggedIn ? <Cart /> : <Navigate to={"/login"} />} />
          <Route path="/products/:productId" element={<Product />} />
          <Route path="*" element={<NotFound />} />
        </Route>

      </Routes>
    </>
  );
}

export default App;